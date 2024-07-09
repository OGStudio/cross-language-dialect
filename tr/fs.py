import os
from cld import *
from fs_aux import *
from tr_Context import *

# Extract directory from config path
#
# Conditions:
# 1. Config path has just been set
@cld_by_value
def fs_locateConfigDir(
    c: tr_Context
) -> tr_Context:
    if (
        c.recentField == "cfgPath"
    ):
        c.cfgDir = os.path.dirname(c.cfgPath)
        c.recentField = "cfgDir"
        return c

    c.recentField = "none"
    return c

# Read config file contents
#
# Conditions:
# 1. Config path has just been set
@cld_by_value
def fs_readConfig(
    c: tr_Context
) -> tr_Context:
    if (
        c.recentField == "cfgPath"
    ):
        c.cfgContents = fs_aux_readFile(c.cfgPath)
        c.recentField = "cfgContents"
        return c

    c.recentField = "none"
    return c
