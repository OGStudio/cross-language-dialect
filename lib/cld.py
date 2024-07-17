import copy

# Function decorator to make deep copies of arguments to treat the arguments
# as structs.
# https://stackoverflow.com/a/15398021
def cld_by_value(f):
    def _f(*args, **kwargs):
        argsCopy = copy.deepcopy(args)
        kwargsCopy = copy.deepcopy(kwargs)
        return f(*argsCopy, **kwargsCopy)
    return _f

# Find substring position in string.
#
# Not found == -1
def cld_find(s, sub):
    return s.find(sub)

# Tell if string is a digit.
def cld_isdigit(s):
    return s.isdigit()

# Get length.
def cld_len(s):
    return len(s)

# Strip spaces from the left.
def cld_lstrip(s):
    return s.lstrip()

# Replace all occurences.
def cld_replace(s, src, dst):
    return s.replace(src, dst)

# Split string by separator.
def cld_split(s, sep):
    return s.split(sep)

# Tell if string starts with certain prefix.
def cld_startswith(s, prefix):
    return s.startswith(prefix)

# Convert string to integer.
def cld_strtoint(s):
    return int(s)
