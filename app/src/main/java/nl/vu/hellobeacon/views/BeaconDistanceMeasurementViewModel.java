package nl.vu.hellobeacon.views;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;


import nl.vu.hellobeacon.data.AppRepository;
import nl.vu.hellobeacon.data.entities.BeaconDistanceMeasurement;

public class BeaconDistanceMeasurementViewModel extends AndroidViewModel {

    private AppRepository repository;


    public BeaconDistanceMeasurementViewModel( Application application) {
        super(application);
        repository = AppRepository.getRepository(application);


    }

    public int getMaxIndex() {return repository.getBeaconDistanceMeasurementIndex();}

    public void insert(BeaconDistanceMeasurement beaconDistanceMeasurement){repository.insertBeaconDistanceMeasurement(beaconDistanceMeasurement);}


}
