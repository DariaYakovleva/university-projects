package lesson7.md.ifmo.ru.filemanagerapp;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by akarimova on 27.10.14.
 */
public class FMLoader extends AsyncTaskLoader<ArrayList<File>> {
    private String path;

    public FMLoader(String path, Context context) {
        super(context);
        this.path = path;
    }

    @Override
    public ArrayList<File> loadInBackground() {
        File file = new File(path);
        File fileList[] = file.listFiles();
        if (fileList != null) {
            return new ArrayList<File>(Arrays.asList(fileList));
        } else {
            return new ArrayList<File>();
        }
    }
}
