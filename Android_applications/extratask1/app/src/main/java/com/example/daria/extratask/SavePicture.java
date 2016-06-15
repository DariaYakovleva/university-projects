package com.example.daria.extratask;

import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * Created by Daria on 17.01.2015.
 */
public class SavePicture {
    private static final String LOG_TAG = "savePicture";

    public SavePicture() {

    }
    public String savePicture(Bitmap image, String name, String folderToSave) {
        try {
            Log.d(LOG_TAG, "start saving image");
            OutputStream fOut = null;
            File file = new File(folderToSave, name);
            fOut = new FileOutputStream(file);
            image.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
            fOut.flush();
            fOut.close();
//            MediaStore.Images.Media.insertImage(getContentResolver(), file.getAbsolutePath(), file.getName(),  file.getName());
            Log.d(LOG_TAG, "image save " + folderToSave + name);
            return name;
        }
        catch (Exception e)
        {
            Log.d(LOG_TAG, e.getMessage());
            return e.getMessage();
        }

    }
}
