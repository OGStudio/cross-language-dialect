package org.opengamestudio

// Application state context
data class Context(
    // Command line arguments
    var arguments: Array<String> = arrayOf(),
    var consoleOutput: String = "",
    var didLaunch: Boolean = false,
    // Dictionary of dictionaries
    var entityFieldComments: Map<Int, Map<String, String>> = mapOf(),
    var httpDefaultPort: Int = 0,
    var httpRequest: NetRequest = NetRequest(),
    override var recentField: String = "",
): CLDContext {
    override fun <T> field(name: String): T {
        if (name == "arguments") {
            return arguments as T
        } else if (name == "consoleOutput") {
            return consoleOutput as T
        } else if (name == "didLaunch") {
            return didLaunch as T
        } else if (name == "entityFieldComments") {
            return entityFieldComments as T
        } else if (name == "httpDefaultPort") {
            return httpDefaultPort as T
        } else if (name == "httpRequest") {
            return httpRequest as T
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
        } else if (name == "entityFieldComments") {
            entityFieldComments = value as Map<Int, Map<String, String>>
        } else if (name == "httpDefaultPort") {
            httpDefaultPort = value as Int
        } else if (name == "httpRequest") {
            httpRequest = value as NetRequest
        }
    }
}


data class FSFile(
    var isDirectory: Boolean = false,
    var isFile: Boolean = false,
    var name: String = "",
) {}

// Network request representation
data class NetRequest(
    var body: String = "",
    // GET, POST, etc.
    var method: String = "",
    var path: String = "",
) {}
