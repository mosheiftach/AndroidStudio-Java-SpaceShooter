package com.example.spaceshooter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.content.Context;
import java.util.Random;

public class Alien {
    Context context;
    Bitmap alienSpaceship;
    int ax,ay;
    int aMove;
    Random random;

    public Alien(Context context){
        this.context=context;
        this.alienSpaceship=BitmapFactory.decodeResource(context.getResources(),R.drawable.alien);
        this.random= new Random();
        ax=200+random.nextInt(400);
        ay = 0;
        aMove = 14+ random.nextInt(10);
    }

    public Bitmap getAlienSpaceship(){
        return alienSpaceship;
    }

    int getAlienSpaceshipWidth(){
        return alienSpaceship.getWidth();
    }

    int getAlienSpaceshipHeight(){
        return alienSpaceship.getHeight();
    }
}
