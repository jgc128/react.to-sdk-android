package to.react.sdk.android.Api.Model;


import com.google.gson.annotations.SerializedName;

public class ReactionMessage extends BaseReactMessage {
    public ReactionMessage()
    {
        Action = "interaction.update";
        Latitude = null;
        Longitude = null;
    }
    public ReactionMessage(String DeviceId, int Value)
    {
        this();

        this.DeviceId = DeviceId;
        this.Value = Value;
    }
    public ReactionMessage(String DeviceId, int Value, Integer Latitude, Integer Longitude)
    {
        this(DeviceId, Value);

        this.Latitude = Latitude;
        this.Longitude = Longitude;
    }

    @SerializedName("device_id")
    public String DeviceId;

    @SerializedName("value")
    public int Value;

    @SerializedName("lat")
    public Integer Latitude;

    @SerializedName("lon")
    public Integer Longitude;
}
