package to.react.sdk.android.Api.Requests;


import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

//import to.react.sdk.android.Api.Model.Account;
//import to.react.sdk.android.Helpers.StringHelper;
//
//public class AccountGeoNearApiRequest extends BaseApiRequest<List<Account>> {
//
//    Account account;
//
//    public AccountGeoNearApiRequest(Account account) {
//        this.account = account;
//    }
//
//    @Override
//    protected String getUrl() {
//        return baseUrl + "react/account/geo?type=near";
//    }
//
//    @Override
//    protected List<Account> getFromJson(JSONObject json) throws Exception {
//
//        List<Account> list = new ArrayList<>();
//
//        JSONArray array = json.getJSONArray("response");
//        for (int i = 0; i < array.length(); i++) {
//            Account account = new Account();
//
//            account.username = StringHelper.toTitleCase(json.getJSONObject("response").getString("status"));
//            account.first_name = StringHelper.toTitleCase(json.getJSONObject("response").getString("status"));
//            account.last_name = StringHelper.toTitleCase(json.getJSONObject("response").getString("status"));
//
//            list.add(account);
//        }
//
//        return list;
//    }
//
//    @Override
//    public void onApiResponse(List<Account> result) {
//
//    }
//
//
//    @Override
//    public void onApiError(String error) {
//
//    }
//}
