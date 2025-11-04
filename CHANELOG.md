
## 1.1.0
#### 2025-08-13

1. Update to Gradle 8.12.0

2. Introduce `prefix-kotlin` to entity YML

    `prefix-kotlin` allows you to specify a line of source code
    that is placed one line before a class declaration

    Here's a sample YML:

    ```
    NetRequest:
        prefix-kotlin: @JsExport
        type: struct
        fields:
            body: String
            method: String
            url: String
    ```

    Here's the result in Kotlin:

    ```
    @JsExport
    data class NetRequest(
        var body: String = "",
        var method: String = "",
        var url: String = "",
    ) {}
    ```

    `@JsExport` is the prefix in this example
