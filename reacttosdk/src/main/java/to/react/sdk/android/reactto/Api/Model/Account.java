package to.react.sdk.Api.Model;

public class Account {
    public enum Gender {
        MA,
        FE,
        NO
    }
    public String device_id;
    public String access_token;
    public String social_network;
    public String device_token;
    public int age;
    public Gender gender;
    public String first_name;
    public String last_name;
    public String username;

}
