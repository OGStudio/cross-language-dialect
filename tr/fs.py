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

# Read input files for JS conversion
#
# Conditions:
# 1. JS conversion has just been arranged
@cld_by_value
def fs_readJSSrcFiles(
    c: tr_Context
) -> tr_Context:
    if (
        c.recentField == "jsConversions" and
        cld_len(c.jsConversions) != 0
    ):
        fileContents = { }
        for file in c.jsConversions:
            path = c.cfgDir + "/" + file
            fileContents[file] = fs_aux_readFile(path)
        c.jsSrcFiles = fileContents
        c.recentField = "jsSrcFiles"
        return c

    c.recentField = "none"
    return c