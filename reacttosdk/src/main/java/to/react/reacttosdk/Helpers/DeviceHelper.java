package to.react.reacttosdk.Helpers;

import android.content.Context;

public class DeviceHelper {
    public String deviceID(Context context) {
        return android.provider.Settings.Secure.getString(
                context.getContentResolver(),
                android.provider.Settings.Secure.ANDROID_ID);
    }
}