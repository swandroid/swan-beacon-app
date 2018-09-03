package nl.vu.hellobeacon;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import nl.vu.hellobeacon.sensors.DiscoverBeacons;
import nl.vu.hellobeacon.views.BeaconViewModel;

public class AddBeacons extends AppCompatActivity {

    private BeaconViewModel beaconViewModel;
    private DiscoverBeacons discoverBeacons;
    private Integer beaconCount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_beacons);

        discoverBeacons = DiscoverBeacons.getDiscoverBeacons();


        beaconViewModel = ViewModelProviders.of(this).get(BeaconViewModel.class);

        beaconViewModel.getLiveCount().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable final Integer count) {
                beaconCount = count;
                final TextView tv = findViewById(R.id.tv);
                tv.post(new Runnable() {
                    @Override
                    public void run() {
                        tv.setText(count.toString());
                    }
                });
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        discoverBeacons.registerSensor(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        discoverBeacons.unregisterSensor(this);

    }
}
