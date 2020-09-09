package com.example.bharath.cropremainder;

import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.content.Intent;
import android.widget.TextView;

import java.util.ArrayList;

public class WrongCrop extends ListActivity {

    ArrayList<String> result=new ArrayList<String>();
    private SQLiteDatabase newDB;
    int i=0;
    String cname,month,content,ucname,umonth,soil,usoil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        cname=getIntent().getStringExtra("cname");
        month=getIntent().getStringExtra("month");
        soil=getIntent().getStringExtra("soil");
        openAndQueryDatabase();
        displayResultList();

    }
    private void displayResultList() {

        TextView tView=new TextView(this);
        tView.setText("Crops to be sown");
        getListView().addHeaderView(tView);
        setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,result));
        final ListView lv=getListView();
        lv.setTextFilterEnabled(true);

    }


    private void openAndQueryDatabase() {


        DBconnector dbHelper = new DBconnector(this.getApplicationContext());
        newDB = dbHelper.getWritableDatabase();
        Cursor c = newDB.rawQuery(" select * from cropdet where month='"+month+"' or cname='"+cname+"' or soil='"+soil+"' ", null);
        if (c != null) {

            if (c.moveToFirst()) {

                do {
                    ucname = c.getString(c.getColumnIndex("cname"));
                    umonth = c.getString(c.getColumnIndex("month"));
                    usoil = c.getString(c.getColumnIndex("soil"));
                    content = "Crop Name: " + ucname + "\nMonth:" + umonth + "\nSoil:" + usoil;
                    result.add(content);

                } while (c.moveToNext());

            }

        }

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), UserHome.class);
        startActivity(intent);
    }
}
