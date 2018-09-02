package nl.vu.hellobeacon;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import nl.vu.hellobeacon.data.entities.Beacon;
import nl.vu.hellobeacon.data.entities.BeaconDistanceMeasurement;
import nl.vu.hellobeacon.data.entities.LocationMeasurement;
import nl.vu.hellobeacon.data.entities.Room;
import nl.vu.hellobeacon.sensors.BeaconDistanceSensor;
import nl.vu.hellobeacon.views.BeaconDistanceMeasurementViewModel;
import nl.vu.hellobeacon.views.BeaconViewModel;
import nl.vu.hellobeacon.views.LocationMeasurementViewModel;

public class AddMeasurements extends AppCompatActivity {

    Room room;
    BeaconViewModel beaconViewModel;
    BeaconDistanceMeasurementViewModel beaconDistanceMeasurementViewModel;
    LocationMeasurementViewModel locationMeasurementViewModel;
    Handler handler;
    List<Beacon> beacons = new ArrayList<>();

    private List<BeaconDistanceSensor> beaconSensors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_measurements);


        String room = getIntent().getStringExtra("room");
        this.room = new Room(room);

        beaconViewModel = ViewModelProviders.of(this).get(BeaconViewModel.class);
        beaconDistanceMeasurementViewModel = ViewModelProviders.of(this).get(BeaconDistanceMeasurementViewModel.class);
        locationMeasurementViewModel = ViewModelProviders.of(this).get(LocationMeasurementViewModel.class);
    }

    @Override
    protected void onResume(){
        super.onResume();


        beaconSensors = new ArrayList<>();
        for (Beacon beacon: beaconViewModel.getAllBeacons()) {
            beaconSensors.add(new BeaconDistanceSensor(beacon.getUuid()));
        }
        for (BeaconDistanceSensor beaconDistanceSensor: beaconSensors){
            beaconDistanceSensor.registerBeacon(this.getApplicationContext());
        }

        handler = new Handler();
        final int delay = 1000;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                insertLocation();
                handler.postDelayed(this,delay);
            }
        }, delay);
    }

    @Override
    protected void onPause(){
        super.onPause();
        handler.removeCallbacksAndMessages(null);
    }

    private void insertLocation() {
        List<BeaconDistanceMeasurement> distanceMeasurements = new ArrayList<>();
        int count = beaconDistanceMeasurementViewModel.getMaxIndex() + 1;

        for(BeaconDistanceSensor beaconDistanceSensor: beaconSensors){
            double distance = beaconDistanceSensor.getDistance();
//            if(distance == -1){
//                break;
//            }
            distanceMeasurements.add(new BeaconDistanceMeasurement(count, beaconDistanceSensor.getUuid(), distance ));
        }


        for(BeaconDistanceMeasurement beaconDistanceMeasurement: distanceMeasurements){

            beaconDistanceMeasurementViewModel.insert(beaconDistanceMeasurement);
        }

        new LocationMeasurement(count, room.roomName);

    }
}
