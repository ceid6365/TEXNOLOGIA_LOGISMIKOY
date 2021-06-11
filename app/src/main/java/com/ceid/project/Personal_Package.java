package com.ceid.project;

import androidx.appcompat.app.AppCompatActivity;

public class Personal_Package extends AppCompatActivity {

    private int id , deadline;

    private String description;

    public Personal_Package()

    {

    }
    public Personal_Package(int a , int b , String c)

    {
        this.id = a;

        this.deadline = b;

        this.description = c;
    }

    public void showPersonal_Package()

    {
        System.out.println("deikse ta xarakthristika tou proswpikou paketou");
    }

    public void showRequests()

    {
        System.out.println("Deikse ta aithmata proswpikwn paketwn");
    }

    public void acceptRequest()

    {
        System.out.println("To prwsopiko sas peketo eggrithike");
    }

}
