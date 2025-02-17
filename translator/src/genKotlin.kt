package org.opengamestudio

// Generate Kotlin comment based on its emptiness
fun genKotlinComment(comment: String): String {
    if (!comment.isEmpty()) {
        return PREFIX_KOTLIN_COMMENT + comment
    }
    return ""
}

// Generate Kotlin representation of all YAML entities in a single file
fun genKotlinEntitiesFile(
    entityNames: Array<String>,
    entityComments: Map<Int, String>
): String {
    var s = ""
    // Entity by id.
    var id = 0
    for (entityName in entityNames) {
        val comment = entityComments[id] ?: ""
        s += genKotlinEntity(entityName, comment) + "\n"
        id++
    }

    return s
}

// Generate Kotlin representation of a single entity
fun genKotlinEntity(
    name: String,
    comment: String
): String {
    val genComment = genKotlinComment(comment)
    return TEMPLATE_KOTLIN_STRUCT
        .replace("%NAME%", name)
        .replace("%COMMENT%", genComment)
}
