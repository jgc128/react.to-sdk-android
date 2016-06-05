package to.react.sdk.android.Api.Model;


import com.google.gson.annotations.SerializedName;

public class ProfileUpdate {
    public ProfileUpdate()
    {
        Email = "";
        FirstName = "";
        LastName = "";
        Age = 0;
        Gender = User.Gender.NO;
        City = "";
        Profession = "";
        Education = "";
    }

    @SerializedName("email")
    public String Email;

    @SerializedName("first_name")
    public String FirstName;

    @SerializedName("last_name")
    public String LastName;

    @SerializedName("age")
    public int Age;

    @SerializedName("gender")
    public User.Gender Gender;

    @SerializedName("city")
    public String City;

    @SerializedName("profession")
    public String Profession;

    @SerializedName("education")
    public String Education;
}
