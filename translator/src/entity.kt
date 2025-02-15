package org.opengamestudio

// Collect comments of entities
fun entityCollectComments(
    entities: Array<String>,
    lines: Array<String>
): Map<String, String> {
    var d = mutableMapOf<String, String>()
    var activeComment = ""
    for (ln in lines) {
        if (ln.startsWith(PREFIX_COMMENT)) {
            val prefixLen = PREFIX_COMMENT.length
            activeComment = ln.substring(prefixLen)
        } else if (
            !entityName(ln).isEmpty() &&
            !activeComment.isEmpty()
        ) {
            val name = entityName(ln)
            d[name] = activeComment
            activeComment = ""
        }
    }

    return d
}

// Collect the names of entities in the order of appearance
fun entityCollectNames(lines: Array<String>): Array<String> {
    var items = arrayOf<String>()
    for (ln in lines) {
        if (!entityName(ln).isEmpty()) {
            items += entityName(ln)
        }
    }

    return items
}

// Extract entity name from input line if it declares entity
fun entityName(ln: String): String {
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
