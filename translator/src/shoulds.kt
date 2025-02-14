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

// Parse output file path
//
// Conditions:
// 1. At app launch output file was specified with command line argument
fun shouldParseOutputFilePath(c: Context): Context {
    if (
        c.recentField == "didLaunch" &&
        cliOutputFile(c.arguments).length > 0
    ) {
        c.outputFile = cliOutputFile(c.arguments)
        c.recentField = "outputFile"
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
        c.consoleOutput = "Usage: {bin} --file=/path/to/file.yml --out=/path/to/file.kt"
        c.recentField = "consoleOutput"
        return c
    }

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

// Reset debug output state
//
// Conditions:
// 1. Arguments are available
fun shouldResetDbg(c: Context): Context {
    if (
        c.recentField == "arguments" &&
        cliDbg(c.arguments)
    ) {
        c.isDbg = cliDbg(c.arguments)
        c.recentField = "isDbg"
        return c
    }

    c.recentField = "none"
    return c
}

// Save generated contents to output file
//
// Conditions:
// 1. Finished preparing file contents
fun shouldWriteOutputFile(c: Context): Context {
    if (
        c.recentField == "outputFileContents" &&
        !c.isGenerating
    ) {
        fsWriteFile(c.outputFile, c.outputFileContents)
        c.didWriteOutputFile = true
        c.recentField = "didWriteOutputFile"
        return c
    }

    c.recentField = "none"
    return c
}
