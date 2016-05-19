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


public class AppsApiRequest extends BaseApiRequest<List<App>> {

    @Override
    protected String getUrl() {
        return baseUrl + "universal_event/apps/";
    }

    @Override
    protected List<App> getObject(JsonElement json) throws Exception {
        JsonElement jsonRes = ((JsonObject)json).getAsJsonArray("results");

        return super.getObject(jsonRes);
    }

    @Override
    protected Type getType() {
        return new TypeToken<List<App>>(){}.getType();
    }

    @Override
    public void onApiResponse(List<App> result) {

    }

    @Override
    public void onApiError(String error) {

    }
}
