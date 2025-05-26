
/// Sample Context used for testing
function ExampleContext() {
    this._construct = function() {
        this.didLaunch = false;
        this.host = "";
        this.sometimes = "";

        this.recentField = "";
    };
    this._construct();

    this.field = function(name) {
        if (name == "didLaunch") {
            return this.didLaunch;
        } else if (name == "host") {
            return this.host;
        } else if (name == "sometimes") {
            return this.sometimes;
        }

        return "unknown-field-name";
    };

    this.selfCopy = function() {
        let that = structuredCopy(this);
        return that;
    };

    this.setField = function(name, value) {
        if (name == "didLaunch") {
            this.didLaunch = value;
        } else if (name == "host") {
            this.host = value;
        } else if (name == "sometimes") {
            this.sometimes = value;
        }
    };
}

/// Validate field access by name
function t01_ExampleContext_field() {
    var c = new ExampleContext();
    c.host = "abc";
    return c.host == c.field("host");
}
