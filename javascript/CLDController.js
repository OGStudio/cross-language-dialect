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

    this.registerCallback = function(cb) {
        this.callbacks.push(cb);
    };

    this.registerFieldCallback = function(fieldName, cb) {
        this.callbacks.push((c) => {
            if (c.recentField == fieldName) {
                cb(c);
            }
        });
    };

    this.registerFunction = function(f) {
        this.functions.push(f);
    };

    this.reportContext = function() {
        for (let i in this.callbacks) {
            let cb = this.callbacks[i];
            cb(this.context);
        }
    };

    this.set = function(fieldName, value) {
        let c - this.context.selfCopy();
        c.setField(fieldName, value);
        c.recentField = fieldName;
        this.queue.push(c);
        this.processQueue();
    };
}
