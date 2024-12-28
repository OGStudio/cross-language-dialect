package org.opengamestudio

// Debug controller/context changes to console
fun consoleDebug(c: Context) {
    val key = c.recentField
    val value = "${c.fieldAny(c.recentField)}"//.take(30)
    println("CLD-DBG key/value: '$key'/'$value'")
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
