package org.opengamestudio

// Collect entity
//
// Conditions:
// 1. This is entity line
fun shouldCollectEntity(c: Context): Context {
    if (c.recentField == "isParsingEntity") {
        println("ИГР shouldCE lineI: '${c.parseLineId}'")
        val line = c.inputFileLines[c.parseLineId]
        // Remove the last colon
        val name = line.dropLast(1)
        println("ИГР shouldCE-0 name: '$name'")
        println("ИГР shouldCE-1 entities: '${c.entities.map { it }}'")
        c.entities += name
        println("ИГР shouldCE-2 entities: '${c.entities.map { it }}'")
        c.recentField = "entities"
        return c
    }

    c.recentField = "none"
    return c
}


// Parse entity line
//
// Conditions:
// 1. The first letter is capitalized and not a comment
fun shouldParseEntityLine(c: Context): Context {
    if (
        c.recentField == "isParsingTopLevelLine" &&
        c.inputFileLines[c.parseLineId].length > 0 &&
        c.inputFileLines[c.parseLineId][0] != '#' &&
        c.inputFileLines[c.parseLineId] == c.inputFileLines[c.parseLineId].capitalize()
    ) {
        c.isParsingEntity = true
        c.recentField = "isParsingEntity"
        println("ИГР shouldPEL lineI: '${c.parseLineId}'")
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

// Parse top level line
//
// Conditions:
// 1. No indentation
fun shouldParseTopLevelLine(c: Context): Context {
    if (
        c.recentField == "parseLineId" &&
        c.inputFileLines[c.parseLineId].length > 0 &&
        c.inputFileLines[c.parseLineId][0] != ' '
    ) {
        c.isParsingTopLevelLine = true
        c.recentField = "isParsingTopLevelLine"
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
