package com.example.bharath.cropremainder;

import java.util.ArrayList;
import java.util.HashMap;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class DBconnector extends SQLiteOpenHelper {

	public DBconnector(Context context) {
		super(context, "crop", null, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String sql = "CREATE TABLE IF NOT EXISTS user(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, phn TEXT,pwd TEXT,loc TEXT,yield text,soil text)";
		db.execSQL(sql);
		String sql1 = "CREATE TABLE IF NOT EXISTS cropdet(id INTEGER PRIMARY KEY AUTOINCREMENT, cname TEXT, month TEXT,soil TEXT,prec TEXT)";
		db.execSQL(sql1);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
		db.execSQL("DROP TABLE user IF EXITS");
		db.execSQL("DROP TABLE cropdet IF EXITS");
		onCreate(db);
	}
	
	public void insert_user(HashMap<String, String> map){
		SQLiteDatabase sb = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put("name",  map.get("name"));
		cv.put("phn",map.get("phn"));
		cv.put("pwd",map.get("pwd"));
		cv.put("loc", map.get("loc"));
		cv.put("yield", map.get("yield"));
		cv.put("soil", map.get("soil"));
		sb.insert("user", null, cv);
	}

    public void insert_cropdet(HashMap<String, String> map){
        SQLiteDatabase sb = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("cname",  map.get("cname"));
        cv.put("month",map.get("month"));
        cv.put("soil",map.get("soil"));
        cv.put("prec", map.get("prec"));
        sb.insert("cropdet", null, cv);
    }


}
