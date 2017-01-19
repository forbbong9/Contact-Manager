package com.example.hl.myfirstapplication;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;

import android.Manifest;
import android.content.Context;
import android.os.Environment;
import android.util.Log;

/**
 * Created by HL on 3/23/16.
 * Read and Write txt file.
 */
public class FileProcessor {

//    // Storage Permissions
//    private static final int REQUEST_EXTERNAL_STORAGE = 1;
//    private static String[] PERMISSIONS_STORAGE = {
//            Manifest.permission.READ_EXTERNAL_STORAGE,
//            Manifest.permission.WRITE_EXTERNAL_STORAGE
//    };

    File myDir = Environment.getExternalStorageDirectory();



    public ArrayList<Contact> getList(){

        ArrayList<Contact> contacts=new ArrayList<Contact>();
        try {

            String s = "";


            int count=CountLines()/5;
            String firstName, lastName, phone, eMail, date;

            BufferedReader br = new BufferedReader(new FileReader(myDir + "/Test.txt"));

            //store file in contacts
            for(int i=0;i<count;i++){
                firstName=br.readLine();
                lastName=br.readLine();
                phone=br.readLine();
                eMail=br.readLine();
                date=br.readLine();

                Contact contact=new Contact(firstName,lastName,phone,eMail,date);
                contacts.add(contact);
            }

//            while(i!=0){
//                Log.d("Output",s);
//                s=br.readLine();
//                i--;
//            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contacts;
    }

    public void setList(ArrayList<Contact> contacts){
    try{
        //clear file?
        FileWriter fw = new FileWriter(myDir + "/Test.txt");

        int count=contacts.size();
        Contact contact;

        for(int i=0;i<count;i++){
            contact=contacts.get(i);
            fw.write(contact.getFirstName()+"\r\n"+contact.getLastName()+"\r\n"+contact.getPhone()
                    +"\r\n"+contact.geteMail()+"\r\n"+contact.getDate()+"\r\n");
        }
        fw.close();

    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
    }
    //Count the total lines the file has.
    int CountLines() throws IOException{
        LineNumberReader lnr = new LineNumberReader(new FileReader(new File(myDir + "/Test.txt")));
        lnr.skip(Long.MAX_VALUE);
        int count=lnr.getLineNumber() + 1; //Add 1 because line index starts at 0
        // Finally, the LineNumberReader object should be closed to prevent resource leak
        lnr.close();
        Log.d("COUNTED LINES: ",Integer.toString(count));
        return count;
    }


}
