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

# Tell if string is a digit.
def cld_isdigit(s):
    return s.isdigit()

# Tell if string starts with certain prefix.
def cld_startswith(s, prefix):
    return s.startswith(prefix)

# Convert string to integer.
def cld_strtoint(s):
    return int(s)
