package org.opengamestudio

// Application state
data class Context(
    // Command line arguments
    var arguments: Array<String> = arrayOf(),
    // String to print to console
    var consoleOutput: String = "",
    // Entity field cursor
    var cursorEntityFieldId: Int = 0,
    // Entity cursor used for both parsing and generating
    var cursorEntityId: Int = 0,
    // The application did finish launching
    var didLaunch: Boolean = false,
    // Finished writing to output file
    var didWriteOutputFile: Boolean = false,
    // Entities in the order of appearance
    var entities: Array<String> = arrayOf(),
    // Currently generated entity enumerated fields to use cursor
    var entityEnumeratedFields: Array<String> = arrayOf(),
    // Entity -> field name -> field type map of maps
    var entityFields: MutableMap<String, MutableMap<String, String>> = mutableMapOf(),
    // Entity -> type map
    var entityTypes: MutableMap<String, String> = mutableMapOf(),
    // Report the end of current entity generation
    var finishGeneratingEntity: Boolean = false,
    // Report the end of current line parsing
    var finishParsingLine: Boolean = false,
    // Path to input file
    var inputFile: String = "",
    // Input file contents as lines
    var inputFileLines: Array<String> = arrayOf(),
    // Generating output file
    var isGenerating: Boolean = false,
    // Parsing input file
    var isParsing: Boolean = false,
    // Entity line
    var isParsingEntity: Boolean = false,
    // Parsing fields now
    var isParsingFields: Boolean = false,
    // Non-top level line
    var isParsingIndentedLine: Boolean = false,
    // Raw Kotlin line
    var isParsingKotlinLine: Boolean = false,
    // True: non-empty/non-comment line without indentation
    // False: top level line should not be parsed
    var isParsingTopLevelLine: Boolean = false,
    // Entity type line
    var isParsingTypeLine: Boolean = false,
    // Raw Kotlin lines
    var kotlinLines: Array<String> = arrayOf(),
    // The last line for generated entity
    var outputEntityEnd: String = "",
    // Currently generated entity field
    var outputEntityField: String = "",
    // The first generated entity output line
    var outputEntityStart: String = "",
    // Path to output file
    var outputFile: String = "",
    // Contents to write to output file
    var outputFileContents: String = "",
    // Input line that is parsed at this iteration
    var parseLineId: Int = 0,
    // Generate code for the specified language
    var targetLanguage: String = "",
    override var recentField: String = "",
): CLDContext {
    override fun <T> field(name: String): T {
        if (name == "arguments") {
            return arguments as T
        } else if (name == "consoleOutput") {
            return consoleOutput as T
        } else if (name == "cursorEntityFieldId") {
            return cursorEntityFieldId as T
        } else if (name == "cursorEntityId") {
            return cursorEntityId as T
        } else if (name == "didLaunch") {
            return didLaunch as T
        } else if (name == "didWriteOutputFile") {
            return didWriteOutputFile as T
        } else if (name == "entities") {
            return entities as T
        } else if (name == "entityEnumeratedFields") {
            return entityEnumeratedFields as T
        } else if (name == "entityFields") {
            return entityFields as T
        } else if (name == "entityTypes") {
            return entityTypes as T
        } else if (name == "finishGeneratingEntity") {
            return finishGeneratingEntity as T
        } else if (name == "finishParsingLine") {
            return finishParsingLine as T
        } else if (name == "inputFile") {
            return inputFile as T
        } else if (name == "inputFileLines") {
            return inputFileLines as T
        } else if (name == "isGenerating") {
            return isGenerating as T
        } else if (name == "isParsing") {
            return isParsing as T
        } else if (name == "isParsingEntity") {
            return isParsingEntity as T
        } else if (name == "isParsingFields") {
            return isParsingFields as T
        } else if (name == "isParsingIndentedLine") {
            return isParsingIndentedLine as T
        } else if (name == "isParsingKotlinLine") {
            return isParsingKotlinLine as T
        } else if (name == "isParsingTopLevelLine") {
            return isParsingTopLevelLine as T
        } else if (name == "isParsingTypeLine") {
            return isParsingTypeLine as T
        } else if (name == "kotlinLines") {
            return kotlinLines as T
        } else if (name == "outputEntityEnd") {
            return outputEntityEnd as T
        } else if (name == "outputEntityField") {
            return outputEntityField as T
        } else if (name == "outputEntityStart") {
            return outputEntityStart as T
        } else if (name == "outputFile") {
            return outputFile as T
        } else if (name == "outputFileContents") {
            return outputFileContents as T
        } else if (name == "parseLineId") {
            return parseLineId as T
        } else if (name == "targetLanguage") {
            return targetLanguage as T
        }
        return "unknown-field-name" as T
    }

    override fun selfCopy(): CLDContext {
        return this.copy()
    }

    override fun setField(
        name: String,
        value: Any?
    ) {
        if (name == "arguments") {
            arguments = value as Array<String>
        } else if (name == "consoleOutput") {
            consoleOutput = value as String
        } else if (name == "cursorEntityFieldId") {
            cursorEntityFieldId = value as Int
        } else if (name == "cursorEntityId") {
            cursorEntityId = value as Int
        } else if (name == "didLaunch") {
            didLaunch = value as Boolean
        } else if (name == "didWriteOutputFile") {
            didWriteOutputFile = value as Boolean
        } else if (name == "entities") {
            entities = value as Array<String>
        } else if (name == "entityEnumeratedFields") {
            entityEnumeratedFields = value as Array<String>
        } else if (name == "entityFields") {
            entityFields = value as MutableMap<String, MutableMap<String, String>>
        } else if (name == "entityTypes") {
            entityTypes = value as MutableMap<String, String>
        } else if (name == "finishGeneratingEntity") {
            finishGeneratingEntity = value as Boolean
        } else if (name == "finishParsingLine") {
            finishParsingLine = value as Boolean
        } else if (name == "inputFile") {
            inputFile = value as String
        } else if (name == "inputFileLines") {
            inputFileLines = value as Array<String>
        } else if (name == "isGenerating") {
            isGenerating = value as Boolean
        } else if (name == "isParsing") {
            isParsing = value as Boolean
        } else if (name == "isParsingEntity") {
            isParsingEntity = value as Boolean
        } else if (name == "isParsingFields") {
            isParsingFields = value as Boolean
        } else if (name == "isParsingIndentedLine") {
            isParsingIndentedLine = value as Boolean
        } else if (name == "isParsingKotlinLine") {
            isParsingKotlinLine = value as Boolean
        } else if (name == "isParsingTopLevelLine") {
            isParsingTopLevelLine = value as Boolean
        } else if (name == "isParsingTypeLine") {
            isParsingTypeLine = value as Boolean
        } else if (name == "kotlinLines") {
            kotlinLines = value as Array<String>
        } else if (name == "outputEntityEnd") {
            outputEntityEnd = value as String
        } else if (name == "outputEntityField") {
            outputEntityField = value as String
        } else if (name == "outputEntityStart") {
            outputEntityStart = value as String
        } else if (name == "outputFile") {
            outputFile = value as String
        } else if (name == "outputFileContents") {
            outputFileContents = value as String
        } else if (name == "parseLineId") {
            parseLineId = value as Int
        } else if (name == "targetLanguage") {
            targetLanguage = value as String
        }
    }
}
