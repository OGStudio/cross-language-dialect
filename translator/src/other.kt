package org.opengamestudio

// Add field to entity
fun entityAddField(
    entities: MutableMap<String, MutableMap<String, String>>,
    entityName: String,
    fieldName: String,
    fieldType: String
) {
    // Add first-level map if it's not yet present.
    if (!entities.contains(entityName)) {
        entities[entityName] = mutableMapOf<String, String>()
    }
    entities[entityName]!![fieldName] = fieldType
}

// Extract input file path from command line arguments
fun cliInputFile(args: Array<String>): String {
    for (arg in args) {
        if (arg.startsWith(ARGUMENT_FILE)) {
            val prefix = ARGUMENT_FILE + "="
            val path = arg.substring(prefix.length)
            return path
        }
    }
    return ""
}
