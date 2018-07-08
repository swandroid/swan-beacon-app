package nl.michelmooiweer.hellobeacon;


import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class BeaconListAdapter extends ArrayAdapter<Beacon> {
    private final Activity context;
    private ArrayList<Beacon> beacons;
    private Storage storage;

    static class ViewHolder {
        public TextView uuid;
        public Button button;
        public TextInputLayout name;

    }


    public BeaconListAdapter(Activity context, ArrayList<Beacon> beacons) {
        super(context, R.layout.content_discover_beacons, beacons);
        this.context = context;
        this.beacons = beacons;
        storage = Storage.getStorage();
    }

    public void setBeacons(){
        this.beacons.removeAll(storage.registeredBeacons);
        notifyDataSetChanged();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
        View rowView = convertView;

        if (rowView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            rowView = inflater.inflate(R.layout.list_beacon, null);
            // configure view holder
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.uuid = (TextView) rowView.findViewById(R.id.uuid);
            viewHolder.button = (Button) rowView.findViewById(R.id.add);
            viewHolder.name = (TextInputLayout) rowView.findViewById(R.id.beaconName);

            rowView.setTag(viewHolder);
        }

        final ViewHolder holder = (ViewHolder) rowView.getTag();
        Beacon b = beacons.get(position);
        holder.uuid.setText(b.uuid);
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                beacons.get(position).name = holder.name.getEditText().getText().toString();
                storage.registeredBeacons.add(beacons.get(position));
                Intent intent = new Intent(context, AddBeacon.class);
                intent.putExtra("beacon Id", beacons.get(position).name);
                context.startActivity(intent);

                setBeacons();
            }
        });


        return rowView;
    }
}
