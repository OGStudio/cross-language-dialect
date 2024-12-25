package org.opengamestudio

// Parse input file path
//
// Conditions:
// 1. At app launch input file was specified with command line argument
fun shouldParseInputFilePath(c: Context): Context {
    if (
        c.recentField == "didLaunch" &&
        cliInputFile(c.arguments).length > 0
    ) {
        c.inputFile = cliInputFile(c.arguments)
        c.recentField = "inputFile"
        return c
    }

    c.recentField = "none"
    return c
}

// Parse input file line
//
// Conditions:
// 1. Input file lines are available
// 2. Line is parsed and there are more lines to parse
fun shouldParseLine(c: Context): Context {
    if (c.recentField == "inputFileLines") {
        c.parseLineId = 0
        c.recentField = "parseLineId"
        return c
    }

    if (
        c.recentField == "parseLineId" &&
        c.parseLineId < c.inputFileLines.size - 1
    ) {
        c.parseLineId += 1
        c.recentField = "parseLineId"
        return c
    }

    c.recentField = "none"
    return c
}

// Print to console
//
// Conditions:
// 1. At app launch no command line arguments were provided
// 2. Line is parsed
fun shouldPrintToConsole(c: Context): Context {
    if (
        c.recentField == "didLaunch" &&
        c.arguments.isEmpty()
    ) {
        c.consoleOutput = "Usage: {bin} --file=/path/to/file.yml"
        c.recentField = "consoleOutput"
        return c
    }

    if (c.recentField == "parseLineId") {
        val id = c.parseLineId
        val line = c.inputFileLines[id]
        c.consoleOutput = "parseLI id/text: '$id'/'$line'"
        c.recentField = "consoleOutput"
        return c
    }

    /*
    if (c.recentField == "inputFileLines") {
        c.consoleOutput = "inputFL:\n" + c.inputFileLines.joinToString("\n")
        c.recentField = "consoleOutput"
        return c
    }
    */

    c.recentField = "none"
    return c
}

// Read input file
//
// Conditions:
// 1. Input file path is available
fun shouldReadInputFile(c: Context): Context {
    if (c.recentField == "inputFile") {
        c.inputFileLines = fsReadFile(c.inputFile)
        c.recentField = "inputFileLines"
        return c
    }

    c.recentField = "none"
    return c
}
