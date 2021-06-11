package com.ceid.project;

import androidx.appcompat.app.AppCompatActivity;

public class Package extends AppCompatActivity {

    private String pack_name ,perioxh;

    private int owner , pack_dates , counter = 0, packID;

    private static int packCount =0;

    private double pack_Price;

    Service[] pack_servs = new Service[12];

    public Package()

    {

    }

    public Package(String a , int b , String c , int d)

    {
        this.pack_name = a;
        this.pack_dates = b;
        this.perioxh = c;
        this.owner = d;
        this.packID = packCount;
        packCount++;
    }

    public void add_to_pack(Service a)

    {
        if(this.counter <= 11)
        {
            pack_servs[this.counter] = a;
            this.counter ++;
        }
    }

    public void showPackage()

    {
        System.out.println("perigrafh paketou");
    }
    public void showpackage_info()

    {
        System.out.println("Plhrofories paketou");

        System.out.println("Onoma Paketoy : " + this.pack_name + "Hmeromhnia : " + this.pack_dates + "Perioxh: " + this.perioxh);
    }
    public void services_of_package()

    {
        System.out.println(" yphresies paketoy");
    }
    public void browsePackage()

    {
        System.out.println("Bre8hke to paketo");
    }
}
