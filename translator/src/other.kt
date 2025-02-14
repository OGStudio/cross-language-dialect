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

// Detect the presence of debug command line argument
fun cliDbg(args: Array<String>): Boolean {
    for (arg in args) {
        if (arg == ARGUMENT_DBG) {
            return true
        }
    }
    return false
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

// Extract output file path from command line arguments
fun cliOutputFile(args: Array<String>): String {
    for (arg in args) {
        if (arg.startsWith(ARGUMENT_OUT)) {
            val prefix = ARGUMENT_OUT + "="
            val path = arg.substring(prefix.length)
            return path
        }
    }
    return ""
}

// Convert string array to debug string
fun dbgStringArray(items: Array<String>): String {
    var output = "(${items.size})["
    var i = 0
    // Construct the preview.
    for (str in items) {
        output += str + ","
        i += 1
        // Interrupt the preview.
        if (i > 2) {
            output += "..."
            break
        }
    }
    output += "]"
    return output
}

fun enumerateFields(fields: Map<String, String>): Array<String> {
    var sortedKeys = arrayOf<String>()
    fields?.keys?.forEach { key ->
        sortedKeys += key
    }
    return sortedKeys
}

// Detect target language based on output file extension
fun fileExtTargetLang(outputFile: String): String {
    if (outputFile.endsWith("." + FILE_EXTENSION_KOTLIN)) {
        return LANGUAGE_KOTLIN
    }

    return "unknown-language"
}

// Generate target language specific code for entity field
fun formatEntityField(
    lang: String,
    name: String,
    type: String
): String {
    if (lang == LANGUAGE_KOTLIN) {
        return formatKotlinEntityField(name, type)
    }

    return "unknown-language field $name"
}
