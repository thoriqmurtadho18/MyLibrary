package com.gunder.mylibrary.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final  String database_name = "db_perpus";
    public static final  String tabel_name = "tabel_perpus";

    public static final  String row_id = "_id";
    public static final  String row_nama = "Nama";
    public static final  String row_judul = "Judul";
    public static final  String row_pinjam = "Pinjam";
    public static final  String row_kembali = "Kembali";
    public static final  String row_status = "Status";

    private final SQLiteDatabase db;

    public DBHelper(Context context) {
        super(context, database_name, null, 2);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = " CREATE TABLE " + tabel_name + " ( "
                + row_id + " INTERGER PRIMARY KEY AUTOINCREMENT, "
                + row_nama + " TEXT, " + row_judul + " TEXT, " + row_pinjam + " TEXT, "
                + row_kembali + " TEXT, " + row_status + " TEXT) ";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS '" +  tabel_name + "'");
    }

    //Menampilkan Semua Data SQLite
    public Cursor AllData() {
        return db.rawQuery(" SELECT * FROM " + tabel_name + " ORDER BY " + row_id +
                " DESC ", null);
    }

    //Menampilkan satu Data Berdasarkan ID
    public Cursor oneData(Long id){
        return db.rawQuery(" SELECT * FROM " + tabel_name + " WHERE " + row_id +
                " = " + id, null);
    }

    //Insert Data
    public void insertData(ContentValues values){
        db.insert(tabel_name, null, values);
    }

    //Update Data
    public void updateData(ContentValues values, long id){
        db.update(tabel_name, values, row_id + "=" + id, null);
    }

    //Delete Data
    public void deleteData(long id){
        db.delete(tabel_name, row_id + "=" + id, null);
    }
}
