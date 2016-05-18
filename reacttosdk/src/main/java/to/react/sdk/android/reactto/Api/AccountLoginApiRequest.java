package to.react.sdk.Api;


import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import to.react.sdk.Api.Model.Account;
import to.react.sdk.Api.Model.ApiRequestStatusResult;
import to.react.sdk.Api.Model.BaseApiPostRequest;
import to.react.sdk.Helpers.StringHelper;

public class AccountLoginApiRequest extends BaseApiPostRequest<Account> {

    Account account;

    public AccountLoginApiRequest(Account account) {
        this.account = account;
    }

    @Override
    protected String getUrl() {
        return baseUrl + "account/login/";
    }

    @Override
    protected Map<String, String> getParams() {

        Map<String, String> params = new HashMap<>();

        params.put("device_id", account.device_id);
        params.put("device_token", account.social_network);

        return params;
    }


    @Override
    protected Account getFromJson(JSONObject json) throws Exception {
        String strStatus = StringHelper.toTitleCase(json.getJSONObject("response").getString("status"));

        if (ApiRequestStatusResult.StatusResult.valueOf(strStatus) == ApiRequestStatusResult.StatusResult.Ok)
            account.device_token = StringHelper.toTitleCase(json.getJSONObject("response").getString("device_token"));

        return account;

    }

    @Override
    public void onApiResponse(Account result) {

    }


    @Override
    public void onApiError(String error) {

    }
}
