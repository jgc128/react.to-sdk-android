package to.react.sdk.android.reactto.Api;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import to.react.sdk.android.reactto.Api.Model.ApiRequestStatusResult;
import to.react.sdk.android.reactto.Api.Model.App;
import to.react.sdk.android.reactto.Api.Model.BaseApiPostRequest;

public class AddEventByLinkApiRequest extends BaseApiPostRequest<ApiRequestStatusResult> {
    App targetApp;
    String eventLink;

    public AddEventByLinkApiRequest(App app, String eventLink) {
        targetApp = app;
        this.eventLink = eventLink;
    }

    @Override
    protected String getUrl() {
        return baseUrl + "universal_event/apps/" + targetApp.Id + "/events/add_by_link/";
    }

    @Override
    protected Map<String, String> getParams() {
        Map<String, String> params = new HashMap<>();

        params.put("link", eventLink);

        return params;
    }

    @Override
    protected ApiRequestStatusResult getFromJson(JSONObject json) throws Exception {
        String strStatus = json.getJSONObject("response").getString("status");

        ApiRequestStatusResult result = new ApiRequestStatusResult();
        result.setStatus(strStatus);

        return result;
    }

    @Override
    public void onApiResponse(ApiRequestStatusResult result) {

    }

    @Override
    public void onApiError(String error) {

    }
}
