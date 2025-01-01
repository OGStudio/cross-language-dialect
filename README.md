Cross-language dialect (**CLD**) is a set of tools and rules to write
portable code for select programming languages at once.

Kotlin subset is the source that can be translated to the subsets of the following
languages:

* Swift

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

$ `cd translator && ./run/run-java --file=../test/test-dir/entities.yml`

# Current avalability of functions

| № | Function | C++ | JavaScript | Kotlin | Python | Swift |
|---|---       |---  |---         |---     |---     |---    |
| 1 | Generate entities | X | X   | X      | X      | X     | 
