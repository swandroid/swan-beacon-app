package nl.vu.hellobeacon.data;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import nl.vu.hellobeacon.data.dataAccesObjects.BeaconDAO;
import nl.vu.hellobeacon.data.dataAccesObjects.RoomDAO;
import nl.vu.hellobeacon.data.entities.Room;
import nl.vu.hellobeacon.data.entities.Beacon;

public class AppRepository {

    private RoomDAO roomDAO;
    private BeaconDAO beaconDAO;
    private LiveData<List<Room>> allRooms;
    private LiveData<List<Beacon>> allBeacons;
    private LiveData<Integer> beaconCount;

    private AppRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        roomDAO = db.roomDAO();
        beaconDAO = db.beaconDAO();
        allRooms = roomDAO.getAll();
        allBeacons = beaconDAO.getAll();
        beaconCount = beaconDAO.count();
    }

    private static AppRepository INSTANCE;

    public static AppRepository getRepository(Application application){
        if (INSTANCE == null) {
            synchronized (AppRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new AppRepository(application);
                }
            }
        }

        return INSTANCE;

    }

    //region Rooms
    public LiveData<List<Room>> getAllRooms() {
        return allRooms;
    }


    public void insertRoom(Room room){new insertRoomAsyncTask(roomDAO).execute(room);}

    private static class insertRoomAsyncTask extends AsyncTask<Room, Void, Void> {

        private RoomDAO mAsyncTaskDao;

        insertRoomAsyncTask(RoomDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Room... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    public void deleteRoom(Room room){new deleteRoomAsyncTask(roomDAO).execute(room);}

    private static class deleteRoomAsyncTask extends AsyncTask<Room, Void, Void> {

        private RoomDAO mAsyncTaskDao;

        deleteRoomAsyncTask(RoomDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Room... params) {
            mAsyncTaskDao.delete(params[0]);
            return null;
        }
    }
    //endregion

    //region Beacons

    public LiveData<List<Beacon>> getAllBeacons() {return allBeacons;}

    public void insertBeacon(Beacon beacon){new insertBeaconAsyncTask(beaconDAO).execute(beacon);}

    private static class insertBeaconAsyncTask extends AsyncTask<Beacon, Void, Void> {

        private BeaconDAO mAsyncTaskDao;

        insertBeaconAsyncTask(BeaconDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Beacon... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    public void deleteBeacon(Beacon beacon){new deleteBeaconAsyncTask(beaconDAO).execute(beacon);}

    private static class deleteBeaconAsyncTask extends AsyncTask<Beacon, Void, Void> {

        private BeaconDAO mAsyncTaskDao;

        deleteBeaconAsyncTask(BeaconDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Beacon... params) {
            mAsyncTaskDao.delete(params[0]);
            return null;
        }
    }

    public LiveData<Integer> getBeaconCount(){return beaconCount;}

    //endregion

}
