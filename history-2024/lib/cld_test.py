from cld import *

def test_cld_find(
) -> str:
    if (
        cld_find("abc", "b") == 1
    ):
        return "OK: cld_find"
    return "ERR: cld_find"

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

def test_cld_lstrip(
) -> str:
    if (
        cld_lstrip("  abc") == "abc"
    ):
        return "OK: cld_lstrip"
    return "ERR: cld_lstrip"

def test_cld_replace(
) -> str:
    if (
        cld_replace("abc", "b", "c") == "acc"
    ):
        return "OK: cld_replace"
    return "ERR: cld_replace"

def test_cld_split(
) -> str:
    sample = "file1 -> file2"
    separator = " -> "
    if (
        cld_split(sample, separator) == sample.split(separator)
    ):
        return "OK: cld_split"
    return "ERR: cld_split"

def test_cld_startswith(
) -> str:
    if (
        cld_startswith("# something", "#")
    ):
        return "OK: cld_startswith"
    return "ERR: cld_startswith"

def test_cld_strtoint(
) -> str:
    if (
        cld_strtoint("123") == 123
    ):
        return "OK: cld_strtoint"
    return "ERR: cld_strtoint"
