package nl.vu.hellobeacon.services;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.widget.Toast;

public class LocationService extends Service {
    public LocationService() {
        super();
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
        final Context context = this.getApplicationContext();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                getLocation();
                handler.postDelayed(this,delay);
            }
        }, delay);

    }

    public void getLocation(){

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }






}
