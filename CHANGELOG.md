# [2.0](https://github.com/OGStudio/kotlin-dialect/pull/13)

TODO

1. The project has been renamed to Kotlin Dialect to emphasize....
1. Version 1.x has been moved to history/2025

# [1.3.0](https://github.com/OGStudio/cross-language-dialect/pull/12)
#### 2026-01-22

1. Translator: Support `Double` and `Long` types

# [1.2.0](https://github.com/OGStudio/cross-language-dialect/pull/11)
#### 2025-11-15

1. Kotlin: Add `registerOneliners()` to simplify registration of multiple callbacks (effects) into `CLDController` in a single call

## [1.1.1](https://github.com/OGStudio/cross-language-dialect/pull/10)
#### 2025-10-15

1. Kotlin: Replace `removeFirst()` with `removeAt(0)` in `CLDController.kt` because `removeFirst()` [crashes on Android](https://www.reddit.com/r/androiddev/comments/1gspjrs/dont_use_kotlins_removefirst_and_removelast_when/)

2. Fix translator interruption when parsing fields in YML

## [1.1.0](https://github.com/OGStudio/cross-language-dialect/pull/9)
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
