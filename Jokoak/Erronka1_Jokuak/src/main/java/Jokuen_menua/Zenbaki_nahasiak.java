/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package Jokuen_menua;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author laguna.aner
 */
public class Zenbaki_nahasiak {

    public static void jolastu(Scanner myObj) {
        int saiakerak = 0;
        int datua;
        int berriro;

        do {
            saiakerak = 0;
            int ZenbakiAleatorioa = (int) (Math.random() * 100) + 1;  //Genera un numero aleatorio entre 1 y 10.
            while (saiakerak <= 6) {
                saiakerak++;
                System.out.println("===ZENBAKIA ASMATU===");
                System.out.println("Sartu zenbaki bat:  ");
                datua = myObj.nextInt();

                if (datua == ZenbakiAleatorioa) {
                    System.out.println("Zenbakia zuzena!");
                    saiakerak = 10;
                } else {
                    if (datua > ZenbakiAleatorioa) {
                        System.out.println("Txikiagoa izan behar da");
                    }
                    if (datua < ZenbakiAleatorioa) {
                        System.out.println("Handiagoa izan behar da");
                    }

                }
            }
            System.out.println("BUKATU DA JOKOA");
            System.out.println("Berriro jolasteko sartu bat");
            myObj.nextLine();
            berriro = myObj.nextInt();

        } while (berriro == 1);

    }

}
