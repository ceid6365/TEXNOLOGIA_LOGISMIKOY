package com.ceid.project;

import androidx.appcompat.app.AppCompatActivity;

public class Business extends AppCompatActivity {

        private boolean certified;
        private String businessName , owner;
        private int serviceCount , packageCount;

        Service[] serv = new Service[100];

        Package[] pack = new Package[100];

    public Business()

    {
        this.serviceCounter = 0;
        this.packageCounter = 0;
    }

    public Business(String b, String c, String d, String e, String f)

    {
        super(b,c,d);
        this.businessName = e;
        this.owner = f;
        this.certified = false;
        this.serviceCounter = 0;
        this.packageCounter = 0;
    }

    public void uploadCertificate()

    {
        System.out.println("Parakalw anevaste pistopoiitiko");
    }

    public void addPackage()

    {

        System.out.println("onoma paketoy:\n");
        String on_pack = c.next();
        System.out.println("topo8esia\n");
        String location = c.next();
        System.out.println("hmeromhnia\n");
        int hmeromhnia = c.nextInt();
        System.out.println("id owner\n");
        int ownerID = c.nextInt();
        this.pack[packCounter] = new Package(on_pack, hmeromhnia,location,ownerID);
        this.packageCounter++;

    }

    public void addService()

    {
        System.out.println("onoma yphresias:\n");
        String yphresia = c.next();
        System.out.println("perigrafh\n");
        String perigrafh = c.next();
        System.out.println("kathgoria\n");
        String category = c.next();
        System.out.println("topo8esia\n");
        String location = c.next();
        System.out.println("Pros8este timh\n");
        int price = c.nextInt();
        System.out.println("H Yphresia proste8hke epitixws!");
        this.serv[serviceCounter] = new Service(yphresia, perigrafh, category, location, price);
        this.serviceCounter ++;

    }



    public void cancelService(Service a)
    {
        a = null;
        System.out.println("H yphresia akurwthike!");
    }

    public Service browseService(String a )

    {

        String test;



        for (int i=0;i<this.servs.length;i++)

        {

            if(this.servs[i] != null )

            {
                if(this.servs[i].getName() == a)

                {
                    return this.servs[i];
                }
            }
        }

        return null;

    }

    }
