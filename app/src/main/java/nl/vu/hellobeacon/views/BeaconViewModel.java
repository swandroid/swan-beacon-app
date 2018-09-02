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
    private LiveData<Integer> liveCount;

    public BeaconViewModel( Application application) {
        super(application);
        repository = AppRepository.getRepository(application);
        liveCount = repository.getBeaconLiveCount();

    }

    public void insert(Beacon beacon){
        repository.insertBeacon(beacon);
    }

    public void delete(Beacon beacon){
        repository.deleteBeacon(beacon);
    }

    public List<Beacon> getAllBeacons() { return repository.getAllBeacons(); }

    public LiveData<List<Beacon>> getLiveBeacons() {return repository.getLiveBeacons();}

    public LiveData<Integer> getLiveCount(){return liveCount;}

    public int count() {return repository.getBeaconCount();}
}
