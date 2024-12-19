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
        value: Any
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

/*
/**
 * Проверяем, что копия, выданная методом `selfCopy()`, является
 * отдельным экземпляром контекста
 */
@JsExport
fun test_example_Context_selfCopy(): Boolean {
    var c1 = example_Context()
    c1.host = "abc"
    var c2 = c1.selfCopy() as example_Context
    c2.host = "123"
    return c1.host == "abc"
}

/**
 * Проверяем задание значения полю по имени
 */
@JsExport
fun test_example_Context_setField(): Boolean {
    var c = example_Context()
    c.host = "abc"
    c.setField("host", "123")
    return c.host == "123"
}

/**
 * Проверяем работу `executeFunctions()` и `set()`
 */
@JsExport
fun test_ctx_Controller_executeFunctions_set(): Boolean {
    var c = example_Context()
    val ctrl = ctx_Controller(c)
    // Отключаем автоматическое исполнение executeFunctions.
    ctrl.isProcessingQueue = true
    ctrl.set("host", "123")

    fun hostToDidLaunch(c: example_Context): example_Context {
        if (c.recentField == "host") {
            c.didLaunch = true
            c.recentField = "didLaunch"
            return c
        }
        c.recentField = "none"
        return c
    }
    ctrl.registerFunction({ c: ctx_Context ->
      hostToDidLaunch(c as example_Context)
    })

    // Применяем задание host.
    ctrl.executeFunctions()
    // Применяем задание didLaunch.
    ctrl.executeFunctions()
    return c.host == "123" && c.didLaunch == true
}

/**
 * Проверяем работу `processQueue()`
 */
@JsExport
fun test_ctx_Controller_processQueue(): Boolean {
    var c = example_Context()
    val ctrl = ctx_Controller(c)

    fun hostToDidLaunch(c: example_Context): example_Context {
        if (c.recentField == "host") {
            c.didLaunch = true
            c.recentField = "didLaunch"
            return c
        }
        c.recentField = "none"
        return c
    }

    ctrl.registerFunction({ c: ctx_Context ->
      hostToDidLaunch(c as example_Context)
    })
    ctrl.set("host", "123")
    return c.didLaunch == true
}

/**
 * Проверяем работу `registerFieldCallback()` при изменении ожидаемого поля
 */
@JsExport
fun test_ctx_Controller_registerFieldCallback_match(): Boolean {
    var c = example_Context()
    c.host = "123"
    c.recentField = "host"
    var callbackHost = ""

    val ctrl = ctx_Controller(c)
    ctrl.registerFieldCallback("host", { c: ctx_Context ->
      callbackHost = (c as example_Context).host
    })
    ctrl.reportContext()

    return c.host == callbackHost
}

/**
 * Проверяем работу `registerFieldCallback()` при изменении поля, которое мы не ждём,
 * т.е. callback не будет вызван
 */
@JsExport
fun test_ctx_Controller_registerFieldCallback_mismatch(): Boolean {
    var c = example_Context()
    c.host = "123"
    c.recentField = "host"
    var callbackHost = ""

    val ctrl = ctx_Controller(c)
    ctrl.registerFieldCallback("didLaunch", { c: ctx_Context ->
      callbackHost = (c as example_Context).host
    })
    ctrl.reportContext()

    return callbackHost == ""
}
*/
