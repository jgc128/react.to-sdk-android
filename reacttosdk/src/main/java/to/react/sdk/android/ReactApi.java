package to.react.sdk.android;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;
import com.neovisionaries.ws.client.WebSocketException;
import com.neovisionaries.ws.client.WebSocketFactory;
import com.neovisionaries.ws.client.WebSocketFrame;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import to.react.sdk.android.Api.Model.BaseReactMessage;
import to.react.sdk.android.Api.Model.GeolocationUpdateMessage;
import to.react.sdk.android.Api.Model.InteractionUpdateMessage;
import to.react.sdk.android.Api.Model.StatUpdateMessage;
import to.react.sdk.android.Api.Requests.BaseApiRequest;

public class ReactApi {
    protected static String logTag = "ReactToSdk";
    protected static String dateFormat = "yyyy-MM-dd'T'HH:mm:ss";

    //    private String serverAddress = "http://react.flowmaster.org/";
    private String serverAddress = "http://flowmaster.org:8080/";
    private String wsAddress = "wss://react.flowmaster.org/realtime/clients/universal_event";

    private Context mCtx;

    private WebSocket ws;
    protected Gson gson;

    public ReactApi(Context context) {
        mCtx = context;

        gson = new GsonBuilder().setDateFormat(dateFormat).create();
        initWebSocket();
    }

    public void executeRequest(BaseApiRequest request){
        Request req = request.getRequest(serverAddress);
        ReactApiSingleton.getInstance(mCtx).addToRequestQueue(req);
    }


    public void sendMessage(BaseReactMessage message) {
        String jsonMessage = gson.toJson(message);
        ws.sendText(jsonMessage);
    }
    public void onReactMessage(BaseReactMessage message) {
        Log.i(logTag, "Message:" + message.Action);
    }
    public void onReactError(String error) {
        Log.e(logTag, error);
    }
    public void onReactConnected() {
        Log.i(logTag, "Connected");
    }
    public void onReactDisconnected() {
        Log.i(logTag, "Disconnected");
    }


    private void initWebSocket() {
        WebSocketFactory factory = new WebSocketFactory();
        try {
            ws = factory.createSocket(wsAddress);
        } catch (IOException e) {
            onReactError("Error creating socket: " + e.getMessage());
            return;
        }

        ws.addListener(new WebSocketAdapter() {
            @Override
            public void onTextMessage(WebSocket websocket, String message) throws Exception {
                // Received a text message.
                JsonParser parser = new JsonParser();
                JsonObject jsonMessage = parser.parse(message).getAsJsonObject();

                if (jsonMessage != null)
                {
                    BaseReactMessage reactMessage = null;

                    String action = jsonMessage.getAsJsonPrimitive("action").getAsString();
                    if (action.equals("interaction.update")) {
                        reactMessage = gson.fromJson(jsonMessage, InteractionUpdateMessage.class);
                    }
                    if (action.equals("geolocation.update")) {
                        reactMessage = gson.fromJson(jsonMessage, GeolocationUpdateMessage.class);
                    }
                    if (action.equals("stat.update")) {
                        reactMessage = gson.fromJson(jsonMessage, StatUpdateMessage.class);
                    }

                    if (reactMessage != null)
                        onReactMessage(reactMessage);
                }
            }
            @Override
            public void onConnectError(WebSocket websocket, WebSocketException e) {
                onReactError("Failed to establish connection: " + e.getMessage());
            }
            @Override
            public void onConnected(WebSocket websocket, Map<String, List<String>> headers) throws Exception
            {
                onReactConnected();
            }
            @Override
            public void onDisconnected(WebSocket websocket,
                                       WebSocketFrame serverCloseFrame, WebSocketFrame clientCloseFrame,
                                       boolean closedByServer) throws Exception
            {
                onReactDisconnected();
            }
        });

        ws.connectAsynchronously();
    }

}
