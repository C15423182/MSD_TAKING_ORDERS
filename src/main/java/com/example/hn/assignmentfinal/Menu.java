package com.example.hn.assignmentfinal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Menu extends AppCompatActivity implements View.OnClickListener
{
    //variables
    ImageView logout,addorder,editorder,vieworder,deleteorder,settings;
    TextView logoutt,addordert,editordert,viewordert,deleteordert,welcomemsg,mainusername;
    dbconnect db = new dbconnect(this);
    String usernametaken;
    User CurrentUser;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        logout = (ImageView) findViewById(R.id.logoutbtn);
        logoutt = (TextView) findViewById(R.id.logouttext);
        addorder = (ImageView) findViewById(R.id.addorderbtn);
        addordert = (TextView) findViewById(R.id.addordertxt);
        vieworder = (ImageView) findViewById(R.id.vieworderbtn);
        viewordert = (TextView) findViewById(R.id.viewordertext);
        deleteorder = (ImageView) findViewById(R.id.deleteorderbtn);
        deleteordert = (TextView) findViewById(R.id.deleteordertxt);
        welcomemsg = (TextView) findViewById(R.id.welcome);
        mainusername = (TextView) findViewById(R.id.mainusername);
        settings = (ImageView) findViewById(R.id.settingsbtn);

        logout.setOnClickListener(this);
        addorder.setOnClickListener(this);
        vieworder.setOnClickListener(this);
        deleteorder.setOnClickListener(this);
        settings.setOnClickListener(this);

        Bundle extras = getIntent().getExtras();
        usernametaken = extras.getString("currentuser");

        CurrentUser = db.GetUserDetails(usernametaken);

        mainusername.setText(usernametaken);
    }

    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.addorderbtn:
                Intent movetoadd = new Intent(this, AddOrderActivity.class);
                movetoadd.putExtra("currentuser", usernametaken);
                startActivity(movetoadd);
                break;
            case R.id.vieworderbtn:
                //Intent movetoview = new Intent(this, ViewOrderActivity.class);
                //movetoview.putExtra("username",CurrentUser.getUsername());
                //startActivity(movetoview);
                break;
            case R.id.settingsbtn:
                Intent movetosettings = new Intent(this, SettingsActivity.class);
                movetosettings.putExtra("currentuser", usernametaken);
                startActivity(movetosettings);
                break;
            case R.id.deleteorderbtn:
                Intent movetodel = new Intent(this, DeleteOrderActivity.class);
                movetodel.putExtra("currentuser",usernametaken);
                startActivity(movetodel);
                break;
            case R.id.logoutbtn:
                Toast.makeText(getApplicationContext(), "Logging Out : " + CurrentUser.getName(), Toast.LENGTH_LONG).show();
                Intent backtologin = new Intent(this, LoginActivity.class);
                startActivity(backtologin);
                db.close();
                break;
        }
    }
}
