package com.example.hl.myfirstapplication;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.KeyListener;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by HL on 3/22/16. NetID: lxh152130
 * Details Activity, New contact, view contact, edit contact are all happening in this activity.
 */
public class MyNew_EditActivity extends AppCompatActivity {

    static Contact contact;
    ArrayList<Contact> contacts;
    int index;
    static EditText DateEdit;
    static Boolean editable=false;
    static Boolean saved=false;
    FileProcessor file=new FileProcessor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        Intent intent = getIntent();
        Bundle bundle =  intent.getExtras();
        if(bundle!=null){
            //View contact
            index = (int)bundle.get("Index");

            contacts=new ArrayList<Contact>((ArrayList<Contact>)bundle.get("Contacts"));
            if(index>=0) {
                contact = contacts.get(index);
                Log.d("Your result is: ", index + contact.getFirstName());
                ShowContact();
            }else{
                //Add contact
                saved=false;
                Log.d("Add","Adding new contact now.");
                AddContact();

            }

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_delete){
            //we can delete an contact weather in edit mood or view mood
            Log.d("Delete","You just deleted me.");
            if(index>=0){
                contacts.remove(index);
                file.setList(contacts);
                Log.d("Index", Integer.toString(index));
            }
            finish();
            Intent intent= new Intent(this, MyListActivity.class);
            startActivity(intent);
            return true;
        }

        if (id == android.R.id.home) {
            finish();
            Intent intent= new Intent(this, MyListActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void AddContact(){
        //make it editable
        editable=true;
        setTitle("Add Contact");

        DateEdit=(EditText)findViewById(R.id.editText5);
        DateEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("click", "You clicked date edit field");
                showTruitonDatePickerDialog(v);
            }
        });

        //Set save button visible
        //To set it visible again, use .setVisibility(View.VISIBLE);
       Button btn=(Button)findViewById(R.id.button_edit);
        btn.setVisibility(View.GONE);
        Button b=(Button) findViewById(R.id.button_save);
        b.setVisibility(View.VISIBLE);
        b.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                SaveNewContact();
                if(saved){
                    Toast.makeText(getApplicationContext(),
                            "New Contact Saved.", Toast.LENGTH_LONG).show();
                    ShowContact();
                    saved=false;
                }
            }
        });

    }
    //Add Contact mode, save contact event
    public boolean SaveNewContact(){
        Log.d("SAVE NEW CONTACT", "You clicked save button");
        if(editable){
            //read edittext field
            EditText firstNameText=(EditText)findViewById(R.id.editText1);
            String firstName=firstNameText.getText().toString();
            if(firstName.equals("")){
                Toast.makeText(getApplicationContext(),
                        "We have to know your FIRST NAME", Toast.LENGTH_LONG).show();
                return false;
            }
            Log.d("Good, First name is ",firstName);

            EditText lastNameText=(EditText)findViewById(R.id.editText2);
            String lastName=lastNameText.getText().toString();

            EditText phoneText=(EditText)findViewById(R.id.editText3);
            String phone=phoneText.getText().toString();

            EditText eMailText=(EditText)findViewById(R.id.editText4);
            String eMail=eMailText.getText().toString();

            EditText dateText=(EditText)findViewById(R.id.editText5);
            String date=dateText.getText().toString();
            //create this contact
            contact=new Contact(firstName,lastName,phone,eMail,date);


            //add it to contacts file

            ArrayList<Contact> contacts=new ArrayList<Contact>();
            contacts=new ArrayList<Contact>(file.getList());
            int count=contacts.size();
            contacts.add(contact);
            file.setList(contacts);

            index=contacts.indexOf(contact);

            saved=true;
            //ShowContact();

        }
        return true;
    }

    public void ShowContact(){
        editable=false;
        setTitle("View Contact");
        //when view the contact,setKeyListener null make it not editable
        //to set it back(editable), use:
        //textView.setKeyListener((KeyListener) textView.getTag());
        EditText firstName=(EditText)findViewById(R.id.editText1);
        firstName.setText(contact.getFirstName());
        firstName.setTag(firstName.getKeyListener());
        firstName.setKeyListener(null);

        EditText lastName=(EditText) findViewById(R.id.editText2);
        lastName.setText(contact.getLastName());
        lastName.setTag(lastName.getKeyListener());
        lastName.setKeyListener(null);

        EditText phone=(EditText) findViewById(R.id.editText3);
        phone.setText(contact.getPhone());
        phone.setTag(phone.getKeyListener());
        phone.setKeyListener(null);

        EditText eMail=(EditText) findViewById(R.id.editText4);
        eMail.setText(contact.geteMail());
        eMail.setTag(eMail.getKeyListener());
        eMail.setKeyListener(null);

        DateEdit=(EditText)findViewById(R.id.editText5);
        DateEdit.setText(contact.getDate());
        DateEdit.setTag(eMail.getKeyListener());
        DateEdit.setKeyListener(null);
        DateEdit.setOnClickListener(null);

        //Set save button invisible,edit button visible
        //To set it visible again, use .setVisibility(View.VISIBLE);
        Button b=(Button) findViewById(R.id.button_save);
        b.setVisibility(View.GONE);
        Button btn=(Button)findViewById(R.id.button_edit);
        btn.setVisibility(View.VISIBLE);

        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.d("EDIT CONTACT", "You clicked edit button");
                EditContact();
            }
        });
    }

    public void EditContact(){
        editable=true;
        setTitle("Edit Contact");
        Button btn=(Button) findViewById(R.id.button_edit);
        btn.setVisibility(View.GONE);
        Button b=(Button) findViewById(R.id.button_save);
        b.setVisibility(View.VISIBLE);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveEditContact();
                if(saved){
                    Toast.makeText(getApplicationContext(),
                    "Edited Contact Saved.", Toast.LENGTH_LONG).show();
                    ShowContact();
                    saved=false;
                }
            }
        });

        EditText firstName=(EditText)findViewById(R.id.editText1);
        firstName.setKeyListener((KeyListener) firstName.getTag());

        EditText lastName=(EditText)findViewById(R.id.editText2);
        lastName.setKeyListener((KeyListener) lastName.getTag());

        EditText phone=(EditText)findViewById(R.id.editText3);
        phone.setKeyListener((KeyListener) phone.getTag());

        EditText eMail=(EditText)findViewById(R.id.editText4);
        eMail.setKeyListener((KeyListener) eMail.getTag());

