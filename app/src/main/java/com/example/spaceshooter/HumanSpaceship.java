package com.example.spaceshooter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.content.Context;
import java.util.Random;


public class HumanSpaceship {
    Context context;
    Bitmap humanSpaceship;
    int hx,hy;
    Random random;

    public HumanSpaceship(Context context){
        this.context=context;
        this.humanSpaceship=BitmapFactory.decodeResource(context.getResources(),R.drawable.rocket);
        this.random= new Random();
        hx=random.nextInt(Spaceship.screenWidth);
        hy = Spaceship.screenHeight -humanSpaceship.getHeight();
    }

    public Bitmap getHumanSpaceship(){
        return humanSpaceship;
    }

    int getHumanSpaceshipWidth(){
        return humanSpaceship.getWidth();
    }

    int getHumanSpaceshipHeight(){
        return humanSpaceship.getHeight();
    }
}
