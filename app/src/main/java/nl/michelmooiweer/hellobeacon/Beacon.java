package nl.michelmooiweer.hellobeacon;

import android.content.Context;
import android.media.TimedMetaData;
import android.util.Log;


import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import interdroid.swancore.swanmain.ExpressionManager;
import interdroid.swancore.swanmain.SwanException;
import interdroid.swancore.swanmain.ValueExpressionListener;
import interdroid.swancore.swansong.ExpressionFactory;
import interdroid.swancore.swansong.ExpressionParseException;
import interdroid.swancore.swansong.TimestampedValue;
import interdroid.swancore.swansong.ValueExpression;

public class Beacon extends SwanSensor {

    public String uuid, name;

    public double distance;
    public boolean swanRegistered;

    Beacon(String uuid){
        super();
        this.distance = -1;
        this.uuid = this.name = uuid;
        this.swanExpression =  uuid + "@beaconDistance:distance{ANY,0}";
        swanRegistered = false;
    }

    void RegisterBeacon(Context context) {
        if (swanRegistered) {
            Log.i("Beacon", "Beacon" + this.uuid + "Already Registered");
            return;
        }
        Log.d("Beacon", "Registering Beacons");

        try{
            ExpressionManager.registerValueExpression(context, uuid,  (ValueExpression) ExpressionFactory.parse(this.swanExpression),
                    new ValueExpressionListener() {

                        @Override
                        public void onNewValues(String id,
                                                TimestampedValue[] arg1) {
                            Log.d("Beacon","New Values:" + id + " uuid:" + uuid);
                            if(arg1 != null && arg1.length > 0){
                                for(TimestampedValue t: arg1)
                                    Log.d("Beacon", "Value" +t.getValue().toString());
                            }

                        }
                    });
            Log.d("Beacon", "Registered Beacon with uuid");
        } catch (SwanException e) {
            e.printStackTrace();
        } catch (ExpressionParseException e) {
            e.printStackTrace();
        }
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




