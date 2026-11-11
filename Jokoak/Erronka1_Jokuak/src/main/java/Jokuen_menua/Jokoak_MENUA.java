/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Jokuen_menua;

import java.util.Scanner;

/**
 *
 * @author Gorka Cereza
 */
public class Jokoak_MENUA {

    public static void main(String[] args) {

        Scanner irakurri = new Scanner(System.in);
        int aukera;

        do {

            System.out.println("\n|-----JOKUEN MENUA-----|");
            System.out.println("1.Poxamon burrukak");
            System.out.println("2.Zebaki nahasiak");
            System.out.println("3.QUIZ kultura");
            System.out.println("4.Altxorraren bila");
            System.out.println("0.Irten");
            aukera = irakurri.nextInt();
            irakurri.nextLine();//Scanner garbitu.

            switch (aukera) {
                case 0:
                    System.out.println("AGUR!");
                    break;

                case 1:
                    Poxamon.jolastu(irakurri);
                    break;

                case 2:
                    Zenbaki_nahasiak.jolastu(irakurri);
                    break;

                case 3:
                    QUIZ_kultura.jolastu(irakurri);
                    break;

                case 4:
                    Altxorraren_bila.jolastu(irakurri);
                    break;
                case 5:

                    break;
            }//switch
        } while (aukera != 0);
    }//main
}
