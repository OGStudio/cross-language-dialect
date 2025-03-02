Cross-language dialect (**CLD**) is a set of tools and rules to write
portable code for select programming languages at once.

Context and Entities keep the state (data), they are described in YML and
can be converted to:

* Kotlin

# Swift

Build tests on macOS:

$ `./swift/make`

Run tests on macOS:

$ `./swift/do-test`

# Kotlin

Build tests on macOS:

$ `./kotlin/make`

Run tests on macOS:

$ `./kotlin/do-test`

# Translator

Build translator:

$ `cd translator && ./gradlew jar`

Parse entities' YML:

$ `cd translator && ./run/run-java --file=../test/test-dir/entities.yml --out=../test/test-dir/entities.kt --dbg`

# Current availability of functions

## Generation of entities

| № | Type    | Kotlin |
|---|---      |---     |
| 1 | struct  | √      |
| 2 | context | X      |

## Generation of entity fields for Kotlin

| № | YML type | Kotlin type | Default value | YML example | Kotlin example |
|---|---       |---          |---            |---          |---             |
| 1 | `Bool`   | `Boolean`   | `false`       | `didLaunch: Bool` | `var didLaunch: Boolean = false` |
| 2 | `String` | `String`    | `""`          | `method: String`  | `var method: String = ""` |
| 3 | `[Type]` | `Array<Type>` | `arrayOf()` | `arguments: [String]` | `var arguments: Array<String> = arrayOf()` |
