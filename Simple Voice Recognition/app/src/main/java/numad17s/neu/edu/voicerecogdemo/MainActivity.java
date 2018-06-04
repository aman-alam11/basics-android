package numad17s.neu.edu.voicerecogdemo;

import android.app.SearchManager;
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
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    public Vibrator v1;
    public static int REQUEST_CODE_ACTIVITY_RECOG = 100;
    ImageButton microphoneButtonIcon;
    TextView displayHint;
    TextView resultTextView;
    ArrayList<String> addVoiceResultToArrayList;
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Voice Recognition Demo");

        displayHint = findViewById(R.id.displayHint);

        //TextView to display top 3 results depending on confidence score
        resultTextView = (TextView) findViewById(R.id.resultV);

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


    /**
     * Recognize the voice
     */
    public void voiceRecog() {

        // Create an Intent using RecognizerIntent for speech Recognition
        Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

        // Identify your application
        i.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getClass().getPackage().getName());


        i.putExtra(RecognizerIntent.EXTRA_PROMPT, displayHint.getText().toString());

        // Use the Speech Recognition model
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH);

        //Start the Voice recognizer activity for the result.
        try {
            if (i.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(i, REQUEST_CODE_ACTIVITY_RECOG);
            } else {
                Log.e(TAG, "Unable to resolve Activity to start intent for Speech Recognition");
            }
        } catch (ActivityNotFoundException e) {
            Log.e(TAG, "ACTIVITY_NOT_FOUND" + e.getMessage());
            Toast.makeText(this, "No google application found to record voice input",
                    Toast.LENGTH_LONG).show();
            try {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("market://details?id=com.google.android.googlequicksearchbox")));
            } catch (Exception exception) {
                Log.e(TAG, "Playstore NOT INSTALLED: " + exception.getMessage());
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.googlequicksearchbox")));
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_CODE_ACTIVITY_RECOG && resultCode == RESULT_OK) {

            addVoiceResultToArrayList = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

            if (addVoiceResultToArrayList != null && addVoiceResultToArrayList.size() > 0) {
                float[] confidence = data.getFloatArrayExtra(RecognizerIntent.EXTRA_CONFIDENCE_SCORES);
                Log.e(TAG, Arrays.toString(data.getFloatArrayExtra(RecognizerIntent.EXTRA_CONFIDENCE_SCORES)));
                //Display The result with its confidence score
                for (int i = 0; i < addVoiceResultToArrayList.size(); i++) {
                    resultTextView.append(addVoiceResultToArrayList.get(i));
                    resultTextView.append(".\tConfidence Score: " + String.valueOf(confidence[i]) + "\n\n");
                }
            }

        }//End of requestCode and resultCode check if block
    }//End of onActivityResult

}//End of MainActivity
