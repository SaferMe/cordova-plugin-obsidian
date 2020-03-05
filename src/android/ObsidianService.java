package com.saferme.obsidian;


import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaArgs;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PermissionHelper;
import org.apache.cordova.PluginResult;
import org.apache.cordova.LOG;
import org.json.JSONArray;
import org.json.JSONException;
import com.saferme.obsidian.api.IResourceCallback;
import com.saferme.obsidian.api_impl.ApiFactory;
import com.saferme.obsidian.store.SafermeDbRepository;
import com.saferme.obsidian.store.SafermeStoreApi;
import com.saferme.obsidian.api.IObsidian;
import android.content.Context;
import kotlin.Pair;
import java.util.ArrayList;

public class ObsidianService extends CordovaPlugin implements IResourceCallback {

    public final String TAG = "ObsidianService";
    CallbackContext context;

    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        LOG.d(TAG, "We are entering execute" + action);
        Context appContext = this.cordova.getActivity().getApplicationContext();
        context = callbackContext;
        IObsidian api = ApiFactory.Companion.getObsidian(SafermeStoreApi.getDbRepository(appContext), appContext);
        if (action.equals("tasks.index")) {
            api.Tasks().index(new ArrayList<Pair<String, String>>(),this);
        }
        context.success(args);
        return true;
    }

     @Override
        public void data(String response) {
            context.success(response);
        }
}
