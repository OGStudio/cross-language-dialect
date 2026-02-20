/**
 * This file is a part of Kotlin dialect:
 *     https://github.com/OGStudio/kotlin-dialect
 * License: CC0
 * Version: 2.0.0
 */

package org.opengamestudio

object AppProto {
    val ctrl: KDController

    init {
        ctrl = KDController(AppContext())
        //setupComponentDebugging(ctrl, "App")
        arrayOf(
            ::appShouldCollectEntityComments,
            ::appShouldCollectEntityFieldComments,
            ::appShouldCollectEntityFields,
            ::appShouldCollectEntityNames,
            ::appShouldCollectEntityPrefixesKotlin,
            ::appShouldCollectEntityTypes,
            ::appShouldCollectRawKotlin,
            ::appShouldGenerateKotlinEntities,
            ::appShouldParseInputFilePath,
            ::appShouldPrintToConsole,
            ::appShouldParseOutputFilePath,
        ).forEach { f ->
            ctrl.registerFunction { c -> f(c as AppContext) }
        }
    }
}
