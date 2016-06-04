package to.react.sdk.android.Api.Model;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DemographicCategory {
    public class DemographicChoice {
        @SerializedName("id")
        public long Id;

        @SerializedName("value")
        public String Value;
    }

    @SerializedName("id")
    public long Id;

    @SerializedName("name")
    public String Name;

    @SerializedName("choices")
    public List<DemographicChoice> Choices;
}
