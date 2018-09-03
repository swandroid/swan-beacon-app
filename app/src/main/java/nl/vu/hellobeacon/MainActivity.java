package nl.vu.hellobeacon;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import nl.vu.hellobeacon.services.LocationService;

public class MainActivity extends AppCompatActivity {

    private IntentFilter intentFilter;
    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("receiver", "received intent");
            Log.d("receiver", intent.getStringExtra("location"));
            ((TextView) findViewById(R.id.textView2)).setText(intent.getStringExtra("location"));


        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intentFilter = new IntentFilter();
        intentFilter.addAction("UpdateLocation");

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

        registerReceiver(mReceiver, intentFilter);


        Intent intent = new Intent(this, LocationService.class);
        startService(intent);

    }

}
