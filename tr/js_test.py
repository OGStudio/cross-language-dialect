from js import *
from Context import *

def test_js_copy(
) -> str:
    c = createContext()
    c.jsConversions = {
        "py/memory.py": "js/memory.js",
        "py/memory_test.py": "js/memory_test.js",
    }
    c.jsSrcFiles = {
        "py/memory.py": [
            "import X",
            "import Y",
        ],
        "py/memory_test.py": [
            "print ABC",
        ],
    }
    c.recentField = "jsSrcFiles"

    c = js_copy(c)
    if (
        c.recentField == "jsFilesCopy" and
        cld_len(c.jsFilesCopy) == 2
    ):
        return "OK: js_copy"
    return "ERR: js_copy"

def test_js_prepareConversions(
) -> str:
    c = createContext()
    c.cfgContents = [
        "[abc]",
        "else",
        "[js]",
        "file1 -> file2",
        "wrongSep->another",
    ]
    c.recentField = "cfgContents"
    c = js_prepareConversions(c)
    if (
        c.recentField == "jsConversions" and
        cld_len(c.jsConversions) == 1
    ):
        return "OK: js_prepareConversions"
    return "ERR: js_prepareConversions"

def test_js_removeImports(
) -> str:
    c = createContext()
    c.jsFilesCopy = {
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
    c.recentField = "jsFilesCopy"

    c = js_removeImports(c)
    if (
        c.recentField == "jsFilesRemoveImports" and
        cld_len(c.jsFilesRemoveImports) == 2
    ):
        return "OK: js_removeImports"
    return "ERR: js_removeImports"

