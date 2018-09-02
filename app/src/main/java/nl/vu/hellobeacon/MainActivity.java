package nl.vu.hellobeacon;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import nl.vu.hellobeacon.services.LocationService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button addRooms = findViewById(R.id.addLocations);
        addRooms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EditRooms.class);
                startActivity(intent);
            }
        });
        final Button addBeacons = findViewById(R.id.addBeacons);
        addBeacons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddBeacons.class);
                startActivity(intent);
            }
        });









        Intent intent = new Intent(this, LocationService.class);
        startService(intent);

    }
}
