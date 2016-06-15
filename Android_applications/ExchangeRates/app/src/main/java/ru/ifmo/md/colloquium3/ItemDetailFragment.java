package ru.ifmo.md.colloquium3;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;


import ru.ifmo.md.colloquium3.DummyContent;


public class ItemDetailFragment extends Fragment {
    public static final String ARG_ITEM_ID = "item_id";
    public static final Uri DB_URI = Uri.parse("content://ru.ifmo.md.colloquium3.providers.cur/cur");
    public static final Uri DB_URI2 = Uri.parse("content://ru.ifmo.md.colloquium3.providers.money/money");
    Double course;
    private DummyContent.DummyItem mItem;

    public ItemDetailFragment() {
    }

    public static final String DEBUG_TAG = "itemdetailactivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments().containsKey(ARG_ITEM_ID)) {
            mItem = DummyContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));
        }
    }

    View setParam(View v) {
        Cursor result = getActivity().getContentResolver().query(DB_URI, null, null, null, null);
        result.moveToPosition(Integer.parseInt(mItem.id) - 1);
        ((TextView) v.findViewById(R.id.cur1)).setText(result.getString(result.getColumnIndex(MyCurDB.CURRENCY)));
        ((TextView) v.findViewById(R.id.cur2)).setText("RUB");
        ((TextView) v.findViewById(R.id.value)).setText(result.getString(result.getColumnIndex(MyCurDB.VALUE)));
        return v;
    }

    View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_item_detail, container, false);
        if (mItem != null) {
            final Uri updUri = ContentUris.withAppendedId(DB_URI2, Integer.parseInt(mItem.id));
            Log.d(DEBUG_TAG, "update" + updUri.toString() + " from " + mItem.id);
            rootView = setParam(rootView);
            ((Button) rootView.findViewById(R.id.buy)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int count = Integer.valueOf(((EditText) rootView.findViewById(R.id.cnt)).getText().toString());
                    Cursor rub = getActivity().getContentResolver().query(DB_URI2, null, null, null, null);
                    rub.moveToPosition(3);
                    double myRub = rub.getDouble(rub.getColumnIndex(MyMoneyDB.VALUE));
                    course = Double.valueOf(((TextView) v.findViewById(R.id.value)).getText().toString());
                    if (course * count <= myRub) {
                        ContentValues cv = new ContentValues();
                        cv.put(MyMoneyDB.CURRENCY, ((TextView) rootView.findViewById(R.id.cur1)).getText().toString());
                        cv.put(MyMoneyDB.VALUE, count);
                        ContentValues cv2 = new ContentValues();
                        cv2.put(MyMoneyDB.CURRENCY, "RUB");
                        cv2.put(MyMoneyDB.VALUE, -course * count);
                        //bug
                        int cnt = getActivity().getContentResolver().update(updUri, cv, null, null);
                        cnt = getActivity().getContentResolver().update(updUri, cv2, null, null);
                        Log.d(DEBUG_TAG, "update" + updUri.toString() + " from " + mItem.id);
                    } else {
//                        Toasr
                    }
                }
            });
        }
        return rootView;
    }
}

