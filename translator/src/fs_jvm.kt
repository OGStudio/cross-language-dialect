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
