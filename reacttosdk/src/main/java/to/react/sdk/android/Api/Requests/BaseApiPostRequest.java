package to.react.sdk.android.Api.Requests;


import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.JsonElement;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import to.react.sdk.android.Api.Requests.BaseApiRequest;

public abstract class BaseApiPostRequest<T> extends BaseApiRequest<T> {

    @Override
    protected Request createRequest(String url, Response.Listener listener, Response.ErrorListener errorListener) {
        final Map<String, String> headers = getHeaders();

        StringRequest request = new StringRequest(Request.Method.POST,url, listener, errorListener){
            @Override
            public String getBodyContentType() {
                return PROTOCOL_CONTENT_TYPE;
            }
            @Override
            public byte[] getBody() {
                JsonElement data = getData();
                String post_data = data.toString();
                try {
                    byte[] body = post_data.getBytes(PROTOCOL_CHARSET);
                    return body;
                } catch (UnsupportedEncodingException e) {
                    Log.e(logTag, e.getMessage());
                    return null;
                }
            }
            public Map<String, String> getHeaders() {
                return headers;
            }
        };
        return  request;
    }

    abstract protected JsonElement getData();
}
