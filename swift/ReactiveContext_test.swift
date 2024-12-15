
/// Sample ReactiveContext used for testing only
struct ExampleContext: ReactiveContext {
    var didLaunch = false
    var host = ""
    var sometimes: String?

    var recentField = ""

    func fieldAny(_ name: String) -> Any {
        return field(name)
    }

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

/// 01. `field()` call for string value
func test01_ExampleContext_field() -> Bool {
    var c = ExampleContext()
    c.host = "abc"
    return c.host == c.field("host")
}

/// 02. `field()` call for optional string value
func test02_ExampleContext_field_optional() -> Bool {
    var c = ExampleContext()
    let ok1 = c.field("sometimes") == nil

    c.sometimes = "def"
    let ok2 = c.sometimes == c.field("sometimes") ?? "N/A"

    return ok1 && ok2
}

/// 03. `setField()` call for boolean value
func test03_ExampleContext_setField() -> Bool {
    var c = ExampleContext()
    c.didLaunch = true
    c.setField("didLaunch", false)
    return c.didLaunch == false
}

/// 04. `setField()` call for optional string value
func test04_ExampleContext_setField_optional() -> Bool {
    var c = ExampleContext()
    c.sometimes = "anything"
    c.setField("sometimes", Optional<String>.none as Any)
    let ok1 = c.field("sometimes") == nil

    c.setField("sometimes", "make it quick")
    let ok2 = c.sometimes == c.field("sometimes") ?? "N/A"

    return ok1 && ok2
}
