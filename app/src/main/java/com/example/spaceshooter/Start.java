package com.example.spaceshooter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;

public class Start extends AppCompatActivity {
    MediaPlayer player;
    boolean musicPlay = true;
    Button saveStateButton;
    Switch switchButton;

    public static final String SHARED_PREFS="sharedPrefs";
    public static final String SWITCHBUTTON = "switchButton";

    boolean switchOnOff;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        play();
        setContentView(R.layout.start);
        saveStateButton = (Button) findViewById(R.id.save_state_butt);
        switchButton = (Switch) findViewById(R.id.switch_butt);

        saveStateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveState();
            }
        });

        loadState();
        updateValues();
    }

    public void saveState(){
        SharedPreferences SharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        android.content.SharedPreferences.Editor editor = SharedPreferences.edit();

        editor.putBoolean(SWITCHBUTTON,switchButton.isChecked());
        editor.apply();
        Toast.makeText(this,"data saved",Toast.LENGTH_SHORT).show();
    }

    public void loadState(){
        SharedPreferences SharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        switchOnOff = SharedPreferences.getBoolean(SWITCHBUTTON,false);
    }

    public void updateValues(){
        switchButton.setChecked(switchOnOff);
    }


    public void play(){
        if(player == null){
            player = MediaPlayer.create(this,R.raw.song2);
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    stopPlayer();
                }
            });
        }

        player.start();
    }

    private void stopPlayer(){
        if(player != null){
            player.release();
            player=null;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopPlayer();
    }

    public void startGame(View view) {
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }
}
