fun main() {
    print("Testing... ")

    val tests = arrayOf(
        ::t01_Context_field,
        ::t02_Context_field_optional,
        ::t03_Context_selfCopy,
        ::t04_Context_setField,
        ::t05_Context_setField_optional,
        ::t06_Controller_executeFunctions_set,
        ::t07_Controller_processQueue,
        ::t08_Controller_registerFieldCallback_match,
        ::t09_Controller_registerFieldCallback_mismatch,
        ::t10_registerOneliners,
    )

    var okCount = 0
    for (test in tests) {
        val result = test()
        if (result) {
            okCount++
        }
    }
    val totalCount = tests.size
    println("$okCount/$totalCount")
}
