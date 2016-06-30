package to.react.sdk.android.Api.Requests;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

//import org.json.JSONArray;
//import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import to.react.sdk.android.Api.Model.App;
import to.react.sdk.android.Api.Model.DemographicCategory;
import to.react.sdk.android.Api.Model.Event;
import to.react.sdk.android.Helpers.DateTimeHelper;

public abstract class AppEventsApiRequest extends BaseApiListRequest<List<Event>> {

    App targetApp;

    public AppEventsApiRequest(App app) {
        targetApp = app;
    }

    @Override
    protected String getRequestUrl() {
        return "universal_event/apps/" + targetApp.Id + "/events/";
    }

    @Override
    protected Type getType() {
        return new TypeToken<List<Event>>(){}.getType();
    }

}
