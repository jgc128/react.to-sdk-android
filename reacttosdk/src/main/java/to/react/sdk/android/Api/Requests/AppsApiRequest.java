package to.react.sdk.android.Api.Requests;

import com.android.volley.Response;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import to.react.sdk.android.Api.Model.App;


public abstract class AppsApiRequest extends BaseApiListRequest<List<App>> {

    @Override
    protected String getRequestUrl() {
        return "universal_event/apps/";
    }

    @Override
    protected Type getType() {
        return new TypeToken<List<App>>(){}.getType();
    }

}
