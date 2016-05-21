package to.react.sdk.android.Api.Requests;


import java.util.HashMap;
import java.util.Map;

public abstract class BaseApiAuthRequest<T> extends BaseApiRequest<T> {

    protected String accessToken;

    public BaseApiAuthRequest(String accessToken) {
        this.accessToken = accessToken;
    }

    protected Map<String, String> getHeaders(){
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Token " + accessToken);
        return headers;
    }

}
