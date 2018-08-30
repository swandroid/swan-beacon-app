package nl.vu.hellobeacon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import nl.vu.hellobeacon.services.LocationService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Intent intent = new Intent(this, LocationService.class);
//        startService(intent);

        Intent intent = new Intent(this, AddBeacons.class);
        startActivity(intent);
    }
}
