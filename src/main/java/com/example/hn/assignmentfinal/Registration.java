package com.example.hn.assignmentfinal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class Registration extends AppCompatActivity implements View.OnClickListener
{
    //variables
    EditText reguname,regname,regemail,regbus,regpass;
    TextView regtext;
    ImageView reg,back;
    dbconnect db = new dbconnect(this);

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        reguname = (EditText) findViewById(R.id.input_username);
        regname = (EditText) findViewById(R.id.input_name);
        regbus = (EditText) findViewById(R.id.input_business);
        regemail = (EditText) findViewById(R.id.input_email);
        regpass = (EditText) findViewById(R.id.input_password);
        reg = (ImageView) findViewById(R.id.confirm_reg);
        regtext = (TextView) findViewById(R.id.registertext);
        reg.setOnClickListener(this);
    }

    public void onClick(View v)
    {

        if(v.getId() == R.id.confirm_reg)
        {
            //convert input to variables
            String username = reguname.getText().toString();
            String pword = regpass.getText().toString();
            String email = regemail.getText().toString();
            String business = regbus.getText().toString();
            String name = regname.getText().toString();

            boolean check = db.CheckUser(username);

            //create instance
            User current_user = new User(username,name,pword,email,business);
            //error check
            //error check
            if (TextUtils.isEmpty(username) || TextUtils.isEmpty(pword) || TextUtils.isEmpty(email) || TextUtils.isEmpty(name) || TextUtils.isEmpty(business))
            {
                Toast.makeText(getApplicationContext(), "Please Enter A Valid Form.", Toast.LENGTH_LONG).show();
            }
            else if (check == true)
            {
                Toast.makeText(getApplicationContext(), "User Already Exists.", Toast.LENGTH_LONG).show();
            }
            else
            {
                //add user function in dbconncet
                db.AddUser(current_user);
                // Toast message to show success message that record saved successfully
                Toast.makeText(getApplicationContext(), "Registration Complete!", Toast.LENGTH_LONG).show();
                //move to login page
                Intent movetologin = new Intent(this, LoginActivity.class);
                startActivity(movetologin);
                db.close();
            }
        }
    }
}
