package nl.vu.hellobeacon.services;

import android.app.Service;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentActivity;
import android.util.SparseArray;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
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
    private List<BeaconDistanceSensor> beaconDistanceSensors;

    SparseArray<List<LocationDistanceJoin>> map = new SparseArray<>();
    private Context context;

    public LocationService(){
        beaconDistanceSensors =new ArrayList<>();
    }


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
                    for(BeaconDistanceSensor b: beaconDistanceSensors){
                        b.unRegisterBeacon(context);
                    }
                    beaconDistanceSensors = new ArrayList<>();
                    for(Beacon b: beaconList){
                        beaconDistanceSensors.add(new BeaconDistanceSensor(b.getUuid()).registerBeacon(context));
                    }
                }
            }
        });

    }

    private void initializeMeasurements(){
        repository.getLocationMeasurement().observeForever(new Observer<List<LocationDistanceJoin>>() {
            @Override
            public void onChanged(@Nullable List<LocationDistanceJoin> ldj) {
                if(ldj != null){
                    locationDistanceJoins = ldj;

                    for(LocationDistanceJoin locationDistanceJoin : locationDistanceJoins){
                        int index = locationDistanceJoin.b.index;
                        if(map.get(index) == null){
                            map.put(index, new ArrayList<LocationDistanceJoin>());
                        }
                        map.get(index).add(locationDistanceJoin);
                    }

                }
            }
        });











    }


    public void getLocation(){








    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }






}
