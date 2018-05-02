package neu.droid.guy.coordinate_layout_parallax_demo;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.scroll,
            R.id.exitUntilCollapsed,
            R.id.enterAlways,
            R.id.enterAlwaysCollapsed,
            R.id.snap})
    public void setViewOnClick(View view){
        switch(view.getId())
        {
            case R.id.scroll:
                    Toast.makeText(this, "scroll", Toast.LENGTH_LONG).show();
                Intent i = new Intent(this,Scroll.class);
                startActivity(i);
                break;

            case R.id.exitUntilCollapsed:
                Toast.makeText(this, "exitUntilCollapsed", Toast.LENGTH_LONG).show();
                Intent e = new Intent(this,ExitUntillCollapsed.class);
                startActivity(e);
                break;

            case R.id.enterAlways:
                Toast.makeText(this, "enterAlways", Toast.LENGTH_LONG).show();
                Intent f = new Intent(this,EnterAlways.class);
                startActivity(f);
                break;

            case R.id.enterAlwaysCollapsed:
                Toast.makeText(this, "enterAlwaysCollapsed", Toast.LENGTH_LONG).show();
                Intent g = new Intent(this,EnterAlwaysCollapsed.class);
                startActivity(g);
                break;

            case R.id.snap:
                Toast.makeText(this, "Snap", Toast.LENGTH_LONG).show();
                Intent h = new Intent(this,Snap.class);
                startActivity(h);
                break;
        }
    }
}
