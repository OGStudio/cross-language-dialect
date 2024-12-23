from cld import *

class Context:
    def __init__(self):
        self.cfgContents = []
        self.cfgDir = None
        self.cfgPath = None
        self.didWriteJSFiles = False
        self.jsConversions = {}
        self.jsFiles = []
        self.jsFilesCopy = {}
        self.jsFilesRemoveImports = {}
        self.jsFilesReplaceComments = {}
        self.jsSrcFiles = {}
        self.recentField = None
        self.scriptDir = None

    def field(self, fieldName):
        return getattr(self, fieldName)

    def setField(self, fieldName, value):
        setattr(self, fieldName, value)

def createContext():
  return Context()
