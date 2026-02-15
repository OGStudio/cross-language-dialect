from js_aux import *

def test_js_aux_conversions(
) -> str:
    lines = [
        "[abc]",
        "else",
        "[js]",
        "file1 -> file2",
        "wrongSep->another",
    ]
    d = js_aux_conversions(lines)
    if (
        cld_len(d) == 1
    ):
        return "OK: js_aux_conversions"
    return "ERR: js_aux_convesions"

def test_js_aux_copy(
) -> str:
    conversions = {
        "py/memory.py": "js/memory.js",
        "py/memory_test.py": "js/memory_test.js",
    }

    fileContents = {
        "py/memory.py": [
            "import X",
            "import Y",
        ],
        "py/memory_test.py": [
            "print ABC",
        ],
    }
    dst = js_aux_copy(conversions, fileContents)
    if (
        cld_len(dst) == 2 and
        cld_len(dst["js/memory.js"]) == 2 and
        cld_len(dst["js/memory_test.js"]) == 1
    ):
        return "OK: js_aux_copy"
    return "ERR: js_aux_copy"

def test_js_aux_removeImports(
) -> str:
    src = {
        "js/memory.js": [
            "import X",
            "from Y import X",
            "def someFunc():",
        ],
        "js/memory_test.js": [
            "import Y",
            "print ABC",
            "@cld_by_value",
        ],
    }
    dst = js_aux_removeImports(src)
    if (
        cld_len(dst) == 2 and
        cld_len(dst["js/memory.js"]) == 1 and
        cld_len(dst["js/memory_test.js"]) == 2
    ):
        return "OK: js_aux_removeImports"
    return "ERR: js_aux_removeImports"

def test_js_aux_replaceComments(
) -> str:
    src = {
        "js/memory.js": [
            "# This is something",
            "a = 1 # This stays intact",
        ],
    }
    dst = js_aux_replaceComments(src)
    if (
        cld_len(dst) == 1 and
        cld_startswith(dst["js/memory.js"][0], "//")
    ):
        return "OK: js_aux_replaceComments"
    return "ERR: js_aux_replaceComments"
