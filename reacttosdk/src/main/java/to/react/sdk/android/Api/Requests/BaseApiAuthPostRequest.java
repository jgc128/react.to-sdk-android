package to.react.sdk.android.Api.Requests;


import java.util.HashMap;
import java.util.Map;

public abstract class BaseApiAuthPostRequest<T> extends BaseApiPostRequest<T>{
    protected String accessToken;

    public BaseApiAuthPostRequest(String accessToken) {
        this.accessToken = accessToken;
    }
    public BaseApiAuthPostRequest(String accessToken, int method) {
        super(method);
        this.accessToken = accessToken;
    }

    protected Map<String, String> getHeaders(){
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Token " + accessToken);
        return headers;
    }
}
