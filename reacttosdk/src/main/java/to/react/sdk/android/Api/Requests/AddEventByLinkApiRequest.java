package to.react.sdk.android.Api.Requests;


import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import to.react.sdk.android.Api.Model.App;
import to.react.sdk.android.Api.Model.Event;

public abstract class AddEventByLinkApiRequest extends BaseApiPostRequest<Event> {
    App targetApp;
    String eventLink;

    public AddEventByLinkApiRequest(App app, String eventLink) {
        targetApp = app;
        this.eventLink = eventLink;
    }

    @Override
    protected String getRequestUrl() {
        return "universal_event/events/add_by_link/";
    }

    @Override
    protected Type getType() {
        return new TypeToken<Event>(){}.getType();
    }


    @Override
    protected JsonElement getData() {
        JsonObject data = new JsonObject();
        data.addProperty("application", targetApp.Id);
        data.addProperty("link", eventLink);

        return data;
    }
}
