/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.polikiroldegia;

import java.time.LocalDate;

/**
 *
 * @author Arrese.Nahia
 */
public class Partaide extends Pertsona
{
    
    private String Jaiot_herria;
    private Boolean Erroldatua;
    private Boolean Bazkide;
    
    public Partaide (String a, String b, String c, LocalDate d, String e, Boolean f, Boolean g)
    {
        super(a,b,c,d);
        Jaiot_herria=e;
        Erroldatua=f;
        Bazkide=g;
    }
    public Partaide ()
    {
        super();
        Jaiot_herria="123A";
        Erroldatua= false;
        Bazkide=false;
    }
    
    public void setJaiot_herria(String a)
    {
        Jaiot_herria=a;
    }
    public void setJaiot_herria() 
    {       
        String a;
        System.out.printf("\nSartu zure jaiot-herria:");
        a=irakurri.nextLine();       
        Jaiot_herria=a;
    }
    
    public void setErroldatua(Boolean a) 
    {             
        Erroldatua=a;
    }
    public void setErroldatua() 
    {    
        String a;
        do
        {
            System.out.printf("\nErroldatua dago? (bai/ez):");
            a=irakurri.nextLine();
            if(a.equals("bai"))
            {
                Erroldatua=true;
            }else if(a.equals("ez"))
            {
                Erroldatua=false;
            }else
            {
                System.out.println("Sarturiko balioa ez da zuzena");
            }
        }while((!a.equals("bai"))&&(!a.equals("ez")));
    }
    
    public void setBazkide(Boolean a) 
    {             
        Bazkide=a;
    }
    public void setBazkide() 
    {    
        String a;
        do
        {
            System.out.printf("\nBazkidea da? (bai/ez):");
            a=irakurri.nextLine();
            if(a.equals("bai"))
            {
                Bazkide=true;
            }else if(a.equals("ez"))
            {
                Bazkide=false;
            }else
            {
                System.out.println("Sarturiko balioa ez da zuzena");
            }
        }while((!a.equals("bai"))&&(!a.equals("ez")));
    }
    
    public void getJaiot_herria()
    {
        System.out.println("Jaiot-herria hau da: "+Jaiot_herria);
    }
    
    public void getErroldatua()
    {
        if(Erroldatua)
        {
            System.out.println("Erroldaturik dago");
        }else
        {
            System.out.println("Ez dago Erroldaturik");
        }
    }
    
    public void getBazkide()
    {
        if(Bazkide)
        {
            System.out.println("Bazkidea da");
        }else
        {
            System.out.println("Ez da bazkide");
        }
    }
    
    /**
    * Bazkidearen datu guztiak bistaratzen ditu,
    * erroldatuta dagoen ala ez barne.
    */
    @Override
    public void getALL()
    {
        super.getALL();
        getJaiot_herria();
        getErroldatua();
        getBazkide();
    }
}
