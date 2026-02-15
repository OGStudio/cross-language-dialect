from cld import *

class sample_Context:
    def __init__(self):
        self.count = 0

def test_Python_cld_by_value(
) -> str:
    c = sample_Context()
    c.count = 1

    @cld_by_value
    def alterValue(c):
        c.count = 2

    alterValue(c)

    if (
        c.count == 1
    ):
        return "OK: cld_by_value"
    return "ERR: cld_by_value"
