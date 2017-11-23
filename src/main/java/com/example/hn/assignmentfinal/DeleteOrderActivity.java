package com.example.hn.assignmentfinal;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DeleteOrderActivity extends AppCompatActivity implements View.OnClickListener
{
    TextView deletetext,userindicator, currentusersusername;
    EditText t_id;
    ImageView delsubmit;
    User CurrentUser;
    dbconnect db = new dbconnect(this);

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        deletetext = (TextView) findViewById(R.id.deletetxt);
        userindicator = (TextView) findViewById(R.id.userind);
        currentusersusername = (TextView) findViewById(R.id.mainusernamedel);
        t_id = (EditText) findViewById(R.id.input_tabletodelete);
        delsubmit=(ImageView) findViewById(R.id.deletesubmitbtn);
        delsubmit.setOnClickListener(this);

        Bundle extras = getIntent().getExtras();
        String usernametaken = extras.getString("currentuser");

        CurrentUser = db.GetUserDetails(usernametaken);

        currentusersusername.setText(CurrentUser.getName());
    }
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.deletesubmitbtn:
                int table_id = Integer.parseInt(t_id.getText().toString());

                db.DeleteOrder(table_id);

                Toast.makeText(getApplicationContext(), "Orders From Table Have Been Deleted!", Toast.LENGTH_LONG).show();

                Intent movebacktomenu = new Intent(this, Menu.class);
                movebacktomenu.putExtra("currentuser", CurrentUser.getUsername());
                startActivity(movebacktomenu);
                break;
        }
    }
}
