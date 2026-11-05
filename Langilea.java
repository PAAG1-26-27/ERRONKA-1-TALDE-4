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
public class Langilea extends Pertsona
{
    
    private String LangileID;
    private int Soldata;
    
    public Langilea (String a, String b, String c, LocalDate d, String e, int f)
    {
        super(a,b,c,d);
        LangileID=e;
        Soldata=f;
    }
    public Langilea ()
    {
        super();
        LangileID="123A";
        Soldata=0;
    }
    
    public void setLangileID(String a)
    {
        LangileID=a;
    }
    public void setLangileID() 
    {       
        String a;
        System.out.printf("\nSartu zure Langile kodea:");
        a=irakurri.nextLine();       
        LangileID=a;
    }
    
    public void setSoldata(int a)
    {
        Soldata=a;
    }
    public void setSoldata() 
    {       
        int a;
        System.out.printf("\nSartu zure Lan-postua:");
        a=Integer.parseInt(irakurri.nextLine());       
        Soldata=a;
    }
    
    public void getLangileID()
    {
        System.out.println("Langilearen kodea hau da: "+LangileID);
    }
    public void getSoldata()
    {
        System.out.println("Langilearen soldata hau da: "+Soldata);
    }    
    
    @Override
    public void getALL()
    {
        super.getALL();
        getLangileID();
        getSoldata();
    }
}

