package to.react.sdk.android.Api.Requests;


import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.Map;

import to.react.sdk.android.Api.Model.User;

public abstract class AccountLogoutApiRequest extends BaseApiAuthRequest<String> {
    public AccountLogoutApiRequest(String accessToken) {
        super(accessToken);
    }

    @Override
    protected Request createRequest(String url, Response.Listener listener, Response.ErrorListener errorListener) {
        final Map<String, String> headers = getHeaders();

        StringRequest request = new StringRequest(Request.Method.DELETE, url, listener, errorListener){
            public Map<String, String> getHeaders() {
                return headers;
            }
        };
        return  request;
    }

    @Override
    protected String getRequestUrl() {
        return "account/logout/";
    }

    @Override
    protected String getObject(String json) throws Exception {
        return "ok";
    }

}
