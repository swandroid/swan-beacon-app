package nl.michelmooiweer.hellobeacon;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

public class DiscoverBeacons extends AppCompatActivity {

    private Storage storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        storage = Storage.getStorage();
        setContentView(R.layout.activity_discover_beacons);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ListView listView = (ListView) findViewById(R.id.foundBeacons);
        BeaconListAdapter bla = new BeaconListAdapter(this, storage.foundBeacons);
        storage.beaconListAdapter = bla;
        listView.setAdapter(bla);
    }

    @Override
    protected void onPause() {
        super.onPause();
        storage.getDiscovery().unregisterSensor(this);
    }

    @Override
    protected void onResume(){
        super.onResume();
        storage.getDiscovery().registerSensor(this);
    }
}
