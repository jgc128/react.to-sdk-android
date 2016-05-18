package to.react.sdk.android.Api;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import to.react.sdk.android.Api.Model.App;


public class AppsApiRequest extends BaseApiRequest<List<App>> {

    @Override
    protected String getUrl() {
        return baseUrl + "react/apps";
    }

    @Override
    protected List<App> getFromJson(JSONObject json) throws Exception {

        List<App> list = new ArrayList<>();

        JSONArray array = json.getJSONArray("response");
        for(int i = 0 ; i < array.length() ; i++){
            App a = new App();
            a.Id  = array.getJSONObject(i).getLong("id");
            a.Name = array.getJSONObject(i).getString("name");
            list.add(a);
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
