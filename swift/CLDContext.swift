protocol CLDContext {
    var recentField: String { get set }

    func field<T>(_ name: String) -> T
    func fieldAny(_ name: String) -> Any
    mutating func setField(_ name: String, _ value: Any)
}

extension CLDContext {
    /// Default implementation of `fieldAny()` that should be enough
    /// for CLDController
    func fieldAny(_ name: String) -> Any {
        return field(name)
    }
}
