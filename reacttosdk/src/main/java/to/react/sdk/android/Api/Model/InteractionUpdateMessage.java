package to.react.sdk.android.Api.Model;


import com.google.gson.annotations.SerializedName;

public class InteractionUpdateMessage extends BaseReactMessage {
    @SerializedName("interaction_id")
    public long InteractionId;

    @SerializedName("value")
    public int Value;

}
