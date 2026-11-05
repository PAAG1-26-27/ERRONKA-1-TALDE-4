/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.erronka1;

import java.time.LocalDate;

/**
 *
 * @author Gorka Cereza
 */
public class Arrunta extends Ekintza {
    
    private int Iraupena;
    
    public Arrunta(String a, String b, LocalDate c, int d){
        super(a,b,c);
        Iraupena = d;
    }
    public Arrunta(){
        super();
        Iraupena = 0;
    }
    
    public void setIraupena(int a){
        Iraupena = a;
    }
    public void setIraupena(){
        int a;
        System.out.println("\nSartu ekintzaren iraupena:");
        a = irakurri.nextInt();
        Iraupena = a;
    }
    
    public void getIraupena(){
        System.out.println("Ekintzaren iraupena: " + Iraupena);
    }
    public void getAll(){
        super.getALL();
        getIraupena();
    } 
}
