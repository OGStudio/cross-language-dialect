package org.opengamestudio

/* Should print to console
 *
 * Conditions:
 * 1. No command line arguments were provided
 */
fun shouldPrintToConsole(c: Context): Context {
    if (
        c.recentField == "didLaunch" &&
        c.arguments.isEmpty()
    ) {
        c.consoleOutput = "Usage: {bin} path-to-file.yml"
        c.recentField = "consoleOutput"
        return c
    }

    c.recentField = "none"
    return c
}

