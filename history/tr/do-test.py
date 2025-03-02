#!/usr/bin/env python3

import os
import sys

SCRIPT_DIR = os.path.dirname(os.path.realpath(sys.argv[0]))
sys.path.append(f"{SCRIPT_DIR}/../lib")

from js_aux_test import *
from js_test import *

functions = [
    test_js_aux_conversions,
    test_js_aux_copy,
    test_js_aux_removeImports,
    test_js_aux_replaceComments,
    test_js_copy,
    test_js_prepareConversions,
    test_js_removeImports,
    test_js_replaceComments,
]

for f in functions:
    print(f())
