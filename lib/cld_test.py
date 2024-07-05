from cld import *

def test_cld_isdigit_digit(
) -> str:
    if (
        cld_isdigit("123")
    ):
        return "OK: cld_isdigit_digit"
    return "ERR: cld_isdigit_digit"

def test_cld_isdigit_notDigit(
) -> str:
    if (
        cld_isdigit("abc")
    ):
        return "ERR: cld_isdigit_notDigit"
    return "OK: cld_isdigit_notDigit"

def test_cld_strtoint(
) -> str:
    if (
        cld_strtoint("123") == 123
    ):
        return "OK: cld_strtoint"
    return "ERR: cld_strtoint"
