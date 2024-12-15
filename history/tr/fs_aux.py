import os
import pathlib

# Make path of directories, including missing parent ones
def fs_aux_mkDirs(
    path: str
):
    dirsOnly = os.path.dirname(os.path.abspath(path))
    pathlib.Path(dirsOnly).mkdir(parents=True, exist_ok=True)

# Read file into an array of strings
def fs_aux_readFile(
    fileName: str
) -> [str]:
    lines = []
    with open(fileName) as file:
        for line in file:
            lines.append(line.rstrip())
    return lines

# Create a file if it doesn't exist
def fs_aux_touch(
    path: str
):
    pathlib.Path(path).touch(exist_ok=True)
