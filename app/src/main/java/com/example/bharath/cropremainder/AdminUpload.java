package com.example.bharath.cropremainder;

import android.content.Intent;
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
import java.util.HashMap;
import java.util.List;

public class AdminUpload extends AppCompatActivity implements AdapterView.OnItemSelectedListener,AdapterView.OnItemClickListener {

    EditText cname,prec;
    Spinner month,soil;
    Button upload;
    String ucname,uprec,umonth,usoil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_upload);

        cname= (EditText) findViewById(R.id.cname);
        prec= (EditText) findViewById(R.id.prec);
        month= (Spinner) findViewById(R.id.month);
        soil= (Spinner) findViewById(R.id.soil);
        upload= (Button) findViewById(R.id.upload);

        ucname=cname.getText().toString();
        uprec=prec.getText().toString();

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(cname.getText().toString().equals("") || prec.getText().toString().equals("") ){
                    Toast.makeText(getApplicationContext(), "Enter all fields", Toast.LENGTH_LONG).show();
                }else{
                    DBconnector dbc=new DBconnector(getApplicationContext());

                    HashMap<String,String> map=new HashMap<String,String>();
                    map.put("cname", cname.getText().toString());
                    map.put("month", umonth);
                    map.put("soil", usoil);
                    map.put("prec", prec.getText().toString());

                    dbc.insert_cropdet(map);

                    Toast.makeText(getApplicationContext(),"Upload Success\nCrop:"+cname.getText().toString()+"\nInfo:"+prec.getText().toString()+"\nMonth:"+umonth+"\nSoil:"+usoil,Toast.LENGTH_LONG).show();

                    Intent i=new Intent(getApplicationContext(),AdminHome.class);
                    startActivity(i);

                }

            }
        });

        soil.setOnItemSelectedListener(this);

        List<String> rname=new ArrayList<String>();

        rname.add("Alluvial Soil");
        rname.add("Black Soil");
        rname.add("Red Soil");
        rname.add("Laterite Soil");
        rname.add("Arid Soil");
        rname.add("Saline Soil");
        rname.add("Peaty Soil");
        rname.add("Forest Soil");

        ArrayAdapter<String> adp=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,rname);
        adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        soil.setAdapter(adp);

        month.setOnItemSelectedListener(this);

        List<String> rname1=new ArrayList<String>();

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

        ArrayAdapter<String> adp1=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,rname1);
        adp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        month.setAdapter(adp1);


    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        usoil=parent.getItemAtPosition(position).toString();
        umonth=parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
