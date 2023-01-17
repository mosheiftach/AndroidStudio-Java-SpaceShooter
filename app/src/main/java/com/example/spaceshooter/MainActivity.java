package com.example.spaceshooter;

import static com.example.spaceshooter.R.color.white;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ImageButton;



public class MainActivity extends AppCompatActivity {
    MediaPlayer player;
    boolean musicPlay = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout newLay = new FrameLayout(this);
        ImageButton butt = new ImageButton(this);
        butt.setBackgroundResource(R.drawable.sound);
        butt.setLayoutParams(new LinearLayout.LayoutParams(70, 70));
        butt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(!musicPlay){
                    musicPlay = !musicPlay;
                    play();
                }else{
                    stopPlayer();
                    musicPlay=!musicPlay;
                }
            }
        });
        play();
        newLay.addView(new Spaceship(this));
        newLay.addView(butt);
        setContentView(newLay);
        //setContentView(new Spaceship(this));
    }

    public void play(){
        if(player == null){
            player = MediaPlayer.create(this,R.raw.song);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}