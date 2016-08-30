package to.react.reacttosdk.Tasks;

import java.io.IOException;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class DeleteTask {

    private Exception exception;

    private OkHttpClient client;
    private String token="";
    private String url;
    private Callback callback;

    public DeleteTask(String url, Callback callback, String token) throws IOException {
        this.url = url;
        this.token = token;
        this.callback = callback;
        client = new OkHttpClient();
        delete();
    }

    public DeleteTask(String url, Callback callback) throws IOException {
        this.url = url;
        this.callback = callback;
        client = new OkHttpClient();
        delete();
    }

    private Request generateRequest(){
        Request.Builder request = new Request.Builder();
        request.addHeader("Content-Type", "application/x-www-form-urlencoded");
        if (token.length() > 1)
            request.addHeader("authorization", "token " + token);
        request.url(url);
        request.delete();
        return request.build();
    }


    public void delete() throws IOException {
        Request request =  generateRequest();
        client.newCall(request).enqueue(callback);
    }
}
