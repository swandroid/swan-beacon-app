package nl.michelmooiweer.hellobeacon;

import java.util.ArrayList;

public class Storage {

    private static Storage instance = null;
    public ArrayList<Beacon> registeredBeacons, foundBeacons;
    public BeaconDiscovery discovery;
    public BeaconListAdapter beaconListAdapter;



    private Storage(){
        registeredBeacons = new ArrayList<>();
        foundBeacons = new ArrayList<>();

    }

    public BeaconDiscovery getDiscovery(){
        if(discovery == null){
            discovery = new BeaconDiscovery();
        }
        return discovery;
    }



    public static Storage getStorage(){
        if(instance == null){
            instance = new Storage();
        }

        return instance;

    }

}
