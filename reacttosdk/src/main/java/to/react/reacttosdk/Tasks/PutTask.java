package to.react.reacttosdk.Tasks;

import android.util.Log;

import java.io.IOException;

import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class PutTask {
    private Exception exception;

    private MediaType JSON;
    private OkHttpClient client;
    private String token="";
    private String url;
    private String json;
    private Callback callback;

    public PutTask(String url, String json, Callback callback, String token) throws IOException {
        this.url = url;
        this.token = token;
        this.json = json;
        this.callback = callback;
        client = new OkHttpClient();
        JSON = MediaType.parse("application/json; charset=utf-8");
        post();
    }
    public PutTask(String url, String json, Callback callback) throws IOException {
        this.url = url;
        this.json = json;
        this.callback = callback;
        client = new OkHttpClient();
        JSON = MediaType.parse("application/json; charset=utf-8");
        post();
    }

    private Request generateRequest(){
        Request.Builder request = new Request.Builder();
        RequestBody body = RequestBody.create(JSON, json);
        request.addHeader("Content-Type", "application/x-www-form-urlencoded");
        Log.e("sss", token);
        if (token.length() > 1) {
            request.addHeader("authorization", "token " + token);
        }
        request.url(url);
        request.put(body);
        return request.build();
    }

    private void post() throws IOException {
        Request request = generateRequest();
        client.newCall(request).enqueue(callback);
    }
}
