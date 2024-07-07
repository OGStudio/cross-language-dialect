#!/usr/bin/env python3

import os
import sys

DIR = os.path.dirname(os.path.realpath(sys.argv[0]))

sys.path.append(f"{DIR}/../lib")

from ctx_test_Python import *

functions = [
    test_ctx_Controller_executeFunctions_registerFunction_set,
    test_ctx_Controller_processQueue,
    test_ctx_Controller_registerFieldCallback_match,
    test_ctx_Controller_registerFieldCallback_mismatch,
    test_sample_Context_field,
    test_sample_Context_setField
]

for f in functions:
    print(f())

