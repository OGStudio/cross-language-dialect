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
// 1. Generated last entity's field
fun shouldFinishGeneratingEntity(c: Context): Context {
    if (
        c.recentField == "outputEntityField" &&
        c.cursorEntityFieldId == c.entityEnumeratedFields.size - 1
    ) {
        c.finishGeneratingEntity = true
        c.recentField = "finishGeneratingEntity"
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
// 6. Parsed raw Kotlin field
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
        c.recentField == "cursorEntityId" &&
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

    if (c.recentField == "isParsingKotlinLine") {
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
        val entityName = c.entities[c.cursorEntityId]
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

// Parse raw Kotlin line
//
// Conditions:
// 1. Top-level line starts with `kotlin:`
fun shouldParseKotlinLine(c: Context): Context {
    if (
        c.recentField == "isParsingTopLevelLine" &&
        c.isParsingTopLevelLine &&
        c.inputFileLines[c.parseLineId].startsWith(PREFIX_KOTLIN)
    ) {
        c.isParsingKotlinLine = true
        c.recentField = "isParsingKotlinLine"
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
// 1. Empty (no characters) line
// 2. Comment line (starts with `#`)
// 3. Version line (starts with `version:`)
// 4. Non-empty not indented line (the one we're after)
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
        val name = c.entities[c.cursorEntityId]
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

// Reset entity field cursor
//
// Conditions:
// 1. Entity fields have been enumerated
// 2. Entity field output has been generated
fun shouldResetCursorEntityFieldId(c: Context): Context {
    if (c.recentField == "entityEnumeratedFields") {
        c.cursorEntityFieldId = 0
        c.recentField = "cursorEntityFieldId"
        return c
    }

    if (
        c.recentField == "outputEntityField" &&
        c.cursorEntityFieldId + 1 < c.entityEnumeratedFields.size
    ) {
        c.cursorEntityFieldId += 1
        c.recentField = "cursorEntityFieldId"
        return c
    }

    c.recentField = "none"
    return c
}

// Reset entity cursor
//
// Conditions:
// 1. Entity has been collected during parsing
// 2. Started generating
// 3. Finished generating current entity
fun shouldResetCursorEntityId(c: Context): Context {
    if (c.recentField == "entities") {
        c.cursorEntityId = c.entities.size - 1
        c.recentField = "cursorEntityId"
        return c
    }

    if (
        c.recentField == "isGenerating" &&
        c.isGenerating
    ) {
        c.cursorEntityId = 0
        c.recentField = "cursorEntityId"
        return c
    }

    if (
        c.recentField == "outputEntityEnd" &&
        c.cursorEntityId < c.entities.size - 1
    ) {
        c.cursorEntityId += 1
        c.recentField = "cursorEntityId"
        return c
    }

    c.recentField = "none"
    return c
}

// Reset entity enumerated fields
//
// Conditions:
// 1. Entity start has been added to output
fun shouldResetEntityEnumeratedFields(c: Context): Context {
    if (c.recentField == "outputEntityStart") {
        val name = c.entities[c.cursorEntityId]
        val fields = c.entityFields[name] ?: mapOf<String, String>()
        c.entityEnumeratedFields = enumerateFields(fields)
        c.recentField = "entityEnumeratedFields"
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

    if (
        c.recentField == "outputEntityEnd" &&
        c.cursorEntityId == c.entities.size - 1
    ) {
        c.isGenerating = false
        c.recentField = "isGenerating"
        return c
    }

    c.recentField = "none"
    return c
}

// Reset ending line of the generated entity
//
// Conditions:
// 1. Raw Kotlin line was parsed
fun shouldResetKotlinLines(c: Context): Context {
    if (c.recentField == "isParsingKotlinLine") {
        val line = c.inputFileLines[c.parseLineId].trim()
        val code = line.substring(PREFIX_KOTLIN.length)
        c.kotlinLines += code + "\n"
        c.recentField = "kotlinLines"
        return c
    }

    c.recentField = "none"
    return c
}

// Reset ending line of the generated entity
//
// Conditions:
// 1. Finished generating entity
fun shouldResetOutputEntityEnd(c: Context): Context {
    if (c.recentField == "finishGeneratingEntity") {
        c.outputEntityEnd = ") {}"
        c.recentField = "outputEntityEnd"
        return c
    }

    c.recentField = "none"
    return c
}

// Reset field contents
//
// Conditions:
// 1. Field cursor changed
fun shouldResetOutputEntityField(c: Context): Context {
    if (c.recentField == "cursorEntityFieldId") {
        val entityName = c.entities[c.cursorEntityId]
        val fieldName = c.entityEnumeratedFields[c.cursorEntityFieldId]
        val fieldType = c.entityFields[entityName]!![fieldName]!!
        c.outputEntityField = formatEntityField(
            c.targetLanguage,
            fieldName,
            fieldType
        )
        c.recentField = "outputEntityField"
        return c
    }

    c.recentField = "none"
    return c
}

// Reset contents for the first line of entity generation
//
// Conditions:
// 1. Entity was selected for generation
fun shouldResetOutputEntityStart(c: Context): Context {
    if (
        c.recentField == "cursorEntityId" &&
        c.isGenerating
    ) {
        val name = c.entities[c.cursorEntityId]
        // TODO: switch language inside other.kt func
        // TODO: no need to do it in the should
        // c.targetLanguage == "Kotlin"
        c.outputEntityStart = FORMAT_KOTLIN_ENTITY_START.replace("%NAME%", name)
        c.recentField = "outputEntityStart"
        return c
    }

    c.recentField = "none"
    return c
}

// Reset contents for output file
//
// Conditions:
// 1. Finished parsing for non-Kotlin target generation
// 2. Finished parsing for Kotlin target generation
// 3. Entity's first line was generated
// 4. Entity's field was generated
// 5. Entity's last line was generated
fun shouldResetOutputFileContents(c: Context): Context {
    if (
        c.recentField == "isParsing" &&
        !c.isParsing &&
        c.targetLanguage != LANGUAGE_KOTLIN
    ) {
        c.outputFileContents = ""
        c.recentField = "outputFileContents"
        return c
    }

    if (
        c.recentField == "isParsing" &&
        !c.isParsing &&
        c.targetLanguage == LANGUAGE_KOTLIN
    ) {
        c.outputFileContents = c.kotlinLines.joinToString("\n")
        c.recentField = "outputFileContents"
        return c
    }

    if (c.recentField == "outputEntityStart") {
        c.outputFileContents += c.outputEntityStart + "\n"
        c.recentField = "outputFileContents"
        return c
    }

    if (c.recentField == "outputEntityField") {
        c.outputFileContents += c.outputEntityField + "\n"
        c.recentField = "outputFileContents"
        return c
    }

    if (c.recentField == "outputEntityEnd") {
        c.outputFileContents += c.outputEntityEnd + "\n"
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
