/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.polikiroldegia;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
/**
 *
 * @author Arrese.Nahia
 */
public class Monitorea extends Langilea 
{
    private String TituloID;
    
    public Monitorea (String a, String b, String c, LocalDate d, String e, int f, String g)
    {
        super(a,b,c,d,e,f);
        TituloID=g;
    }
    public Monitorea ()
    {
        super();
        TituloID="123A";
    }
    
    public void setTituloID(String a)
    {
        TituloID=a;
    }
    public void setTituloID() 
    {       
        String a;
        System.out.printf("\nSartu zure Monitore kodea:");
        a=irakurri.nextLine();       
        TituloID=a;
    }
    
    public void getTituloID()
    {
        System.out.println("Monitorearen kodea hau da: "+TituloID);
    }
    
    /**
    * Bazkidearen datu guztiak bistaratzen ditu,
    * erroldatuta dagoen ala ez barne.
    */
    @Override
    public void getALL()
    {
        super.getALL();
        getTituloID();
    }
}
