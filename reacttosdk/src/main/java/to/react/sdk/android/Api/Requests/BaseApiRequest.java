package to.react.sdk.android.Api.Requests;


import android.annotation.TargetApi;
import android.os.Build;
import android.util.ArrayMap;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Based on https://developer.android.com/training/volley/request-custom.html
public abstract class BaseApiRequest<T> {
    protected static final String PROTOCOL_CHARSET = StandardCharsets.UTF_8.displayName();//"utf-8";
    protected static final String PROTOCOL_CONTENT_TYPE = String.format("application/json; charset=%s", PROTOCOL_CHARSET);
    protected static String dateFormat = "yyyy-MM-dd'T'HH:mm:ss";

    protected Gson gson;

    protected static String logTag = "ReactToSdk";

    public BaseApiRequest(){
        gson = new GsonBuilder().setDateFormat(dateFormat).create();
    }

    public Request getRequest(String baseUrl) {
        String url = baseUrl + getRequestUrl();
        Response.Listener listener = createListener();
        Response.ErrorListener errorListener = createErrorListener();
        Request request = createRequest(url, listener, errorListener);
        return request;
    }

    protected Response.Listener createListener() {
        Response.Listener<String> listener =  new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    T result = getObject(response);
                    onApiResponse(result);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(logTag, e.getMessage());
                    onApiError(e.getMessage());
                }
            }
        };
        return listener;
    }
    protected Response.ErrorListener createErrorListener() {
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String apiErrorMessage = new String(error.networkResponse.data, StandardCharsets.UTF_8);
                onApiError(apiErrorMessage);
            }
        };

        return errorListener;
    }


    protected Request createRequest(String url, Response.Listener listener, Response.ErrorListener errorListener) {
        final Map<String, String> headers = getHeaders();

        StringRequest request = new StringRequest(url, listener, errorListener){
            public Map<String, String> getHeaders() {
                return headers;
            }
        };
        return  request;
    }

    protected String getRequestUrl() {
        return "";
    }

    protected Map<String, String> getHeaders(){
        return new HashMap<>();
    }

    protected Type getType() {
        return new TypeToken<T>() {}.getType();
    }

    protected T getObject(String json) throws Exception {
        JsonParser parser = new JsonParser();
        JsonObject o = parser.parse(json).getAsJsonObject();

        return getObject(o);
    }

    protected T getObject(JsonElement json) throws Exception {
        Type type = getType();
        T result = gson.fromJson(json, type);

        return result;
    }

    public void onApiError(String error) {
        Log.e(logTag, error);
    }

    abstract public void onApiResponse(T result);

}
