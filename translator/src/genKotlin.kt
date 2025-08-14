/**
 * This file is part of Cross-language dialect:
 *     https://github.com/OGStudio/cross-language-dialect
 * License: CC0
 * Version: 1.0.1
 */

package org.opengamestudio

// Generate Kotlin comment based on its emptiness
fun genKotlinComment(comment: String): String {
    if (!comment.isEmpty()) {
        return PREFIX_KOTLIN_ENTITY_COMMENT + comment
    }
    return ""
}

// Generate Kotlin representation of all YAML entities in a single file
fun genKotlinEntitiesFile(
    entityComments: Map<Int, String>,
    entityFieldComments: Map<Int, Map<String, String>>,
    entityFields: Map<Int, Map<String, String>>,
    entityNames: Array<String>,
    entityPrefixes: Map<Int, String>,
    entityTypes: Map<Int, String>,
    rawKotlin: String
): String {
    var s = rawKotlin
    // Entity by id.
    var id = 0
    for (name in entityNames) {
        val comment = entityComments[id] ?: ""
        val prefix = entityPrefixes[id] ?: ""
        val type = entityTypes[id] ?: ""
        val fieldComments = entityFieldComments[id] ?: mapOf<String, String>()
        val fields = entityFields[id] ?: mapOf<String, String>()
        s += genKotlinEntity(comment, fieldComments, fields, name, prefix, type)
        id++
    }

    return s
}

// Generate Kotlin representation of a single entity
fun genKotlinEntity(
    comment: String,
    fieldComments: Map<String, String>,
    fields: Map<String, String>,
    name: String,
    prefix: String,
    type: String
): String {
    // Use `struct` template by default
    var template = TEMPLATE_KOTLIN_STRUCT
    if (type == TYPE_CONTEXT) {
        template = TEMPLATE_KOTLIN_CONTEXT
    }

    val genComment = genKotlinComment(comment)
    val genFields = genKotlinFields(fieldComments, fields)
    val genGetters = genKotlinGetters(fields)
    val genSetters = genKotlinSetters(fields)
    return template
        .replace("%NAME%", name)
        .replace("%COMMENT%", genComment)
        .replace("%FIELDS%", genFields)
        .replace("%GETTERS%", genGetters)
        .replace("%PREFIX%", prefix)
        .replace("%SETTERS%", genSetters)
}

// Generate Kotlin field and its comment
fun genKotlinField(
    comment: String,
    name: String,
    ymlType: String
): String {
    var contents = ""
    if (!comment.isEmpty()) {
        contents += PREFIX_KOTLIN_FIELD_COMMENT + comment + NEWLINE
    }
    val default = genKotlinFieldDefault(ymlType)
    val type = genKotlinFieldType(ymlType)
    contents += TEMPLATE_KOTLIN_FIELD
        .replace("%NAME%", name)
        .replace("%TYPE%", type)
        .replace("%DEFAULT%", default)
    return contents
}

// Construct type's default value string
fun genKotlinFieldDefault(type: String): String {
    // `Bool` -> `false`
    if (type == "Bool") {
        return "false"
    }
    // `Int` -> `0`
    if (type == "Int") {
        return "0"
    }
    // `String` -> `""`
    if (type == "String") {
        return "\"\""
    }
    // `[Type]` -> `arrayOf()`
    if (
        type.startsWith("[") &&
        type.endsWith("]") &&
        !type.contains(DICTIONARY_DELIMITER)
    ) {
        return "arrayOf()"
    }
    // `[TypeA: TypeB]` -> `mapOf()`
    if (
        type.startsWith("[") &&
        type.endsWith("]") &&
        type.contains(DICTIONARY_DELIMITER)
    ) {
        return "mapOf()"
    }

    // `AnyOtherType` -> `AnyOtherType()`
    return "$type()"
}

// Generate Kotlin fields and their comments
fun genKotlinFields(
    fieldComments: Map<String, String>,
    fields: Map<String, String>
): String {
    var contents = ""
    val sortedFields = fields.toSortedMap()
    for (name in sortedFields.keys) {
        // Add new line among fields
        if (!contents.isEmpty()) {
            contents += NEWLINE
        }
        val comment = fieldComments[name] ?: ""
        val type = sortedFields[name] ?: ""
        contents += genKotlinField(comment, name, type)
    }
    return contents
}

// Construct type string
fun genKotlinFieldType(type: String): String {
    // `Bool` -> `Boolean`
    if (type == "Bool") {
        return "Boolean"
    }
    // `[Type]` -> `Array<Type>`
    if (
        type.startsWith("[") &&
        type.endsWith("]") &&
        !type.contains(DICTIONARY_DELIMITER) // Exclude dictionary
    ) {
        val innerString = type.substring(1, type.length - 1)
        // Recursive call to format inner string
        val innerType = genKotlinFieldType(innerString)
        return "Array<$innerType>"
    }
    // `[TypeA: TypeB]` -> `Map<TypeA, TypeB>`
    if (
        type.startsWith("[") &&
        type.endsWith("]") &&
        type.contains(DICTIONARY_DELIMITER)
    ) {
        val innerString = type.substring(1, type.length - 1)
        val parts = innerString.split(DICTIONARY_DELIMITER, limit = 2)
        // Recursive calls to format types
        val innerTypeA = genKotlinFieldType(parts.first())
        val innerTypeB = genKotlinFieldType(parts.last())
        return "Map<$innerTypeA, $innerTypeB>"
    }

    // Return everything else as is
    return type
}

// Generate Kotlin Context getter
fun genKotlinGetter(
    isFirst: Boolean,
    name: String
): String {
    // Use `GETTER` template by default
    var template = TEMPLATE_KOTLIN_CONTEXT_GETTER
    if (isFirst) {
        template = TEMPLATE_KOTLIN_CONTEXT_GETTER_FIRST
    }
    var contents = ""
    contents += template
        .replace("%NAME%", name)
    return contents
}

// Generate Kotlin Context getters
fun genKotlinGetters(fields: Map<String, String>): String {
    var contents = ""
    var isFirst = true
    val sortedFields = fields.toSortedMap()
    for (name in sortedFields.keys) {
        contents += genKotlinGetter(isFirst, name)
        isFirst = false
    }
    return contents
}

// Generate Kotlin Context setter
fun genKotlinSetter(
    isFirst: Boolean,
    name: String,
    ymlType: String
): String {
    // Use `SETTER` template by default
    var template = TEMPLATE_KOTLIN_CONTEXT_SETTER
    if (isFirst) {
        template = TEMPLATE_KOTLIN_CONTEXT_SETTER_FIRST
    }
    var contents = ""
    val type = genKotlinFieldType(ymlType)
    contents += template
        .replace("%NAME%", name)
        .replace("%TYPE%", type)
    return contents
}

// Generate Kotlin Context setters
fun genKotlinSetters(fields: Map<String, String>): String {
    var contents = ""
    var isFirst = true
    val sortedFields = fields.toSortedMap()
    for (name in sortedFields.keys) {
        val type = sortedFields[name] ?: ""
        contents += genKotlinSetter(isFirst, name, type)
        isFirst = false
    }
    return contents
}
