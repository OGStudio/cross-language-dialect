package org.opengamestudio

// Construct entity field line
fun formatKotlinEntityField(
    name: String,
    type: String
): String {
    val fmtType = formatKotlinEntityFieldType(type)
    val fmtDefault = formatKotlinEntityFieldDefault(type)
    return FORMAT_KOTLIN_ENTITY_FIELD
        .replace("%NAME%", name)
        .replace("%TYPE%", fmtType)
        .replace("%DEFAULT%", fmtDefault)
}

// Construct type's default value string
fun formatKotlinEntityFieldDefault(type: String): String {
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
        !type.contains(": ")
    ) {
        return "arrayOf()"
    }

    // `AnyOtherType` -> `AnyOtherType()`
    return "$type()"
}

// Construct type string
fun formatKotlinEntityFieldType(type: String): String {
    // `Bool` -> `Boolean`
    if (type == "Bool") {
        return "Boolean"
    }
    // `[Type]` -> `Array<Type>`
    if (
        type.startsWith("[") &&
        type.endsWith("]") &&
        !type.contains(": ")
    ) {
        val innerString = type.substring(1, type.length - 1)
        // Recursive call to format inner string
        val innerType = formatKotlinEntityFieldType(innerString)
        return "Array<$innerType>"
    }

    // Return everything else as is
    return type
}
