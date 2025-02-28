package org.opengamestudio

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
