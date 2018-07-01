package neu.droid.guy.recyclerviewsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    SampleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_view_sample);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);


        adapter = new SampleAdapter(prepareRandomData());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }

    /**
     * @return a list of SampleData for Recycler View
     */
    private ArrayList<SampleData> prepareRandomData() {

        ArrayList<SampleData> sampleData = new ArrayList<>(50);
        for (int i = 1; i < 50; i++) {
            sampleData.add(new SampleData("Main Text Data: " + i,
                    "Left Text Data: " + i,
                    "Right Text Data: " + i));
        }

        return sampleData;
    }
}
