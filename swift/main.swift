print("Testing...")

let tests = [
    t01_ExampleContext_field,
    t02_ExampleContext_field_optional,
    t03_ExampleContext_setField,
    t04_ExampleContext_setField_optional,
    t05_CLDController_executeFunctions_set,
    t06_CLDController_processQueue,
]

for (i, test) in tests.enumerated() {
    print(i + 1, test())
}
