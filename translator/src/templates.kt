package org.opengamestudio


/*
const val FORMAT_KOTLIN_CONTEXT = """
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
*/
/*
const val FORMAT_KOTLIN_STRUCT = """
data class %NAME%(
%FIELDS%
) {}
"""
*/

const val FORMAT_KOTLIN_ENTITY_END = ") {}"
const val FORMAT_KOTLIN_ENTITY_FIELD = "    var %NAME%: %TYPE% = %DEFAULT%,"
const val FORMAT_KOTLIN_ENTITY_START = "data class %NAME%("
