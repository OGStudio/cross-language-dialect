import os
from cld import *
from js_aux import *
from Context import *

# Copy src file to dst for further modifications
#
# Conditions:
# 1. JS source files' contents became available
@cld_by_value
def js_copy(
    c: Context
) -> Context:
    if (
        c.recentField == "jsSrcFiles"
    ):
        c.jsFilesCopy = js_aux_copy(c.jsConversions, c.jsSrcFiles)
        c.recentField = "jsFilesCopy"
        return c

    c.recentField = "none"
    return c

# Prepare dictionary of src->dst conversions based on config
#
# Conditions:
# 1. Config contents have just become available
@cld_by_value
def js_prepareConversions(
    c: Context
) -> Context:
    if (
        c.recentField == "cfgContents"
    ):
        c.jsConversions = js_aux_conversions(c.cfgContents)
        c.recentField = "jsConversions"
        return c

    c.recentField = "none"
    return c
