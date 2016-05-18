package to.react.sdk.android.Api;


import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import to.react.sdk.android.Api.Model.Account;
import to.react.sdk.android.Api.Model.ApiRequestStatusResult;
import to.react.sdk.android.Api.Model.BaseApiPostRequest;
import to.react.sdk.android.Api.Model.Event;
import to.react.sdk.android.Helpers.StringHelper;

public class AccountLikeEventApiRequest extends BaseApiPostRequest<ApiRequestStatusResult> {

    Account account;
    Event event;

    public AccountLikeEventApiRequest(Account account, Event event) {
        this.account = account;
        this.event = event;
    }

    @Override
    protected String getUrl() {
        return baseUrl + "react/account/like/" + event.Id;
    }

    @Override
    protected Map<String, String> getParams() {

        Map<String, String> params = new HashMap<>();

        params.put("access_token", account.access_token);
        params.put("device_id", account.device_id);

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