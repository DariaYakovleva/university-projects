package ru.ifmo.md.lesson1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Random;

/**
 * Created by thevery on 11/09/14.
 */
class WhirlView extends SurfaceView implements Runnable {
    int[] field = null;
    int width = 0;
    int height = 0;
    int[] field2 = null;
    int[] colors = null;
    public static final int MAX_COLOR = 10;
    public static final int STD_WIDTH = 240;
    public static final int STD_HEIGHT = 320;
    int[] palette = {0xFFFF0000, 0xFF800000, 0xFF808000, 0xFF008000, 0xFF00FF00, 0xFF008080, 0xFF0000FF, 0xFF000080, 0xFF800080, 0xFFFFFFFF};
    SurfaceHolder holder;
    Thread thread = null;
    volatile boolean running = false;
    boolean ok = false;
   // Thread draw = new Thread();
   // Thread upd = new Thread();

    public WhirlView(Context context) {
        super(context);
        holder = getHolder();
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


    public void run() {
//        draw.start();
 //       upd.start();
        while (running) {
            if (holder.getSurface().isValid()) {
                long startTime = System.nanoTime();
                Canvas canvas = holder.lockCanvas();
                updateField();
                onDraw(canvas);
                holder.unlockCanvasAndPost(canvas);
                long finishTime = System.nanoTime();
                Log.i("TIME", "Circle: " + (finishTime - startTime) / 1000000);
                try {
                    Thread.sleep(1);
                } catch (InterruptedException ignore) {
                }
            }
        }
    }

    @Override
    public void onSizeChanged(int w, int h, int oldW, int oldH) {
        width = w;
        height = h;
        initField();
    }

    void initField() {
        field = new int[STD_WIDTH * STD_HEIGHT];
        field2 = new int[STD_WIDTH * STD_HEIGHT];
        colors = new int[STD_WIDTH * STD_HEIGHT];
        Random rand = new Random();
        for (int x = 0; x < STD_WIDTH; x++) {
            for (int y = 0; y < STD_HEIGHT; y++) {
                field[y * STD_WIDTH + x] = rand.nextInt(MAX_COLOR);
            }
        }
    }
    void updateField() {
        for (int x = 0; x < STD_WIDTH; x++) {
            for (int y = 0; y < STD_HEIGHT; y++) {
                int tmp = field[y * STD_WIDTH + x] + 1;
                field2[y * STD_WIDTH + x] = field[y * STD_WIDTH + x];
                ok = false;

                for (int dx = 1; dx >= -1; dx--) {
                    if (ok) break;
                    int x2 = x + dx;
                    if (x2 < 0) x2 += STD_WIDTH;
                    if (x2 >= STD_WIDTH) x2 -= STD_WIDTH;

                    for (int dy = 1; dy >= -1; dy--) {
                        int y2 = y + dy;
                        if (y2 < 0) y2 += STD_HEIGHT;
                        if (y2 >= STD_HEIGHT) y2 -= STD_HEIGHT;
                        if (tmp >= MAX_COLOR) tmp -= MAX_COLOR;
                        if (tmp == field[y2 * STD_WIDTH + x2]) {
                            field2[y * STD_WIDTH + x] = field[y2 * STD_WIDTH + x2];
                            ok = true;
                            break;
                        }
                    }
                }
            }
        }
        int[] tmp = field;
        field = field2;
        field2 = tmp;

    }

    Rect dst;
    Bitmap image;
    @Override
    public void onDraw(Canvas canvas) {
        if (dst == null) {
            dst = new Rect(0, 0, width, height);
        }
        if (image == null) {
            image = Bitmap.createBitmap(STD_WIDTH,  STD_HEIGHT, Bitmap.Config.RGB_565);
        }
        for (int x = 0; x < STD_WIDTH; x++) {
            for (int y = 0; y < STD_HEIGHT; y++) {
                colors[y * STD_WIDTH + x] = palette[field[y * STD_WIDTH + x]];
            }
        }
        image.setPixels(colors, 0, STD_WIDTH, 0, 0, STD_WIDTH,  STD_HEIGHT);
        canvas.drawBitmap(image, null, dst, null);

    }
}
