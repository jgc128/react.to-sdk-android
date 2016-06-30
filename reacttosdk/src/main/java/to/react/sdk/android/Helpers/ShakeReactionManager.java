package to.react.sdk.android.Helpers;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

import java.util.Date;

public class ShakeReactionManager implements SensorEventListener {

    private SensorManager sManager;
    private Sensor s;

    private double gravity[] = new double[3];
    private double linearAcceleration[] = new double[3];
    private double currentAcceleration[] = new double[3];

    private int shakes = 0;
    private int prevShakes = 0;

    private final double reactionThreshold = 3.0;
    private final double negativeThreshold = -7;
    private final double positiveThreshold = -1;

    private final int reactionInterval = 500;
    private final double t = 400; // assume dT is 100

    private Date prevAccTime;
    private Date prevReactionTime;

    private ShakeListener listener;

    public ShakeReactionManager() {
        prevAccTime = new Date();
        prevReactionTime = new Date();
        gravity[0] = 0;
        gravity[1] = 0;
        gravity[2] = 0;
    }

    public void setListener(ShakeListener listener) {
        this.listener = listener;
    }

    public void init(Context ctx) {
        sManager = (SensorManager)  ctx.getSystemService(Context.SENSOR_SERVICE);
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

        shakes *= negposMul ;

        if (timeNow.getTime() - prevReactionTime.getTime() > reactionInterval)
        {
            if (shakes != prevShakes)
            {
                int evArgs = shakes;
                if (listener != null)
                    listener.onReaction(evArgs);
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
