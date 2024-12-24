package org.opengamestudio

// Should print to console
//
// Conditions:
// 1. No command line arguments were provided
fun shouldPrintToConsole(c: Context): Context {
    if (
        c.recentField == "didLaunch" &&
        c.arguments.isEmpty()
    ) {
        c.consoleOutput = "Usage: {bin} --file /path/to/file.yml"
        c.recentField = "consoleOutput"
        return c
    }

    c.recentField = "none"
    return c
}

// Parse input file path
//
// Conditions:
// 1. Input file was specified with command line argument
fun shouldParseInputFilePath(c: Context): Context {
    if (
        c.recentField == "arguments" &&
        cliInputFile(c.arguments).length > 0
    ) {
        c.inputFile = cliInputFile(c.arguments)
        c.recentField = "inputFile"
        return c
    }

    c.recentField = "none"
    return c
}
