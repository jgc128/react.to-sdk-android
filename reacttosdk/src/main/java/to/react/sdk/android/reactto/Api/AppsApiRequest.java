package to.react.sdk.android.reactto.Api;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class AppsApiRequest extends BaseApiRequest<List<App>> {

    @Override
    protected String getUrl() {
        return baseUrl + "react/apps";
    }

    @Override
    protected List<App> getFromJson(JSONObject json) {

        List<App> list = new ArrayList<App>();

        try {
            JSONArray array = json.getJSONArray("response");
            for(int i = 0 ; i < array.length() ; i++){
                App a = new App();
                a.Id  = array.getJSONObject(i).getInt("id");
                a.Name = array.getJSONObject(i).getString("name");
                list.add(a);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public void onApiResponse(List<App> result) {

    }

    @Override
    public void onApiError(String error) {

    }


}
