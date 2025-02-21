package org.opengamestudio

// Generate Kotlin comment based on its emptiness
fun genKotlinComment(comment: String): String {
    if (!comment.isEmpty()) {
        return PREFIX_KOTLIN_ENTITY_COMMENT + comment
    }
    return ""
}

// Generate Kotlin field and its comment
fun genKotlinField(
    comment: String,
    name: String,
    type: String
): String {
    var contents = ""
    if (!comment.isEmpty()) {
        contents += PREFIX_KOTLIN_FIELD_COMMENT + comment + NEWLINE
    }
    contents += TEMPLATE_KOTLIN_FIELD
        .replace("%NAME%", name)
        .replace("%TYPE%", type)
        //.replace("%DEFAULT%", default)
    return contents + NEWLINE
}

// Generate Kotlin fields and their comments
fun genKotlinFields(
    fieldComments: Map<String, String>,
    fields: Map<String, String>
): String {
    var contents = ""
    val sortedFields = fields.toSortedMap()
    for (name in sortedFields.keys) {
        val comment = fieldComments[name] ?: ""
        val type = sortedFields[name] ?: ""
        contents += genKotlinField(comment, name, type)
    }
    return contents
}

// Generate Kotlin representation of all YAML entities in a single file
fun genKotlinEntitiesFile(
    entityComments: Map<Int, String>,
    entityFieldComments: Map<Int, Map<String, String>>,
    entityFields: Map<Int, Map<String, String>>,
    entityNames: Array<String>,
    entityTypes: Map<Int, String>
): String {
    var s = ""
    // Entity by id.
    var id = 0
    for (name in entityNames) {
        val comment = entityComments[id] ?: ""
        val type = entityTypes[id] ?: ""
        val fieldComments = entityFieldComments[id] ?: mapOf<String, String>()
        val fields = entityFields[id] ?: mapOf<String, String>()
        s += genKotlinEntity(comment, fieldComments, fields, name, type)
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
    type: String
): String {
    // Assume struct template by default.
    var template = TEMPLATE_KOTLIN_STRUCT
    if (type == TYPE_CONTEXT) {
        template = TEMPLATE_KOTLIN_CONTEXT
    }

    val genComment = genKotlinComment(comment)
    val genFields = genKotlinFields(fieldComments, fields)
    return template
        .replace("%NAME%", name)
        .replace("%COMMENT%", genComment)
        .replace("%FIELDS%", genFields)
}
