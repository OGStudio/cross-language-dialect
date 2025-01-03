package org.opengamestudio

// Collect entity
//
// Conditions:
// 1. This is entity line
fun shouldCollectEntity(c: Context): Context {
    if (c.recentField == "isParsingEntity") {
        val line = c.inputFileLines[c.parseLineId]
        // Remove the last colon
        val name = line.dropLast(1)
        c.entities += name
        c.recentField = "entities"
        return c
    }

    c.recentField = "none"
    return c
}

// Finish generating current entity
//
// Conditions:
// 1. ...
fun shouldFinishGeneratingEntity(c: Context): Context {
    if (
        c.recentField == "isParsingTopLevelLine" &&
        !c.isParsingTopLevelLine
    ) {
        c.finishParsingLine = true
        c.recentField = "finishParsingLine"
        return c
    }

    c.recentField = "none"
    return c
}

// Finish parsing current line
//
// Conditions:
// 1. Top level line that should not be parsed
// 2. Finished parsing entity
// 3. Finished parsing entity type
// 4. Started parsing fields
// 5. Parsed entity field
fun shouldFinishParsingLine(c: Context): Context {
    if (
        c.recentField == "isParsingTopLevelLine" &&
        !c.isParsingTopLevelLine
    ) {
        c.finishParsingLine = true
        c.recentField = "finishParsingLine"
        return c
    }

    if (
        c.recentField == "entityId" &&
        c.isParsing
    ) {
        c.finishParsingLine = true
        c.recentField = "finishParsingLine"
        return c
    }

    if (c.recentField == "entityTypes") {
        c.finishParsingLine = true
        c.recentField = "finishParsingLine"
        return c
    }

    if (
        c.recentField == "isParsingFields" &&
        c.isParsingFields
    ) {
        c.finishParsingLine = true
        c.recentField = "finishParsingLine"
        return c
    }

    if (c.recentField == "entityFields") {
        c.finishParsingLine = true
        c.recentField = "finishParsingLine"
        return c
    }

    c.recentField = "none"
    return c
}

// Parse entity line
//
// Conditions:
// 1. The first letter is capitalized and not a comment
// 2. Empty top level line
fun shouldParseEntityLine(c: Context): Context {
    if (
        c.recentField == "isParsingTopLevelLine" &&
        c.isParsingTopLevelLine &&
        c.inputFileLines[c.parseLineId].length > 0 &&
        c.inputFileLines[c.parseLineId][0] != '#' &&
        c.inputFileLines[c.parseLineId] == c.inputFileLines[c.parseLineId].capitalize()
    ) {
        c.isParsingEntity = true
        c.recentField = "isParsingEntity"
        return c
    }

    if (
        c.recentField == "isParsingTopLevelLine" &&
        c.isParsingTopLevelLine &&
        c.isParsingEntity
    ) {
        c.isParsingEntity = false
        c.recentField = "isParsingEntity"
        return c
    }

    c.recentField = "none"
    return c
}

// Parse single field
//
// Conditions:
// 1. Line is indented while we are parsing fields
fun shouldParseField(c: Context): Context {
    if (
        c.recentField == "isParsingIndentedLine" &&
        c.isParsingFields
    ) {
        val entityName = c.entities[c.entityId]
        val line = c.inputFileLines[c.parseLineId].trim()
        val parts = line.split(": ")
        entityAddField(c.entityFields, entityName, parts[0], parts[1])
        c.recentField = "entityFields"
        return c
    }

    c.recentField = "none"
    return c
}

// Start parsing fields
//
// Conditions:
// 1. Indented line reads "fields:"
// 2. Top level line goes after indented one
fun shouldParseFields(c: Context): Context {
    if (
        c.recentField == "isParsingIndentedLine" &&
        c.inputFileLines[c.parseLineId].trim() == SECTION_FIELDS
    ) {
        c.isParsingFields = true
        c.recentField = "isParsingFields"
        return c
    }

    if (
        c.recentField == "isParsingTopLevelLine" &&
        c.isParsingFields
    ) {
        c.isParsingFields = false
        c.recentField = "isParsingFields"
        return c
    }

    c.recentField = "none"
    return c
}

