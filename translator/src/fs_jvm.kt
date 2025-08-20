/**
 * This file is part of Cross-language dialect:
 *     https://github.com/OGStudio/cross-language-dialect
 * License: CC0
 * Version: 1.1.0
 */

package org.opengamestudio

import java.io.*

/**
 * Read text file
 *
 * JVM implementation
 */
fun fsReadFile(path: String): Array<String> {
    return File(path).readLines().toTypedArray()
}

/**
 * Write text file
 *
 * JVM implementation
 */
fun fsWriteFile(
    path: String,
    contents: String
) {
    File(path).writeText(contents)
}
