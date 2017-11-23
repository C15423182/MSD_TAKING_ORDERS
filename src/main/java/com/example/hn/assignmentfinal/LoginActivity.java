package com.example.hn.assignmentfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.net.Inet4Address;

public class LoginActivity extends AppCompatActivity  implements View.OnClickListener
{
    //variables
    EditText username,password;
    ImageView icon,loginbtn,regbtn,loginicon,pwordicon;
    dbconnect db = new dbconnect(this);


    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = (EditText) findViewById(R.id.login_username);
        password = (EditText) findViewById(R.id.login_password);
        regbtn = (ImageView) findViewById(R.id.registerbtn);
        loginbtn = (ImageView) findViewById(R.id.login_btn);
        icon = (ImageView) findViewById(R.id.appicon);
        loginicon = (ImageView) findViewById(R.id.login_icon);
        pwordicon = (ImageView) findViewById(R.id.password_icon);
        regbtn.setOnClickListener(this);
        loginbtn.setOnClickListener(this);
    }

    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.registerbtn:
                Intent movetoreg = new Intent(this,Registration.class);
                startActivity(movetoreg);
                break;

            case R.id.login_btn:
                String checkusername = username.getText().toString();
                String checkpassword = password.getText().toString();

                if (TextUtils.isEmpty(checkusername) || TextUtils.isEmpty(checkpassword))
                {
                    Toast.makeText(getApplicationContext(), "Please Enter A Valid Form.", Toast.LENGTH_LONG).show();
                }
                else
                {
                    boolean check = db.CheckLoginExists(checkusername, checkpassword);

                    if (check = true)
                    {
                        Toast.makeText(getApplicationContext(), "Login Successful. Logging In...", Toast.LENGTH_LONG).show();

                        Intent movetomenu = new Intent(this, Menu.class);
                        movetomenu.putExtra("currentuser", checkusername);
                        startActivity(movetomenu);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Login Failed. User Doesn't Exist!", Toast.LENGTH_LONG).show();
                    }
                    break;
                }
        }
        /*
        if(v.getId() == R.id.registerbtn)
        {
            Intent movetoreg = new Intent(this,Registration.class);
            startActivity(movetoreg);
        }
        if(v.getId() == R.id.login_btn)
        {
            String checkusername = username.getText().toString();
            String checkpassword = password.getText().toString();

            boolean check = db.CheckLoginExists(checkusername, checkpassword);

            if (check = true)
            {
                Toast.makeText(getApplicationContext(), "Login Successful. Logging In...", Toast.LENGTH_LONG).show();
                Intent movetomenu = new Intent(this, Menu.class);
                movetomenu.putExtra("currentuser",checkusername);
                startActivity(movetomenu);
            }
            else
            {
                Toast.makeText(getApplicationContext(), "Login Failed. User Doesn't Exist!", Toast.LENGTH_LONG).show();

            }
        }
        */
    }
}
