package to.react.sdk.android.reactto;


import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import to.react.sdk.android.reactto.Api.Model.BaseReactMessage;
import to.react.sdk.android.reactto.Api.Model.Event;
import to.react.sdk.android.reactto.Api.Model.Interaction;
import to.react.sdk.android.reactto.Api.Model.InteractionUpdateMessage;
import to.react.sdk.android.reactto.Api.Model.UsersCounterUpdateMessage;
import to.react.sdk.android.reactto.Helpers.DateTimeHelper;

public class ReactOrtc extends ReactBaseOrtc {
    public enum UsersCounterType
    {
        Connect,
        Disconnect
    }

    public ReactOrtc(String apiKey, String sendChannel, String receiveChannel) {
        super(apiKey, sendChannel, receiveChannel);
    }
    public ReactOrtc(String apiKey, String sendChannel) {
        super(apiKey, sendChannel);
    }

    @Override
    protected void onMessage(String message) {
        try {
            JSONObject jsonMsg = new JSONObject(message);

            String action = jsonMsg.getString("action");

            if (action.equals("interaction.update")) {
                onInteractionUpdateMessage(jsonMsg);
            }

            if (action.equals("users_counter.update")) {
                onUsersCounterUpdateMessage(jsonMsg);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    protected void onInteractionUpdateMessage(JSONObject jsonMsg) throws JSONException {
        InteractionUpdateMessage msg = new InteractionUpdateMessage();

        msg.Action = jsonMsg.getString("action");
        msg.InteractionId = jsonMsg.getLong("interaction_id");
        msg.InteractionType = jsonMsg.getString("interaction_type");
        msg.Value = jsonMsg.getDouble("value");

        if (jsonMsg.has("mode")) {
            msg.Mode = InteractionUpdateMessage.InteractionMode.valueOf(jsonMsg.getString("mode"));
        } else {
            msg.Mode = InteractionUpdateMessage.InteractionMode.Positive;
        }

        onReactMessage(msg);
    }

    protected void onUsersCounterUpdateMessage(JSONObject jsonMsg) throws JSONException {
        UsersCounterUpdateMessage msg = new UsersCounterUpdateMessage();

        msg.Action = jsonMsg.getString("action");
        msg.EventId = jsonMsg.getLong("event_id");
        msg.Users = jsonMsg.getLong("users");
        msg.Male = jsonMsg.getLong("male");
        msg.Female = jsonMsg.getLong("female");

        onReactMessage(msg);
    }

//    public void onInteractionUpdate(InteractionUpdateMessage message) {
//
//    }
//
//    public void onUsersCounterUpdate(UsersCounterUpdateMessage message) {
//
//    }

    public void onReactMessage(BaseReactMessage message)
    {

    }

    // TODO: refactor "send*" ?
    public void sendReaction(String deviceId, Interaction interaction, double value) {

        JSONObject jsonMsg = null;

        try {
            jsonMsg = new JSONObject();

            jsonMsg.put("action", "reaction");
            jsonMsg.put("device_id", deviceId);
            jsonMsg.put("value", value);
            jsonMsg.put("interaction_id", interaction.Id);
            jsonMsg.put("interaction_type", interaction.Type);

            String timeStamp = DateTimeHelper.formatDateString(new Date());
            jsonMsg.put("timestamp", timeStamp);

            sendMessage(jsonMsg.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void sendUsersCounter(String deviceId, Event event, UsersCounterType usersCounterType)
    {
        JSONObject jsonMsg = null;

        try {
            jsonMsg = new JSONObject();

            jsonMsg.put("action", "users_counter");
            jsonMsg.put("device_id", deviceId);
            jsonMsg.put("event_id", event.Id);
            jsonMsg.put("type", usersCounterType.toString().toLowerCase());

            sendMessage(jsonMsg.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
