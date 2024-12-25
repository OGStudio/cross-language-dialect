package org.opengamestudio

// Application state
data class Context(
    // Command line arguments
    var arguments: Array<String> = arrayOf(),
    // String to print to console
    var consoleOutput: String = "",
    // The application did finish launching
    var didLaunch: Boolean = false,
    // Path to input file
    var inputFile: String = "",
    // Input file contents as lines
    var inputFileLines: Array<String> = arrayOf(),
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
        } else if (name == "inputFile") {
            return inputFile as T
        } else if (name == "inputFileLines") {
            return inputFileLines as T
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
        } else if (name == "inputFile") {
            inputFile = value as String
        } else if (name == "inputFileLines") {
            inputFileLines = value as Array<String>
        } else if (name == "parseLineId") {
            parseLineId = value as Int
        }
    }
}
