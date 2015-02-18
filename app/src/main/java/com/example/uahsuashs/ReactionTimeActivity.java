package com.example.uahsuashs;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.SystemClock;
import android.os.Vibrator;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cognetive.app.Game_Stroop;

import java.util.Timer;
import java.util.TimerTask;


public class ReactionTimeActivity extends ActionBarActivity {
    private Timer timer = new Timer();
    private long startTime = 0L;
    private long clickTime = 0L;
    private long reactionTime = 0L;
    private long reactionTimeSum;
    private int testCounter = 0;
    boolean timerStatus = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reaction_time);
        android.app.ActionBar actionBar = getActionBar();
        actionBar.hide();

//        Intent k = new Intent(MainActivity.this, TestEnd.class);
//        startActivity(k);

        Button beginButton = (Button) findViewById(R.id.beginButton);
        beginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                beginTest("");
            }
        });

    }

    private void beginTest(String t) {
        final Button beginButton = (Button) findViewById(R.id.beginButton);
        final RelativeLayout background = (RelativeLayout) findViewById(R.id.background);
        final TextView reactionTimeText = (TextView) findViewById(R.id.testReactionTime);
        final TextView time = (TextView) findViewById(R.id.time);
        final TextView infoText = (TextView) findViewById(R.id.infoText);

        timerStatus = false;

        beginButton.setVisibility(View.INVISIBLE);

        reactionTimeText.setText(t);

        this.timer.cancel();
        this.timer = new Timer();

        TimerTask action = new TimerTask() {
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        reactionTimeText.setVisibility(View.INVISIBLE);
                        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        v.vibrate(100);
                        background.setBackgroundColor(Color.GREEN);
                        startTime = SystemClock.uptimeMillis();
                        timerStatus = true;
                    }
                });
            }

        };

        this.timer.schedule(action, 1500); //this starts the task

        background.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                if (timerStatus == true) {
                    reactionTimeText.setVisibility(View.INVISIBLE);
                    background.setClickable(true);
                    clickTime = SystemClock.uptimeMillis();
                    reactionTime = clickTime - startTime;
                    reactionTimeSum += reactionTime;
                    testCounter++;

                    time.setText("Time:" + reactionTime);
                    background.setBackgroundColor(Color.RED);
                    beginButton.setVisibility(View.VISIBLE);
                    beginButton.setText("Start test "+(testCounter+1)+"/3");
                    background.setClickable(false);
                    if(testCounter==3){
                        infoText.setVisibility(View.INVISIBLE);
                        reactionTimeText.setVisibility(View.VISIBLE);
                        reactionTimeText.setText("Test completed, your average reaction time is: " + (reactionTimeSum / 3));
                        beginButton.setText("next test");
                        beginButton.setOnClickListener(new View.OnClickListener() {
                            Intent intent = new Intent(getApplicationContext(), Game_Stroop.class);
                            public void onClick(View v) {

                                startActivity(intent);
                            }
                        });
                        time.setVisibility(View.INVISIBLE);
                        background.setClickable(false);
                    }
                } else {
                    reactionTimeText.setVisibility(View.VISIBLE);
                    beginTest("To early, wait until the screen turns green!");
                }
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_reaction_time, menu);
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

}

