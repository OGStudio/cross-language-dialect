from js import *
from tr_Context import *

def test_js_prepareConversions(
) -> str:
    c = tr_createContext()
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
        cld_len(c.jsConversions) == 1
    ):
        return "OK: js_prepareConversions"
    return "ERR: js_prepareConversions"
