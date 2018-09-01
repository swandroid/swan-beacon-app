package nl.vu.hellobeacon;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import nl.vu.hellobeacon.data.entities.Room;
import nl.vu.hellobeacon.views.BeaconViewModel;

public class AddMeasurements extends AppCompatActivity {

    Room room;
    BeaconViewModel beaconViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_measurements);


        String room = getIntent().getStringExtra("room");

        beaconViewModel = ViewModelProviders.of(this).get(BeaconViewModel.class);











    }
}
