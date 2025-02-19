package org.opengamestudio

// Collect comments of the entities
//
// Conditions:
// 1. Entity names are available
fun shouldCollectEntityComments(c: Context): Context {
    if (c.recentField == "entityNames") {
        c.entityComments = parseEntityComments(c.inputFileLines)
        c.recentField = "entityComments"
        return c
    }

    c.recentField = "none"
    return c
}

// Collect field names and values of the entities
//
// Conditions:
// 1. Entity types are available
fun shouldCollectEntityFields(c: Context): Context {
    if (c.recentField == "entityTypes") {
        c.entityFields = parseEntityFields(c.inputFileLines)
        c.recentField = "entityFields"
        return c
    }

    c.recentField = "none"
    return c
}

// Collect names of the declared entities
//
// Conditions:
// 1. Input file contents are available
fun shouldCollectEntityNames(c: Context): Context {
    if (c.recentField == "inputFileLines") {
        c.entityNames = parseEntityNames(c.inputFileLines)
        c.recentField = "entityNames"
        return c
    }

    c.recentField = "none"
    return c
}

// Collect types of the entities
//
// Conditions:
// 1. Entity comments are available
fun shouldCollectEntityTypes(c: Context): Context {
    if (c.recentField == "entityComments") {
        c.entityTypes = parseEntityTypes(c.inputFileLines)
        c.recentField = "entityTypes"
        return c
    }

    c.recentField = "none"
    return c
}

// Generate Kotlin version of the entities
//
// Conditions:
// 1. Entity fields are available
fun shouldGenerateKotlinEntities(c: Context): Context {
    if (c.recentField == "entityFields") {
        c.outputFileContents = genKotlinEntitiesFile(
            c.entityComments,
            c.entityNames,
            c.entityTypes
        )
        c.recentField = "outputFileContents"
        return c
    }

    c.recentField = "none"
    return c
}

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
    if (c.recentField == "outputFileContents") {
        fsWriteFile(c.outputFile, c.outputFileContents)
        c.didWriteOutputFile = true
        c.recentField = "didWriteOutputFile"
        return c
    }

    c.recentField = "none"
    return c
}
