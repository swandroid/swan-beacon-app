package nl.vu.hellobeacon.views;


import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;

import nl.vu.hellobeacon.data.AppRepository;
import nl.vu.hellobeacon.data.entities.LocationMeasurement;

public class LocationMeasurementViewModel extends AndroidViewModel {

    private AppRepository repository;


    public LocationMeasurementViewModel(Application application) {
        super(application);
        repository = AppRepository.getRepository(application);
    }

    public void insert(LocationMeasurement locationMeasurement) {
        repository.insertLocationMeasurement(locationMeasurement);
    }

}
