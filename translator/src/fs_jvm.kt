package org.opengamestudio

import java.io.*

/**
 * Read text file
 *
 * JVM implementation
 */
fun fsReadFile(path: String): Array<String>{
    return File(path).readLines().toTypedArray()
}
