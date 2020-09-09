package com.example.bharath.cropremainder;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class UserSearch extends AppCompatActivity {

    Button search;
    EditText cname;
    Spinner month, soil;
    String ucname, umonth, usoil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_search);

        cname = (EditText) findViewById(R.id.cname);
        month = (Spinner) findViewById(R.id.month);
        soil = (Spinner) findViewById(R.id.soil);
        ucname = cname.getText().toString();
        search = (Button) findViewById(R.id.search);

        List<String> rname1 = new ArrayList<String>();

        rname1.add("January");
        rname1.add("February");
        rname1.add("March");
        rname1.add("April");
        rname1.add("May");
        rname1.add("June");
        rname1.add("July");
        rname1.add("August");
        rname1.add("September");
        rname1.add("October");
        rname1.add("November");
        rname1.add("December");

        ArrayAdapter<String> adp1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, rname1);
        adp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        month.setAdapter(adp1);

        month.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                umonth = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        List<String> rname2 = new ArrayList<String>();

        rname2.add("Alluvial Soil");
        rname2.add("Black Soil");
        rname2.add("Red Soil");
        rname2.add("Laterite Soil");
        rname2.add("Arid Soil");
        rname2.add("Saline Soil");
        rname2.add("Peaty Soil");
        rname2.add("Forest Soil");

        ArrayAdapter<String> adp2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, rname2);
        adp2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        soil.setAdapter(adp2);

        soil.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                usoil = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (cname.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Enter crop name", Toast.LENGTH_LONG).show();
                } else {
                    SQLiteDatabase db = openOrCreateDatabase("crop", SQLiteDatabase.CREATE_IF_NECESSARY, null);
                    Cursor c = db.rawQuery("select * from cropdet where cname='" + cname.getText().toString() + "' and month='" + umonth
                            + "' and soil='" + usoil + "' ", null);
                    c.moveToFirst();
                    if (c != null) {
                        if (c.getCount() > 0) {

                            String dcn = c.getString(c.getColumnIndex("cname"));
                            String dm = c.getString(c.getColumnIndex("month"));
                            String ds = c.getString(c.getColumnIndex("soil"));

                            if (dcn.equalsIgnoreCase(cname.getText().toString()) && dm.equalsIgnoreCase(umonth) &&
                                    ds.equalsIgnoreCase(usoil)) {
                                Intent intent = new Intent(getApplicationContext(), CorrectCrop.class);
                                intent.putExtra("cname", cname.getText().toString());
                                intent.putExtra("month", umonth);
                                intent.putExtra("soil", usoil);
                                startActivity(intent);
                            } else  {
                                Intent intent = new Intent(getApplicationContext(), WrongCrop.class);
                                intent.putExtra("cname", cname.getText().toString());
                                intent.putExtra("month", umonth);
                                intent.putExtra("soil", usoil);
                                startActivity(intent);
                            }
                        } else  {
                            Intent intent = new Intent(getApplicationContext(), WrongCrop.class);
                            intent.putExtra("cname", cname.getText().toString());
                            intent.putExtra("month", umonth);
                            intent.putExtra("soil", usoil);
                            startActivity(intent);
                        }
                    }
                }

            }
        });

    }

}
