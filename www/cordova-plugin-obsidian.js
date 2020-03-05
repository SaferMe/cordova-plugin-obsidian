var exec = require('cordova/exec');

var PLUGIN_NAME = "ObsidianService";

exports.getTasks = function (data, success, error) {
    exec(success, error, PLUGIN_NAME, 'tasks.index', [data]);
}
