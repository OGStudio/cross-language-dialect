import os
from cld import *
from js_aux import *
from tr_Context import *

# Prepare dictionary of src->dst conversions based on config
#
# Conditions:
# 1. Config contents have just become available
@cld_by_value
def js_prepareConversions(
    c: tr_Context
) -> tr_Context:
    if (
        c.recentField == "cfgContents"
    ):
        c.jsConversions = js_aux_conversions(c.cfgContents)
        c.recentField = "jsConversions"
        return c

    c.recentField = "none"
    return c
