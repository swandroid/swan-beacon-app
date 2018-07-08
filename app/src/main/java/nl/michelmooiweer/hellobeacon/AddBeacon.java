package nl.michelmooiweer.hellobeacon;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class AddBeacon extends AppCompatActivity {

    String beaconId;
    Beacon beacon;
    int maxDistance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_beacon);
        Intent intent = getIntent();
        this.beaconId = intent.getStringExtra("beaconId");
        beacon =new Beacon(beaconId);

        final Handler seekBarHandler = new Handler();
        final SeekBar distanceSeekbar = findViewById(R.id.distanceSeekBar);
        distanceSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                final TextView distanceText = findViewById(R.id.distance);
                distanceText.setText(""+progress);
                maxDistance = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        final EditText distanceText = findViewById(R.id.distance);
        distanceText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }


            @Override
            public void afterTextChanged(Editable s) {
                int pos = distanceText.getSelectionStart();
                final SeekBar seekBar = findViewById(R.id.distanceSeekBar);
                try {
                    final int result = Integer.parseInt(s.toString());
                    seekBar.setProgress(result);
                    seekBarHandler.post(new Runnable() {
                        @Override
                        public void run() {
                                seekBar.setProgress(result);
                        }
                    });

                    distanceText.setSelection(pos);


                }catch (NumberFormatException e){
                    seekBar.setProgress(0);
                }
            }


        });
    }








}
