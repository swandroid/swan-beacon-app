package nl.michelmooiweer.hellobeacon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainBattery extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_battery);
        Button button = findViewById(R.id.toNetworkButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainBattery.this, NetworkLocation.class);
                startActivity(intent);
            }
        });

        button = findViewById(R.id.toGPSButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainBattery.this, GpsLocation.class);
                startActivity(intent);
            }
        });
    }
}
