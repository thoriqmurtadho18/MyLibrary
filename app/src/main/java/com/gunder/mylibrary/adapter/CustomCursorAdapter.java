package com.gunder.mylibrary.adapter;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.gunder.mylibrary.R;

public class CustomCursorAdapter extends CursorAdapter {
    private LayoutInflater ly;
    private SparseBooleanArray nSelectedItem;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public CustomCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
        ly = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        nSelectedItem = new SparseBooleanArray();
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        View v = ly.inflate(R.layout.row_data,viewGroup,false);
        MyHolder holder = new MyHolder();
        holder.ListID = (TextView)v.findViewById(R.id.listID);
        holder.ListJudul = (TextView)v.findViewById(R.id.listJudul);
        holder.ListNama = (TextView)v.findViewById(R.id.listNama);
        holder.ListPinjam = (TextView)v.findViewById(R.id.listTglPinjam);
        holder.ListStatus = (TextView)v.findViewById(R.id.listStatus);

        v.setTag(holder);
        return v;
    }

    @SuppressLint("Range")
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        MyHolder holder = (MyHolder)view.getTag();
        holder.ListID.setText(cursor.getString(cursor.getColumnIndex(DBHelper.row_id)));
        holder.ListJudul.setText(cursor.getString(cursor.getColumnIndex(DBHelper.row_judul)));
        holder.ListNama.setText(cursor.getString(cursor.getColumnIndex(DBHelper.row_nama)));
        holder.ListPinjam.setText(cursor.getString(cursor.getColumnIndex(DBHelper.row_pinjam))
                + " - " + cursor.getString(cursor.getColumnIndex(DBHelper.row_kembali)));
        holder.ListStatus.setText(cursor.getString(cursor.getColumnIndex(DBHelper.row_status)));
    }

    private class MyHolder {
        public TextView ListID;
        public TextView ListJudul;
        public TextView ListNama;
        public TextView ListPinjam;
        public TextView ListStatus;
    }
}
