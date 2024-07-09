
# Read file into an array of strings
def fs_aux_readFile(
    fileName: str
) -> [str]:
    lines = []
    with open(fileName) as file:
        for line in file:
            lines.append(line.rstrip())
    return lines
