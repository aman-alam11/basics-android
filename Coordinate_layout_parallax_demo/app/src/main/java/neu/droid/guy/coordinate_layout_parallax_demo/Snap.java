package neu.droid.guy.coordinate_layout_parallax_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

public class Snap extends AppCompatActivity {

    @BindView(R.id.snapTV) TextView snapTextView;
    @BindString(R.string.random_text) String randomTextToAppend;
    @BindString(R.string.snap) String snapDetailString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.snap);
        ButterKnife.bind(this);
        /*
        Upon a scroll ending, if the view is only partially visible then it will be
        snapped and scrolled to it's closest edge.
        Using this option will determine what to do when a view only has been partially reduced.
        If scrolling ends and the view size has been reduced to less than 50% of its original,
        then this view to return to its original size.
        If the size is greater than 50% of its sized, it will disappear completely.
         */
        snapTextView.append(snapDetailString);
        snapTextView.append(randomTextToAppend);
    }



}
