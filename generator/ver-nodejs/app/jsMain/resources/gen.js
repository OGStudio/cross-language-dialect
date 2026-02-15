#!/usr/bin/env node

let fs = require("fs");
let KT = require("./ver-nodejs-app").org.opengamestudio;

//!<-- API -->

function genCtrlSet(key, value) {
    cmp.ctrl.set(key, value);
}

//!<-- Constants -->

//let GEN_TMP = "BINARY";

//!<-- Component -->

function GenComponent() {
    this._construct = function() {
        // TODO 1. Create ctrl instance in KMP
        // TODO Only reference it here
        // TODO 2. Accept isDbg to enabled dbg output
 
        // Effects
        /*
        let oneliners = [ 
            "deleteFile", (c) => { srvDeleteFile(c.deleteFile) },
            "listDir", (c) => { srvListDir(c.listDir) },
            "projectDir", (c) => { srvResolvePath(c.projectDir) },
            "readFile", (c) => { srvReadFile(c.readFile) },
            "url", (c) => { open(c.url) },
            "writeFile", (c) => { srvWriteFile(c.writeFile[0], c.writeFile[1]) },
        ];
        KT.registerOneliners(this.ctrl, oneliners);
        */

        // Defaults.
        /*
        srvCtrl().set("arguments", process.argv);
        srvCtrl().set("defaultBrowserDir", __dirname + "/../ver-browser");
        */
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

//<!-- Functions -->


//<!-- Installation -->

let cmp = new GenComponent();

//<!-- Main loop -->

console.log("ИГР Gen hello world");
