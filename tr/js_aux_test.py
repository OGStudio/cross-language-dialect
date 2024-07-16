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
