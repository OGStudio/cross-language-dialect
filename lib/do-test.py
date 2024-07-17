#!/usr/bin/env python3

from cld_test import *
from cld_test_Python import *

functions = [
    test_cld_isdigit_digit,
    test_cld_isdigit_notDigit,
    test_cld_find,
    test_cld_len,
    test_cld_lstrip,
    test_cld_replace,
    test_cld_split,
    test_cld_startswith,
    test_cld_strtoint,
    test_Python_cld_by_value,
]

for f in functions:
    print(f())
