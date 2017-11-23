package com.example.hn.assignmentfinal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class AddOrderActivity extends AppCompatActivity implements View.OnClickListener
{
    TextView ordertext,userindicator, currentusersusername;
    EditText tableid,foodname,fooddes,drinkname;
    ImageView submit;

    dbconnect db = new dbconnect(this);
    User CurrentUser;


    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addorder);
        ordertext = (TextView) findViewById(R.id.addordertextpage);
        userindicator = (TextView) findViewById(R.id.usernameind);
        currentusersusername = (TextView) findViewById(R.id.usernamedisplay);

        tableid = (EditText) findViewById(R.id.add_tableid);
        foodname = (EditText) findViewById(R.id.order_foodname);
        fooddes = (EditText) findViewById(R.id.food_descrip);
        drinkname = (EditText) findViewById(R.id.order_drinkname);

        submit=(ImageView)  findViewById(R.id.submitorderbtn);
        submit.setOnClickListener(this);

        Bundle extras = getIntent().getExtras();
        String usernametaken = extras.getString("currentuser");

        CurrentUser = db.GetUserDetails(usernametaken);

        currentusersusername.setText(CurrentUser.getName());
    }

    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.submitorderbtn:
                int tid = Integer.parseInt(tableid.getText().toString());
                String fname = foodname.getText().toString();
                String fdes = fooddes.getText().toString();
                String dname = drinkname.getText().toString();
                String waitername = CurrentUser.getUsername();

                if (   tid <= 0 || TextUtils.isEmpty(fname))
                {
                    Toast.makeText(getApplicationContext(), "Please Enter A Valid Form.", Toast.LENGTH_LONG).show();
                }
                else
                {
                    db.AddOrder(tid, fname, fdes, dname, waitername);

                    Toast.makeText(getApplicationContext(), "Order was Added!", Toast.LENGTH_LONG).show();

                    Intent movebacktomenu = new Intent(this, Menu.class);
                    movebacktomenu.putExtra("currentuser", CurrentUser.getUsername());
                    startActivity(movebacktomenu);
                }
                break;
        }
    }
}
