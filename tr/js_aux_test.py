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
