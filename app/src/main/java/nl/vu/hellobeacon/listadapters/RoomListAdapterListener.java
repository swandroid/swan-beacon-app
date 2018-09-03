package nl.vu.hellobeacon.listadapters;

import android.view.View;

import nl.vu.hellobeacon.data.entities.Room;

public interface RoomListAdapterListener {
    void deleteRoomOnClick(View v, Room room);

    void addMeasurements(View v, Room room);
}
