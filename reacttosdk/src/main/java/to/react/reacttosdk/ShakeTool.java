package to.react.reacttosdk;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import java.util.Date;

public class ShakeTool implements SensorEventListener {

    public interface ImpactListener{
        void onImpactUpdate();
        void onReactionValueUpdate();
    }

    private SensorManager sManager;
    private Sensor s;

    private double gravity[] = new double[3];
    private double linearAcceleration[] = new double[3];
    private double currentAcceleration[] = new double[3];

    private int shakes = 0;
    private int prevShakes = 0;

    private final double reactionThreshold = 4.0;
    private final double negativeThreshold = -7;
    private final double positiveThreshold = -1;

    private final int reactionInterval = 400;
    private final double t = 300; // assume dT is 100

    private Date prevAccTime;
    private Date prevReactionTime;

    private ShakeListener listener;

    private Thread process;
    private int reactionValue;
    private boolean canNegative;


    public ShakeTool(Context context, ShakeListener listener, boolean canNegative) {
        this.canNegative = canNegative;
        prevAccTime = new Date();
        prevReactionTime = new Date();
        gravity[0] = 0;
        gravity[1] = 0;
        gravity[2] = 0;

        this.listener = listener;

        sManager = (SensorManager)  context.getSystemService(Context.SENSOR_SERVICE);
        s = sManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        register();
    }

    public void register() {
        sManager.registerListener(this, s, SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void deregister()  {
        sManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        currentAcceleration[0] = sensorEvent.values[0];
        currentAcceleration[1] = sensorEvent.values[1];
        currentAcceleration[2] = sensorEvent.values[2];


        Date timeNow = new Date();

        double alpha = 0.8;
        long dT = timeNow.getTime() - prevAccTime.getTime();
        if (dT < 5000)
            alpha = t / (t + dT);

        gravity[0] = alpha * gravity[0] + (1 - alpha) * currentAcceleration[0];
        gravity[1] = alpha * gravity[1] + (1 - alpha) * currentAcceleration[1];
        gravity[2] = alpha * gravity[2] + (1 - alpha) * currentAcceleration[2];

        linearAcceleration[0] = currentAcceleration[0] - gravity[0];
        linearAcceleration[1] = currentAcceleration[1] - gravity[1];
        linearAcceleration[2] = currentAcceleration[2] - gravity[2];

        int negposMul = 0;
        if (currentAcceleration[2] < negativeThreshold)
        {
            negposMul  = -1;
        }
        if (currentAcceleration[2] > positiveThreshold)
        {
            negposMul  = 1;
        }

        if (Math.abs(linearAcceleration[0]) > reactionThreshold)
        {
            shakes += 1;
        }

        if (canNegative)
            shakes *= negposMul ;

        if (timeNow.getTime() - prevReactionTime.getTime() > reactionInterval)
        {
            Log.e("----------------", shakes + " " + prevShakes);
            if (shakes != prevShakes)
            {
                int evArgs = shakes;
                if (listener != null)
                    listener.onReaction((evArgs/2));
            }

            prevShakes = shakes;
            shakes = 0;
            prevReactionTime = timeNow;
        }

        prevAccTime = timeNow;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {}


    public static interface ShakeListener {
        public void onReaction(int value);
    }
}
