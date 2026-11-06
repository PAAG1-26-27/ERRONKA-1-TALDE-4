/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Erronka1;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
/**
 *
 * @author Arrese.Nahia
 */
public class Administratibo extends Langilea 
{
    private String Postua;
    
    public Administratibo (String a, String b, String c, LocalDate d, String e, int f, String g)
    {
        super(a,b,c,d,e,f);
        Postua=g;
    }
    public Administratibo ()
    {
        super();
        Postua="123A";
    }
    
    public void setPostua(String a)
    {
        Postua=a;
    }
    public void setPostua() 
    {       
        String a;
        System.out.printf("\nSartu langilearen Postuaren izena:");
        a=irakurri.nextLine();       
        Postua=a;
    }
    
    public void getPostua()
    {
        System.out.println("Langilearen postua hau da: "+Postua);
    }
    
    
    @Override
    public void getALL()
    {
        super.getALL();
        getPostua();
    }
}
