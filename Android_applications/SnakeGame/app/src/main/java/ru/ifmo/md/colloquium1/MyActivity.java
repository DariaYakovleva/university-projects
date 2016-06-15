package ru.ifmo.md.colloquium1;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class MyActivity extends Activity implements Runnable {

    ImageView image;
    Button left;
    Button right;
    Button restart;
    TextView scoreText;
    public static final int MAX_COLOR = 2;
    public static final int STD_WIDTH = 40;
    public static final int STD_HEIGHT = 60;
    int width;
    int height;
    int[] palette = {0xFFFFFFFF, 0x00FF0000, 0x0000FF00};
    int[] colors = null;
    int[] field = null;
    int headx;
    int heady;
    int score = 0;
    char move; //tblr
    boolean running = true;
    Thread thread = null;
    List<Integer> snake = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        image = (ImageView) findViewById(R.id.imageView);
        left = (Button) findViewById(R.id.button1);
        right = (Button) findViewById(R.id.button2);
        restart = (Button) findViewById(R.id.button3);
        scoreText = (TextView) findViewById(R.id.textView);
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (move == 't') {
                    move = 'l';
                } else if (move == 'b') {
                    move = 'r';
                } else if (move == 'l'){
                    move = 'b';
                } else {
                    move = 't';
                }
            }
        });
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (move == 't') {
                    move = 'r';
                } else if (move == 'b') {
                    move = 'l';
                } else if (move == 'l'){
                    move = 't';
                } else {
                    move = 'b';
                }
            }
        });
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                running = false;
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                thread = new Thread(MyActivity.this);
                running = true;
                score = 0;
                thread.start();
            }
        });
        width = 300;
        height = 400;
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        initSnake();
        while (running) {
            updateSnake();
            try {
                thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                image.setImageBitmap(image2);
                scoreText.setText("Game Over! Score: " + score);
            }
        });

    }

    public void resume() {
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public void pause() {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException ignore) {
        }
    }

    void initSnake() {
        headx = 0;
        heady = 2;
        snake.clear();
        field = new int[STD_WIDTH * STD_HEIGHT];
        colors = new int[STD_WIDTH * STD_HEIGHT];
        Random rand = new Random();
        for (int x = 0; x < STD_WIDTH * STD_HEIGHT; x++) {
            field[x] = 0;
        }
        field[0] = 2;
        field[1] = 2;
        field[2] = 2;
        for (int i = 0; i < 50; i++) {
            boolean upd = true;
            while (upd) {
                int meal = rand.nextInt(STD_HEIGHT * STD_WIDTH);
                if (field[meal] == 0) {
                    upd = false;
                    field[meal] = 1;
                }
            }
        }
        snake.add(0);
        snake.add(1);
        snake.add(2);
        move = 'l';
        snakeDraw();
    }

    void updateSnake() {
        int nHeadX = headx;
        int nHeadY = heady;

        if (move == 't') {
            nHeadY = heady - 1;
        } else if (move == 'b') {
            nHeadY = heady + 1;
        } else if (move == 'l') {
            nHeadX = headx - 1;
        } else {
            nHeadX = headx + 1;
        }
        nHeadX = (nHeadX + STD_WIDTH) % STD_WIDTH;
        nHeadY = (nHeadY + STD_HEIGHT) % STD_HEIGHT;
        if (field[nHeadY * STD_WIDTH + nHeadX] == 0) {
            field[snake.get(0)] = 0;
            snake.remove(0);
        } else if (field[nHeadY * STD_WIDTH + nHeadX] == 2) {
            running = false;
        } else {
            score += 1;
            Random rand = new Random();
            boolean upd = true;
            while (upd) {
                int meal = rand.nextInt(STD_HEIGHT * STD_WIDTH);
                if (field[meal] == 0) {
                    upd = false;
                    field[meal] = 1;
                }
            }
        }
        headx = nHeadX;
        heady = nHeadY;
        field[nHeadY * STD_WIDTH + nHeadX] = 2;
        snake.add(nHeadY * STD_WIDTH + nHeadX);
        snakeDraw();
    }

    Bitmap image2, image3;
    Rect dst;

    public void snakeDraw() {
        if (dst == null) {
            dst = new Rect(0, 0, width, height);
        }
        if (image2 == null) {
            image2 = Bitmap.createBitmap(STD_WIDTH, STD_HEIGHT, Bitmap.Config.RGB_565);
        }
        for (int x = 0; x < STD_WIDTH; x++) {
            for (int y = 0; y < STD_HEIGHT; y++) {
                colors[y * STD_WIDTH + x] = palette[field[y * STD_WIDTH + x]];
            }
        }
        image2.setPixels(colors, 0, STD_WIDTH, 0, 0, STD_WIDTH, STD_HEIGHT);
        image3 = Bitmap.createScaledBitmap(image2, width, height, false);



        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                image.setImageBitmap(image3);
                scoreText.setText("Score: " + score);
            }
        });
    }
}
