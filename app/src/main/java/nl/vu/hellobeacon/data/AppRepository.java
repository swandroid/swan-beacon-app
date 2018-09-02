package nl.vu.hellobeacon.data;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import nl.vu.hellobeacon.data.dataAccesObjects.BeaconDAO;
import nl.vu.hellobeacon.data.dataAccesObjects.BeaconDistanceMeasurementDAO;
import nl.vu.hellobeacon.data.dataAccesObjects.LocationMeasurementDAO;
import nl.vu.hellobeacon.data.dataAccesObjects.RoomDAO;
import nl.vu.hellobeacon.data.entities.BeaconDistanceMeasurement;
import nl.vu.hellobeacon.data.entities.LocationMeasurement;
import nl.vu.hellobeacon.data.entities.Room;
import nl.vu.hellobeacon.data.entities.Beacon;

public class AppRepository {

    private RoomDAO roomDAO;
    private BeaconDAO beaconDAO;
    private BeaconDistanceMeasurementDAO beaconDistanceMeasurementDAO;
    private LocationMeasurementDAO locationMeasurementDAO;
    private LiveData<List<Room>> allRooms;
    private LiveData<List<Beacon>> allBeacons;
    private LiveData<Integer> beaconCount;
    private LiveData<Integer> beaconDistanceMeasurementIndex;

    private AppRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        roomDAO = db.roomDAO();
        beaconDAO = db.beaconDAO();
        beaconDistanceMeasurementDAO = db.beaconDistanceMeasurementDAO();
        locationMeasurementDAO = db.locationMeasurementDAO();
        allRooms = roomDAO.getAll();
        allBeacons = beaconDAO.getAll();
        beaconCount = beaconDAO.count();
        beaconDistanceMeasurementIndex = beaconDistanceMeasurementDAO.getMaxIndex();
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

    //region BeaconDistanceMeasurement
    public LiveData<Integer> getBeaconDistanceMeasurementIndex(){return beaconDistanceMeasurementIndex;}

    public void insertBeaconDistanceMeasurement(BeaconDistanceMeasurement beaconDistanceMeasurement){
        new insertBeaconDistanceMeasurementAsyncTask(beaconDistanceMeasurementDAO).execute(beaconDistanceMeasurement);
    }

    private static class insertBeaconDistanceMeasurementAsyncTask extends AsyncTask<BeaconDistanceMeasurement, Void, Void> {

        private BeaconDistanceMeasurementDAO mAsyncTaskDao;

        insertBeaconDistanceMeasurementAsyncTask(BeaconDistanceMeasurementDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final BeaconDistanceMeasurement... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
    //endregion

    //region LocationMeasurement


    public void insertLocationMeasurement(LocationMeasurement locationMeasurement){
        new insertLocationMeasurementAsyncTask(locationMeasurementDAO).execute(locationMeasurement);
    }

    private static class insertLocationMeasurementAsyncTask extends AsyncTask<LocationMeasurement, Void, Void>{

        private LocationMeasurementDAO myAsyncTaskDao;

        insertLocationMeasurementAsyncTask(LocationMeasurementDAO dao) {
            myAsyncTaskDao = dao;
        }



        @Override
        protected Void doInBackground(final LocationMeasurement... params) {
            myAsyncTaskDao.insert(params[0]);
            return null;
        }
    }


    //endregion



}
