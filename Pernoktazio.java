/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Erronka1;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 *
 * @author Gorka Cereza
 */
public class Pernoktazio extends Ekintza {

    public String Lolekua;
    public LocalDate BukaeraData;

    public Pernoktazio(String a, String b, LocalDate c, String d, LocalDate e) {
        super(a, b, c);
        Lolekua = d;
        BukaeraData = e;
    }

    public Pernoktazio() {
        super();
        Lolekua = "AAA";
        BukaeraData = LocalDate.now();
    }

    
    public void setLolekua(String a) {
        Lolekua = a;
    }

    public void setLolekua() {
        String a;
        System.out.println("\nSartu Ekintzaren lolekua:");
        a = irakurri.nextLine();
        Lolekua = a;
    }

    
    public void setBukaeraData(LocalDate sarrera) {
        
        BukaeraData = sarrera;
    }

    public void setBukaeraData() {
        LocalDate fecha = null;
        String sarrera;
        System.out.print("\nSartu bukaera data (uuuu/HH/ee) formatuan: ");
        sarrera = irakurri.nextLine();
        try {
            fecha = LocalDate.parse(sarrera, formato);
            BukaeraData = fecha;
        } catch (DateTimeParseException e) {
            System.out.println("Data formatu desegokian dago.");
        }
    }

    
    public void getLolekua() {
        System.out.println("Ekintzaren lolekua: " + Lolekua);
    }

    public void getBukaeraData() {
        System.out.println("Ekintzaren bukaera data: " + BukaeraData);
    }

    
    public void getAll() {
        super.getALL();
        getLolekua();
        getBukaeraData();
    }
}
