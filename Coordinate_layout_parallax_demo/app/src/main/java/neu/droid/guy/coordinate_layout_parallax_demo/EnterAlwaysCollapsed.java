package neu.droid.guy.coordinate_layout_parallax_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

public class EnterAlwaysCollapsed extends AppCompatActivity {

    @BindView(R.id.enterAlwaysCollapsedTV) TextView enterAlwaysCollapsedTextView;
    @BindString(R.string.enter_always_collapsed) String enterAlwaysCollapsedDetailsString;
    @BindString(R.string.random_text) String randomString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enter_always_collapsed);
        ButterKnife.bind(this);

        enterAlwaysCollapsedTextView.append(enterAlwaysCollapsedDetailsString);
        enterAlwaysCollapsedTextView.append(randomString);
    }
}
