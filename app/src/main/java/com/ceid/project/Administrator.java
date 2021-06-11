package com.ceid.project;

import androidx.appcompat.app.AppCompatActivity;

public class Administrator extends AppCompatActivity {
    private String Onoma , Dikaiwmata;

    public Administrator()

    {

    }



    public Administrator(String b , String c , String d , String e , String f)

    {

        super(b,c,d);

        this.Onoma = e;

        this.Dikaiwmata = f;

    }

    public void getRequests()

    {

        System.out.println("Ta aithmata sas einai ta");

    }
}
