package org.opengamestudio
data class Context(
    var arguments: Array<String> = arrayOf(),
    var consoleOutput: String = "",
    var defaultDir: String = "",
    var didLaunch: Boolean = false,
    var dir: String = "",
    var httpDefaultPort: Int = 0,
    var httpLaunch: Boolean = false,
    var httpPort: Int = 0,
    var httpReply: String = "",
    var httpRequest: NetRequest = NetRequest(),
) {}
data class FSFile(
    var isDirectory: Boolean = false,
    var isFile: Boolean = false,
    var name: String = "",
) {}
data class NetRequest(
    var body: String = "",
    var method: String = "",
    var path: String = "",
) {}
