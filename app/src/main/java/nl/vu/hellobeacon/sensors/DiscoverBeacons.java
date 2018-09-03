package nl.vu.hellobeacon.sensors;


import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import interdroid.swancore.swanmain.ExpressionManager;
import interdroid.swancore.swanmain.SwanException;
import interdroid.swancore.swanmain.ValueExpressionListener;
import interdroid.swancore.swansong.ExpressionFactory;
import interdroid.swancore.swansong.ExpressionParseException;
import interdroid.swancore.swansong.TimestampedValue;
import interdroid.swancore.swansong.ValueExpression;
import nl.vu.hellobeacon.views.BeaconViewModel;

public class DiscoverBeacons {

    private static DiscoverBeacons INSTANCE;
    private final String[] beaconTypes = new String[]{"ibeaconuuid", "eddystoneuid", "altbeacon", "estimotenearable"};
    public String id, swanExpression;
    BeaconViewModel beaconViewModel;

    private DiscoverBeacons() {
        swanExpression = "self@beacon_discovery:%s{ANY, 10}";

    }

    public static DiscoverBeacons getDiscoverBeacons() {
        if (INSTANCE == null) {
            synchronized (DiscoverBeacons.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DiscoverBeacons();
                }
            }
        }

        return INSTANCE;

    }


    public void registerSensor(final Context context) {
        beaconViewModel = ViewModelProviders.of((FragmentActivity) context).get(BeaconViewModel.class);
        ValueExpressionListener valueExpressionListener = new ValueExpressionListener() {

            @Override
            public void onNewValues(String id,
                                    TimestampedValue[] arg1) {
                if (arg1 != null && arg1.length > 0) {
                    for (TimestampedValue t : arg1) {
                        beaconViewModel.insert(new nl.vu.hellobeacon.data.entities.Beacon(t.getValue().toString()));
                    }
                }
            }
        };


        try {
            for (String beaconType : beaconTypes) {
                Log.d("BeaconDistanceSensor", String.format(swanExpression, beaconType));
                ExpressionManager.registerValueExpression(context, id + beaconType, (ValueExpression) ExpressionFactory.parse(String.format(swanExpression, beaconType)), valueExpressionListener);
            }

        } catch (ExpressionParseException e) {
            e.printStackTrace();
        } catch (SwanException e) {
            e.printStackTrace();
        }
    }

    public void unregisterSensor(final Context context) {
        for (String beaconType : beaconTypes)
            ExpressionManager.unregisterExpression(context, id + beaconType);
    }
}

