package org.opengamestudio

class JVMApp { }

fun main(args: Array<String>) {
    val ctrl = CLDController(Context())
    consoleRegisterCallbacks(ctrl)

    // Register behaviour.
    arrayOf(
        ::shouldCollectEntityComments,
        ::shouldCollectEntityFieldComments,
        ::shouldCollectEntityFields,
        ::shouldCollectEntityNames,
        ::shouldCollectEntityTypes,
        ::shouldCollectRawKotlin,
        ::shouldGenerateKotlinEntities,
        ::shouldParseInputFilePath,
        ::shouldPrintToConsole,
        ::shouldParseOutputFilePath,
        ::shouldReadInputFile,
        ::shouldResetDbg,
        ::shouldWriteOutputFile,
    ).forEach { f ->
        ctrl.registerFunction { c -> f(c as Context) }
    }

    // Pass CLI parameters.
    ctrl.set("arguments", args)
    // Launch.
    ctrl.set("didLaunch", true)
}
