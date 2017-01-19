package com.example.hl.myfirstapplication;

import java.io.Serializable;

/**
 * Created by HL on 3/22/16. NetID: lxh152130
 * Store contact structure.
 */
public class Contact implements Comparable<Contact>, Serializable {
    private String firstName, lastName, phone, eMail, date;

    public Contact(String firstName, String lastName, String phone, String eMail, String date){
        this.firstName=firstName;
        this.lastName=lastName;
        this.phone=phone;
        this.eMail=eMail;
        this.date=date;
    }

    public Contact(){}

    public String getFirstName(){
        return firstName;
    }
    public void setFirstName(String firstName){
        this.firstName=firstName;
    }

    public String getLastName(){
        return lastName;
    }
    public void setLastName(String lastName){
        this.lastName=lastName;
    }

    public String getPhone(){
        return phone;
    }
    public void setPhone(String phone){
        this.phone=phone;
    }

    public String geteMail(){
        return eMail;
    }
    public void seteMail(String eMail){
        this.eMail=eMail;
    }

    public String getDate(){
        return date;
    }
    public void setDate(){
        this.date=date;
    }

    public int compareTo(Contact other) {

        return firstName.toUpperCase().compareTo(other.firstName.toUpperCase());
    }

}
