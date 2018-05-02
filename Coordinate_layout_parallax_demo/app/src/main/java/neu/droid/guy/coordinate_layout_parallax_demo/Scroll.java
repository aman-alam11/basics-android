package neu.droid.guy.coordinate_layout_parallax_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

public class Scroll extends AppCompatActivity {

    @BindView(R.id.scrollTV) TextView scrollTextView;
    @BindString(R.string.scroll) String scrollDetailsString;
    @BindString(R.string.random_text) String randomText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scroll);
        ButterKnife.bind(this);


        scrollTextView.append(scrollDetailsString);
        scrollTextView.append(randomText);
    }
}
