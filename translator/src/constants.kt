package org.opengamestudio

const val ARGUMENT_FILE = "--file"
const val ARGUMENT_OUT = "--out"
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
const val FORMAT_KOTLIN_STRUCT = """
data class %NAME%(
%FIELDS%
) {}
"""
const val LANGUAGE_KOTLIN = "Kotlin"
const val LANGUAGE_SWIFT = "Swift"
const val PREFIX_KOTLIN = "kotlin: "
const val PREFIX_TYPE = "type: "
const val SECTION_FIELDS = "fields:"

// TODO: REMOVE, OBSOLETE
const val FORMAT_KOTLIN_ENTITY_END = ") {}"
const val FORMAT_KOTLIN_ENTITY_FIELD = "    var %NAME%: %TYPE% = %DEFAULT%,"
const val FORMAT_KOTLIN_ENTITY_START = "data class %NAME%("
