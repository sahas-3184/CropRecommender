package com.example.bharath.cropremainder;

import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.content.Intent;

import java.util.ArrayList;

public class CorrectCrop extends ListActivity {

    ArrayList<String> result=new ArrayList<String>();
    private SQLiteDatabase newDB;
    int i=0;
    String cname,month,soil,prec,content,ucname,umonth,usoil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        cname=getIntent().getStringExtra("cname");
        month=getIntent().getStringExtra("month");
        usoil=getIntent().getStringExtra("soil");
        openAndQueryDatabase();
        displayResultList();


    }

    @Override
    public void onBackPressed(){
        Intent intent=new Intent(getApplicationContext(),UserHome.class);
        startActivity(intent);
    }

    private void displayResultList() {

        TextView tView=new TextView(this);
        tView.setText("Information about crops");
        getListView().addHeaderView(tView);
        setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,result));
        final ListView lv=getListView();
        lv.setTextFilterEnabled(true);

    }


    private void openAndQueryDatabase() {


        DBconnector dbHelper = new DBconnector(this.getApplicationContext());
        newDB = dbHelper.getWritableDatabase();
        Cursor c = newDB.rawQuery(" select * from cropdet where cname='"+cname+"' and month='"+month+"' and soil='"+usoil+"' ", null);
        if (c != null) {

            if (c.moveToFirst()) {

                do {
                    ucname = c.getString(c.getColumnIndex("cname"));
                    umonth = c.getString(c.getColumnIndex("month"));
                    prec = c.getString(c.getColumnIndex("prec"));
                    soil = c.getString(c.getColumnIndex("soil"));

                    content = "Crop Name: " + ucname + "\nMonth:" + umonth + "\nSoil Type:" + soil + "\nInformation:" + prec;
                    result.add(content);

                } while (c.moveToNext());

            }

        }

    }

}
