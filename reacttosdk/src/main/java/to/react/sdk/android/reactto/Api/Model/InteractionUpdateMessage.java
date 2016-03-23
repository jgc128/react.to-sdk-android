package to.react.sdk.android.reactto.Api.Model;


public class InteractionUpdateMessage extends BaseReactMessage {

    public enum InteractionMode
    {
        Negative,
        Positive,
        Neutral
    }

    public double Value;
    public InteractionMode Mode;
    public long InteractionId;
    public String InteractionType;
}
