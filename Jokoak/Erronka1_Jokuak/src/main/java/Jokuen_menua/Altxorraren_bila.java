package Jokuen_menua;

import java.util.Scanner;

/**
 * Altxorraren bila: aurkitu ezkutatutako altxorra.
 *
 * @author Gorka Cereza
 */
public class Altxorraren_bila {

    // Mapa "*" ikurrez betetzen du.
    public static void mapaBete(String[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = "*";
            }
        }
    }

    // Mapa erakusten du.
    public static void mapaErakutsi(String[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Altxorraren eta jokalariaren arteko distantzia kalkulatzen du.
    public static int distantziaKalkulatu(int x, int y, int altX, int altY) {
        return Math.abs(x - altX) + Math.abs(y - altY);
    }

    public static void jolastu(Scanner myObj) {

        int zailtasuna;
        int altxorraX, altxorraY;
        int jokalariaX, jokalariaY;
        int jokalariaXkop, jokalariaYkop;
        int mugimendua;
        int aukeraMax = 0;
        int aukera;
        int distantziaLehen, distantziaOrain;

        String berriro;

        boolean mugiOndo;
        boolean irabazi;

        String[][] mapa = null;

        do {
            // Partida berriaren balioak hasieratu.
            aukera = 0;
            irabazi = false;

            // Zailtasuna aukeratu.
            do {
                System.out.println("Aukeratu zailtasuna:");
                System.out.println("1. 4x4");
                System.out.println("2. 5x5");
                System.out.println("3. 6x6");

                zailtasuna = myObj.nextInt();
                System.out.println();

                switch (zailtasuna) {
                    case 1:
                        mapa = new String[4][4];
                        aukeraMax = 10;
                        break;

                    case 2:
                        mapa = new String[5][5];
                        aukeraMax = 14;
                        break;

                    case 3:
                        mapa = new String[6][6];
                        aukeraMax = 16;
                        break;

                    default:
                        System.out.println(
                                "Zailtasun hori ez dago. Aukeratu 1, 2 edo 3."
                        );
                        break;
                }
            } while (zailtasuna != 1 && zailtasuna != 2 && zailtasuna != 3);

            mapaBete(mapa);

            // Altxorraren posizioa ausaz aukeratu.
            altxorraY = (int) (Math.random() * mapa.length);
            altxorraX = (int) (Math.random() * mapa[0].length);

            // Jokalariaren hasierako posizioa ausaz aukeratu.
            // Ezin da altxorraren posizio bera izan.
            do {
                jokalariaY = (int) (Math.random() * mapa.length);
                jokalariaX = (int) (Math.random() * mapa[0].length);
            } while (altxorraY == jokalariaY && altxorraX == jokalariaX);

            // Jokalaria mapan jarri.
            mapa[jokalariaY][jokalariaX] = "J";

            do {
                aukera++;

                // Aurreko posizioa gorde, gero bertan "*" jartzeko.
                jokalariaXkop = jokalariaX;
                jokalariaYkop = jokalariaY;

                do {
                    mugiOndo = false;

                    mapaErakutsi(mapa);

                    System.out.println(
                            "MUGIMENDUAK (Txanda: "
                            + aukera + "/" + aukeraMax + ")"
                    );
                    System.out.println("1. Gora");
                    System.out.println("2. Eskuinera");
                    System.out.println("3. Behera");
                    System.out.println("4. Ezkerrera");

                    mugimendua = myObj.nextInt();
                    System.out.println();

                    // Mugimendua egiaztatu eta, baliozkoa bada,
                    // posizio berria gorde.
                    switch (mugimendua) {
                        case 1:
                            if (jokalariaY - 1 < 0) {
                                System.out.println("Ezin zara mapatik atera!");
                            } else {
                                jokalariaY = jokalariaY - 1;
                                mugiOndo = true;
                            }
                            break;

                        case 2:
                            if (jokalariaX + 1 >= mapa[0].length) {
                                System.out.println("Ezin zara mapatik atera!");
                            } else {
                                jokalariaX = jokalariaX + 1;
                                mugiOndo = true;
                            }
                            break;

                        case 3:
                            if (jokalariaY + 1 >= mapa.length) {
                                System.out.println("Ezin zara mapatik atera!");
                            } else {
                                jokalariaY = jokalariaY + 1;
                                mugiOndo = true;
                            }
                            break;

                        case 4:
                            if (jokalariaX - 1 < 0) {
                                System.out.println("Ezin zara mapatik atera!");
                            } else {
                                jokalariaX = jokalariaX - 1;
                                mugiOndo = true;
                            }
                            break;

                        default:
                            System.out.println(
                                    "Mugimendu hori ez dago. "
                                    + "Aukeratu 1, 2, 3 edo 4."
                            );
                            break;
                    }
                } while (!mugiOndo);

                // Jokalaria posizio berrian jarri.
                // Aurreko posizioan "*" idatzi.
                mapa[jokalariaY][jokalariaX] = "J";
                mapa[jokalariaYkop][jokalariaXkop] = "*";

                // Jokalaria eta altxorra posizio berean dauden egiaztatu.
                if (jokalariaY == altxorraY && jokalariaX == altxorraX) {
                    irabazi = true;
                    mapa[altxorraY][altxorraX] = "A";

                    System.out.println("Zorionak! Altxorra aurkitu duzu!");
                    System.out.println();

                    mapaErakutsi(mapa);

                } else if (aukera == aukeraMax) {
                    // Aukerak agortu diren egiaztatu.
                    System.out.println("GAME OVER!! Aukerak agortu dituzu!");

                } else {
                    // Pista eman.
                    distantziaLehen = distantziaKalkulatu(
                            jokalariaXkop, jokalariaYkop,
                            altxorraX, altxorraY
                    );

                    distantziaOrain = distantziaKalkulatu(
                            jokalariaX, jokalariaY,
                            altxorraX, altxorraY
                    );

                    if (distantziaOrain < distantziaLehen) {
                        System.out.println(
                                "Pista: altxorrera hurbiltzen ari zara."
                        );
                    } else {
                        System.out.println(
                                "Pista: altxorretik urruntzen ari zara."
                        );
                    }
                }

            } while (!irabazi && aukera < aukeraMax);

            // Berriro jolastu nahi duen galdetu.
            do {
                System.out.println("Berriro jolastu nahi duzu? (bai/ez)");
                berriro = myObj.next();

                if (!berriro.equals("bai") && !berriro.equals("ez")) {
                    System.out.println("Idatzi 'bai' edo 'ez'.");
                }
            } while (!berriro.equals("bai") && !berriro.equals("ez"));

        } while (berriro.equals("bai"));

        System.out.println("Eskerrik asko jolasteagatik!");
    }
}
