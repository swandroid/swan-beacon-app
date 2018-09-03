package nl.vu.hellobeacon.data;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.os.AsyncTask;
import android.support.annotation.Nullable;

import java.util.List;

import nl.vu.hellobeacon.data.dataAccesObjects.BeaconDAO;
import nl.vu.hellobeacon.data.dataAccesObjects.BeaconDistanceMeasurementDAO;
import nl.vu.hellobeacon.data.dataAccesObjects.LocationMeasurementDAO;
import nl.vu.hellobeacon.data.dataAccesObjects.RoomDAO;
import nl.vu.hellobeacon.data.entities.Beacon;
import nl.vu.hellobeacon.data.entities.BeaconDistanceMeasurement;
import nl.vu.hellobeacon.data.entities.LocationMeasurement;
import nl.vu.hellobeacon.data.entities.Room;

public class AppRepository {

    private static AppRepository INSTANCE;
    private RoomDAO roomDAO;
    private BeaconDAO beaconDAO;
    private BeaconDistanceMeasurementDAO beaconDistanceMeasurementDAO;
    private LocationMeasurementDAO locationMeasurementDAO;
    private LiveData<List<Room>> allRooms;
    private List<Beacon> allBeacons;
    private List<BeaconDistanceMeasurement> allBeaconDistanceMeasurements;
    private int beaconCount;

    private AppRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        roomDAO = db.roomDAO();
        beaconDAO = db.beaconDAO();
        beaconDistanceMeasurementDAO = db.beaconDistanceMeasurementDAO();
        locationMeasurementDAO = db.locationMeasurementDAO();
        allRooms = roomDAO.getAll();


        beaconDAO.count().observeForever(new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                beaconCount = integer == null ? 0 : integer;
            }
        });

        beaconDAO.getAll().observeForever(new Observer<List<Beacon>>() {
            @Override
            public void onChanged(@Nullable List<Beacon> beacons) {
                allBeacons = beacons;
            }
        });


    }

    public static AppRepository getRepository(Application application) {
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


    public void insertRoom(Room room) {
        new insertRoomAsyncTask(roomDAO).execute(room);
    }

    public void deleteRoom(Room room) {
        new deleteRoomAsyncTask(roomDAO).execute(room);
    }

    public void deletAllRooms() {
        new deleteAllRoomAsyncTask(roomDAO).execute();
    }

    public List<Beacon> getAllBeacons() {
        return allBeacons;
    }

    public LiveData<List<Beacon>> getLiveBeacons() {
        return beaconDAO.getAll();
    }

    public void insertBeacon(Beacon beacon) {
        new insertBeaconAsyncTask(beaconDAO).execute(beacon);
    }


    //endregion

    //region Beacons

    public void deleteBeacon(Beacon beacon) {
        new deleteBeaconAsyncTask(beaconDAO).execute(beacon);
    }

    public int getBeaconCount() {
        return beaconCount;
    }

    public LiveData<Integer> getBeaconLiveCount() {
        return beaconDAO.count();
    }

    //region BeaconDistanceMeasurement
    public int getBeaconDistanceMeasurementIndex() {
        return beaconDistanceMeasurementDAO.getMaxIndex();
    }

    public List<BeaconDistanceMeasurement> getBeaconDistanceMeasurements() {
        return beaconDistanceMeasurementDAO.getDistanceMeasurement();
    }

    public void insertBeaconDistanceMeasurement(BeaconDistanceMeasurement beaconDistanceMeasurement) {
        new insertBeaconDistanceMeasurementAsyncTask(beaconDistanceMeasurementDAO).execute(beaconDistanceMeasurement);
    }

    public void insertLocationMeasurement(LocationMeasurement locationMeasurement) {
        new insertLocationMeasurementAsyncTask(locationMeasurementDAO).execute(locationMeasurement);
    }

    public LiveData<List<LocationDistanceJoin>> getLocationMeasurement() {
        return locationMeasurementDAO.getAll();
    }

    //endregion

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

    private static class deleteAllRoomAsyncTask extends AsyncTask<Void, Void, Void> {

        private RoomDAO mAsyncTaskDao;

        deleteAllRoomAsyncTask(RoomDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Void... params) {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }

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
    //endregion

    //region LocationMeasurement

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

    private static class insertLocationMeasurementAsyncTask extends AsyncTask<LocationMeasurement, Void, Void> {

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
