package org.opengamestudio

class JVMApp { }

fun main(args: Array<String>) {
    val ctrl = CLDController(Context())
    consoleRegisterCallbacks(ctrl)

    // Register behaviour.
    arrayOf(
        ::shouldCollectEntity,
        ::shouldFinishParsingLine,
        ::shouldParseInputFilePath,
        ::shouldParseLine,
        ::shouldPrintToConsole,
        ::shouldParseEntityLine,
        ::shouldParseField,
        ::shouldParseFields,
        ::shouldParseIndentedLine,
        ::shouldParseOutputFilePath,
        ::shouldParseTopLevelLine,
        ::shouldParseTypeLine,
        ::shouldReadInputFile,
        ::shouldResetEntityId,
        ::shouldResetOutputFileContents,
        ::shouldResetParsing,
        ::shouldResetTargetLanguage,
    ).forEach { f ->
        ctrl.registerFunction { c -> f(c as Context) }
    }

    // Pass CLI parameters.
    ctrl.set("arguments", args)
    // Launch.
    ctrl.set("didLaunch", true)
}
