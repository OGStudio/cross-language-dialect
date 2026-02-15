package org.opengamestudio

import kotlin.js.JsExport

// Extract command line argument value
@JsExport
fun cliArgumentValue(
    args: Array<String>,
    argument: String
): String {
    for (item in args) {
        if (item.startsWith(argument)) {
            val prefix = argument + "="
            val value = item.substring(prefix.length)
            return value
        }
    }
    return ""
}

// Debug representation of a value
@JsExport
fun debugString(v: Any): String {
    // Prepend a string with its length
    if (v is String) {
        return "S(${v.length})$v"
    }

    // Prepend an array with its size
    if (v is Array<*>) {
        var out = ""
        for (item in v) {
            if (!out.isEmpty()) {
                out += ","
            }
            out += debugString(item!!)
        }
        return "A(${v.size})$out"
    }

    // Prepend a dictionary with its size
    if (v is Map<*, *>) {
        var out = ""
        for ((key, value) in v) {
            if (!out.isEmpty()) {
                out += ","
            }
            out += debugString(key!!) + ":" + debugString(value!!)
        }
        return "D(${v.size})$out"
    }

    // For other types return whatever Kotlin returns by default
    return "$v"
}
