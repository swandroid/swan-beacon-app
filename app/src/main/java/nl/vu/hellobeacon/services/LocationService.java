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

import java.util.List;

import nl.vu.hellobeacon.sensors.BeaconDistanceSensor;
import nl.vu.hellobeacon.views.BeaconViewModel;

public class LocationService extends Service {

    private List<BeaconDistanceSensor> beacons;
    private BeaconViewModel beaconViewModel;
    final Context context;



    public LocationService() {
        super();
        context = this.getApplicationContext();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Toast.makeText(this, " MyService Created ", Toast.LENGTH_LONG).show();
        final Handler handler = new Handler();
        final int delay = 1000;


        beaconViewModel = ViewModelProviders.of((FragmentActivity) context).get(BeaconViewModel.class);

        LiveData<List<nl.vu.hellobeacon.data.entities.Beacon>> beacons = beaconViewModel.getAllBeacons();

        beacons.observe((LifecycleOwner) context, new Observer<List<nl.vu.hellobeacon.data.entities.Beacon>>() {
            @Override
            public void onChanged(@Nullable List<nl.vu.hellobeacon.data.entities.Beacon> beacons) {
                updateBeacons(beacons);
            }
        });

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                getLocation();
                handler.postDelayed(this,delay);
            }
        }, delay);

    }

    private void updateBeacons(List<nl.vu.hellobeacon.data.entities.Beacon> beaconList){
        for(BeaconDistanceSensor beacon: beacons){
            beacon.unRegisterBeacon(context);
        }
        beacons.clear();
        for(nl.vu.hellobeacon.data.entities.Beacon beacon: beaconList){
            beacons.add(new BeaconDistanceSensor(beacon.getUuid()));
        }

        for(BeaconDistanceSensor beacon: beacons){
            beacon.registerBeacon(this.getApplicationContext());
        }
    }

    public void getLocation(){

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }






}
