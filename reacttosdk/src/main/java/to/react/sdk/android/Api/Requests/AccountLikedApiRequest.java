package to.react.sdk.android.Api.Requests;


//public class AccountLikedApiRequest extends BaseApiPostRequest<List<Integer>> {
//
//    Account account;
//
//    public AccountLikedApiRequest(Account account) {
//        this.account = account;
//    }
//
//    @Override
//    protected String getUrl() {
//        return baseUrl + "react/account/liked/";
//    }
//
//    @Override
//    protected Map<String, String> getParams() {
//
//        Map<String, String> params = new HashMap<>();
//
//        params.put("access_token", account.access_token);
//        params.put("device_id", account.device_id);
//
//        return params;
//    }
//
//
//    @Override
//    protected List<Integer> getFromJson(JSONObject json) throws Exception {
//        List<Integer> list = new ArrayList<>();
//
//        JSONArray array = json.getJSONArray("response");
//        for (int i = 0; i < array.length(); i++) {
//            list.add(json.getJSONObject("response").getInt("id"));
//        }
//
//        return list;
//    }
//
//    @Override
//    public void onApiResponse(List<Integer> result) {
//
//    }
//
//
//    @Override
//    public void onApiError(String error) {
//
//    }
//}
