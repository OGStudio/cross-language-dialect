#!/usr/bin/env node

let fs = require("fs");
let KT = require("./ver-nodejs-app").org.opengamestudio;

//!<-- API -->

function appSet(key, value) {
    KT.appCtrl().set(key, value);
}

//!<-- Constants -->

//let APP_TMP = "BINARY";

//!<-- Component -->

function AppComponent() {
    this._construct = function() {
        // Effects
        let oneliners = [ 
            "inputFile", (c) => { appReadFile(c.inputFile) },
            "writeFile", (c) => { appWriteFile(c.outputFile, c.outputFileContets) },
        ];
        KT.registerOneliners(KT.appCtrl(), oneliners);

        // Defaults.
        appSet("arguments", process.argv);
    };

    this._construct();
}

//<!-- Effects -->

/*
function srvDeleteFile(fileName) {
    var isOk = true;
    try {
        fs.rmSync(fileName);
    } catch (e) {
        console.error("ERR srvDF e:", e);
        isOk = false;
    }
    srvCtrl().set("didDeleteFile", isOk);
}
*/

//<!-- Other functions -->

function appReadFile(fileName) {
    let contents = fs.readFileSync(fileName, { encoding: "utf8", flag: "r" });
    let lines = contents.split("\n");
    appSet("inputFileLines", lines);
}

function appWriteFile(fileName, contents) {
    fs.writeFileSync(fileName, contents);
    appSet("didWriteOutputFile", true);
}

//<!-- Installation -->

let cmp = new AppComponent();

//<!-- Run -->
appSet("didLaunch", true);
