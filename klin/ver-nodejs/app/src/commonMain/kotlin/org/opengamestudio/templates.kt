/**
 * This file is a part of Kotlin dialect:
 *     https://github.com/OGStudio/kotlin-dialect
 * License: CC0
 * Version: 2.0.0
 */

package org.opengamestudio

const val TEMPLATE_KOTLIN_CONTEXT = """
%COMMENT%
%PREFIX%
data class %NAME%(
%FIELDS%
    override var recentField: String = "",
): KDContext {
    override fun <T> field(name: String): T {
%GETTERS%        }
        return "unknown-field-name" as T
    }

    override fun selfCopy(): KDContext {
        return this.copy()
    }

    override fun setField(
        name: String,
        value: Any?
    ) {
%SETTERS%        }
    }
}
"""

const val TEMPLATE_KOTLIN_CONTEXT_GETTER = """        } else if (name == "%NAME%") {
            return %NAME% as T
"""

const val TEMPLATE_KOTLIN_CONTEXT_GETTER_FIRST = """        if (name == "%NAME%") {
            return %NAME% as T
"""

const val TEMPLATE_KOTLIN_CONTEXT_SETTER = """        } else if (name == "%NAME%") {
            %NAME% = value as %TYPE%
"""

const val TEMPLATE_KOTLIN_CONTEXT_SETTER_FIRST = """        if (name == "%NAME%") {
            %NAME% = value as %TYPE%
"""

const val TEMPLATE_KOTLIN_FIELD = "    var %NAME%: %TYPE% = %DEFAULT%,"

const val TEMPLATE_KOTLIN_STRUCT = """
%COMMENT%
%PREFIX%
data class %NAME%(
%FIELDS%
) {}
"""
