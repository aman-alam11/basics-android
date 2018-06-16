package neu.droid.guy.roomviewmodellivedata;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private AppDatabase mDb;
    private List<POJO> sampleListOfPOJO;
    private List<POJO> sampleListOfPOJO_FromDatabase;

    @BindView(R.id.sample_text_view)
    TextView sampleTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        sampleListOfPOJO = new ArrayList<>();
        sampleListOfPOJO_FromDatabase = new ArrayList<>();
        mDb = AppDatabase.getInstance(getApplicationContext());
        sampleTextView.setText("Sample Data From Database: \n");

        /**
         * PLEASE DON'T INCLUDE SUCH FOR LOOPS IN ONCREATE
         * THIS IS JUST A SAMPLE
         * */


        /**
         * Add random data
         * */
        for (int i = 0; i < 10; i++) {
            sampleListOfPOJO.add(new POJO("Movie Name: " + i,
                    true,
                    i * i,
                    "https://RandomMovieDataURL: " + i));
        }

        /**
         * Please don' abuse thread like done below
         * This is not the proper way to use Threads.
         * This can also create Race Conditions
         * However, since I just want to show that there is data, I am simply adding it to TextView
         * Use AppExecuters
         * */
        new Thread(new Runnable() {
            @Override
            public void run() {
                /**
                 * NO FOR LOOPS PLEASE
                 * Insert all the sample objects made above in the database
                 * */
                for (int i = 0; i < sampleListOfPOJO.size(); i++)
                    mDb.taskDAO().insertObject(sampleListOfPOJO.get(i));

                /**
                 * NO FOR LOOPS PLEASE
                 * Retrieve all the sample objects made above from the database
                 * */
                for (int i = 0; i < sampleListOfPOJO.size(); i++) {
                    sampleListOfPOJO_FromDatabase.addAll(mDb.taskDAO().getAllData());
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        /**
                         * Display the data from Database in a simple textview
                         * */
                        for (int i = 0; i < sampleListOfPOJO.size(); i++) {
                            String s = sampleListOfPOJO_FromDatabase.get(i).getImageUrl() + "\n" +
                                    sampleListOfPOJO_FromDatabase.get(i).getId() + "\n" +
                                    sampleListOfPOJO_FromDatabase.get(i).getMovieName() + "\n" +
                                    sampleListOfPOJO_FromDatabase.get(i).getIsMovieFav() + "\n" +
                                    sampleListOfPOJO_FromDatabase.get(i).getMovieRating() + "\n \n \n";
                            sampleTextView.append(s);
                        }

                    }
                });
            }
        }).start();
    }
}