package nl.vu.hellobeacon.data;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import nl.vu.hellobeacon.data.dataAccesObjects.RoomDAO;
import nl.vu.hellobeacon.data.entities.Room;

public class AppRepository {

    private RoomDAO roomDAO;
    private LiveData<List<Room>> allRooms;

    public AppRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        roomDAO = db.roomDAO();
        allRooms = roomDAO.getAll();
    }

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

}
