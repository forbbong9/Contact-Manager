package com.example.hl.myfirstapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

/**
 * Created by HL on 3/22/16. NetID: lxh152130
 * This activity has no more use.
 */
public class MyViewActivity extends AppCompatActivity {

    Contact contact=new Contact();
    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_my);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();

        Bundle bundle =  intent.getExtras();
        if(bundle!=null){
            index = (int)bundle.get("Index");
            contact = (Contact)bundle.get("Contact");
            Log.d("Your result is: ",index+contact.getFirstName());
        }else{
            Log.d("Cannot find EXTRAS!","");
        }
        ShowView();
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_edit) {
            return true;
        }
        if (id == R.id.action_delete) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void ShowView(){
        TextView nameText=(TextView)findViewById(R.id.nameText);
        nameText.setText(contact.getFirstName()+" "+contact.getLastName());


        TextView phoneText=(TextView)findViewById(R.id.phoneText);
        phoneText.setText(contact.getPhone());

        TextView eMailText=(TextView)findViewById(R.id.eMailText);
        eMailText.setText(contact.geteMail());

        TextView dateText=(TextView)findViewById(R.id.dateText);
        dateText.setText(contact.getDate());

    }
}
