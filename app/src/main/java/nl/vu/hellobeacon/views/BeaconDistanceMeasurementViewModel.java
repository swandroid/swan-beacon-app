package nl.vu.hellobeacon.views;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import nl.vu.hellobeacon.data.AppRepository;
import nl.vu.hellobeacon.data.entities.BeaconDistanceMeasurement;

public class BeaconDistanceMeasurementViewModel extends AndroidViewModel {

    private AppRepository repository;
    private LiveData<Integer> maxIndex;


    public BeaconDistanceMeasurementViewModel( Application application) {
        super(application);
        repository = AppRepository.getRepository(application);
        maxIndex = repository.getBeaconDistanceMeasurementIndex();

    }

    public LiveData<Integer> getMaxIndex() {return maxIndex;}

    public void insert(BeaconDistanceMeasurement beaconDistanceMeasurement){repository.insertBeaconDistanceMeasurement(beaconDistanceMeasurement);}


}
