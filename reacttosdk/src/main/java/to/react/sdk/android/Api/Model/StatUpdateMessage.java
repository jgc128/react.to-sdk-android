package to.react.sdk.android.Api.Model;


import com.google.gson.annotations.SerializedName;

public class StatUpdateMessage extends BaseReactMessage {
    @SerializedName("interaction_id")
    public long InteractionId;

    @SerializedName("count_all")
    public long CountAll;

    @SerializedName("sum_all")
    public long SumAll;

    @SerializedName("devices_all")
    public long DevicesAll;

    @SerializedName("devices_current")
    public long DevicesCurrent;
}
