import os
from cld import *
from Context import *
from fs_aux import *

# Extract directory from config path
#
# Conditions:
# 1. Config path has just been set
@cld_by_value
def fs_locateConfigDir(
    c: Context
) -> Context:
    if (
        c.recentField == "cfgPath"
    ):
        c.cfgDir = os.path.dirname(c.cfgPath)
        c.recentField = "cfgDir"
        return c

    c.recentField = "none"
    return c

# Make output files for JS conversion and intermediate directories
#
# Conditions:
# 1. JS conversion has just been arranged
@cld_by_value
def fs_mkJSFiles(
    c: Context
) -> Context:
    if (
        c.recentField == "jsConversions" and
        cld_len(c.jsConversions) != 0
    ):
        files = []
        for src in c.jsConversions:
            dst = c.cfgDir + "/" + c.jsConversions[src]
            fs_aux_mkDirs(dst)
            fs_aux_touch(dst)
            files.append(dst)
        c.jsFiles = files
        c.recentField = "jsFiles"
        return c

    c.recentField = "none"
    return c

# Read config file contents
#
# Conditions:
# 1. Config path has just been set
@cld_by_value
def fs_readConfig(
    c: Context
) -> Context:
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
    c: Context
) -> Context:
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

# Write JS files
#
# Conditions:
# 1. Imports have just been removed
@cld_by_value
def fs_writeJSFiles(
    c: Context
) -> Context:
    if (
        c.recentField == "jsFilesRemoveImports"
    ):
        for file, contents in c.jsFilesRemoveImports.items():
            path = c.cfgDir + "/" + file
            contents = "\n".join(contents)
            with open(path, "w") as f:
                f.write(contents)

        c.didWriteJSFiles = True
        c.recentField = "didWriteJSFiles"
        return c

    c.recentField = "none"
    return c
