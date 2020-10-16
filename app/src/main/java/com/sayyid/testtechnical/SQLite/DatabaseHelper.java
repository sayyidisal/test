package com.sayyid.testtechnical.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

public class DatabaseHelper extends SQLiteOpenHelper {

    //nama database

    public static final String DATABASE_NAME = "Barang.db";

    //nama table

    public static final String TABLE_NAME = "barang";

    //versi database

    private static final int DATABASE_VERSION = 1;

    //table field

    public static final String COL_1 = "ID";

    public static final String COL_2 = "NAME";

    public static final String COL_3 = "QTY";

    public static final String COL_4 = "EXPDATE";

    public static final String COL_5 = "HARGA";



    public DatabaseHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        SQLiteDatabase db = this.getWritableDatabase();

    }



    @Override

    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table barang(id integer primary key autoincrement," +

                "name text ," +

                "qty text ," +

                "expdate text ," +

                "harga integer );");

    }



    @Override

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);

        onCreate(db);

    }



    //metode untuk tambah data

    public boolean insertData(String name, String qty, String expdate, String harga) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_2,name);

        contentValues.put(COL_3,qty);

        contentValues.put(COL_4,expdate);

        contentValues.put(COL_5,harga);

        long result = db.insert(TABLE_NAME, null, contentValues);

        if(result == -1)

            return false;

        else

            return true;

    }



    //metode untuk mengambil data

    public Cursor getAllData() {

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor res = db.rawQuery("select * from barang", null);

        return res;

    }

    public ArrayList<HashMap<String, String>> GetUserByUserId(int userid){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        String query = "SELECT id, name, qty, expdate, harga FROM "+ TABLE_NAME;
        Cursor cursor = db.query(TABLE_NAME, new String[]{COL_2, COL_3, COL_4, COL_5}, COL_1+ "=?",new String[]{String.valueOf(userid)},null, null, null, null);
        if (cursor.moveToNext()){
            HashMap<String,String> user = new HashMap<>();
            user.put("name",cursor.getString(cursor.getColumnIndex(COL_2)));
            user.put("qty",cursor.getString(cursor.getColumnIndex(COL_3)));
            user.put("expdate",cursor.getString(cursor.getColumnIndex(COL_4)));
            user.put("harga",cursor.getString(cursor.getColumnIndex(COL_5)));
            userList.add(user);
        }
        return  userList;
    }



    //metode untuk merubah data

    public boolean updateData(String id,String name,String qty,String expdate, String harga) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1,id);

        contentValues.put(COL_2,name);

        contentValues.put(COL_3,qty);

        contentValues.put(COL_4,expdate);

        contentValues.put(COL_5,harga);

        db.update(TABLE_NAME,contentValues,"ID = ?",new String[] {id});

        return true;

    }



    //metode untuk menghapus data

    public int deleteData (String id) {

        SQLiteDatabase db = this.getWritableDatabase();

        return db.delete(TABLE_NAME, "ID = ?", new String[] {id});

    }

}