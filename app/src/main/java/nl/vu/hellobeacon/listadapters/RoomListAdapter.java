package nl.vu.hellobeacon.listadapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import nl.vu.hellobeacon.R;
import nl.vu.hellobeacon.data.entities.Room;


public class RoomListAdapter extends RecyclerView.Adapter<RoomListAdapter.RoomViewHolder> {

    private RoomListAdapterListener onClickListener;

    class RoomViewHolder extends RecyclerView.ViewHolder {
        private final TextView RoomItemView;
        private final ImageButton DeleteRoom;
        private final ImageButton AddMeasurements;

        private RoomViewHolder(View itemView) {
            super(itemView);
            RoomItemView = itemView.findViewById(R.id.placehloder);
            DeleteRoom = itemView.findViewById(R.id.deleteRoom);
            AddMeasurements = itemView.findViewById(R.id.addMeasurements);
        }
    }

    private final LayoutInflater mInflater;
    private List<Room> mRooms; // Cached copy of Rooms

    public RoomListAdapter(Context context, RoomListAdapterListener onClickListener) {
        mInflater = LayoutInflater.from(context);
        this.onClickListener = onClickListener;
    }

    @Override
    public RoomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);

        return new RoomViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RoomViewHolder holder, final int position) {
        holder.RoomItemView.setText(mRooms.get(position).getRoomName());
        holder.DeleteRoom.setOnClickListener(
                new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         onClickListener.deleteRoomOnClick(v, mRooms.get(position));

                     }
                 }

        );

        holder.AddMeasurements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.addMeasurements(v, mRooms.get(position));
            }
        });
    }

    public void setRooms(List<Room> Rooms){
        mRooms = Rooms;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mRooms has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mRooms != null)
            return mRooms.size();
        else return 0;
    }


}

