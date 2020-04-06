package com.saferme.obsidian;

import android.util.Log;

import com.saferme.obsidian.jsinterface.JSCallback;
import com.saferme.obsidian.jsinterface.ObsidianJSInterface;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ObsidianService extends CordovaPlugin  {

    public final String TAG = "ObsidianCordovaPlugin";

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        Log.v(TAG, "execute action: "+action+" args:\n\t"+args.toString());
        ObsidianJSInterface.execute(this.cordova.getContext(), action, args, new CallBackWrapper(callbackContext));
        return true;
    }

    private class CallBackWrapper implements JSCallback {
        private final CallbackContext wrappedCallback;

        public CallBackWrapper(CallbackContext context) {
            this.wrappedCallback = context;
        }

        /** If true, then no further success or error messages should be sent */
        @Override
        public boolean isFinished() {
            Log.v(TAG, "isFinished() called");
            return wrappedCallback.isFinished();
        }

        /** Will be called:
         * 1. Immediately after execute method
         * 2. Whenever the payload changes
         * @param message As per above schema
         */
        @Override
        public void success(JSONObject message) {
            Log.v(TAG, "success() called with: "+message.toString());
            wrappedCallback.success(message);
        }

        /**
         * Will be called in the event of an unrecoverable failure
         * No further calls to this callback will be made.
         * @param message as per above Schema
         */
        @Override
        public void error(JSONObject message) {
            Log.v(TAG, "error() called with: "+message.toString());
            wrappedCallback.error(message);
        }
    }
}
