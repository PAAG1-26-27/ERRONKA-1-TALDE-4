/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.erronka1;


import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
/**
 *
 * @author Gorka Cereza
 */
public class Ekintza {
    
    Scanner irakurri = new Scanner(System.in);
    DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    
    private String ID;
    private String Izena;
    private LocalDate HasieraData;
    
    
    public Ekintza(String a, String b, LocalDate c)
    {
        ID = a;
        Izena = b;
        HasieraData = c;
    }
    public Ekintza (String a, String b)
    {
        ID=a;
        Izena=b;
        HasieraData=LocalDate.now();
    }
    public Ekintza ()
    {
        ID="123A";
        Izena="AAA";
        HasieraData=LocalDate.now();
    }
    
     
    public void setID(String a) 
    {         
        ID=a;
    }
    public void setID() 
    {       
        String a;
        System.out.printf("\nSartu Ekintzaren ID-a:");
        a=irakurri.nextLine();       
        ID=a;
    }
    
    public void setIzena(String a) 
    {           
        Izena=a;
    }
    public void setIzena() 
    {       
        String a;
        System.out.printf("\nSartu ekintzaren Izen-a:");
        a=irakurri.nextLine();       
        Izena=a;
    }
    
    public void setHasieraData(String sarrera) 
    {     
        LocalDate fecha=null;
        try 
        {
            fecha = LocalDate.parse(sarrera, formato);
            HasieraData=fecha;
        } catch (DateTimeParseException e) 
        {
            System.out.println("Data formatu desegokian dago.");
        }
    }
    public void setHasieraData() 
    {     
        LocalDate fecha=null;
        String sarrera;
        System.out.print("\nSartu data (uuuu/HH/ee) formatuan: ");
        sarrera=irakurri.nextLine();
        try 
        {
            fecha = LocalDate.parse(sarrera, formato);
            HasieraData=fecha;
        } catch (DateTimeParseException e) 
        {
            System.out.println("Data formatu desegokian dago.");
        }
    }
    
    public void getID()
    {
        System.out.println("Bazkidearen NAN zenbakia hau da: "+ID);
    }
    public void getIzena()
    {
        System.out.println("Bazkidearen Izena hau da: "+Izena);
    }
    public void getHasieraData()
    {
        System.out.println("Bazkidearen Abizena hau da: "+HasieraData);
    }
    
    public void getALL()
    {
        getID();
        getIzena();
        getHasieraData();
    }
    
    
    
    
    
    
}
