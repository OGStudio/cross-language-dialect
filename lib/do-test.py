from cld_test import *
from cld_test_Python import *

functions = [
    test_cld_isdigit_digit,
    test_cld_isdigit_notDigit,
    test_cld_strtoint,
    test_Python_cld_by_value,
]

for f in functions:
    print(f())
