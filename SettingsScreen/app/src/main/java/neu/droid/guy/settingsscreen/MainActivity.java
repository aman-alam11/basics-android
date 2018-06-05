package neu.droid.guy.settingsscreen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    private TextView textViewWeight5;
    private TextView textViewWeight3;
    private TextView textViewWeight1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewWeight5 = findViewById(R.id.text_view_weight_5);
        textViewWeight3 = findViewById(R.id.text_view_weight_3);
        textViewWeight1 = findViewById(R.id.text_view_weight_1);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        preferences.registerOnSharedPreferenceChangeListener(this);

        setupVisibilityFromPreference(preferences);

    }


    /**
     * Read from preferences in OnCreate to setup visibility of textviews
     * If someone has made a textview invisible from last run, it will be read from preferences
     * Else on create will automatically make everything visible
     */
    private void setupVisibilityFromPreference(SharedPreferences preferences) {
        toggleVisibilityTextView(
                preferences.getBoolean(getResources().getString(R.string.tv_weight_5_check_box_key),
                        getResources().getBoolean(R.bool.pref_show_tv5)),
                textViewWeight5);
        toggleVisibilityTextView(
                preferences.getBoolean(getResources().getString(R.string.tv_weight_3_check_box_key),
                        getResources().getBoolean(R.bool.pref_show_tv3)),
                textViewWeight3);
        toggleVisibilityTextView(
                preferences.getBoolean(getResources().getString(R.string.tv_weight_1_check_box_key),
                        getResources().getBoolean(R.bool.pref_show_tv1)),
                textViewWeight1);
    }


    /**
     * Initialize the contents of the Activity's standard options menu.  You
     * should place your menu items in to <var>menu</var>.
     * <p>
     * <p>This is only called once, the first time the options menu is
     * displayed.  To update the menu every time it is displayed, see
     * {@link #onPrepareOptionsMenu}.
     * <p>
     * <p>The default implementation populates the menu with standard system
     * menu items.  These are placed in the {@link Menu#CATEGORY_SYSTEM} group so that
     * they will be correctly ordered with application-defined menu items.
     * Deriving classes should always call through to the base implementation.
     * <p>
     * <p>You can safely hold on to <var>menu</var> (and any items created
     * from it), making modifications to it as desired, until the next
     * time onCreateOptionsMenu() is called.
     * <p>
     * <p>When you add items to the menu, you can implement the Activity's
     * {@link #onOptionsItemSelected} method to handle them there.
     *
     * @param menu The options menu in which you place your items.
     * @return You must return true for the menu to be displayed;
     * if you return false it will not be shown.
     * @see #onPrepareOptionsMenu
     * @see #onOptionsItemSelected
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings, menu);
        return true;
    }


    /**
     * This hook is called whenever an item in your options menu is selected.
     * The default implementation simply returns false to have the normal
     * processing happen (calling the item's Runnable or sending a message to
     * its Handler as appropriate).  You can use this method for any items
     * for which you would like to do processing without those other
     * facilities.
     * <p>
     * <p>Derived classes should call through to the base class for it to
     * perform the default menu handling.</p>
     *
     * @param item The menu item that was selected.
     * @return boolean Return false to allow normal menu processing to
     * proceed, true to consume it here.
     * @see #onCreateOptionsMenu
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.settings_item) {
            Intent openSettingsIntent = new Intent(this, SettingsActivity.class);
            startActivity(openSettingsIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Called when a shared preference is changed, added, or removed. This
     * may be called even if a preference is set to its existing value.
     * <p>
     * <p>This callback will be run on your main thread.
     *
     * @param sharedPreferences The {@link SharedPreferences} that received
     *                          the change.
     * @param key               The key of the preference that was changed, added, or
     */
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

        if (key.equals(getResources().getString(R.string.tv_weight_5_check_box_key))) {
            toggleVisibilityTextView(
                    sharedPreferences
                            .getBoolean(getResources().getString(R.string.tv_weight_5_check_box_key),
                                    getResources().getBoolean(R.bool.pref_show_tv5)),
                    textViewWeight5);
        } else if (key.equals(getResources().getString(R.string.tv_weight_3_check_box_key))) {
            toggleVisibilityTextView(
                    sharedPreferences
                            .getBoolean(getResources().getString(R.string.tv_weight_3_check_box_key),
                                    getResources().getBoolean(R.bool.pref_show_tv3)),
                    textViewWeight3
            );
        } else if (key.equals(getResources().getString(R.string.tv_weight_1_check_box_key))) {
            toggleVisibilityTextView(
                    sharedPreferences
                            .getBoolean(getResources().getString(R.string.tv_weight_1_check_box_key),
                                    getResources().getBoolean(R.bool.pref_show_tv1)),
                    textViewWeight1
            );
        }

    }

    /**
     * Change visibility of text view based on checkboxpreference using the key
     */
    void toggleVisibilityTextView(Boolean isVisible, TextView textViewToToggle) {
        if (!isVisible) {
            textViewToToggle.setVisibility(View.INVISIBLE);
        } else {
            textViewToToggle.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        android.support.v7.preference.PreferenceManager.getDefaultSharedPreferences(this)
                .unregisterOnSharedPreferenceChangeListener(this);
    }

}
