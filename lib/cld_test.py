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

def test_cld_len(
) -> str:
    if (
        cld_len("abc") == 3
    ):
        return "OK: cld_len"
    return "ERR: cld_len"

def test_cld_split(
) -> str:
    sample = "file1 -> file2"
    separator = " -> "
    if (
        cld_split(sample, separator) == sample.split(separator)
    ):
        return "OK: cld_split"
    return "ERR: cld_split"

def test_cld_strtoint(
) -> str:
    if (
        cld_strtoint("123") == 123
    ):
        return "OK: cld_strtoint"
    return "ERR: cld_strtoint"
