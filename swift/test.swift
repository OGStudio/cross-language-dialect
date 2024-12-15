
/// Sample Context used for testing
struct ExampleContext: CLDContext {
    var didLaunch = false
    var host = ""
    var sometimes: String?

    var recentField = ""

    func field<T>(_ name: String) -> T {
        if (name == "didLaunch") {
            return didLaunch as! T
        } else if (name == "host") {
            return host as! T
        } else if (name == "sometimes") {
            return sometimes as! T
        }

        return "unknown-field-name" as! T
    }

    mutating func setField(
        _ name: String,
        _ value: Any
    ) {
        if (name == "didLaunch") {
            didLaunch = value as! Bool
        } else if (name == "host") {
            host = value as! String
        } else if (name == "sometimes") {
            sometimes = value as! Optional<String>
        }
    }
}

/// 01. Validate field access by name
func t01_ExampleContext_field() -> Bool {
    var c = ExampleContext()
    c.host = "abc"
    return c.host == c.field("host")
}

/// 02. Validate field access by name for optional value
func t02_ExampleContext_field_optional() -> Bool {
    var c = ExampleContext()
    let ok1 = c.field("sometimes") == nil

    c.sometimes = "def"
    let ok2 = c.sometimes == c.field("sometimes") ?? "N/A"

    return ok1 && ok2
}

/// 03. Validate changing field value by name
func t03_ExampleContext_setField() -> Bool {
    var c = ExampleContext()
    c.didLaunch = true
    c.setField("didLaunch", false)
    return c.didLaunch == false
}

/// 04. Validate changing field optional value by name
func t04_ExampleContext_setField_optional() -> Bool {
    var c = ExampleContext()
    c.sometimes = "anything"
    c.setField("sometimes", Optional<String>.none as Any)
    let ok1 = c.field("sometimes") == nil

    c.setField("sometimes", "make it quick")
    let ok2 = c.sometimes == c.field("sometimes") ?? "N/A"

    return ok1 && ok2
}
