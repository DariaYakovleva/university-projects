package lesson7.md.ifmo.ru.filemanagerapp;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;

import java.io.File;


public class FileActivity extends Activity {
    public static String EXTRA_PATH = "path";
    private File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);
        String extraValue = getIntent().getStringExtra(EXTRA_PATH);
        if (TextUtils.isEmpty(extraValue)) {
            throw new IllegalStateException("Extra is null!");
        }
        file = new File(extraValue);
        getActionBar().setTitle(file.getName());
    }
}
