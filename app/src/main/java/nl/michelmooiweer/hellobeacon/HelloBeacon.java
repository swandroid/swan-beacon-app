package nl.michelmooiweer.hellobeacon;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import interdroid.swancore.swansong.ExpressionFactory;
import interdroid.swancore.swansong.ExpressionParseException;
import interdroid.swancore.swansong.TriStateExpression;
import nl.michelmooiweer.hellobeacon.firebase.FirebaseService;


public class HelloBeacon extends AppCompatActivity {

    FirebaseService fbs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_beacon);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        try {
            TriStateExpression a = (TriStateExpression)ExpressionFactory.parse("self@beaconDistance:distance{MEAN,1000}>500");
            Log.d("TRI", a.toParseString());

        } catch (ExpressionParseException e) {
            e.printStackTrace();
        }
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

    protected void onStop(){
        super.onStop();
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
        }

        return super.onOptionsItemSelected(item);
    }

}
