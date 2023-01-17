package com.example.spaceshooter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.content.Context;

public class SpaceshipFire {
    Bitmap fire;
    Context context;
    int fx,fy;

    public SpaceshipFire(Context context, int x,int y){
        this.context=context;
        fire =BitmapFactory.decodeResource(context.getResources(),R.drawable.shot);
        this.fx=x;
        this.fy=y;
    }

    public Bitmap getFire(){
        return fire;
    }

    public int getFireWidth(){
        return fire.getWidth();
    }

    public int getFireHeight(){
        return fire.getHeight();
    }
}
