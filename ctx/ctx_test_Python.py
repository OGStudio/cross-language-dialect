from ctx import *
from cld import *

class sample_Context:
    def __init__(self):
        self.input = ""
        self.output = ""
        self.recentField = "none"

    def field(self, fieldName):
        return getattr(self, fieldName)

    def setField(self, fieldName, value):
        setattr(self, fieldName, value)

def test_ctx_Controller_executeFunctions_registerFunction_set(
) -> str:
    c = sample_Context()
    ctrl = ctx_Controller(c)
    # Disable automatic invocation of executeFunctions.
    ctrl.isProcessingQueue = True
    ctrl.set("input", "123")

    @cld_by_value
    def processInput(c):
        if (
            c.recentField == "input"
        ):
            c.output = "Checked"
            c.recentField = "output"
            return c
        c.recentField = "none"
        return c

    ctrl.registerFunction(processInput)
    # Apply 'input'.
    ctrl.executeFunctions()
    # Apply 'output'.
    ctrl.executeFunctions()

    if (
        c.input == "123" and
        c.output == "Checked"
    ):
        return "OK: ctx_Controller_executeFunctions_set"

    return "ERR: ctx_Controller_executeFunctions_set"

def test_ctx_Controller_processQueue(
) -> str:
    c = sample_Context()
    ctrl = ctx_Controller(c)

    @cld_by_value
    def processInput(c):
        if (
            c.recentField == "input"
        ):
            c.output = "Checked"
            c.recentField = "output"
            return c
        c.recentField = "none"
        return c

    ctrl.registerFunction(processInput)
    ctrl.set("input", "abc");

    if (
        c.input == "abc" and
        c.output == "Checked"
    ):
      return "OK: ctx_Controller_processQueue"

    return "ERR: ctx_Controller_processQueue"

def test_ctx_Controller_registerFieldCallback_match(
) -> str:
    c = sample_Context()
    ctrl = ctx_Controller(c)
    c.input = "123"
    c.recentField = "input"
    
    globals()["callbackInput"] = ""
    def setCallbackInput(c):
        if (
            c.recentField == "input"
        ):
            globals()["callbackInput"] = c.input

    ctrl.registerFieldCallback("input", setCallbackInput)
    ctrl.reportContext()

    if (
        c.input == globals()["callbackInput"]
    ):
        return "OK: ctx_Controller_registerFieldCallback_match"

    return "ERR: ctx_Controller_registerFieldCallback_match"

def test_ctx_Controller_registerFieldCallback_mismatch(
) -> str:
    c = sample_Context()
    ctrl = ctx_Controller(c)
    c.input = "123"
    c.output = "you"
    # A field other than 'input' is marked recent.
    c.recentField = "output"

    globals()["callbackInput"] = ""
    def setCallbackInput(c):
        if (
            c.recentField == "input"
        ):
            globals()["callbackInput"] = c.input

    ctrl.registerFieldCallback("input", setCallbackInput)
    ctrl.reportContext()
    if (
        globals()["callbackInput"] == ""
    ):
        return "OK: ctx_Controller_registerFieldCallback_mismatch"

    return "ERR: ctx_Controller_registerFieldCallback_mismatch"

def test_sample_Context_field(
) -> str:
    c = sample_Context()
    c.input = "abc"
    if (
        c.field("input") == "abc"
    ):
       return "OK: sample_Context_field"

    return "ERR: sample_Context_field"

def test_sample_Context_setField(
) -> str:
    c = sample_Context()
    c.input = "abc"
    c.setField("input", "123")
    if (
        c.input == "123"
    ):
        return "OK: sample_Context_setField"

    return "ERR: sample_Context_setField"
