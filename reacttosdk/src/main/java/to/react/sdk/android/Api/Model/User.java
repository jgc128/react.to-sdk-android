package to.react.sdk.android.Api.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class User {
    public enum Gender {
        MA,
        FE,
        NO
    }

    public class Profile {
        @SerializedName("id")
        public long Id;

        @SerializedName("email")
        public String Email;

        @SerializedName("first_name")
        public String FirstName;

        @SerializedName("last_name")
        public String LastName;

        @SerializedName("gender")
        public Gender Gender;

        @SerializedName("city")
        public String City;

        @SerializedName("profession")
        public String Profession;

        @SerializedName("education")
        public String Education;

        @SerializedName("facebook_id")
        public Long FacebookId;

        @SerializedName("link")
        public String Link;
    }

    public class UserDemographicChoice
    {
        @SerializedName("id")
        public int CategoryId;

        @SerializedName("choice")
        public int ChoiceId;
    }

    @SerializedName("id")
    public long Id;

    @SerializedName("profiles")
    public List<Profile> Profiles;

    @SerializedName("user_demographics")
    public List<UserDemographicChoice> DemographicChoices;
}
