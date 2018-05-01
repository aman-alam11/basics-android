package numad17s.neu.edu.voicerecogdemo;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.speech.RecognizerIntent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public Vibrator v1;
    public static int REQUEST_CODE = 100;
    ImageButton microphoneButtonIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Voice Recognition Demo");

        //Initialize vibrator else it will throw null pointer
        //Also make changes in Manifest file and give the permission to vibrate
        v1 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        //Get the ImageButton's XML Id and link it
        microphoneButtonIcon = findViewById(R.id.microphone);

        //Set a Listener on that imageButton
        microphoneButtonIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                v1.vibrate(100);
                voiceRecog();
            }
        });

        // 1. Instantiate an AlertDialog.Builder with its constructor
        final AlertDialog.Builder acknowledgementsDialogBuilder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            acknowledgementsDialogBuilder = new AlertDialog.Builder(this,
                    android.R.style.Theme_Material_Dialog_Alert);
        } else {
            acknowledgementsDialogBuilder = new AlertDialog.Builder(this);
        }

        // 2. Chain together various setter methods to set the dialog characteristics
        acknowledgementsDialogBuilder
                .setMessage(getResources().getString(R.string.actual_acknowledgements))
                .setTitle(getResources().getString(R.string.acknowledgements_title))
                .setCancelable(true);

        acknowledgementsDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        // 3. Get the AlertDialog from create()
        final AlertDialog acknowledgementDialog = acknowledgementsDialogBuilder.create();

        final Button ack = findViewById(R.id.ack);
        ack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                acknowledgementDialog.create();
                acknowledgementDialog.show();
            }
        });
    }

    public void voiceRecog() {

        // Create an Intent using RecognizerIntent for speech Recognition
        Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

        // Identify your application
        i.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getClass().getPackage().getName());

        TextView displayHint = (TextView) findViewById(R.id.displayHint);
        i.putExtra(RecognizerIntent.EXTRA_PROMPT, displayHint.getText().toString());

        // Use the Speech Recognition model
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH);

        //Start the Voice recognizer activity for the result.
        try {
//            if (i.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(i, REQUEST_CODE);
//            }
        } catch (ActivityNotFoundException e) {
            Log.e("ACTIVITY_NOT_FOUND", e.getMessage());
            Toast.makeText(this, "No google application found to record voice input", Toast.LENGTH_LONG).show();
            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.google.android.googlequicksearchbox")));
            } catch (Exception exception) {
                Log.e("Playstore_NOT_INSTALLED", exception.getMessage());
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.googlequicksearchbox")));
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        //TextView to display top 3 results depending on confidence score
        TextView t = (TextView) findViewById(R.id.resultV);


        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {

            ArrayList<String> addResultToArrayList = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

            if (addResultToArrayList != null) {
                Log.e("ARRAY", addResultToArrayList.toString());

//                    if (addResultToArrayList.get(0).contains("search")) {
                //Get the 1st from ArrayList
//                        String searchTheNet = addResultToArrayList.get(0);
//                        Log.e("searchTheNet: ", addResultToArrayList.get(0));
//                        searchTheNet = searchTheNet.replace("search", "");
//                        Log.e("searchTheNet: \t", searchTheNet);
                //Send a web search intent
//                        Intent searchIntent = new Intent(Intent.ACTION_WEB_SEARCH);
//                        searchIntent.putExtra(SearchManager.QUERY, searchTheNet);
//                        startActivity(searchIntent);
//                    }
                float[] confidence = data.getFloatArrayExtra(RecognizerIntent.EXTRA_CONFIDENCE_SCORES);

                //Display The result with its confidence score
                if (addResultToArrayList.size() >= 3 && confidence.length >= 3) {
                    for (int i = 0; i < addResultToArrayList.size(); i++) {
                        t.append(addResultToArrayList.get(i));
                        t.append(".\tConfidence Score: " + String.valueOf(confidence[i]) + "\n\n");
                    }
                }

            }
        }
    }

}
