/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.zenbakinahasiak;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author laguna.aner
 */
public class ZenbakiNahasiak {

    public static void main(String[] args) throws Exception {
        int saiakerak = 0;
        int datua;
        Scanner teklatua;
        int berriro;

        teklatua = new Scanner(System.in);  //Teklatutik datuak irakurtzeko.

        do 
        {
            saiakerak = 0;
            int ZenbakiAleatorioa = (int) (Math.random() * 100) + 1;  //Genera un numero aleatorio entre 1 y 10.
            while (saiakerak <= 6) 
            {
                saiakerak++;
                System.out.println("===ZENBAKIA ASMATU===");
                System.out.println("Sartu zenbaki bat:  ");
                datua = teklatua.nextInt();

                if (datua == ZenbakiAleatorioa) 
                {
                    System.out.println("Zenbakia zuzena!");
                    saiakerak = 10;
                } 
                else 
                {
                    if (datua > ZenbakiAleatorioa)
                    {
                        System.out.println("Txikiagoa izan behar da");
                    }
                    if (datua < ZenbakiAleatorioa) 
                    {
                        System.out.println("Handiagoa izan behar da");
                    }

                }
            }
            System.out.println("BUKATU DA JOKOA");
            System.out.println("Berriro jolasteko sartu bat");
            teklatua.nextLine();
            berriro = teklatua.nextInt();

        } while (berriro == 1);

    }

}
