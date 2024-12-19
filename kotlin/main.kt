fun main() {
    println("Testing...")

    val tests = arrayOf(
        ::t01_ExampleContext_field,
        ::t02_ExampleContext_field_optional,
        /*
        t03_ExampleContext_setField,
        t04_ExampleContext_setField_optional,
        t05_CLDController_executeFunctions_set,
        t06_CLDController_processQueue,
        t07_CLDController_registerFieldCallback_match,
        t08_CLDController_registerFieldCallback_mismatch,
        */
    )

    for ((i, test) in tests.withIndex()) {
        println("${i + 1} ${test()}")
    }
}
