package org.opengamestudio

// Collect the names of entities in the order of appearance
fun entityCollectNames(lines: Array<String>): Array<String> {
    var items = arrayOf<String>()
    for (ln in lines) {
        if (
            !ln.startsWith(" ") &&
            !ln.startsWith("#") &&
            ln != "" &&
            ln.endsWith(":") &&
            ln == ln.capitalize()
        ) {
            items += ln.dropLast(1)
        }
    }

    return items
}
