print("Testing...")

let tests = [
    t01_ExampleContext_field,
    t02_ExampleContext_field_optional,
    t03_ExampleContext_setField,
    t04_ExampleContext_setField_optional,
]

for (i, test) in tests.enumerated() {
    print(i, test())
}
