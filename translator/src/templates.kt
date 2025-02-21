package org.opengamestudio

const val TEMPLATE_KOTLIN_CONTEXT = """
%COMMENT%
data class %NAME%(
%FIELDS%
    override var recentField: String = "",
): CLDContext {
    override fun <T> field(name: String): T {
%GETTERS%
        return "unknown-field-name" as T
    }

    override fun selfCopy(): CLDContext {
        return this.copy()
    }

    override fun setField(
        name: String,
        value: Any?
    ) {
%SETTERS%
    }
}
"""

const val TEMPLATE_KOTLIN_FIELD = "    var %NAME%: %TYPE% = %DEFAULT,"

const val TEMPLATE_KOTLIN_STRUCT = """
%COMMENT%
data class %NAME%(
%FIELDS%
) {}
"""

// REMOVE: OBSOLETE
const val FORMAT_KOTLIN_ENTITY_END = ") {}"
const val FORMAT_KOTLIN_ENTITY_FIELD = "    var %NAME%: %TYPE% = %DEFAULT%,"
const val FORMAT_KOTLIN_ENTITY_START = "data class %NAME%("
