package nl.vu.hellobeacon.views;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

import nl.vu.hellobeacon.data.AppRepository;
import nl.vu.hellobeacon.data.entities.Room;

public class RoomViewModel extends AndroidViewModel{

    private AppRepository repository;
    private LiveData<List<Room>> allRooms;
    public RoomViewModel( Application application) {
        super(application);
        repository = AppRepository.getRepository(application);
        allRooms = repository.getAllRooms();

    }

    public void insert(Room room){
        repository.insertRoom(room);}

    public void delete(Room room){
        repository.deleteRoom(room);
    }

    public LiveData<List<Room>> getAllRooms() { return allRooms; }
}
