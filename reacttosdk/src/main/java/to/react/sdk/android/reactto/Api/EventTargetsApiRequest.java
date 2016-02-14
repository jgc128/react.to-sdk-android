package to.react.sdk.android.reactto.Api;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import to.react.sdk.android.reactto.Api.Model.Event;
import to.react.sdk.android.reactto.Api.Model.EventTarget;
import to.react.sdk.android.reactto.Api.Model.Interaction;
import to.react.sdk.android.reactto.Helpers.DateTimeHelper;

public class EventTargetsApiRequest extends BaseApiRequest<List<EventTarget>> {

    Event targetEvent;

    public EventTargetsApiRequest(Event event) {
        targetEvent = event;
    }

    @Override
    protected String getUrl() {
        return baseUrl + "react/events/" + targetEvent.Id + "/targets/";
    }

    @Override
    protected List<EventTarget> getFromJson(JSONObject json) throws Exception {
        List<EventTarget> list = new ArrayList<>();

        JSONArray array = json.getJSONArray("response");
        for(int i = 0 ; i < array.length() ; i++){
            EventTarget et = new EventTarget();

            et.Id  = array.getJSONObject(i).getLong("id");
            et.Name = array.getJSONObject(i).getString("name");
            et.ParentTarget = array.getJSONObject(i).getLong("parent");
            et.DateStart = DateTimeHelper.parseDateString(array.getJSONObject(i).getString("start_date"));
            et.DateEnd = DateTimeHelper.parseDateString(array.getJSONObject(i).getString("end_date"));
            et.ImageUrl = array.getJSONObject(i).getString("image_url");

            et.Interactions = new ArrayList<>();
            JSONArray arrayInteractions = array.getJSONObject(i).getJSONArray("interaction");
            for(int j = 0 ; j < arrayInteractions.length() ; j++) {
                Interaction it = new Interaction();

                it.Id  = arrayInteractions.getJSONObject(j).getLong("id");
                it.Name = arrayInteractions.getJSONObject(j).getString("name");
                it.Type = arrayInteractions.getJSONObject(j).getString("type");
                it.MinValue = arrayInteractions.getJSONObject(j).getDouble("min_value");
                it.MaxValue = arrayInteractions.getJSONObject(j).getDouble("max_value");

                et.Interactions.add(it);
            }

            list.add(et);
        }

        return list;
    }

    @Override
    public void onApiResponse(List<EventTarget> result) {

    }

    @Override
    public void onApiError(String error) {

    }
}
