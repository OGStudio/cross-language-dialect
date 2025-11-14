/**
 * This file is part of Cross-language dialect:
 *     https://github.com/OGStudio/cross-language-dialect
 * License: CC0
 * Version: 1.2.0
 */

package org.opengamestudio

// Debug controller/context changes to console
fun consoleDebug(c: Context) {
    val key = c.recentField
    val value = c.fieldAny(c.recentField)
    // Limit the length of printed value for non-arrays
    // Arrays are limited by dbgStringArray function
    var strval = "${value}".take(DBG_LEN)
    // Preview array of strings
    (value as? Array<String>)?.also { items ->
        strval = dbgStringArray(items)
    }

    if (c.isDbg) {
        println("CLD-DBG '$key': '$strval'")
    }
}

// Print to console
fun consolePrint(c: Context) {
    if (c.recentField == "consoleOutput") {
        println(c.consoleOutput)
    }
}

// Register callbacks to print to console
fun consoleRegisterCallbacks(ctrl: CLDController) {
    ctrl.registerCallback({ cc ->
        val c = cc as Context
        consoleDebug(c)
        consolePrint(c)
    })
}
