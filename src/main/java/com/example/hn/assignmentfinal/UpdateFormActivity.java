package com.example.hn.assignmentfinal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class UpdateFormActivity extends AppCompatActivity implements View.OnClickListener
{
    TextView changestext;
    ImageView submitchangesbtn;
    EditText takenname,takenpword,takenemail,takenbus;

    dbconnect db = new dbconnect(this);
    String usernametaken;
    User CurrentUser;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updateacc);
        changestext = (TextView) findViewById(R.id.changestxt) ;
        submitchangesbtn = (ImageView) findViewById(R.id.submitchanges);
        takenname = (EditText) findViewById(R.id.newname);
        takenpword = (EditText) findViewById(R.id.newpword);
        takenemail = (EditText) findViewById(R.id.newemail);
        takenbus = (EditText) findViewById(R.id.newbus);

        submitchangesbtn.setOnClickListener(this);

        Bundle extras = getIntent().getExtras();
        usernametaken = extras.getString("currentuser");
        CurrentUser = db.GetUserDetails(usernametaken);
    }

    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.submitchanges:
                String changedname = takenname.getText().toString();
                String changedpword = takenpword.getText().toString();
                String changedemail = takenemail.getText().toString();
                String changedbus = takenbus.getText().toString();

                db.UpdateUserDetails(changedname,changedpword,changedemail,changedbus);

                Toast.makeText(getApplicationContext(), "Returning to Menu... ", Toast.LENGTH_LONG).show();
                Intent backtomenu = new Intent(this, Menu.class);
                backtomenu.putExtra("currentuser",usernametaken)  ;
                startActivity(backtomenu);
        }
    }
}
