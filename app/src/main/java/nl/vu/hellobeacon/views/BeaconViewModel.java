package nl.vu.hellobeacon.views;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

import nl.vu.hellobeacon.data.AppRepository;
import nl.vu.hellobeacon.data.entities.Room;
import nl.vu.hellobeacon.data.entities.Beacon;

public class BeaconViewModel extends AndroidViewModel {

    private AppRepository repository;
    private LiveData<List<Beacon>> allBeacons;
    private LiveData<Integer> count;
    public BeaconViewModel( Application application) {
        super(application);
        repository = AppRepository.getRepository(application);
        allBeacons = repository.getAllBeacons();
        count = repository.getBeaconCount();

    }

    public void insert(Beacon beacon){
        repository.insertBeacon(beacon);
    }

    public void delete(Beacon beacon){
        repository.deleteBeacon(beacon);
    }

    public LiveData<List<Beacon>> getAllBeacons() { return allBeacons; }

    public LiveData<Integer> count() {return count;}
}
