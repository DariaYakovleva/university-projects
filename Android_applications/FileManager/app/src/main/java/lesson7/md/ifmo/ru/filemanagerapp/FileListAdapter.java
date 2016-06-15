package lesson7.md.ifmo.ru.filemanagerapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.io.File;
import java.text.DateFormat;
import java.util.List;

/**
 * Created by akarimova on 28.10.14.
 */
public class FileListAdapter extends BaseAdapter {
    private static final int DIR_VIEW_TYPE = 0;
    private static final int FILE_VIEW_TYPE = 1;
    private LayoutInflater inflater;
    private List<File> data;
    private DateFormat dateFormat;

    public FileListAdapter(Context context, List<File> newData) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        data = newData; // better to copy
        dateFormat = android.text.format.DateFormat.getDateFormat(context);
    }

    public void setData(List<File> newFileList) {
        data = newFileList;
        notifyDataSetChanged();//listView
    }

    @Override
    public int getCount() {
        return data != null ? data.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int elementType = getItemViewType(position);
        File file = (File) getItem(position);
        View itemView = null;
        if (convertView != null) {
            Integer tag = (Integer) convertView.getTag();
            if (tag != null && tag == elementType) {
                itemView = convertView;
            }
        }
        if (itemView == null) {
            if (elementType == DIR_VIEW_TYPE) {
                itemView = inflater.inflate(R.layout.folder_view, parent, false);
            } else {
                itemView = inflater.inflate(R.layout.file_view, parent, false);
            }
        }
        //android.amberfog.com/?p=276 ??
        if (elementType == DIR_VIEW_TYPE) {
            TextView dirName = (TextView) itemView.findViewById(R.id.folder_name);
            dirName.setText(file.getName());
            TextView filesCount = (TextView) itemView.findViewById(R.id.files_count);
            File[] files = file.listFiles();
            filesCount.setText(itemView.getContext().getString(R.string.items_count, (files == null ? 0 : files.length)));
        } else {
            TextView fileName = (TextView) itemView.findViewById(R.id.file_name);
            fileName.setText(file.getName());
            TextView lastModified = (TextView) itemView.findViewById(R.id.last_modified);
            lastModified.setText(dateFormat.format(file.lastModified()));
        }

        return itemView;
    }

    @Override
    public int getItemViewType(int position) {
        File file = (File) getItem(position);
        return file.isDirectory() ? DIR_VIEW_TYPE : FILE_VIEW_TYPE;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }
}
