package com.cognetive.app;

import android.support.v7.app.ActionBarActivity;


import java.util.Random;
import java.util.concurrent.TimeUnit;


import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.os.Build;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;

import com.example.uahsuashs.R;

public class Game_Stroop extends ActionBarActivity {
    private static String[] colors = {"Red", "Blue", "Yellow", "Purple", "Black", "White", "Green", "Brown"};
    private static String[] colors_hex = {"#FF0000", "#4169E1", "#FFFF00", "#800080", "#00000A", "#FFFFF0", "#008000","#8B4513"};
    Activity activity;
    View mainView;
    TextView tvColor;
    Button leftBtn;
    Button rightBtn;
    long startTime;
    int attepmts = 20;
    Intent intent;
    String correctColor;
    int correctAnswers = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stroop);
        if (Build.VERSION.SDK_INT < 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        } else {
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
            android.app.ActionBar actionBar = getActionBar();
            actionBar.hide();
        }
        intent = new Intent(this, Result.class);
        mainView = this.getWindow().getDecorView();

        setRandomColor();

        // Set the color
        tvColor = (TextView) findViewById(R.id.textView1);
        //tvTimer = (TextView) findViewById(R.id.textView2);

        leftBtn = (Button) findViewById(R.id.button1);
        rightBtn = (Button) findViewById(R.id.button2);

        leftBtn.setOnClickListener(onClickListener);
        rightBtn.setOnClickListener(onClickListener);
        leftBtn.setVisibility(View.GONE);
        rightBtn.setVisibility(View.GONE);

        if (Build.VERSION.SDK_INT < 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        } else {
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
            android.app.ActionBar actionBar = getActionBar();
            actionBar.hide();
        }


        final TextView textic = (TextView) findViewById(R.id.textView1);

        CountDownTimer Count = new CountDownTimer(4000, 1000) {
            public void onTick(long millisUntilFinished) {
                textic.setText("" + millisUntilFinished / 1000);
            }

            public void onFinish() {
                textic.setText("GO!");
                createColorText();
                startTime = System.nanoTime();
                rightBtn.setVisibility(View.VISIBLE);
                leftBtn.setVisibility(View.VISIBLE);



            }
        };

        Count.start();

    }


    private OnClickListener onClickListener = new OnClickListener() {
        @TargetApi(Build.VERSION_CODES.GINGERBREAD)
        @SuppressLint("NewApi")
        @Override
        public void onClick(View v) {
            if(attepmts != 0){
                switch(v.getId()){
                    case R.id.button1:
                        String lText = (String) leftBtn.getText();
                        checkColor(lText);
                        createColorText();
                        attepmts = attepmts-1;
                        setRandomColor();
                        break;
                    case R.id.button2:
                        String lText2 = (String) rightBtn.getText();
                        checkColor(lText2);
                        createColorText();
                        attepmts = attepmts-1;
                        setRandomColor();

                        break;
                }
            }
            else{
                TextView textic = (TextView) findViewById(R.id.textView1);
                long endTime = System.nanoTime();
                long elapsedTime = endTime - startTime;

                String text = String.format("%d min, %d sec",
                        TimeUnit.NANOSECONDS.toMinutes(elapsedTime),
                        TimeUnit.NANOSECONDS.toSeconds(elapsedTime) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.NANOSECONDS.toMinutes(elapsedTime))
                );

                intent.putExtra("time", text);
                System.out.println(correctAnswers);
                intent.putExtra("correct", Integer.toString(correctAnswers));
                startActivity(intent);

            }


        }
    };

    public void game(){

    }

    public void setRandomColor(){
        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        mainView.setBackgroundColor(color);
    }
    public void checkColor(String color){
        if(color == correctColor){
            correctAnswers++;
        }
    }
    public void createColorText(){
        int randomHexNumber;
        int randText;

        do{
            Random rand = new Random();
            randomHexNumber = rand.nextInt(7 -0 + 1) + 0;
            randText = rand.nextInt(7 -0 +1) +0;

        }while(randomHexNumber == randText);

        String btnColorTextLeft = colors[randomHexNumber];
        String btnColorTextRight = colors[randText];

        changeBackgroundColor(colors_hex[randomHexNumber], randomHexNumber);
        changeColorText(colors[randText]);
        changeButtonText(btnColorTextLeft, btnColorTextRight);
    }

    public void changeButtonText(String button1, String button2){
        Random n = new Random();
        int i = n.nextInt(100 -0 +1) + 0;
        if ((i % 2) == 0) {
            rightBtn.setText(button1);
            leftBtn.setText(button2);

        }
        else {
            rightBtn.setText(button2);
            leftBtn.setText(button1);
        }
    }
    public void changeBackgroundColor(String color, int randomHexNumber){
        tvColor.setTextColor(Color.parseColor(color));
        correctColor = colors[randomHexNumber];
    }
    public void changeColorText(String text){
        tvColor.setText(text);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.stroop, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
