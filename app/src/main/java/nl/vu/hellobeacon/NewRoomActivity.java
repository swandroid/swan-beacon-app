package nl.vu.hellobeacon;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewRoomActivity extends AppCompatActivity {


    public static final String EXTRA_REPLY = "nl.vu.hellobeacon.roomlistsql.REPLY";

    private EditText editroomView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_room);
        editroomView = findViewById(R.id.edit_room);

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(editroomView.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    String room = editroomView.getText().toString();
                    replyIntent.putExtra(EXTRA_REPLY, room);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }
}
