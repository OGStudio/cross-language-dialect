from cld import *

class tr_Context:
    def __init__(self):
        self.cfgContents = []
        self.cfgDir = None
        self.cfgPath = None
        self.jsConversions = {}
        self.scriptDir = None

    def field(self, fieldName):
        return getattr(self, fieldName)

    def setField(self, fieldName, value):
        setattr(self, fieldName, value)

def tr_createContext():
  return tr_Context()
