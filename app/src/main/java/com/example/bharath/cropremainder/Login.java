package com.example.bharath.cropremainder;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.content.Intent;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Login extends AppCompatActivity implements AdapterView.OnItemSelectedListener,AdapterView.OnItemClickListener {

    EditText phn,pwd;
    Spinner role;
    String urole;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        role= (Spinner) findViewById(R.id.role);

        role.setOnItemSelectedListener(this);

        List<String> rname=new ArrayList<String>();

        rname.add("Admin");
        rname.add("User");

        ArrayAdapter<String> adp=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,rname);
        adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        role.setAdapter(adp);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        urole=parent.getItemAtPosition(position).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public  void login(View view){

        phn= (EditText) findViewById(R.id.phn);
        pwd= (EditText) findViewById(R.id.pwd);

        if(phn.getText().toString().equals("") || pwd.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(),"Fill all fields",Toast.LENGTH_LONG).show();
        }else if(urole.equals("Admin") && phn.getText().toString().equalsIgnoreCase("admin") && pwd.getText().toString().equalsIgnoreCase("admin")){
            Intent intent=new Intent(getApplicationContext(),AdminHome.class);
            startActivity(intent);
        }else if(urole.equals("Admin") && !phn.getText().toString().equalsIgnoreCase("admin") && pwd.getText().toString().equalsIgnoreCase("admin")){
            Toast.makeText(getApplicationContext(),"Invalid Credentials",Toast.LENGTH_LONG).show();
        }else if(urole.equals("User")){
            SQLiteDatabase db=openOrCreateDatabase("crop", SQLiteDatabase.CREATE_IF_NECESSARY, null);
            Cursor c=db.rawQuery("select * from user where phn='"+phn.getText().toString()+"' and pwd='"+pwd.getText().toString()+"'",null);
            c.moveToFirst();
            if(c!=null){
                if(c.getCount()>0){
                    Intent intent=new Intent(getApplicationContext(),UserHome.class);
                    intent.putExtra("uphn",phn.getText().toString());
                    startActivity(intent);
                }
            }else{
                Toast.makeText(getApplicationContext(),"Invalid Credentials",Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(getApplicationContext(),"Invalid Credentials",Toast.LENGTH_LONG).show();
        }

    }

}
