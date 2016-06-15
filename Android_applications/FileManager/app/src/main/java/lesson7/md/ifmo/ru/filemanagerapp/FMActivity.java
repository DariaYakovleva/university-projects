package lesson7.md.ifmo.ru.filemanagerapp;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class FMActivity extends Activity implements LoaderManager.LoaderCallbacks<ArrayList<File>>, AdapterView.OnItemClickListener {
    public static final String EXTRA_DIR_PATH = "dir_path";
    private FileListAdapter adapter;
    private ListView listView;
    private String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fm);
        path = getIntent().getStringExtra(EXTRA_DIR_PATH);
        if (TextUtils.isEmpty(path)) {
            path = Environment.getExternalStorageDirectory().toString();
        }
        listView = (ListView) findViewById(android.R.id.list);
        listView.setOnItemClickListener(this);
        Loader<ArrayList<File>> arrayListLoader = getLoaderManager().initLoader(0, null, this);
        arrayListLoader.forceLoad();
    }

    @Override
    public Loader<ArrayList<File>> onCreateLoader(int id, Bundle args) {
        return new FMLoader(path, this);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<File>> loader, ArrayList<File> data) {
        if (adapter == null) {
            adapter = new FileListAdapter(this, data);
            listView.setAdapter(adapter);
        } else {
            adapter.setData(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<File>> loader) {
        adapter.setData(null);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        File file = (File) adapter.getItem(position);
        Intent intent;
        if (file.isFile()) {
//            intent = new Intent(this, FileActivity.class);
//            intent.putExtra(FileActivity.EXTRA_PATH, file.getPath());
            try {
                FileOpen.openFile(this, file);
            } catch (IOException e) {
                Toast.makeText(this, R.string.no_activity_found_to_handle, Toast.LENGTH_SHORT).show();
            }
        } else {
            intent = new Intent(this, FMActivity.class);
            intent.putExtra(FMActivity.EXTRA_DIR_PATH, file.getPath());
            startActivity(intent);
        }
    }
}
