class CLDController {
    var callbacks = [(CLDContext) -> Void]()
    var functions = [(CLDContext) -> CLDContext]()
    var isProcessingQueue = false
    var queue = [CLDContext]()

    init(_ context: CLDContext) {
        self.context = context
    }

    func executeFunctions() {
        let c = queue.removeFirst()
        context.recentField = c.recentField
        context.setField(c.recentField, c.field(c.recentField))
      
        for (f in functions) {
            let ctx = f(context)
            if (ctx.recentField != "none") {
                queue.append(ctx)
            }
        }
      
        reportContext()
    }

    func processQueue() {
        // Prevent recursion.
        if (isProcessingQueue) {
            return
        }
      
        isProcessingQueue = true
      
        while (queue.count > 0) {
            executeFunctions()
        }
      
        isProcessingQueue = false
    }

    func registerCallback(_ cb: (CLDContext) -> Void) {
        callbacks.append(cb)
    }

    func registerFieldCallback(
        _ fieldName: String,
        _ cb: (CLDContext) -> Void
    ) {
        callbacks.append({c in if (c.recentField == fieldName) cb(c) })
    }

    func registerFunction(_ f: (CLDContext) -> CLDContext) {
        functions.append(f)
    }

    func reportContext() {
        for (cb in callbacks) {
            cb(context)
        }
    }

    func set(
        _ fieldName: String,
        _ value: Any
    ) {
        var c = context
        c.setField(fieldName, value)
        c.recentField = fieldName
        queue.append(c)
        processQueue()
    }
}
