
/// Sample Context for testing
function ExampleContext() {
    this._construct = function() {
        this.didLaunch = false;
        this.host = "";
        // NOTE: JavaScript has no optionals, that's why `sometimes` field is absent

        this.recentField = "";
    };
    this._construct();

    this.field = function(name) {
        if (name == "didLaunch") {
            return this.didLaunch;
        } else if (name == "host") {
            return this.host;
        }

        return "unknown-field-name";
    };

    this.selfCopy = function() {
        let that = new ExampleContext();
        that.didLaunch = this.didLaunch;
        that.host = this.host;
        that.recentField = this.recentField;
        return that;
    };

    this.setField = function(name, value) {
        if (name == "didLaunch") {
            this.didLaunch = value;
        } else if (name == "host") {
            this.host = value;
        }
    };
}

/// Sample function for processing context change
function hostToDidLaunch(c) {
    if (c.recentField == "host") {
        c.didLaunch = true;
        c.recentField = "didLaunch";
        return c;
    }

    c.recentField = "none";
    return c;
}


/// Validate field access by name
function t01_ExampleContext_field() {
    var c = new ExampleContext();
    c.host = "abc";
    return c.host == c.field("host");
}

/// Validate changing field value by name
function t02_ExampleContext_setField() {
    var c = new ExampleContext();
    c.didLaunch = true;
    c.setField("didLaunch", false);
    return c.didLaunch == false;
}

/// Validate `executeFunctions()` and `set()`
function t03_CLDController_executeFunctions_set() {
    let ctrl = new CLDController(new ExampleContext());

    // Disable the execution of `executeFunctions()` for testing purpose.
    ctrl.isProcessingQueue = true;

    ctrl.set("host", "123");

    ctrl.registerFunction(hostToDidLaunch);

    // Apply `host` value.
    ctrl.executeFunctions();
    // Apply `didLaunch` value.
    ctrl.executeFunctions();

    let c = ctrl.context;
    return c.host == "123" &&
        c.didLaunch == true;
}
