package neu.droid.guy.coordinate_layout_parallax_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ExitUntillCollapsed extends AppCompatActivity {

    @BindView(R.id.exitUntilCollapsedTV) TextView exitUntillCollapsedtextView;
    @BindString(R.string.random_text) String randomString;
    @BindString(R.string.exit_untill_collapsed) String exitUntillCollapsedDetailsString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exit_untill_collapsed);
        ButterKnife.bind(this);

        exitUntillCollapsedtextView.append(exitUntillCollapsedDetailsString);
        exitUntillCollapsedtextView.append(randomString);
    }
}
