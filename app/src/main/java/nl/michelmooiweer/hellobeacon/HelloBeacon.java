package nl.michelmooiweer.hellobeacon;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;

import nl.michelmooiweer.hellobeacon.firebase.FirebaseService;


public class HelloBeacon extends AppCompatActivity {

    FirebaseService fbs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_beacon);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fbs = new FirebaseService();
    }

    protected void onResume(){
        super.onResume();
        registerSensors();

    }

    private void registerSensors() {
        for (Beacon b : Storage.getStorage().registeredBeacons) b.RegisterBeacon(this);
    }


    public void unregisterSensors(){
        for(Beacon b: Storage.getStorage().registeredBeacons){
            b.unRegisterBeacon(this);
        }
    }

    protected void onPause(){
        super.onPause();
        unregisterSensors();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_hello_beacon, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent;

        switch (item.getItemId()){
            case R.id.addBeaconMenuItem:
                intent = new Intent(this, DiscoverBeacons.class);
                startActivity(intent);
                return true;
            case R.id.settingsMenuItem:
                intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;
            case R.id.batteryTestMenuItem:
                intent = new Intent(this, MainBattery.class);
                startActivity(intent);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
