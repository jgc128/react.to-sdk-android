package to.react.sdk.android.reactto.Api;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import to.react.sdk.android.reactto.Api.Model.ApiRequestStatusResult;
import to.react.sdk.android.reactto.Api.Model.App;
import to.react.sdk.android.reactto.Api.Model.BaseApiPostRequest;
import to.react.sdk.android.reactto.Api.Model.NewEvent;
import to.react.sdk.android.reactto.Helpers.DateTimeHelper;
import to.react.sdk.android.reactto.Helpers.StringHelper;


public class AddEventApiRequest extends BaseApiPostRequest<ApiRequestStatusResult> {

    App targetApp;
    NewEvent targetEvent;

    public AddEventApiRequest(App app, NewEvent event) {
        targetApp = app;
        targetEvent = event;
    }

    @Override
    protected String getUrl() {
        return baseUrl + "universal_event/apps/" + targetApp.Id + "/events/add/";
    }

    @Override
    protected Map<String, String> getParams() {

        Map<String, String> params = new HashMap<>();

        params.put("name", targetEvent.Name);
        params.put("description", targetEvent.Description);
        params.put("start_date", Long.toString(DateTimeHelper.DateToUnixTimeStamp(targetEvent.DateStart)));
        params.put("end_date", Long.toString(DateTimeHelper.DateToUnixTimeStamp(targetEvent.DateEnd)));
        params.put("negative_reactions", Boolean.toString(targetEvent.IsNegativeReactions));

        params.put("is_two", targetEvent.IsTwoTarget ? "1" : "0");

        if (targetEvent.IsTwoTarget) {
            params.put("target_one", targetEvent.TargetOne);
            params.put("target_two", targetEvent.TargetTwo);
        }

        return params;
    }


    @Override
    protected ApiRequestStatusResult getFromJson(JSONObject json) throws Exception {
        String strStatus = StringHelper.toTitleCase(json.getJSONObject("response").getString("status"));

        ApiRequestStatusResult result = new ApiRequestStatusResult();
        result.Status = ApiRequestStatusResult.StatusResult.valueOf(strStatus);

        return result;

    }

    @Override
    public void onApiResponse(ApiRequestStatusResult result) {

    }

    @Override
    public void onApiError(String error) {

    }
}
