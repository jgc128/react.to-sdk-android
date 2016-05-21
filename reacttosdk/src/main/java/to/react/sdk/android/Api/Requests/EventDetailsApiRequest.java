package to.react.sdk.android.Api.Requests;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import to.react.sdk.android.Api.Model.App;
import to.react.sdk.android.Api.Model.Event;

public abstract class EventDetailsApiRequest extends BaseApiRequest<Event> {

    Event targetEvent;

    public EventDetailsApiRequest(Event event) {
        targetEvent = event;
    }

    @Override
    protected String getRequestUrl() {
        return "universal_event/events/" + targetEvent.Id + "/";
    }

    @Override
    protected Type getType() {
        return new TypeToken<Event>(){}.getType();
    }

}
