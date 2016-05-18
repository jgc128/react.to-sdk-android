package to.react.sdk.Api;


import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import to.react.sdk.Api.Model.Account;
import to.react.sdk.Api.Model.BaseApiPostRequest;
import to.react.sdk.Helpers.StringHelper;

public class AccountFriendsApiRequest extends BaseApiPostRequest<List<Account>> {

    Account account;

    public AccountFriendsApiRequest(Account account) {
        this.account = account;
    }

    @Override
    protected String getUrl() {
        return baseUrl + "react/account/friends/";
    }

    @Override
    protected Map<String, String> getParams() {

        Map<String, String> params = new HashMap<>();

        params.put("device_id", account.device_id);
        params.put("device_token", account.social_network);

        return params;
    }


    @Override
    protected List<Account> getFromJson(JSONObject json) throws Exception {

        List<Account> list = new ArrayList<>();

        JSONArray array = json.getJSONArray("response");
        for (int i = 0; i < array.length(); i++) {
            Account account = new Account();

            account.username = StringHelper.toTitleCase(json.getJSONObject("response").getString("status"));
            account.first_name = StringHelper.toTitleCase(json.getJSONObject("response").getString("status"));
            account.last_name = StringHelper.toTitleCase(json.getJSONObject("response").getString("status"));
            account.age = (json.getJSONObject("response").getInt("id"));
            String strStatus = StringHelper.toTitleCase(json.getJSONObject("response").getString("gender"));
            account.gender = Account.Gender.valueOf(strStatus);

            list.add(account);
        }

        return list;
    }

    @Override
    public void onApiResponse(List<Account> result) {

    }


    @Override
    public void onApiError(String error) {

    }
}
