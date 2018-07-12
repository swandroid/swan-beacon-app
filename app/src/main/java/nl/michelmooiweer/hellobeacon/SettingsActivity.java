package nl.michelmooiweer.hellobeacon;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

import com.google.firebase.iid.FirebaseInstanceId;
/**
 * A {@link PreferenceActivity} that presents a set of application settings. On
 * handset devices, settings are presented as a single list. On tablets,
 * settings are split by category, with category headers shown to the left of
 * the list of settings.
 * <p>
 * See <a href="http://developer.android.com/design/patterns/settings.html">
 * Android Design: Settings</a> for design guidelines and the <a
 * href="http://developer.android.com/guide/topics/ui/settings.html">Settings
 * API Guide</a> for more information on developing a Settings UI.
 */
public class SettingsActivity extends AppCompatPreferenceActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Display the fragment as the main content.
        getFragmentManager().beginTransaction().replace(android.R.id.content,
                new PrefsFragment()).commit();
    }




    public static class PrefsFragment extends PreferenceFragment {

        private static final String FIRE_BASE_ID = "pref_fireBaseId";

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            addPreferencesFromResource(R.xml.pref_general);

            findPreference(FIRE_BASE_ID).setSummary(FirebaseInstanceId.getInstance().getToken());

            //Make firebase Token selectable;
            Context c = findPreference(FIRE_BASE_ID).getContext();
            SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(c).edit();
            editor.putString(FIRE_BASE_ID, FirebaseInstanceId.getInstance().getToken());
            editor.commit();

        }

    }
}
