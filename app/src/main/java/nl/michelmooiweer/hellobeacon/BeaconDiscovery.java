package nl.michelmooiweer.hellobeacon;

import android.content.Context;
import android.util.Log;

import interdroid.swancore.swanmain.ExpressionManager;
import interdroid.swancore.swanmain.SwanException;
import interdroid.swancore.swanmain.TriStateExpressionListener;
import interdroid.swancore.swanmain.ValueExpressionListener;
import interdroid.swancore.swansong.Expression;
import interdroid.swancore.swansong.ExpressionFactory;
import interdroid.swancore.swansong.ExpressionParseException;
import interdroid.swancore.swansong.TimestampedValue;
import interdroid.swancore.swansong.TriState;
import interdroid.swancore.swansong.TriStateExpression;
import interdroid.swancore.swansong.ValueExpression;

public class BeaconDiscovery extends SwanSensor{

    public String id;
    private Storage storage;
    private final  String[] beaconTypes =new String[]{"ibeaconuuid", "eddystoneuid", "altbeacon", "estimotenearable"};

    BeaconDiscovery(){
        super();
        storage = Storage.getStorage();
        swanExpression = "self@beacon_discovery:%s{ANY, 10}";
    }

    public void registerSensor(final Context context) {
        this.id = context.getString(R.string.app_name) + "-discoverBeacons";
        if (this.swanRegistered) {
            return;
        }

        this.swanRegistered = true;
        ValueExpressionListener valueExpressionListener = new ValueExpressionListener() {

            @Override
            public void onNewValues(String id,
                                    TimestampedValue[] arg1) {
                if (arg1 != null && arg1.length > 0) {
                    for (TimestampedValue t : arg1) {
                        Beacon b = new Beacon(t.getValue().toString());
                        if (!storage.registeredBeacons.contains(b) &&
                                !storage.foundBeacons.contains(b)) {
                            storage.foundBeacons.add(b);
                            storage.beaconListAdapter.setBeacons();
                            storage.beaconListAdapter.notifyDataSetChanged();

                        }
                    }
                }
            }
        };

        TriStateExpressionListener triStateExpressionListener = new TriStateExpressionListener() {
            @Override
            public void onNewState(String id, long timestamp, TriState newState) {

            }
        };

        try {
            for(String beaconType: beaconTypes) {
                Log.d("Beacon", String.format(swanExpression, beaconType));
                ExpressionManager.registerValueExpression(context, id+beaconType, (ValueExpression) ExpressionFactory.parse(String.format(swanExpression, beaconType)), valueExpressionListener);
            }

        } catch (ExpressionParseException e) {
            e.printStackTrace();
        } catch (SwanException e) {
            e.printStackTrace();
        }
    }

    public void unregisterSensor(final Context context) {
        for(String beaconType: beaconTypes)
            ExpressionManager.unregisterExpression(context, id+beaconType);
        this.swanRegistered = false;
    }
}

