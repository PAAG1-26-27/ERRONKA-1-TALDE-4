/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.polikiroldegia;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
/**
 *
 * @author Arrese.Nahia
 */
public class Pertsona 
{
    Scanner irakurri = new Scanner(System.in);
    DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    
    private String NAN;
    private String Izena;
    private String Abizena;    
    private LocalDate JaiotzeData;
    
    
    
    public Pertsona (String a, String b, String c, LocalDate d)
    {
        NAN=a;
        Izena=b;
        Abizena=c;
        JaiotzeData=d;
    }
    public Pertsona ()
    {
        NAN="000A";
        Izena="AAA";
        Abizena="BBB";
        JaiotzeData=LocalDate.now();
    }
    
    public void setNAN(String a)
    {
        NAN=a;
    }
    public void setNAN() 
    {       
        String a;
        System.out.printf("\nSartu zure NAN-a:");
        a=irakurri.nextLine();       
        NAN=a;
    }
    
    public void setIzena(String a) 
    {             
        Izena=a;
    }
    public void setIzena() 
    {       
        String a;
        System.out.printf("\nSartu zure Izen-a:");
        a=irakurri.nextLine();       
        Izena=a;
    }
    
    public void setAbizena(String a) 
    {             
        Abizena=a;
    }
    public void setAbizena() 
    {       
        String a;
        System.out.printf("\nSartu zure Abizen-a:");
        a=irakurri.nextLine();       
        Abizena=a;
    }
    
    public void setHasieraData(String sarrera) 
    {     
        LocalDate fecha=null;
        try 
        {
            fecha = LocalDate.parse(sarrera, formato);
            JaiotzeData=fecha;
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
            JaiotzeData=fecha;
        } catch (DateTimeParseException e) 
        {
            System.out.println("Data formatu desegokian dago.");
        }
    }
    
    public void getNAN()
    {
        System.out.println("Bazkidearen NAN zenbakia hau da: "+NAN);
    }
    public void getIzena()
    {
        System.out.println("Bazkidearen Izena hau da: "+Izena);
    }
    public void getAbizena()
    {
        System.out.println("Bazkidearen Abizena hau da: "+Abizena);
    }
    public void getJaiotzeData()
    {
        System.out.println("Bazkidearen Abizena hau da: "+JaiotzeData);
    }
    
    public void getALL()
    {
        getNAN();
        getIzena();
        getAbizena();
        getJaiotzeData();
    }
}
