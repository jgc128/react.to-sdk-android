package to.react.sdk.android.Api;


import org.json.JSONObject;

import to.react.sdk.android.Api.Model.Event;
import to.react.sdk.android.Api.Model.Interaction;
import to.react.sdk.android.Api.Model.InteractionUpdateMessage;

public class InteractionUpdateApiRequest  extends BaseApiRequest<InteractionUpdateMessage>{

    Interaction targeInteraction;

    public InteractionUpdateApiRequest(Interaction interaction) {
        targeInteraction = interaction;
    }

    @Override
    protected String getUrl() {
        return baseUrl + "avg_reactions/" + targeInteraction.Id + "/";
    }

    @Override
    protected InteractionUpdateMessage getFromJson(JSONObject jsonMsg) throws Exception {
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

        return msg;
    }

    @Override
    public void onApiResponse(InteractionUpdateMessage result) {

    }

    @Override
    public void onApiError(String error) {

    }
}
