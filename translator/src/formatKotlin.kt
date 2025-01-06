package org.opengamestudio

// Construct entity field line
fun formatKotlinEntityField(
    name: String,
    type: String
): String {
    return FORMAT_KOTLIN_ENTITY_FIELD
        .replace("%NAME%", name)
        .replace("%TYPE%", "FMT:TODO-TYPE")
        .replace("%DEFAULT%", "FMT:TODO-DEFAULT")
}
