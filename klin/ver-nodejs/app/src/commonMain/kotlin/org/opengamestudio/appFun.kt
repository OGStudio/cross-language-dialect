/**
 * This file is a part of Kotlin dialect:
 *     https://github.com/OGStudio/kotlin-dialect
 * License: CC0
 * Version: 2.0.0
 */

package org.opengamestudio

import kotlin.js.JsExport

//<!-- Shoulds -->

// Collect comments of the entities
//
// Conditions:
// 1. Entity names are available
fun appShouldCollectEntityComments(c: AppContext): AppContext {
    if (c.recentField == "entityNames") {
        c.entityComments = parseEntityComments(c.inputFileLines)
        c.recentField = "entityComments"
        return c
    }

    c.recentField = "none"
    return c
}

// Collect field comments
//
// Conditions:
// 1. Entity fields are available
fun appShouldCollectEntityFieldComments(c: AppContext): AppContext {
    if (c.recentField == "entityFields") {
        c.entityFieldComments = parseEntityFieldComments(c.inputFileLines)
        c.recentField = "entityFieldComments"
        return c
    }

    c.recentField = "none"
    return c
}

// Collect field names and values of the entities
//
// Conditions:
// 1. Entity types are available
fun appShouldCollectEntityFields(c: AppContext): AppContext {
    if (c.recentField == "entityTypes") {
        c.entityFields = parseEntityFields(c.inputFileLines)
        c.recentField = "entityFields"
        return c
    }

    c.recentField = "none"
    return c
}

// Collect names of the declared entities
//
// Conditions:
// 1. Input file contents are available
fun appShouldCollectEntityNames(c: AppContext): AppContext {
    if (c.recentField == "inputFileLines") {
        c.entityNames = parseEntityNames(c.inputFileLines)
        c.recentField = "entityNames"
        return c
    }

    c.recentField = "none"
    return c
}

// Collect prefixes of the entities for Kotlin
//
// Conditions:
// 1. Entity comments are available
fun appShouldCollectEntityPrefixesKotlin(c: AppContext): AppContext {
    if (c.recentField == "entityComments") {
        c.entityPrefixesKotlin =
            parseEntityPrefixes(
              c.inputFileLines,
              PREFIX_PREFIX_KOTLIN
            )
        c.recentField = "entityPrefixesKotlin"
        return c
    }

    c.recentField = "none"
    return c
}

// Collect types of the entities
//
// Conditions:
// 1. Entity comments are available
fun appShouldCollectEntityTypes(c: AppContext): AppContext {
    if (c.recentField == "entityComments") {
        c.entityTypes = parseEntityTypes(c.inputFileLines)
        c.recentField = "entityTypes"
        return c
    }

    c.recentField = "none"
    return c
}

// Collect raw Kotlin source code
//
// Conditions:
// 1. Input file contents are available
fun appShouldCollectRawKotlin(c: AppContext): AppContext {
    if (c.recentField == "inputFileLines") {
        c.rawKotlin = parseRawKotlin(c.inputFileLines)
        c.recentField = "rawKotlin"
        return c
    }

    c.recentField = "none"
    return c
}

// Generate Kotlin version of the entities
//
// Conditions:
// 1. Entity field comments are available
fun appShouldGenerateKotlinEntities(c: AppContext): AppContext {
    if (c.recentField == "entityFieldComments") {
        c.outputFileContents = genKotlinEntitiesFile(
            c.entityComments,
            c.entityFieldComments,
            c.entityFields,
            c.entityNames,
            c.entityPrefixesKotlin,
            c.entityTypes,
            c.rawKotlin
        )
        c.recentField = "outputFileContents"
        return c
    }

    c.recentField = "none"
    return c
}

// Parse input file path
//
// Conditions:
// 1. At app launch input file was specified with command line argument
fun appShouldParseInputFilePath(c: AppContext): AppContext {
    if (
        c.recentField == F.didLaunch &&
        cliArgumentValue(c.arguments, ARGUMENT_FILE).length > 0
    ) {
        c.inputFile = cliArgumentValue(c.arguments, ARGUMENT_FILE)
        c.recentField = F.inputFile
        return c
    }

    c.recentField = "none"
    return c
}

// Parse output file path
//
// Conditions:
// 1. At app launch output file was specified with command line argument
fun appShouldParseOutputFilePath(c: AppContext): AppContext {
    if (
        c.recentField == "didLaunch" &&
        cliArgumentValue(c.arguments, ARGUMENT_OUT).length > 0
    ) {
        c.outputFile = cliArgumentValue(c.arguments, ARGUMENT_OUT)
        c.recentField = "outputFile"
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
fun appShouldPrintToConsole(c: AppContext): AppContext {
    if (
        c.recentField == "didLaunch" &&
        c.arguments.isEmpty()
    ) {
        c.consoleOutput = "Usage: {bin} --file=/path/to/file.yml --out=/path/to/file.kt"
        c.recentField = "consoleOutput"
        return c
    }

    c.recentField = "none"
    return c
}

//<!-- Other functions -->

@JsExport
fun appCtrl(): KDController {
    return AppProto.ctrl
}
