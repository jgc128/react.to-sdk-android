package to.react.reacttosdk;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.telecom.Call;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import to.react.reacttosdk.Models.Detail;
import to.react.reacttosdk.Models.Event.Event;

class ReactApi {

    public interface ReactListener{
        void onSuccess();
        void onError(String message);
    }

    protected GsonBuilder builder = new GsonBuilder();
    protected Gson gson = builder.create();

    protected Context context;
    protected ReactListener listener;
    protected final Handler handler;

    public ReactApi(Context context, ReactListener listener) {
        this.context = context;
        this.handler = new Handler(Looper.getMainLooper());
        this.listener = listener;

    }
    protected void Error(final String message){
        handler.post(new Runnable() {
            @Override
            public void run() {
                listener.onError(message);
            }
        });
    }

    abstract class ReactCallback implements Callback {

        protected String result;
        protected abstract void processResponse();

        @Override
        public void onFailure(okhttp3.Call call, IOException e) {
            listener.onError(e.getMessage());
        }

        @Override
        public void onResponse(okhttp3.Call call, Response response) throws IOException {
            result = response.body().string();
            Log.e("sdcs", result);
            if (!response.isSuccessful()){
                if (result.contains("detail")){
                    final Detail detail = gson.fromJson(result,Detail.class);
                    Error(response.code() + " - " + detail.detail);
                }
                else if (result.contains("[")){
                    Error(response.code() + " - " + result.substring(result.indexOf('[') + 2,
                            result.indexOf(']') - 2));
                }
                else {
                    Error(String.valueOf(response.code()));
                }
            }
            else {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        processResponse();
                    }
                });
            }
        }
    }

}

