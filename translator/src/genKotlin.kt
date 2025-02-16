package org.opengamestudio

// Generate Kotlin representation of entities in single file
fun genKotlinEntitiesFile(
    entityNames: Array<String>,
    entityComments: Map<Int, String>
): String {
    var s = "TODO: real generation "
    // Entity by id.
    var id = 0
    for (entityName in entityNames) {
        val comment = entityComments[id] ?: ""
        s += genKotlinEntity(entityName, comment) + "\n"
        id++
    }

    return s
}

// Generate Kotlin representation of a single entities
fun genKotlinEntity(
    name: String,
    comment: String
): String {
    return TEMPLATE_KOTLIN_STRUCT
        .replace("%NAME%", name)
}
