package com.example.hn.assignmentfinal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by HN on 22/11/2017.
 */

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener
{
    TextView updatetxt,settingstitle,unameind,nameind,pwordind,emailind,busind,displayusername,displayname, displaypword,displayemail,displaybusiness;
    ImageView toupdate,returnbtn;

    dbconnect db = new dbconnect(this);
    String usernametaken;
    User CurrentUser;


    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        toupdate = (ImageView) findViewById(R.id.updatebtn);
        returnbtn = (ImageView) findViewById(R.id.backbtn);

        settingstitle = (TextView) findViewById(R.id.settingstxt);
        unameind = (TextView) findViewById(R.id.usernametxt);
        nameind = (TextView) findViewById(R.id.nametxt);
        pwordind = (TextView) findViewById(R.id.passwordtxt);
        emailind = (TextView) findViewById(R.id.emailtxt);
        busind = (TextView) findViewById(R.id.businesstxt);
        updatetxt =(TextView) findViewById(R.id.updatetxt);

        displayusername = (TextView) findViewById(R.id.getusername);
        displayname = (TextView) findViewById(R.id.getname);
        displaypword = (TextView) findViewById(R.id.getpass);
        displayemail = (TextView) findViewById(R.id.getemail);
        displaybusiness = (TextView) findViewById(R.id.getbus);


        toupdate.setOnClickListener(this);
        returnbtn.setOnClickListener(this);

        Bundle extras = getIntent().getExtras();
        usernametaken = extras.getString("currentuser");
        CurrentUser = db.GetUserDetails(usernametaken);

        displayusername.setText(usernametaken);
        displayname.setText(CurrentUser.getName());
        displaypword.setText(CurrentUser.getPassword());
        displayemail.setText(CurrentUser.getEmail());
        displaybusiness.setText(CurrentUser.getBusiness());

    }

    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.backbtn:
                Toast.makeText(getApplicationContext(), "Returning to Menu... ", Toast.LENGTH_LONG).show();
                Intent backtoMenu = new Intent(this, Menu.class);
                backtoMenu.putExtra("currentuser",usernametaken)  ;
                startActivity(backtoMenu);
                break;
            case R.id.updatebtn:
                Intent tochangedetails = new Intent(this, UpdateFormActivity.class);
                tochangedetails.putExtra("currentuser",usernametaken)  ;
                startActivity(tochangedetails);
                break;
        }
    }
}
