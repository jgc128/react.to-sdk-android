package to.react.reacttosdk;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;
import com.neovisionaries.ws.client.WebSocketException;
import com.neovisionaries.ws.client.WebSocketFactory;
import com.neovisionaries.ws.client.WebSocketFrame;
import com.neovisionaries.ws.client.WebSocketListener;
import com.neovisionaries.ws.client.WebSocketState;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

import to.react.reacttosdk.Helpers.DeviceHelper;

public class ReactionApi {

    private final Handler handler;

    public interface ReactionListener {
        void onInteractionUpdate(long interaction_id, long value);
        void onStatUpdate(long interaction, int devices_all, int count_all, int sum_all, int devices_current);
    }

    String wss_addr = "wss://react.flowmaster.org/realtime/clients/universal_event/";
    WebSocket ws = null;
    String device;
    ReactionListener reactionListener;

    public ReactionApi(Context context, ReactionListener reactionListener, int eventId) {
        this.reactionListener = reactionListener;
        device = new DeviceHelper().deviceID(context);
        try {
            ws = new WebSocketFactory().createSocket(wss_addr + eventId);
            ws.addListener(adapter);
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.handler = new Handler(Looper.getMainLooper());
    }

    public void connect(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ws.connect();
                } catch (WebSocketException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }
    public WebSocketState о(){
        return ws.getState();
    }
    public void disconnect(){
        ws.disconnect();
    }

    public void sendReaction(long interaction_id, int value, double lat, double lng){
        final String s = "{\"action\":\"interaction.update\",\"interaction_id\": " +
                "[" + interaction_id + "]" + ",\"device_id\": \"" + device
                + "\",\"value\": "
                + value + "," +

                "  \"lat\": \"" + lat + "\",\n" +
                "  \"lon\": \"" + lng + "\"" + "}";
        ws.sendText(s);
    }

    public void sendReaction(long interaction_id, int value){
        final String s = "{\"action\":\"interaction.update\",\"interaction_id\": " +
                "[" + interaction_id + "]" + ",\"device_id\": \"" + device
                + "\",\"value\": "
                + value + "}";
        ws.sendText(s);
    }


    WebSocketAdapter adapter = new WebSocketAdapter() {
        @Override
        public void onTextMessage(WebSocket websocket, String message) throws Exception {
            JSONObject json = new JSONObject(message);
            String action = json.getString("action");
            JSONArray interactions = json.getJSONArray("interaction_id");

            final long interaction = interactions.getInt(0);

            if (action.contains("stat.update")) {
                final int devices_all = json.getInt("devices_all");
                final int count_all = json.getInt("count_all");
                final int sum_all = json.getInt("sum_all");
                final int devices_current = json.getInt("devices_current");
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        reactionListener.onStatUpdate(interaction, devices_all, count_all, sum_all, devices_current);
                    }
                });
            } else if (action.contains("interaction.update")) {
                final long intervalue = json.getLong("value");
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        reactionListener.onInteractionUpdate(interaction, intervalue);
                    }
                });

            }
        }
    };
}
