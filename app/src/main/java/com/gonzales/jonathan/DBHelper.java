package com.gonzales.jonathan;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    final static String DBNAME = "pupils.db";
    final static int VER = 1;
    final static String TABLE = "points";

    public DBHelper(Context context) {
        super(context, DBNAME, null, VER);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE points (ID INTEGER PRIMARY KEY AUTOINCREMENT, Fname TEXT, Lname TEXT, Point INTEGER)";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS points";
        db.execSQL(query);
        onCreate(db);
    }

    public boolean insert(String fname, String lname, int point) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Fname", fname);
        values.put("Lname", lname);
        values.put("Point", point);
        long isInserted = db.insert(TABLE,null, values);

        if(isInserted == -1){
            return false;
        } else {
            return true;
        }
    }

    public Cursor selectRecords(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM points", null);
    }

    public Cursor getUpdatedRecord(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM points WHERE ID = " + id, null);
    }

    public boolean updateRecord(String id, String fname, String lname, int point){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Fname", fname);
        values.put("Lname", lname);
        values.put("Point", point);
        int affectedRows = db.update(TABLE, values, "ID=?", new String[]{id});

        if(affectedRows > 0) {
            return true;
        } else {
            return false;
        }
    }

    public Integer deleteRecord(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE, "ID=?", new String[]{id});

    }
}
