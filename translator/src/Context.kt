package org.opengamestudio

// Application state
data class Context(
    // Command line arguments
    var arguments: Array<String> = arrayOf(),
    // String to print to console
    var consoleOutput: String = "",
    // The application did finish launching
    var didLaunch: Boolean = false,
    // Entities in the order of appearance
    var entities: Array<String> = arrayOf(),
    // Entity -> field name -> field type map of maps
    var entityFields: MutableMap<String, MutableMap<String, String>> = mutableMapOf(),
    // Last parsed entity id
    var entityId: Int = 0,
    // Entity -> type map
    var entityTypes: MutableMap<String, String> = mutableMapOf(),
    // Report the end of current line parsing
    var finishParsingLine: Boolean = false,
    // Path to input file
    var inputFile: String = "",
    // Input file contents as lines
    var inputFileLines: Array<String> = arrayOf(),
    // Parsing input file
    var isParsing: Boolean = false,
    // Entity line
    var isParsingEntity: Boolean = false,
    // Parsing fields now
    var isParsingFields: Boolean = false,
    // Non-top level line
    var isParsingIndentedLine: Boolean = false,
    // True: non-empty/non-comment line without indentation
    // False: top level line should not be parsed
    var isParsingTopLevelLine: Boolean = false,
    // Entity type line
    var isParsingTypeLine: Boolean = false,
    // Path to output file
    var outputFile: String = "",
    // Input line that is parsed at this iteration
    var parseLineId: Int = 0,
    override var recentField: String = "",
): CLDContext {
    override fun <T> field(name: String): T {
        if (name == "arguments") {
            return arguments as T
        } else if (name == "consoleOutput") {
            return consoleOutput as T
        } else if (name == "didLaunch") {
            return didLaunch as T
        } else if (name == "entities") {
            return entities as T
        } else if (name == "entityFields") {
            return entityFields as T
        } else if (name == "entityId") {
            return entityId as T
        } else if (name == "entityTypes") {
            return entityTypes as T
        } else if (name == "finishParsingLine") {
            return finishParsingLine as T
        } else if (name == "inputFile") {
            return inputFile as T
        } else if (name == "inputFileLines") {
            return inputFileLines as T
        } else if (name == "isParsing") {
            return isParsing as T
        } else if (name == "isParsingEntity") {
            return isParsingEntity as T
        } else if (name == "isParsingFields") {
            return isParsingFields as T
        } else if (name == "isParsingIndentedLine") {
            return isParsingIndentedLine as T
        } else if (name == "isParsingTopLevelLine") {
            return isParsingTopLevelLine as T
        } else if (name == "isParsingTypeLine") {
            return isParsingTypeLine as T
        } else if (name == "outputFile") {
            return outputFile as T
        } else if (name == "parseLineId") {
            return parseLineId as T
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
        } else if (name == "didLaunch") {
            didLaunch = value as Boolean
        } else if (name == "entities") {
            entities = value as Array<String>
        } else if (name == "entityFields") {
            entityFields = value as MutableMap<String, MutableMap<String, String>>
        } else if (name == "entityId") {
            entityId = value as Int
        } else if (name == "entityTypes") {
            entityTypes = value as MutableMap<String, String>
        } else if (name == "finishParsingLine") {
            finishParsingLine = value as Boolean
        } else if (name == "inputFile") {
            inputFile = value as String
        } else if (name == "inputFileLines") {
            inputFileLines = value as Array<String>
        } else if (name == "isParsing") {
            isParsing = value as Boolean
        } else if (name == "isParsingEntity") {
            isParsingEntity = value as Boolean
        } else if (name == "isParsingFields") {
            isParsingFields = value as Boolean
        } else if (name == "isParsingIndentedLine") {
            isParsingIndentedLine = value as Boolean
        } else if (name == "isParsingTopLevelLine") {
            isParsingTopLevelLine = value as Boolean
        } else if (name == "isParsingTypeLine") {
            isParsingTypeLine = value as Boolean
        } else if (name == "outputFile") {
            outputFile = value as String
        } else if (name == "parseLineId") {
            parseLineId = value as Int
        }
    }
}
