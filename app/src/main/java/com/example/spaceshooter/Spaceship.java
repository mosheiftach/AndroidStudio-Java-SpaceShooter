package com.example.spaceshooter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Handler;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Random;

public class Spaceship extends View {
    Context context;
    Bitmap background, lifeImage;
    Handler handler;
    Button sound;
    long refreshTime = 25;
    static int screenWidth, screenHeight;
    int points = 0;
    int life = 3;
    Paint scorePaint;
    int TEXT_SIZE = 80;
    boolean paused = false;
    HumanSpaceship humanSpaceship;
    Alien alienSpaceship;
    Random random;
    ArrayList<SpaceshipFire> alienShots, humanSpaceShipShots;
    Explosions explosion;
    ArrayList<Explosions> explosions;
    boolean alienShotAction = false;

    final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            invalidate();
        }
    };

    public Spaceship(Context context) {
        super(context);
        this.context = context;
        Display display = ((Activity) getContext()).getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;
        random = new Random();
        alienShots = new ArrayList<>();
        humanSpaceShipShots = new ArrayList<>();
        explosions = new ArrayList<>();
        humanSpaceship = new HumanSpaceship(context);
        alienSpaceship = new Alien(context);
        handler = new Handler();
        background = BitmapFactory.decodeResource(context.getResources(), R.drawable.back);
        lifeImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.heart);
        scorePaint = new Paint();
        scorePaint.setColor(Color.RED);
        scorePaint.setTextSize(TEXT_SIZE);
        scorePaint.setTextAlign(Paint.Align.LEFT);
        sound = new Button(context);
        sound.setText("asdasd");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(background, 0, 0, null);
        canvas.drawText("Score: " + points, 73, TEXT_SIZE, scorePaint);
        for(int i=life; i>=1; i--){
            canvas.drawBitmap(lifeImage, screenWidth - lifeImage.getWidth() * i, 0, null);
        }
        // check if life == 0 and go to the EndGame page
        if(life == 0){
            paused = true;
            handler = null;
            Intent intent = new Intent(context, EndGame.class);
            intent.putExtra("points", points);
            context.startActivity(intent);
            ((Activity) context).finish();
        }
        // standard move for AlienSpaceship
        alienSpaceship.ax += alienSpaceship.aMove;
        // right end side reached Alien spaceship change direction
        if(alienSpaceship.ax + alienSpaceship.getAlienSpaceshipWidth() >= screenWidth){
            alienSpaceship.aMove *= -1;
        }
        // left end side reached Alien spaceship change direction
        if(alienSpaceship.ax <=0){
            alienSpaceship.aMove *= -1;
        }
        // if false shot from a random dist a shot
        if(alienShotAction == false){
            if(alienSpaceship.ax >= 200 + random.nextInt(400)){
                SpaceshipFire enemyShot = new SpaceshipFire(context, alienSpaceship.ax + alienSpaceship.getAlienSpaceshipWidth() / 2, alienSpaceship.ay );
                alienShots.add(enemyShot);
                alienShotAction = true;
            }
            if(alienSpaceship.ax >= 400 + random.nextInt(800)){
                SpaceshipFire enemyShot = new SpaceshipFire(context, alienSpaceship.ax + alienSpaceship.getAlienSpaceshipWidth() / 2, alienSpaceship.ay );
                alienShots.add(enemyShot);
                alienShotAction = true;
            }
            else{
                SpaceshipFire enemyShot = new SpaceshipFire(context, alienSpaceship.ax + alienSpaceship.getAlienSpaceshipWidth() / 2, alienSpaceship.ay );
                alienShots.add(enemyShot);
                alienShotAction = true;
            }
        }
        // Draw the alien space ship
        canvas.drawBitmap(alienSpaceship.getAlienSpaceship(), alienSpaceship.ax, alienSpaceship.ay, null);
        // Draw our spaceship between the left and right edge of the screen
        if(humanSpaceship.hx > screenWidth - humanSpaceship.getHumanSpaceshipWidth()){
            humanSpaceship.hx = screenWidth -humanSpaceship.getHumanSpaceshipWidth();
        }else if(humanSpaceship.hx < 0){
            humanSpaceship.hx = 0;
        }
        //draw the HumanSpaceship
        canvas.drawBitmap(humanSpaceship.getHumanSpaceship(), humanSpaceship.hx, humanSpaceship.hy, null);
        // enemy shot to our spaceship
        for(int i=0; i < alienShots.size(); i++){
            alienShots.get(i).fy += 15;
            canvas.drawBitmap(alienShots.get(i).getFire(), alienShots.get(i).fx, alienShots.get(i).fy, null);
            if((alienShots.get(i).fx >= humanSpaceship.hx)
                    && alienShots.get(i).fx <= humanSpaceship.hx + humanSpaceship.getHumanSpaceshipWidth()
                    && alienShots.get(i).fy >= humanSpaceship.hy
                    && alienShots.get(i).fy <= screenHeight){
                life--;
                alienShots.remove(i);
                explosion = new Explosions(context, humanSpaceship.hx, humanSpaceship.hy);
                explosions.add(explosion);
            }else if(alienShots.get(i).fy >= screenHeight){
                alienShots.remove(i);
            }
            if(alienShots.size() < 1){
                alienShotAction = false;
            }
        }
        // Our shot to enemy spaceship
        for(int i=0; i < humanSpaceShipShots.size(); i++){
            humanSpaceShipShots.get(i).fy -= 15;
            canvas.drawBitmap(humanSpaceShipShots.get(i).getFire(), humanSpaceShipShots.get(i).fx, humanSpaceShipShots.get(i).fy, null);
            if((humanSpaceShipShots.get(i).fx >= alienSpaceship.ax)
                    && humanSpaceShipShots.get(i).fx <= alienSpaceship.ax + alienSpaceship.getAlienSpaceshipWidth()
                    && humanSpaceShipShots.get(i).fy <= alienSpaceship.getAlienSpaceshipWidth()
                    && humanSpaceShipShots.get(i).fy >= alienSpaceship.ay){
                points++;
                humanSpaceShipShots.remove(i);
                explosion = new Explosions(context, alienSpaceship.ax, alienSpaceship.ay);
                explosions.add(explosion);
            }else if(humanSpaceShipShots.get(i).fy <=0){
                humanSpaceShipShots.remove(i);
            }
        }
        // explosions
        for(int i=0; i < explosions.size(); i++){
            canvas.drawBitmap(explosions.get(i).getExplosions(explosions.get(i).frame), explosions.get(i).ex, explosions.get(i).ey, null);
            explosions.get(i).frame++;
            if(explosions.get(i).frame > 8){
                explosions.remove(i);
            }
        }
        // refresh the the draw each refreshTime
        if(!paused)
            handler.postDelayed(runnable, refreshTime);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int touchX = (int)event.getX();
        // check if arrayList <1 then create a shot when releasing the touch
        if(event.getAction() == MotionEvent.ACTION_UP){
            if(humanSpaceShipShots.size() < 1){
                SpaceshipFire ourShot = new SpaceshipFire(context, humanSpaceship.hx + humanSpaceship.getHumanSpaceshipWidth() / 2, humanSpaceship.hy);
                humanSpaceShipShots.add(ourShot);
            }
        }
        // while pressing on screen make HumanSpaceship movement
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            humanSpaceship.hx = touchX;
        }
        // while keep pressing, change the location of HumanSpaceship according to touch movement on screen
        if(event.getAction() == MotionEvent.ACTION_MOVE){
            humanSpaceship.hx = touchX;
        }

        return true;    }
}
