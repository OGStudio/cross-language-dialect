/**
 * This file is part of Cross-language dialect:
 *     https://github.com/OGStudio/cross-language-dialect
 * License: CC0
 * Version: 1.1.0
 */

package org.opengamestudio

// Collect comments of entities
fun parseEntityComments(lines: Array<String>): Map<Int, String> {
    var d = mutableMapOf<Int, String>()
    var entityId = 0
    for (ln in lines) {
        if (ln.startsWith(PREFIX_ENTITY_COMMENT)) {
            val prefixLen = PREFIX_ENTITY_COMMENT.length
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
    val parts = nameAndValue.split(FIELD_DELIMITER, limit = 2)
    if (parts.size != 2) {
        return arrayOf<String>()
    }
    return parts.toTypedArray()
}

// Extract entity field comments
fun parseEntityFieldComments(lines: Array<String>): Map<Int, Map<String, String>> {
    var d = mutableMapOf<Int, Map<String, String>>()
    var entityId = 0
    var comments = mutableMapOf<String, String>()
    var isParsingFields = false
    var lastComment = ""

    for (ln in lines) {
        val isSectionMarker = (ln == SECTION_FIELDS)
        val isComment = isParsingFields && ln.startsWith(PREFIX_FIELD_COMMENT)
        val isField = isParsingFields && !parseEntityField(ln).isEmpty()
        val isEntityEndMarker = isParsingFields && ln.isEmpty()
        val isLastEntityEndMarker = isParsingFields && (ln == lines.last())
        /**/if (isLastEntityEndMarker) {
        }

        if (isSectionMarker) {
            isParsingFields = true
        }

        if (isComment) {
            val prefixLen = PREFIX_FIELD_COMMENT.length
            lastComment = ln.substring(prefixLen)
        }

        if (
            isField &&
            !lastComment.isEmpty()
        ) {
            val parts = parseEntityField(ln)
            val name = parts[0]
            comments[name] = lastComment
            lastComment = ""
        }

        if (
            isEntityEndMarker ||
            isLastEntityEndMarker
        ) {
            isParsingFields = false
            d[entityId] = comments
            entityId++
            comments = mutableMapOf<String, String>()
        }
    }

    return d
}

// Extract entity field name from input line
fun parseEntityFields(lines: Array<String>): Map<Int, Map<String, String>> {
    var d = mutableMapOf<Int, Map<String, String>>()
    var entityId = 0
    var fields = mutableMapOf<String, String>()
    var isParsingFields = false

    for ((i, ln) in lines.withIndex()) {
        val isSectionMarker = (ln == SECTION_FIELDS)
        val isField = isParsingFields && !parseEntityField(ln).isEmpty()
        val isEntityEndMarker = isParsingFields && ln.isEmpty()
        val isLastEntityEndMarker = isParsingFields && (i == lines.size - 1)
        /**/println("ИГР parseEF-01 ln/isPF/lines.last: '$ln'")

        if (isSectionMarker) {
            /**/println("ИГР parseEF-02 isPF: 'true'")
            isParsingFields = true
        }

        if (isField) {
            val parts = parseEntityField(ln)
            val name = parts[0]
            val type = parts[1]
            fields[name] = type
        }

        if (
            isEntityEndMarker ||
            isLastEntityEndMarker
        ) {
            /**/println("ИГР parseEF-03 isEEM/isLEEM: '$isEntityEndMarker'/'$isLastEntityEndMarker'")
            isParsingFields = false
            d[entityId] = fields
            entityId++
            fields = mutableMapOf<String, String>()
        }
    }

    return d
}

// Extract entity name from input line if it declares entity
fun parseEntityName(ln: String): String {
    if (
        !ln.startsWith(" ") &&
        !ln.startsWith(PREFIX_ENTITY_COMMENT) &&
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

// Collect prefixes for entities
fun parseEntityPrefixes(
    lines: Array<String>,
    pre: String
): Map<Int, String> {
    var d = mutableMapOf<Int, String>()
    var entityId = -1
    for (ln in lines) {
        if (ln.startsWith(pre)) {
            val prefixLen = pre.length
            val value = ln.substring(prefixLen)
            d[entityId] = value
        } else if (!parseEntityName(ln).isEmpty()) {
            entityId++
        }
    }

    return d
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
