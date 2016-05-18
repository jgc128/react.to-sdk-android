package to.react.sdk.android.Api;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import to.react.sdk.android.Api.Model.App;
import to.react.sdk.android.Api.Model.Event;
import to.react.sdk.android.Helpers.DateTimeHelper;

public class AppEventsApiRequest extends BaseApiRequest<List<Event>> {

    App targetApp;

    public AppEventsApiRequest(App app) {
        targetApp = app;
    }

    @Override
    protected String getUrl() {
        return baseUrl + "universal_event/apps/" + targetApp.Id + "/";
    }

    @Override
    protected List<Event> getFromJson(JSONObject json) throws Exception {
        List<Event> list = new ArrayList<>();

        JSONArray array = json.getJSONArray("response");
        for(int i = 0 ; i < array.length() ; i++){
            Event e = new Event();

            e.Id  = array.getJSONObject(i).getLong("id");
            e.Name = array.getJSONObject(i).getString("name");
            e.ParentEventId = array.getJSONObject(i).getLong("parent");
            e.DateStart = DateTimeHelper.parseDateString(array.getJSONObject(i).getString("start_date"));
            e.DateEnd = DateTimeHelper.parseDateString(array.getJSONObject(i).getString("end_date"));
            e.ImageUrl = array.getJSONObject(i).getString("image_url");
            e.Link = array.getJSONObject(i).getString("link");
            e.IsNegativeReactions = array.getJSONObject(i).getBoolean("negative_reactions");

            list.add(e);
        }

        return list;
    }

    @Override
    public void onApiResponse(List<Event> result) {

    }

    @Override
    public void onApiError(String error) {

    }
}
