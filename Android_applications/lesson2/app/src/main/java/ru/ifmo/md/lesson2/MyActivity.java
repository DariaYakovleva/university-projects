package ru.ifmo.md.lesson2;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class MyActivity extends Activity {

    ImageButton imgButton;
    boolean change = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        super.onCreate(savedInstanceState);
        Bitmap realImage = BitmapFactory.decodeResource(getResources(), R.drawable.source);
//        toChangeImages(toCompressImageGood(realImage), toCompressImageFast(realImage));
//        toChangeImages(setBright(rotate(toCompressImageGood(realImage))), setBright(rotate(toCompressImageFast(realImage))));

        ListView list = new ListView(this);
        list.setAdapter(new ArrayAdapter(this, android.R.layout.activity_list_item, new String[]{"Anastasia", "Daria", "Marianna"}));
        setContentView(list);
    }


    public Bitmap toCompressImageFast(Bitmap imageBm) {
        int width = imageBm.getWidth();
        int height = imageBm.getHeight();
        int nwidth = width * 100 / 173;
        int nheight = height * 100 / 173;
        int[] pixels = new int[nwidth * nheight];
        for (int i = 0; i < nheight; i++) {
            for (int j = 0; j < nwidth; j++) {
                pixels[i * nwidth + j] = imageBm.getPixel(Math.min(width - 1, j * 173 / 100), Math.min(height - 1, i * 173 / 100));
            }
        }
        Bitmap compImage = Bitmap.createBitmap(pixels, nwidth, nheight, Bitmap.Config.ARGB_8888);
        return compImage;
    }

    public Bitmap toCompressImageGood(Bitmap imageBm) {
        int width = imageBm.getWidth();
        int height = imageBm.getHeight();
        int nwidth = width * 100 / 173;
        int nheight = height * 100 / 173;
        int[] pixels = new int[nwidth * nheight];
        int cur = 0;
        for (int i = 0; i < nheight; i++) {
            for (int j = 0; j < nwidth; j++) {
                int raverage = 0;
                int gaverage = 0;
                int baverage = 0;
                int alpha = 0;
                int cnt = 0;
                for (int k = Math.min(width - 1, j * 173 / 100); k <= Math.min(width - 1, (j + 1) * 173 / 100 + 1); k++)
                    for (int n = Math.min(height - 1, i * 173 / 100); n <= Math.min(height - 1, (i + 1) * 173 / 100 + 1); n++) {
                        int pixel = imageBm.getPixel(k, n);
                        raverage += Color.red(pixel);
                        gaverage += Color.green(pixel);
                        baverage += Color.blue(pixel);
                        alpha += Color.alpha(pixel);
                        cnt++;
                    }
                pixels[i * nwidth + j] = Color.argb(alpha / cnt, raverage / cnt, gaverage / cnt, baverage / cnt);
                cur++;
            }
        }
        Bitmap compImage = Bitmap.createBitmap(pixels, nwidth, nheight, Bitmap.Config.ARGB_8888);
        return compImage;
    }

    public Bitmap rotate(Bitmap imageBm) {
        int width = imageBm.getWidth();
        int height = imageBm.getHeight();
        int[] pixels = new int[width * height];
        int cur = 0;
        for (int i = width - 1; i >= 0; i--) {
            for (int j = height - 1; j >= 0; j--) {
                pixels[cur] = imageBm.getPixel(i, j);
                cur++;
            }
        }
        Bitmap rotImage = Bitmap.createBitmap(pixels, height, width, Bitmap.Config.ARGB_8888);
        return rotImage;
    }

    public void toChangeImages(final Bitmap img1, final Bitmap img2) {
        imgButton = (ImageButton) findViewById(R.id.imageButton);
        imgButton.setImageBitmap(img2);
        imgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (change) {
                    imgButton.setImageBitmap(img1);
                } else {
                    imgButton.setImageBitmap(img2);
                }
                change = !change;
            }
        });

    }

    public Bitmap setBright(Bitmap imageBm) {
        Bitmap imageBm2 = Bitmap.createBitmap(imageBm.getWidth(), imageBm.getHeight(), Bitmap.Config.ARGB_8888);
        for (int i = 0; i < imageBm.getHeight(); i++) {
            for (int j = 0; j < imageBm.getWidth(); j++) {
                int color = imageBm.getPixel(j, i);
                int red = Color.red(color);
                int green = Color.green(color);
                int blue = Color.blue(color);
                red = Math.min(0xff, (red + 10) * 3 / 2);
                green = Math.min(0xff, (green + 10) * 3 / 2);
                blue = Math.min(0xff, (blue + 10) * 3 / 2);
                imageBm2.setPixel(j, i, Color.argb(Color.alpha(color), red, green, blue));
            }
        }
        return imageBm2;
    }


}
