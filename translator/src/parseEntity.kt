package org.opengamestudio

// Collect comments of entities
fun parseEntityComments(lines: Array<String>): Map<Int, String> {
    var d = mutableMapOf<Int, String>()
    var entityId = 0
    for (ln in lines) {
        if (ln.startsWith(PREFIX_COMMENT)) {
            val prefixLen = PREFIX_COMMENT.length
            val comment = ln.substring(prefixLen)
            d[entityId] = comment 
        } else if (!parseEntityName(ln).isEmpty()) {
            // Increase id only when entity declaration is met
            entityId++
        }
    }

    return d
}

// Extract name and type of a field
fun parseEntityField(ln: String): Array<String> {
    // Verify prefix
    if (!ln.startsWith(PREFIX_FIELD)) {
        return arrayOf<String>()
    }
    val prefixLen = PREFIX_FIELD.length
    val nameAndValue = ln.substring(prefixLen)
    val parts = nameAndValue.split(FIELD_DELIMITER)
    // Verify format
    if (parts.size != 2) {
        return arrayOf<String>()
    }

    return parts.toTypedArray()
}

// Extract entity field name from input line
fun parseEntityFieldNames(lines: Array<String>): Map<Int, Array<String>> {
    var d = mutableMapOf<Int, Array<String>>()
    var entityId = 0
    var entityFields = arrayOf<String>()
    var isParsingFields = false

    for (ln in lines) {
        val isSectionMarker = (ln == SECTION_FIELDS)
        val isField = isParsingFields && !parseEntityField(ln).isEmpty()
        val isEntityEndMarker = isParsingFields && ln.isEmpty()
        val isLastEntityEndMarker = isParsingFields && (ln == lines.last())

        if (isSectionMarker) {
            isParsingFields = true
        }

        if (isField) {
            val parts = parseEntityField(ln)
            val name = parts[0]
            entityFields += name
        }

        if (
            isEntityEndMarker ||
            isLastEntityEndMarker
        ) {
            isParsingFields = false
            d[entityId] = entityFields
            entityId++
            entityFields = arrayOf<String>()
        }
    }

    return d
}

// Extract entity name from input line if it declares entity
fun parseEntityName(ln: String): String {
    if (
        !ln.startsWith(" ") &&
        !ln.startsWith(PREFIX_COMMENT) &&
        ln != "" &&
        ln.endsWith(POSTFIX_ENTITY) &&
        ln == ln.capitalize() // The first character is capitalized
    ) {
        val ending = POSTFIX_ENTITY.length
        return ln.dropLast(ending)
    }
    return ""
}

// Collect the names of entities in the order of appearance
fun parseEntityNames(lines: Array<String>): Array<String> {
    var items = arrayOf<String>()
    for (ln in lines) {
        if (!parseEntityName(ln).isEmpty()) {
            items += parseEntityName(ln)
        }
    }

    return items
}

// Collect types of entities
fun parseEntityTypes(lines: Array<String>): Map<Int, String> {
    var d = mutableMapOf<Int, String>()
    var entityId = -1
    for (ln in lines) {
        if (ln.startsWith(PREFIX_TYPE)) {
            val prefixLen = PREFIX_TYPE.length
            val type = ln.substring(prefixLen)
            d[entityId] = type
        } else if (!parseEntityName(ln).isEmpty()) {
            entityId++
        }
    }

    return d
}
