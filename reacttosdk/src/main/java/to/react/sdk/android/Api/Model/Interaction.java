package to.react.sdk.android.Api.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Interaction {
    @SerializedName("id")
    public long Id;
    @SerializedName("name")
    public String Name;

    @SerializedName("activated")
    public boolean IsActivated;

    @SerializedName("unique")
    public boolean IsUniqueReaction;

    @SerializedName("min_value")
    public double MinValue;

    @SerializedName("max_value")
    public double MaxValue;

    @SerializedName("demographics")
    public List<DemographicCategory.DemographicChoice> Demographics;
}
