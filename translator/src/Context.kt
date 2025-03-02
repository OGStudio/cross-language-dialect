package org.opengamestudio

// Application state
data class Context(
    // Command line arguments
    var arguments: Array<String> = arrayOf(),
    // String to print to console
    var consoleOutput: String = "",
    // The application did finish launching
    var didLaunch: Boolean = false,
    // Entity comments with entities referenced by index
    var entityComments: Map<Int, String> = mapOf(),
    // Entity id -> Field name -> Field comment
    var entityFieldComments: Map<Int, Map<String, String>> = mapOf(),
    // Entity id -> Field name -> Field type
    var entityFields: Map<Int, Map<String, String>> = mapOf(),
    // Names of entities in the order of appearance
    var entityNames: Array<String> = arrayOf(),
    // Entity types with entities referenced by index
    var entityTypes: Map<Int, String> = mapOf(),
    // Finished writing to output file
    var didWriteOutputFile: Boolean = false,
    // Path to input file
    var inputFile: String = "",
    // Input file contents as lines
    var inputFileLines: Array<String> = arrayOf(),
    // Debug output state
    var isDbg: Boolean = false,
    // Path to output file
    var outputFile: String = "",
    // Contents to write to output file
    var outputFileContents: String = "",
    // Raw Kotlin source code to insert as is at the beginning of the generated file
    var rawKotlin: String = "",
    override var recentField: String = "",
): CLDContext {
    override fun <T> field(name: String): T {
        if (name == "arguments") {
            return arguments as T
        } else if (name == "consoleOutput") {
            return consoleOutput as T
        } else if (name == "didLaunch") {
            return didLaunch as T
        } else if (name == "didWriteOutputFile") {
            return didWriteOutputFile as T
        } else if (name == "entityComments") {
            return entityComments as T
        } else if (name == "entityFieldComments") {
            return entityFieldComments as T
        } else if (name == "entityFields") {
            return entityFields as T
        } else if (name == "entityNames") {
            return entityNames as T
        } else if (name == "entityTypes") {
            return entityTypes as T
        } else if (name == "inputFile") {
            return inputFile as T
        } else if (name == "inputFileLines") {
            return inputFileLines as T
        } else if (name == "isDbg") {
            return isDbg as T
        } else if (name == "outputFile") {
            return outputFile as T
        } else if (name == "outputFileContents") {
            return outputFileContents as T
        } else if (name == "rawKotlin") {
            return rawKotlin as T
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
        } else if (name == "didWriteOutputFile") {
            didWriteOutputFile = value as Boolean
        } else if (name == "entityComments") {
            entityComments = value as Map<Int, String>
        } else if (name == "entityFieldComments") {
            entityFieldComments = value as Map<Int, Map<String, String>>
        } else if (name == "entityFields") {
            entityFields = value as Map<Int, Map<String, String>>
        } else if (name == "entityNames") {
            entityNames = value as Array<String>
        } else if (name == "entityTypes") {
            entityTypes = value as Map<Int, String>
        } else if (name == "inputFile") {
            inputFile = value as String
        } else if (name == "inputFileLines") {
            inputFileLines = value as Array<String>
        } else if (name == "isDbg") {
            isDbg = value as Boolean
        } else if (name == "outputFile") {
            outputFile = value as String
        } else if (name == "outputFileContents") {
            outputFileContents = value as String
        } else if (name == "rawKotlin") {
            rawKotlin = value as String
        }
    }
}
