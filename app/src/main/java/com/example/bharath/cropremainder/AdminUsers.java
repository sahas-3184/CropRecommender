package com.example.bharath.cropremainder;

import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class AdminUsers extends ListActivity {

    ArrayList<String> result=new ArrayList<String>();
    private SQLiteDatabase newDB;
    int i=0;
    String name,loc,soil,yield,content,phn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        openAndQueryDatabase();
        displayResultList();
    }
    private void displayResultList() {

        TextView tView=new TextView(this);
        tView.setText("Users");
        getListView().addHeaderView(tView);
        setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,result));
        final ListView lv=getListView();
        lv.setTextFilterEnabled(true);

    }


    private void openAndQueryDatabase() {


        DBconnector dbHelper = new DBconnector(this.getApplicationContext());
        newDB = dbHelper.getWritableDatabase();
        Cursor c = newDB.rawQuery(" select * from user ", null);
        if (c != null) {

            if (c.moveToFirst()) {

                do {
                    name = c.getString(c.getColumnIndex("name"));
                    phn = c.getString(c.getColumnIndex("phn"));
                    loc = c.getString(c.getColumnIndex("loc"));
                    yield = c.getString(c.getColumnIndex("yield"));
                    soil = c.getString(c.getColumnIndex("soil"));

                    content = "Name: " + name + "\nPhone:" + phn + "\nLocation:" + loc + "\nYield:" + yield + "\nSoil:" + soil;
                    result.add(content);

                } while (c.moveToNext());

            }

        }

    }

}
