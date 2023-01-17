package com.example.spaceshooter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.content.Context;
public class Explosions {
    Bitmap explosions[] = new Bitmap[9];
    int frame;
    int ex,ey;

    public Explosions (Context context, int x , int y){
        explosions[0] = BitmapFactory.decodeResource(context.getResources(),R.drawable.explo1);
        explosions[1] = BitmapFactory.decodeResource(context.getResources(),R.drawable.explo2);
        explosions[2] = BitmapFactory.decodeResource(context.getResources(),R.drawable.explo3);
        explosions[3] = BitmapFactory.decodeResource(context.getResources(),R.drawable.explo4);
        explosions[4] = BitmapFactory.decodeResource(context.getResources(),R.drawable.explo5);
        explosions[5] = BitmapFactory.decodeResource(context.getResources(),R.drawable.explo6);
        explosions[6] = BitmapFactory.decodeResource(context.getResources(),R.drawable.explo7);
        explosions[7] = BitmapFactory.decodeResource(context.getResources(),R.drawable.explo8);
        explosions[8] = BitmapFactory.decodeResource(context.getResources(),R.drawable.explo9);

        this.frame = 0;
        this.ex = x;
        this.ey = y;
    }

    public Bitmap getExplosions(int eFrame){
        return explosions[eFrame];
    }
}
