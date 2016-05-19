package to.react.sdk.android.Api.Requests;

//public class AccountApiRequest  extends BaseApiPostRequest<Account> {
//    Account account;
//
//    public AccountApiRequest(Account account) {
//        this.account = account;
//    }
//
//    @Override
//    protected String getUrl() {
//        return baseUrl + "react/account/";
//    }
//
//    @Override
//    protected Map<String, String> getParams() {
//
//        Map<String, String> params = new HashMap<>();
//
//        params.put("device_id", account.device_id);
//        params.put("device_token", account.social_network);
//
//        return params;
//    }
//
//
//    @Override
//    protected Account getFromJson(JSONObject json) throws Exception {
//
//        account.username = StringHelper.toTitleCase(json.getJSONObject("response").getString("status"));
//        account.first_name = StringHelper.toTitleCase(json.getJSONObject("response").getString("status"));
//        account.last_name = StringHelper.toTitleCase(json.getJSONObject("response").getString("status"));
//        account.age =  (json.getJSONObject("response").getInt("id"));
//        String strStatus = StringHelper.toTitleCase(json.getJSONObject("response").getString("gender"));
//        account.gender = Account.Gender.valueOf(strStatus);
//
//        return account;
//
//    }
//
//    @Override
//    public void onApiResponse(Account result) {
//
//    }
//
//
//    @Override
//    public void onApiError(String error) {
//
//    }
//}
