package com.example.bharath.cropremainder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.content.Intent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Register extends AppCompatActivity implements AdapterView.OnItemSelectedListener,AdapterView.OnItemClickListener {

    EditText name,phn,pwd,loc,yield;
    Spinner soil;
    String usoil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        soil= (Spinner) findViewById(R.id.soil);

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

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        usoil=parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void reg(View view){

        name= (EditText) findViewById(R.id.name);
        phn= (EditText) findViewById(R.id.phn);
        pwd= (EditText) findViewById(R.id.pwd);
        loc= (EditText) findViewById(R.id.loc);
        yield= (EditText) findViewById(R.id.yield);

        if(name.getText().toString().equals("") || phn.getText().toString().equals("") || pwd.getText().toString().equals("") ||
                loc.getText().toString().equals("") || yield.getText().toString().equals("") ){
            Toast.makeText(getApplicationContext(), "Enter all fields", Toast.LENGTH_LONG).show();
        }else{
            DBconnector dbc=new DBconnector(getApplicationContext());

            HashMap<String,String> map=new HashMap<String,String>();
            map.put("name", name.getText().toString());
            map.put("phn", phn.getText().toString());
            map.put("pwd", pwd.getText().toString());
            map.put("loc", loc.getText().toString());
            map.put("yield", yield.getText().toString());
            map.put("soil", usoil);

            dbc.insert_user(map);

            Toast.makeText(getApplicationContext(),"Registration Success",Toast.LENGTH_LONG).show();

            Intent i=new Intent(Register.this,MainActivity.class);
            startActivity(i);

        }

    }

}
