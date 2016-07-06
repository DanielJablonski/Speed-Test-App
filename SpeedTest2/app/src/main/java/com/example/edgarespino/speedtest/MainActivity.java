package com.example.edgarespino.speedtest;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements MainFragment.OnMainFragmentInteractionListener
{

    TextView textView, tv2;
    long startTime, endTime, duration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        android.support.v4.app.FragmentManager manager= getSupportFragmentManager();
        android.support.v4.app.Fragment fragment = manager.findFragmentById(R.id.fragment_container);

        if(fragment == null){
            fragment = new MainFragment();
            manager.beginTransaction().add(R.id.fragment_container, fragment).commit();

        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        textView = (TextView) findViewById(R.id.textView);


        final Button btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {
                startTime = System.currentTimeMillis();
                textView.setVisibility(View.VISIBLE);
                btn.setVisibility(View.INVISIBLE);



            }
        });

        Button btn2 = (Button) findViewById(R.id.button2);
        btn2.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {

                TimeStringBuilder time = new TimeStringBuilder();

                endTime = System.currentTimeMillis();
                duration = (endTime - startTime) / 1000;
                float wpm = 604 / ((float) duration / 60);

                //This creates a dialog box that displays the results
                new AlertDialog.Builder(MainActivity.this,R.style.AlertDialogCustom)
                        .setTitle("Results")

                        .setMessage("You read this passage in " + time.getTimeDurationStringFromMinutesFloat((float)duration / 60) + " and at " + Float.toString((long)wpm) + " wpm. You should sign up for one of our classes to improve upon that number. Click OK to continue to our website.")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                String urlString="http://www.irisreading.com";
                                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse(urlString));
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.setPackage("com.android.chrome");
                                try {
                                    MainActivity.this.startActivity(intent);
                                } catch (ActivityNotFoundException ex) {
                                    // Chrome browser presumably not installed so allow user to choose instead
                                    intent.setPackage(null);
                                    MainActivity.this.startActivity(intent);
                                }
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMainFragmentInteraction(Uri uri) {

    }
}
