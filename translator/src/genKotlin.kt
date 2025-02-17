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
    entityComments: Map<Int, String>,
    entityNames: Array<String>,
    entityTypes: Map<Int, String>
): String {
    var s = ""
    // Entity by id.
    var id = 0
    for (name in entityNames) {
        val comment = entityComments[id] ?: ""
        val type = entityTypes[id] ?: ""
        s += genKotlinEntity(comment, name, type)
        id++
    }

    return s
}

// Generate Kotlin representation of a single entity
fun genKotlinEntity(
    comment: String,
    name: String,
    type: String
): String {
    // Assume struct template by default.
    var template = TEMPLATE_KOTLIN_STRUCT
    if (type == TYPE_CONTEXT) {
        template = TEMPLATE_KOTLIN_CONTEXT
    }

    val genComment = genKotlinComment(comment)
    return template
        .replace("%NAME%", name)
        .replace("%COMMENT%", genComment)
}