//        EditText date=(EditText)findViewById(R.id.editText5);
//        date.setKeyListener((KeyListener) date.getTag());

        DateEdit=(EditText)findViewById(R.id.editText5);
        DateEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("click", "You clicked date edit field");
                showTruitonDatePickerDialog(v);
            }
        });

    }

    //in edit contact mode, save button event
    public boolean SaveEditContact(){
        Log.d("SAVE CONTACT", "You clicked save button");
        if(editable){
            //read edittext field
            EditText firstNameText=(EditText)findViewById(R.id.editText1);
            String firstName=firstNameText.getText().toString();
            if(firstName.equals("")){
                Toast.makeText(getApplicationContext(),
                        "We have to know your FIRST NAME", Toast.LENGTH_LONG).show();
                return false;
            }
            Log.d("Good, First name is ",firstName);

            EditText lastNameText=(EditText)findViewById(R.id.editText2);
            String lastName=lastNameText.getText().toString();

            EditText phoneText=(EditText)findViewById(R.id.editText3);
            String phone=phoneText.getText().toString();

            EditText eMailText=(EditText)findViewById(R.id.editText4);
            String eMail=eMailText.getText().toString();

            EditText dateText=(EditText)findViewById(R.id.editText5);
            String date=dateText.getText().toString();
            //create this contact
            contact=new Contact(firstName,lastName,phone,eMail,date);

            //add it to contacts file
            FileProcessor file=new FileProcessor();
            ArrayList<Contact> contacts=new ArrayList<Contact>();
            contacts=new ArrayList<Contact>(file.getList());
            int count=contacts.size();
            contacts.set(index,contact);
            file.setList(contacts);

            saved=true;

        }
        return true;
    }

    public void showTruitonDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

        int nowYear,nowMonth,nowDay;

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            nowYear = c.get(Calendar.YEAR);
            nowMonth = c.get(Calendar.MONTH);
            nowDay = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, nowYear, nowMonth, nowDay);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {

            if(year==nowYear&&month==nowMonth&&day>nowDay){
                //alert it is a future date
                Toast.makeText(getActivity().getApplication(),
                        "Hey, it's a FUTURE Date", Toast.LENGTH_LONG).show();
            }else if(year==nowYear&&month>nowMonth){
                Toast.makeText(getActivity().getApplication(),
                        "Hey, it's a FUTURE Date", Toast.LENGTH_LONG).show();
            }else if(year>nowYear){
                Toast.makeText(getActivity().getApplication(),
                        "Hey, it's a FUTURE Date", Toast.LENGTH_LONG).show();
            }else{
                //validate, set date
                DateEdit.setText((month + 1) + "/" + day + "/" + year);
            }
        }
    }
}
