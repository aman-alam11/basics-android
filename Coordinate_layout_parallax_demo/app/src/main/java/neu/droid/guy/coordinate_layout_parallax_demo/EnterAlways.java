package neu.droid.guy.coordinate_layout_parallax_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

public class EnterAlways extends AppCompatActivity {

    @BindString(R.string.random_text) String randomString;
    @BindString(R.string.enter_always) String enterAlwaysDetailsString;
    @BindView(R.id.enterAlwaysTV) TextView enterAlwaysTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enter_always);
        ButterKnife.bind(this);


        enterAlwaysTextView.append(enterAlwaysDetailsString);
        enterAlwaysTextView.append(randomString);
    }
}
