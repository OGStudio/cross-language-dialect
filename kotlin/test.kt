import org.opengamestudio.*

// Sample context used for testing
data class ExampleContext(
    var didLaunch: Boolean = false,
    var host: String = "",
    var sometimes: String? = null,

    override var recentField: String = "",
): CLDContext {
    override fun <T> field(name: String): T {
        if (name == "didLaunch") {
            return didLaunch as T
        } else if (name == "host") {
            return host as T
        } else if (name == "sometimes") {
            return sometimes as T
        }

        return "unknown-field-name" as T
    }

    override fun selfCopy(): CLDContext {
        return this.copy()
    }

    override fun setField(
        name: String,
        value: Any?
    ) {
        if (name == "didLaunch") {
            didLaunch = value as Boolean
        } else if (name == "host") {
            host = value as String
        } else if (name == "sometimes") {
            sometimes = value as String?
        }
    }
}

// Sample function for processing context change
fun hostToDidLaunch(c: ExampleContext): ExampleContext {
    if (c.recentField == "host") {
        c.didLaunch = true
        c.recentField = "didLaunch"
        return c
    }
    c.recentField = "none"
    return c
}

// Validate field access by name
fun t01_ExampleContext_field(): Boolean {
    var c = ExampleContext()
    c.host = "abc"
    return c.host == c.field("host")
}

// Validate field access by name for optional value
fun t02_ExampleContext_field_optional(): Boolean {
    var c = ExampleContext()
    val ok1 = c.field("sometimes") as String? == null

    c.sometimes = "def"
    val ok2 = c.sometimes == c.field("sometimes") ?: "N/A"

    return ok1 && ok2
}

// Validate `selfCopy()` returns the deep copy of a context
fun t03_ExampleContext_selfCopy(): Boolean {
    var c1 = ExampleContext()
    c1.host = "abc"
    var c2 = c1.selfCopy() as ExampleContext
    c2.host = "123"
    return c1.host == "abc"
}

// Validate changing field value by name
fun t04_ExampleContext_setField(): Boolean {
    var c = ExampleContext()
    c.didLaunch = true
    c.setField("didLaunch", false)
    return c.didLaunch == false
}

// Validate changing field optional value by name
fun t05_ExampleContext_setField_optional(): Boolean {
    var c = ExampleContext()
    c.sometimes = "anything"
    c.setField("sometimes", null)
    val ok1 = c.field("sometimes") as String? == null

    c.setField("sometimes", "make it quick")
    val ok2 = c.sometimes == c.field("sometimes") ?: "N/A"

    return ok1 && ok2
}

// Validate `executeFunctions()` and `set()`
fun t06_CLDController_executeFunctions_set(): Boolean {
    val ctrl = CLDController(ExampleContext())

    // Disable the execution of `executeFunctions()` for testing purpose.
    ctrl.isProcessingQueue = true

    ctrl.set("host", "123")

    ctrl.registerFunction({ c ->
        hostToDidLaunch(c as ExampleContext)
    })

    // Apply `host` value.
    ctrl.executeFunctions()
    // Apply `didLaunch` value.
    ctrl.executeFunctions()

    val c = ctrl.context as ExampleContext
    return c.host == "123" &&
        c.didLaunch == true
}

// Validate `processQueue()`
fun t07_CLDController_processQueue(): Boolean {
    val ctrl = CLDController(ExampleContext())

    ctrl.registerFunction({ c ->
        hostToDidLaunch(c as ExampleContext)
    })
    ctrl.set("host", "123")
    val c = ctrl.context as ExampleContext
    return c.didLaunch == true
}

// Validate `registerFieldCallback()` if an expected field was changed
fun t08_CLDController_registerFieldCallback_match(): Boolean {
    var ec = ExampleContext()
    ec.host = "123"
    ec.recentField = "host"
    var callbackHost = ""

    val ctrl = CLDController(ec)
    ctrl.registerFieldCallback("host", { c ->
        callbackHost = (c as ExampleContext).host
    })
    ctrl.reportContext()

    val c = ctrl.context as ExampleContext
    return c.host == callbackHost
}

/// Validate `registerFieldCallback()` if an unexpected field was changed, i.e.,
/// callback should not be called
fun t09_CLDController_registerFieldCallback_mismatch(): Boolean {
    var ec = ExampleContext()
    ec.host = "123"
    ec.recentField = "host"
    var callbackHost = ""

    val ctrl = CLDController(ec)
    ctrl.registerFieldCallback("didLaunch", { c ->
        callbackHost = (c as ExampleContext).host
    })
    ctrl.reportContext()

    return callbackHost == ""
}
