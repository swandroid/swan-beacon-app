package nl.michelmooiweer.hellobeacon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class BeaconBatteryTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beacon_battery_test);
    }

    @Override
    protected void onResume(){
        super.onResume();
        registerSensors();
        setCounter();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        unregisterSensors();

    }

    private void registerSensors() {
        for (Beacon b : Storage.getStorage().registeredBeacons) b.RegisterBeacon(this);
    }

    private void unregisterSensors(){
        for (Beacon b : Storage.getStorage().registeredBeacons) b.unRegisterBeacon(this);

    }

    private void setCounter(){
        int count = Storage.getStorage().registeredBeacons.size();
        TextView numberOfBeacons = (TextView)findViewById(R.id.numberOfBeacons);
        numberOfBeacons.setText(String.format("%d", count));
    }
}
