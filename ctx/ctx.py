import copy

class ctx_Controller:
    def __init__(self, c):
        self.callbacks = []
        self.context = c
        self.functions = []
        self.isProcessingQueue = False
        self.queue = []
    
    def executeFunctions(self):
        c = self.queue.pop(0)
        for f in self.functions:
            ctx = f(c)
            if ctx.recentField != "none":
                self.queue.append(ctx)
      
        self.context.recentField = c.recentField
        self.context.setField(c.recentField, c.field(c.recentField))
        self.reportContext()

    def processQueue(self):
        # Decline recursion.
        if self.isProcessingQueue:
            return
        self.isProcessingQueue = True
        while len(self.queue) > 0:
            self.executeFunctions()
        self.isProcessingQueue = False

    def registerCallback(self, cb):
        self.callbacks.append(cb)

    def registerFieldCallback(self, fieldName, cb):
        self.callbacks.append(lambda c: cb(c) if c.recentField == fieldName else None)

    def registerFunction(self, f):
        self.functions.append(f)

    def registerFunctions(self, funcs):
        for f in funcs:
            self.functions.append(f)

    def reportContext(self):
        for cb in self.callbacks:
            cb(self.context)

    def set(self, fieldName, value):
        c = copy.deepcopy(self.context)
        c.setField(fieldName, value)
        c.recentField = fieldName
        self.queue.append(c)
        self.processQueue()
