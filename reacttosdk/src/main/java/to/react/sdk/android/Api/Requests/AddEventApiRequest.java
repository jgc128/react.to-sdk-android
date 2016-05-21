package to.react.sdk.android.Api.Requests;


import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import to.react.sdk.android.Api.Model.App;
import to.react.sdk.android.Api.Model.Event;
import to.react.sdk.android.Api.Model.NewEvent;
import to.react.sdk.android.Helpers.DateTimeHelper;

public abstract class AddEventApiRequest extends BaseApiPostRequest<Event> {

    App targetApp;
    NewEvent targetEvent;

    public AddEventApiRequest(App app, NewEvent event) {
        targetApp = app;
        targetEvent = event;
    }

    @Override
    protected String getRequestUrl() {
        return "universal_event/events/add/";
    }

    @Override
    protected Type getType() {
        return new TypeToken<Event>(){}.getType();
    }


    @Override
    protected JsonElement getData() {
        JsonObject data = (JsonObject)gson.toJsonTree(targetEvent);
        data.addProperty("application", targetApp.Id);

        return data;
    }


}
