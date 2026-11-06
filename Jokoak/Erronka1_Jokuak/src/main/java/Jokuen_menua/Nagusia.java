package Jokuen_menua;

import java.util.Scanner;

/**
 * Programaren sarrera-puntua ({@code int main()} funtzioaren baliokidea).
 *
 * <p>Aurretiko partida bat gordeta badago, jokoak kargatu nahi den galdetuko du;
 * bestela partida berria hasiko da. Partida amaitzean, berriro jokatzeko aukera
 * emango da.</p>
 */
public class Nagusia {

    public static void jolastu(Scanner irakurri) {
        try {
            new Jokoa().jolastu();
        } catch (Teklatua.SarreraAmaiera sarreraAmaiera) {
            // Teklatua agortu da (EOF): jokoa lasai amaitzen da.
            System.out.println();
        }
    }
}
