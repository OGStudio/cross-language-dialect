/**
 * This file is part of Cross-language dialect:
 *     https://github.com/OGStudio/cross-language-dialect
 * License: CC0
 * Version: 1.0.1
 */

function CLDController(context) {
    this._construct = function() {
        this.callbacks = [];
        this.context = context;
        this.functions = [];
        this.isProcessingQueue = false;
        this.queue = [];
    };
    this._construct();
 
    this.executeFunctions = function() {
        let c = this.queue.shift();
        this.context.recentField = c.recentField;
        this.context.setField(c.recentField, c.field(c.recentField));

        for (let i in this.functions) {
            let f = functions[i];
            let ctx = f(this.context.selfCopy());
            if (ctx.recentField != "none") {
                queue.push(ctx);
            }
        }

        reportContext();
    };

    this.processQueue = function() {
        // Prevent recursion.
        if (this.isProcessingQueue) {
            return;
        }

        this.isProcessingQueue = true;

        while (this.queue.length > 0) {
            this.executeFunctions();
        }

        this.isProcessingQueue = false;
    };

    fun registerCallback(cb: (c: CLDContext) -> Unit) {
        callbacks.add(cb)
    }
 
    fun registerFieldCallback(
        fieldName: String,
        cb: (CLDContext) -> Unit
    ) {
        callbacks.add({ c ->
            if (c.recentField == fieldName) {
                cb(c)
            }
        })
    }
 
    fun registerFunction(f: (CLDContext) -> CLDContext) {
        functions.add(f)
    }
 
    fun reportContext() {
        for (cb in callbacks) {
            cb(context)
        }
    }
 
    fun set(fieldName: String, value: Any) {
        var c = context.selfCopy()
        c.setField(fieldName, value)
        c.recentField = fieldName
        queue.add(c)
        processQueue()
    }
}