// Parse indented line
//
// Conditions:
// 1. Has indentation
fun shouldParseIndentedLine(c: Context): Context {
    if (
        c.recentField == "parseLineId" &&
        c.inputFileLines[c.parseLineId].length > 0 &&
        c.inputFileLines[c.parseLineId][0] == ' '
    ) {
        c.isParsingIndentedLine = true
        c.recentField = "isParsingIndentedLine"
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
// 1. Started parsing
// 2. Finished parsing current line
fun shouldParseLine(c: Context): Context {
    if (
        c.recentField == "isParsing" &&
        c.isParsing
    ) {
        c.parseLineId = 0
        c.recentField = "parseLineId"
        return c
    }

    if (
        c.recentField == "finishParsingLine" &&
        c.parseLineId < c.inputFileLines.size - 1
    ) {
        c.parseLineId += 1
        c.recentField = "parseLineId"
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

// Parse top level line
//
// Conditions:
// 1. Empty line
// 2. Comment line
// 3. Version line
// 4. No indentation
fun shouldParseTopLevelLine(c: Context): Context {
    if (
        c.recentField == "parseLineId" &&
        c.inputFileLines[c.parseLineId].length == 0
    ) {
        c.isParsingTopLevelLine = false
        c.recentField = "isParsingTopLevelLine"
        return c
    }

    if (
        c.recentField == "parseLineId" &&
        c.inputFileLines[c.parseLineId].length > 0 &&
        c.inputFileLines[c.parseLineId][0] == '#'
    ) {
        c.isParsingTopLevelLine = false
        c.recentField = "isParsingTopLevelLine"
        return c
    }

    if (
        c.recentField == "parseLineId" &&
        c.inputFileLines[c.parseLineId].startsWith("version:")
    ) {
        c.isParsingTopLevelLine = false
        c.recentField = "isParsingTopLevelLine"
        return c
    }

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

// Parse type line
//
// Conditions:
// 1. Indented line starts with "type:"
fun shouldParseTypeLine(c: Context): Context {
    if (
        c.recentField == "isParsingIndentedLine" &&
        c.inputFileLines[c.parseLineId].trim().startsWith(PREFIX_TYPE)
    ) {
        val line = c.inputFileLines[c.parseLineId].trim()
        val type = line.substring(PREFIX_TYPE.length)
        val name = c.entities[c.entityId]
        c.entityTypes[name] = type
        c.recentField = "entityTypes"
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

// Reset entity id
//
// Conditions:
// 1. Entity has been collected during parsing
// 2. Started generating
// 3. Cycle through entities while generating
fun shouldResetEntityId(c: Context): Context {
    if (c.recentField == "entities") {
        c.entityId = c.entities.size - 1
        c.recentField = "entityId"
        return c
    }

    if (
        c.recentField == "isGenerating" &&
        c.isGenerating
    ) {
        c.entityId = 0
        c.recentField = "entityId"
        return c
    }

    // TMP replace with real condition
    if (
        c.recentField == "outputFileContents" &&
        c.isGenerating &&
        c.entityId < c.entities.size - 1
    ) {
        c.entityId += 1
        c.recentField = "entityId"
        return c
    }

    c.recentField = "none"
    return c
}

// Detect if we start or finish generating
//
// Conditions:
// 1. Finished parsing
// 2. Out of entities to generate
fun shouldResetGenerating(c: Context): Context {
    if (
        c.recentField == "isParsing" &&
        !c.isParsing
    ) {
        c.isGenerating = true
        c.recentField = "isGenerating"
        return c
    }

    /*
    if (
        c.recentField == "outputFileContents" &&
        c.isGenerating &&
        c.outputFileContents.size == c.entities.size
        //c.entityId == c.entities.size - 1
    ) {
        c.isGenerating = false
        c.recentField = "isGenerating"
        return c
    }
    */

    c.recentField = "none"
    return c
}

// Reset contents for output file
//
// Conditions:
// 1. Finished parsing
// 2. Generating entity
fun shouldResetOutputFileContents(c: Context): Context {
    if (
        c.recentField == "isParsing" &&
        !c.isParsing
    ) {
        c.outputFileContents = ""
        c.recentField = "outputFileContents"
        return c
    }

    if (
        c.recentField == "entityId" &&
        c.isGenerating &&
        c.targetLanguage == "Kotlin"
    ) {
        val name = c.entities[c.entityId]
        c.outputFileContents += FORMAT_KOTLIN_ENTITY_START.replace("%NAME%", name)
        c.recentField = "outputFileContents"
        return c
    }

    c.recentField = "none"
    return c
}

// Detect if we start or finish parsing
//
// Conditions:
// 1. Input file lines are available
// 2. Finished parsing last line
fun shouldResetParsing(c: Context): Context {
    if (c.recentField == "inputFileLines") {
        c.isParsing = true
        c.recentField = "isParsing"
        return c
    }

    if (
        c.recentField == "finishParsingLine" &&
        c.parseLineId == c.inputFileLines.size - 1
    ) {
        c.isParsing = false
        c.recentField = "isParsing"
        return c
    }

    c.recentField = "none"
    return c
}

// Detect target language
//
// Conditions:
// 1. Output file was specified
fun shouldResetTargetLanguage(c: Context): Context {
    if (c.recentField == "outputFile") {
        c.targetLanguage = fileExtTargetLang(c.outputFile)
        c.recentField = "targetLanguage"
        return c
    }

    c.recentField = "none"
    return c
}

// Save generated contents to output file
//
// Conditions:
// 1. Output contents are ready
fun shouldWriteOutputFile(c: Context): Context {
    if (
        c.recentField == "isGenerating" &&
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
