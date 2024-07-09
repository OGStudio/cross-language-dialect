from cld import *

# Extract JS conversion related setup from config
def js_aux_conversions(
    cfgContents: [str]
) -> dict[str, str]:
    # 1. Collect conversion lines.
    n = cld_len(cfgContents)
    isCollecting = False
    convLines: [str] = []
    for i in range(0, n):
        line = cfgContents[i]

        # Set collection mode.
        if (
            line == "[js]"
        ):
            isCollecting = True
        elif (
            isCollecting and
            cld_startswith(line, "[")
        ):
            isCollecting = False

        # Collect.
        if (
            isCollecting and
            cld_len(line) > 0 and
            not cld_startswith(line, "[")
        ):
            convLines.append(line)

    # 2. Construct conversion dictionary.
    n = cld_len(convLines)
    res: dict[str, str] = {}
    for i in range(0, n):
        line = convLines[i]
        parts = cld_split(line, " -> ")
        if (
            cld_len(parts) == 2
        ):
            res[parts[0]] = parts[1]

    return res
