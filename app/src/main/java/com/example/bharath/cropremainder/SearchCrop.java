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

public class SearchCrop extends AppCompatActivity {

    Button search;
    EditText cname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_crop);

        cname = (EditText) findViewById(R.id.cname);
        search = (Button) findViewById(R.id.search);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (cname.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Enter crop name", Toast.LENGTH_LONG).show();
                } else {
                    SQLiteDatabase db = openOrCreateDatabase("crop", SQLiteDatabase.CREATE_IF_NECESSARY, null);
                    Cursor c = db.rawQuery("select * from cropdet where cname='" + cname.getText().toString() + "' ", null);
                    c.moveToFirst();
                    if (c != null) {
                        if (c.getCount() > 0) {
                            Intent intent = new Intent(getApplicationContext(), CropDetails.class);
                            intent.putExtra("cname", cname.getText().toString());
                            startActivity(intent);
                        }
                    }
                }

            }
        });

    }

}
