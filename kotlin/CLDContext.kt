interface CLDContext {
    /**
     * Name of the field that has just changed
     *
     * Allows shoulds/handlers/behaviour functions to react to only
     * relevant changes and ignore other changes of CLDContext
     */
    var recentField: String

    /**
     * Get field's value by its name
     */
    fun field(name: String): Any
    /**
     * Create a copy of the CLDContext derivative
     *
     * Used by CLDController to treat all derived contexts as CLDContext
     */
    fun selfCopy(): CLDContext
    /**
     * Set field's value by its name
     */
    fun setField(name: String, value: Any)
}
