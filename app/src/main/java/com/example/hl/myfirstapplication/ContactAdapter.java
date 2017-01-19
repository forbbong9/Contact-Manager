package com.example.hl.myfirstapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by HL on 3/22/16.
 * ArrayAdapter of Arraylist<Contact> for showing list of contact in the List Activity.
 */
public class ContactAdapter extends ArrayAdapter<Contact> {

    private ArrayList<Contact> contacts=new ArrayList<Contact>();

    public ContactAdapter(Context context, ArrayList<Contact> contacts) {
        super(context, R.layout.list_contact, contacts);
        this.contacts = contacts;
    }

    //getView() is the method that returns the actual view used as a row within the ListView at a particular position.
    public View getView(int position, View convertView, ViewGroup parent){

        // assign the view we are converting to a local variable
        View v = convertView;

        // first check to see if the view is null. if so, we have to inflate it.
        // to inflate it basically means to render, or show, the view.
        if (v == null) {
//            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            v = inflater.inflate(R.layout.list_my, null);
            v=LayoutInflater.from(getContext()).inflate(R.layout.list_contact, null, true);
        }

		/*
		 * Recall that the variable position is sent in as an argument to this method.
		 * The variable simply refers to the position of the current object in the list. (The ArrayAdapter
		 * iterates through the list we sent it)
		 *
		 * Therefore, i refers to the current Item object.
		 */

        Contact i = contacts.get(position);
//        if (i != null) {

            // This is how you obtain a reference to the TextViews.
            // These TextViews are created in the XML files we defined.
            TextView nameText = (TextView) v.findViewById(R.id.nameText);
            TextView phoneText = (TextView) v.findViewById(R.id.phoneText);

            // check to see if each individual textview is null.
            // if not, assign some text!

            if (nameText != null){
                nameText.setText(i.getFirstName()+" "+i.getLastName());
            }
            if (phoneText != null){
                phoneText.setText(i.getPhone());
            }
//        }

        // the view must be returned to our activity
        return v;

    }

}
