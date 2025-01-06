package org.opengamestudio

// Construct entity field line
fun formatKotlinEntityField(
    name: String,
    type: String
): String {
    val fmtType = formatKotlinEntityFieldType(type)
    return FORMAT_KOTLIN_ENTITY_FIELD
        .replace("%NAME%", name)
        .replace("%TYPE%", fmtType)
        .replace("%DEFAULT%", "FMT:TODO-DEFAULT")
}

// Construct type string
fun formatKotlinEntityFieldType(type: String): String {
    // `Bool` -> `Boolean`
    if (type == "Bool") {
        return "Boolean"
    // `[Type]` -> `Array<Type>`
    } else if (
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
