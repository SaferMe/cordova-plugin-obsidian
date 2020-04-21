var exec = require('cordova/exec');

var PLUGIN_NAME = "ObsidianService";

/**

Success callback will be called immediately once with the current
available data, and will be called again whenever the data changes.

Only the most recent success callback will be used for data changes to
a given method

Success callback schema:
{
	status: OK | PENDING
	payload: {
		tasks: [
			{ id: /id/, ... }, ...
		]
	}
}

Failure callback will be called if an unrecoverable exception occurs.
No further calls to either the success or failure callbacks will be made
once this is called.

Failure callback schema:
{
	status: ERROR,
	payload: {
		type: exception type
		message: exception message
		trace: Stacktrace of exception
	}
}
*/

/** Tasks **/
module.exports.Tasks = {
	/** Get a list of all available tasks, data argument is ignored */
	index: function(data, success, error) { exec(success, error, PLUGIN_NAME, 'tasks.index', [data]) }
}

module.exports.Credentials = {
	/** Set tyhe credentials to use for API calls
	Expected Data:
	{
		ApiKey: /auth token/,
		InstallationId: /installation_id/,
		AppID: /bundle  id/	
		TeamId: /team id/
	}

	Example call:
	ObsidianService.Credentials.set({ApiKey: "test", InstallationId: "test", AppID: "test", TeamID: "test"}, function(data){console.log("CredSet success:"); console.dir(data)}, function(data){console.log("CredSet fail:"); console.dir(+data)})
	*/
	set: function(data, success, error) { exec(success, error, PLUGIN_NAME, 'credentials.set', [data]) }
}
