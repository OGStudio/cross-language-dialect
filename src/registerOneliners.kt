/**
 * This file is a part of Kotlin dialect:
 *     https://github.com/OGStudio/kotlin-dialect
 * License: CC0
 * Version: 2.0.0
 */

package org.opengamestudio

import kotlin.js.JsExport

// Register several oneliner callbacks to a controller
@JsExport
fun registerOneliners(
    ctrl: KDController,
    items: Array<Any>
) {
    val halfCount = items.size / 2
    for (i in 0..<halfCount) {
        val field = items[i * 2] as String
        val callback = items[i * 2 + 1] as (c: KDContext) -> Unit
        ctrl.registerFieldCallback(field, callback);
    }
}
