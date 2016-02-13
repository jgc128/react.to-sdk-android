package to.react.sdk.android.reactto.Api;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
        List<Event> list = new ArrayList<Event>();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US);

        JSONArray array = json.getJSONArray("response");
        for(int i = 0 ; i < array.length() ; i++){
            Event e = new Event();

            e.Id  = array.getJSONObject(i).getInt("id");
            e.Name = array.getJSONObject(i).getString("name");
            e.ParentEventId = array.getJSONObject(i).getInt("parent");
            e.DateStart = dateFormat.parse(array.getJSONObject(i).getString("start_date"));
            e.DateEnd = dateFormat.parse(array.getJSONObject(i).getString("end_date"));
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
