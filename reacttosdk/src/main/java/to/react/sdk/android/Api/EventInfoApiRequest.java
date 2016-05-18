package to.react.sdk.android.Api;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;

import to.react.sdk.android.Api.Model.Event;
import to.react.sdk.android.Api.Model.EventTarget;
import to.react.sdk.android.Api.Model.Interaction;
import to.react.sdk.android.Helpers.DateTimeHelper;

public class EventInfoApiRequest  extends BaseApiRequest<Event> {

    Event targetEvent;

    public EventInfoApiRequest(Event event) {
        targetEvent = event;

        Log.e("---","success5");
    }

    @Override
    protected String getUrl() {

        Log.e("---",baseUrl + "universal_event/events/" + targetEvent.Id + "/");
        return baseUrl + "universal_event/events/" + targetEvent.Id + "/";
    }

    @Override
    protected Event getFromJson(JSONObject json) throws Exception {

        Event event = new Event();
        event.Id = json.getLong("id");
        event.Name = json.getString("name");
        event.DateStart = DateTimeHelper.parseDateString(json.getString("date_start"));
        event.DateEnd = DateTimeHelper.parseDateString(json.getString("date_end"));
        event.IsNegativeReactions = json.getBoolean("negative_reactions");
        event.Targets = new ArrayList<>();
        JSONArray arrayTargets = json.getJSONArray("targets");
        for (int i = 0; i < arrayTargets.length(); i++){
            EventTarget et = new EventTarget();
            et.Id = arrayTargets.getJSONObject(i).getLong("id");
            et.Name = arrayTargets.getJSONObject(i).getString("name");
            et.ImageUrl = arrayTargets.getJSONObject(i).getString("image_url");
            et.Interactions = new ArrayList<>();
            JSONArray arrayInteractions = arrayTargets.getJSONObject(i).getJSONArray("interactions");
            for (int j = 0; j < arrayInteractions.length(); j++){
                Interaction interaction = new Interaction();
                interaction.Id = arrayInteractions.getJSONObject(j).getLong("id");
                et.Interactions.add(interaction);
            }
            event.Targets.add(et);
        }
        return event;

    }

    @Override
    public void onApiResponse(Event result) {

    }

    @Override
    public void onApiError(String error) {

    }
}
