/**
 * This file is a part of Kotlin dialect:
 *     https://github.com/OGStudio/kotlin-dialect
 * License: CC0
 * Version: 2.0.0
 */

package org.opengamestudio

// Extract command line argument value
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

// Detect command line argument presence
fun cliHasArgument(
    args: Array<String>,
    argument: String
): Boolean {
    for (arg in args) {
        if (arg == argument) {
            return true
        }
    }
    return false
}

// Debug representation of a value
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

// Collect raw Kotlin source code
fun parseRawKotlin(lines: Array<String>): String {
    var contents = ""
    for (ln in lines) {
        if (ln.startsWith(PREFIX_RAW_KOTLIN)) {
            val prefixLen = PREFIX_RAW_KOTLIN.length
            val kotlinCode = ln.substring(prefixLen)
            contents += kotlinCode + NEWLINE
        }
    }

    return contents
}

fun setupComponentDebugging(
    ctrl: KDController,
    prefix: String
) {
    ctrl.registerCallback { c ->
        //val value = "${c.field<String>(c.recentField)}"
        val value = debugString(c.fieldAny(c.recentField))
        println("ИГР $prefix k/v: '${c.recentField}'/'$value'")
    }
}

// TMP
@JsExport
object F {
    val didLaunch = "didLaunch"
    val inputFile = "inputFile"
}
