package nl.vu.hellobeacon.sensors;

import android.content.Context;
import android.util.Log;

import interdroid.swancore.swanmain.ExpressionManager;
import interdroid.swancore.swanmain.SwanException;
import interdroid.swancore.swanmain.ValueExpressionListener;
import interdroid.swancore.swansong.ExpressionFactory;
import interdroid.swancore.swansong.ExpressionParseException;
import interdroid.swancore.swansong.TimestampedValue;
import interdroid.swancore.swansong.ValueExpression;


public class Beacon {

    private String uuid;
    private double distance;
    private boolean swanRegistered;
    private long timestamp;
    private final static int MAX_DELAY = 10000;


    Beacon(String uuid){
        super();
        this.distance = -1;
        this.uuid = uuid;
        swanRegistered = false;
        timestamp = 0;
    }

    void RegisterBeacon(final Context context) {
        if (swanRegistered) {
            return;
        }


        try{
            ExpressionManager.registerValueExpression(context, uuid,  (ValueExpression) ExpressionFactory.parse(uuid + "@beaconDistance:distance{MEAN,100}"),
                    new ValueExpressionListener() {

                        @Override
                        public void onNewValues(String id,
                                                TimestampedValue[] arg1) {
                            Log.d("Beacon","New Values:" + id + " uuid:" + uuid);
                            if(arg1 != null && arg1.length > 0){
                                for(TimestampedValue t: arg1) {
                                    Log.d("Beacon", "Value" + t.getValue().toString());
                                    distance = (double)t.getValue();
                                    timestamp = t.getTimestamp();
                                }
                            }

                        }
                    });
            Log.d("Beacon", "Registered Beacon with uuid" + uuid);
        } catch (SwanException e) {
            e.printStackTrace();
        } catch (ExpressionParseException e) {
            e.printStackTrace();
        }
    }



    public double getDistance(){
        if(timestamp < System.currentTimeMillis() - MAX_DELAY)
            return -1;

        return distance;
    }

    @Override
    public boolean equals(Object obj){
        if(obj == null){
            return false;
        }

        if(!Beacon.class.isAssignableFrom(obj.getClass())){
            return false;
        }

        final Beacon other = (Beacon) obj;

        return this.uuid == null ? other.uuid == null : this.uuid.equals(other.uuid);
    }

    @Override
    public int hashCode(){
        return uuid.hashCode();
    }

    void unRegisterBeacon(Context context) {
        ExpressionManager.unregisterExpression(context, uuid);
    }

}




