package com.example.hl.myfirstapplication;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by HL on 3/22/16. NetID: lxh152130
 * Main Activity used for showing list of contacts.
 */
public class MyListActivity extends AppCompatActivity {

     ArrayList<Contact> contacts=new ArrayList<Contact>();
     ContactAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_my);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


//        LinearLayout lView = (LinearLayout)findViewById(R.id.mylinearlayout);

        FileProcessor file=new FileProcessor();
//        file.setList(contacts);
        contacts=new ArrayList<Contact>(file.getList());
        int count=contacts.size();
        Log.d("New Contacts' count= ", Integer.toString(contacts.size()));

        showList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add) {

            Log.d("Add new Contact", "yeah");
            Intent intent=new Intent(MyListActivity.this, MyNew_EditActivity.class);
            intent.putExtra("Index",-1);
            intent.putExtra("Contacts",contacts);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void showList(){
        //Sort contact list every time showList on the main activity
        Collections.sort(contacts);

        //
        adapter = new ContactAdapter(this, contacts);
        ListView listView = (ListView) findViewById(R.id.mylist);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Contact contact = (Contact) parent.getItemAtPosition(position);
                int index = contacts.indexOf(contact);
                //open Edit
                Intent intent = new Intent(MyListActivity.this, MyNew_EditActivity.class);
                intent.putExtra("Index", index);
                intent.putExtra("Contacts", contacts);
                startActivity(intent);

            }
        });

    }


}
