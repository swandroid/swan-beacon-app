package nl.vu.hellobeacon;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.MainThread;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import nl.vu.hellobeacon.data.entities.Beacon;
import nl.vu.hellobeacon.data.entities.Room;
import nl.vu.hellobeacon.listadapters.RoomListAdapter;
import nl.vu.hellobeacon.listadapters.RoomListAdapterListener;
import nl.vu.hellobeacon.views.BeaconViewModel;
import nl.vu.hellobeacon.views.RoomViewModel;

public class EditRooms extends AppCompatActivity {

    public static final int NEW_ROOM_ACTIVITY_REQUEST_CODE = 1;
    private RoomViewModel roomViewModel;
    private BeaconViewModel beaconViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_rooms);
        beaconViewModel = new BeaconViewModel(this.getApplication());
        roomViewModel = ViewModelProviders.of(this).get(RoomViewModel.class);

        if(BuildConfig.DEBUG) {
//            roomViewModel.deleteAll();

//            roomViewModel.insert(new Room("Bedroom"));
//            roomViewModel.insert(new Room("Kitchen"));
//            roomViewModel.insert(new Room("Living Room"));
//            roomViewModel.insert(new Room("Bathroom"));
//            roomViewModel.insert(new Room("Hallway"));
//
//            beaconViewModel.insert(new Beacon("BEACONUUID"));
        }







        RecyclerView recyclerView = findViewById(R.id.recylcerView);
        final RoomListAdapter adapter = new RoomListAdapter(this, new RoomListAdapterListener() {
            @Override
            public void deleteRoomOnClick(View v, Room room) {
                roomViewModel.delete(room);
            }

            @Override
            public void addMeasurements(View v, Room room){
                if(beaconViewModel.count() == 0){
                    Toast.makeText(EditRooms.this, "Register Beacons First", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent = new Intent(v.getContext(), AddMeasurements.class);
                intent.putExtra("room", room.roomName);
                startActivity(intent);


            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        roomViewModel.getAllRooms().observe(this, new Observer<List<Room>>() {
            @Override
            public void onChanged(@Nullable List<Room> rooms) {
                adapter.setRooms(rooms);


            }
        });

        FloatingActionButton fab = findViewById(R.id.addRoomFAB);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditRooms.this, NewRoomActivity.class);
                startActivityForResult(intent, NEW_ROOM_ACTIVITY_REQUEST_CODE);
            }
        });
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_ROOM_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Room room = new Room(data.getStringExtra(NewRoomActivity.EXTRA_REPLY));
            roomViewModel.insert(room);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }



}
