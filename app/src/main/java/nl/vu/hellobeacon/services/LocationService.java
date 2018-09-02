package nl.vu.hellobeacon.services;

import android.app.Service;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import nl.vu.hellobeacon.data.AppRepository;
import nl.vu.hellobeacon.data.LocationDistanceJoin;
import nl.vu.hellobeacon.data.entities.BeaconDistanceMeasurement;
import nl.vu.hellobeacon.data.entities.LocationMeasurement;
import nl.vu.hellobeacon.sensors.BeaconDistanceSensor;
import nl.vu.hellobeacon.views.BeaconDistanceMeasurementViewModel;
import nl.vu.hellobeacon.views.BeaconViewModel;
import nl.vu.hellobeacon.data.entities.Beacon;
import nl.vu.hellobeacon.views.LocationMeasurementViewModel;
import nl.vu.hellobeacon.views.RoomViewModel;


public class LocationService extends Service {


    private AppRepository repository;
    private List<LocationDistanceJoin> locationDistanceJoins;
    private List<Beacon> beacons;

    private Context context;


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        context = this.getApplicationContext();
        repository = AppRepository.getRepository(this.getApplication());

        initializeBeacons();
        initializeMeasurements();

        final Handler handler = new Handler();
        final int delay = 1000;


        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                getLocation();
                handler.postDelayed(this,delay);
            }
        }, delay);

    }

    private void initializeBeacons() {
        beacons = new ArrayList<>();
        repository.getLiveBeacons().observeForever(new Observer<List<Beacon>>() {
            @Override
            public void onChanged(@Nullable List<Beacon> beaconList) {
                if(beaconList != null){
                    beacons = beaconList;
                }
            }
        });

    }

    private void initializeMeasurements(){
        locationDistanceJoins = new ArrayList<>();
        repository.getLocationMeasurement().observeForever(new Observer<List<LocationDistanceJoin>>() {
            @Override
            public void onChanged(@Nullable List<LocationDistanceJoin> ldj) {
                if(ldj != null){
                    locationDistanceJoins = ldj;
                }
            }
        });
    }

//    private void updateBeacons(List<Beacon> beaconList){
//        for(BeaconDistanceSensor beacon: beacons){
//            beacon.unRegisterBeacon(context);
//        }
//        beacons.clear();
//        for(Beacon beacon: beaconList){
//            beacons.add(new BeaconDistanceSensor(beacon.getUuid()));
//        }
//
//        for(BeaconDistanceSensor beacon: beacons){
//            beacon.registerBeacon(this.getApplicationContext());
//        }
//    }

    public void getLocation(){





    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }






}
