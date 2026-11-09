
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package Jokuen_menua;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class QUIZ_kultura {

    public static void Jokoa(Scanner sc) {

        String galdera = "";
        String zuzena = "";
        int galderaPos;

        int puntuak = 0;
        int contador = 1;
        Random randomGalderak = new Random();

        ArrayList<String> galderakArruntak = new ArrayList<>();
        ArrayList<String> zuzenakArruntak = new ArrayList<>();
        ArrayList<String> galderakZaia = new ArrayList<>();
        ArrayList<String> zuzenakZaia = new ArrayList<>();

        // GALDERAK ERRASA
        galderakArruntak.add("Nork margotu zuen Mona Lisa?\n1) Miguel Angel\n2) Rafael\n3) Leonardo da Vinci");
        zuzenakArruntak.add("3");
        galderakArruntak.add("Zein da munduko ozeanorik handiena?\n1) Atlantikoa\n2) Pazifikoa\n3) Indikoa");
        zuzenakArruntak.add("2");
        galderakArruntak.add("Zein planeta da eguzki-sistemako handiena?\n1) Saturno\n2) Jupiter\n3) Neptunoa");
        zuzenakArruntak.add("2");
        galderakArruntak.add("Nork idatzi zituen Romeo eta Julieta?\n1) William Shakespeare\n2) Miguel de Cervantes\n3) Charles Dickens");
        zuzenakArruntak.add("1");
        galderakArruntak.add("Zein urtetan hasi zen Bigarren Mundu Gerra?\n1) 1939\n2) 1945\n3) 1914");
        zuzenakArruntak.add("1");
        galderakArruntak.add("Zein mendikatek bereizten ditu Espainia eta Frantzia?\n1) Alpeak\n2) Andeak\n3) Pirinioak");
        zuzenakArruntak.add("3");
        galderakArruntak.add("Zein elementu kimikoren sinboloa da Fe?\n1) Fluorra\n2) Burdina\n3) Fosforoa");
        zuzenakArruntak.add("2");
        galderakArruntak.add("Zein literatura-generotakoa da eszenatoki batean antzezteko idatzitako obra bat?\n1) Narratiboa\n2) Dramatikoa\n3) Lirikoa");
        zuzenakArruntak.add("2");
        galderakArruntak.add("Antzinako zein zibilizaziok sortu zuen Erromako hiria?\n1) Grekoak\n2) Erromatarrak\n3) Egiptoarrak");
        zuzenakArruntak.add("2");
        galderakArruntak.add("Zein da Kanadako hiriburua?\n1) Ottawa\n2) Toronto\n3) Vancouver");
        zuzenakArruntak.add("1");
        galderakArruntak.add("Zer animalia mota da balea?\n1) Arraina\n2) Narrastia\n3) Ugaztuna");
        zuzenakArruntak.add("3");
        galderakArruntak.add("Zer testu mota da pertsonaiekin eta gertaerekin istorio bat kontatzen duena?\n1) Poema\n2) Narrazioa\n3) Albistea");
        zuzenakArruntak.add("2");
        galderakArruntak.add("Zein kirol praktikatzen zuen Michael Phelpsek?\n1) Igeriketa\n2) Atletismoa\n3) Gimnasia");
        zuzenakArruntak.add("1");
        galderakArruntak.add("Zein arte martzialetan erabiltzen da gerriko beltza maila aurreratuaren ikur gisa?\n1) Judoa\n2) Boxeoa\n3) Eskrima");
        zuzenakArruntak.add("1");
        galderakArruntak.add("Zein futbol selekziok irabazi zuen 2006ko Munduko Txapelketa?\n1) Frantziak\n2) Italiak\n3) Alemaniak");
        zuzenakArruntak.add("2");
        galderakArruntak.add("Zenbaki bat 4rekin biderkatuta 144 ematen badu, zein da zenbaki hori?\n1) 36\n2) 32\n3) 42");
        zuzenakArruntak.add("1");
        galderakArruntak.add("Zein da Frantziako hiriburua?\n1) Paris\n2) Lyon\n3) Marseilla");
        zuzenakArruntak.add("1");
        galderakArruntak.add("Zenbat planeta daude eguzki-sisteman?\n1) 7\n2) 8\n3) 9");
        zuzenakArruntak.add("2");
        galderakArruntak.add("Nork idatzi zuen \"Don Kixote Mantxakoa\"?\n1) Lope de Vega\n2) Miguel de Cervantes\n3) Francisco de Quevedo");
        zuzenakArruntak.add("2");
        galderakArruntak.add("Zein urtetan iritsi zen Kristobal Kolon Amerikara?\n1) 1392\n2) 1592\n3) 1492");
        zuzenakArruntak.add("3");
        galderakArruntak.add("Zein da kontinenterik populatuena?\n1) Afrika\n2) Asia\n3) Europa");
        zuzenakArruntak.add("2");
        galderakArruntak.add("Zein gas behar dute gizakiek arnasteko?\n1) Oxigenoa\n2) Nitrogenoa\n3) Helioa");
        zuzenakArruntak.add("1");
        galderakArruntak.add("Zein da Italiako hiriburua?\n1) Milan\n2) Napoli\n3) Erroma");
        zuzenakArruntak.add("3");
        galderakArruntak.add("Zein da uraren formula kimikoa?\n1) CO2\n2) H2O\n3) O2");
        zuzenakArruntak.add("2");
        galderakArruntak.add("Zein ibaik zeharkatzen du Bilbo?\n1) Nerbioi\n2) Ebro\n3) Bidasoa");
        zuzenakArruntak.add("1");
        galderakArruntak.add("Zein planeta ezagutzen da \"planeta gorria\" izenez?\n1) Artizarra\n2) Marte\n3) Jupiter");
        zuzenakArruntak.add("2");
        galderakArruntak.add("Nork idatzi zuen \"Printze Txikia\"?\n1) Victor Hugo\n2) Jules Verne\n3) Antoine de Saint-Exupéry");
        zuzenakArruntak.add("3");
        galderakArruntak.add("Zenbat alde ditu hexagono batek?\n1) 5\n2) 6\n3) 8");
        zuzenakArruntak.add("2");
        galderakArruntak.add("Zein da Japoniako hiriburua?\n1) Tokio\n2) Osaka\n3) Kyoto");
        zuzenakArruntak.add("1");
        galderakArruntak.add("Zein urtetan erori zen Berlingo harresia?\n1) 1989\n2) 1991\n3) 1979");
        zuzenakArruntak.add("1");
        galderakArruntak.add("Zein da giza gorputzeko organorik handiena?\n1) Gibela\n2) Larruazala\n3) Birikak");
        zuzenakArruntak.add("2");

        //GALDERAK ZAIA
        galderakZaia.add("Zein urtetan hasi zen Frantziako Iraultza?\n1) 1789\n2) 1776\n3) 1799");
        zuzenakZaia.add("1");
        galderakZaia.add("Zein da Australiako hiriburua?\n1) Sydney\n2) Canberra\n3) Melbourne");
        zuzenakZaia.add("2");
        galderakZaia.add("Zein elementu kimikoren sinboloa da Au?\n1) Zilarra\n2) Aluminioa\n3) Urrea");
        zuzenakZaia.add("3");
        galderakZaia.add("Nork idatzi zuen \"Ehun urteko bakardadea\"?\n1) Gabriel García Márquez\n2) Julio Cortázar\n3) Mario Vargas Llosa");
        zuzenakZaia.add("1");
        galderakZaia.add("Zein urtetan hartu zuten otomandarrek Konstantinopla?\n1) 1204\n2) 1453\n3) 1492");
        zuzenakZaia.add("2");
        galderakZaia.add("Nork idatzi zuen \"Ulises\"?\n1) Samuel Beckett\n2) Oscar Wilde\n3) James Joyce");
        zuzenakZaia.add("3");
        galderakZaia.add("Zein fisikarik formulatu zuen ziurgabetasun-printzipioa?\n1) Werner Heisenberg\n2) Niels Bohr\n3) Erwin Schrödinger");
        zuzenakZaia.add("1");
        galderakZaia.add("Zein da Kazakhstango hiriburua?\n1) Almaty\n2) Astana\n3) Taxkent");
        zuzenakZaia.add("2");
        galderakZaia.add("Zein da giza gorputzeko hezurrik luzeena?\n1) Femurra\n2) Tibia\n3) Humeroa");
        zuzenakZaia.add("1");
        galderakZaia.add("Nor izan zen Erromako lehen enperadorea?\n1) Julio Zesar\n2) Neron\n3) Augusto");
        zuzenakZaia.add("3");

        // WHILE GALDERAK ERRESEKIN
        while (contador <= 10) {

            if (contador == 1) {
                galderaPos = randomGalderak.nextInt(galderakArruntak.size());
                galdera = galderakArruntak.remove(galderaPos);
                zuzena = zuzenakArruntak.remove(galderaPos);
            }
            if (contador == 2) {
                galderaPos = randomGalderak.nextInt(galderakArruntak.size());
                galdera = galderakArruntak.remove(galderaPos);
                zuzena = zuzenakArruntak.remove(galderaPos);
            }
            if (contador == 3) {
                galderaPos = randomGalderak.nextInt(galderakArruntak.size());
                galdera = galderakArruntak.remove(galderaPos);
                zuzena = zuzenakArruntak.remove(galderaPos);
            }
            if (contador == 4) {
                galderaPos = randomGalderak.nextInt(galderakArruntak.size());
                galdera = galderakArruntak.remove(galderaPos);
                zuzena = zuzenakArruntak.remove(galderaPos);
            }
            if (contador == 5) {
                galderaPos = randomGalderak.nextInt(galderakArruntak.size());
                galdera = galderakArruntak.remove(galderaPos);
                zuzena = zuzenakArruntak.remove(galderaPos);
            }
            if (contador == 6) {
                galderaPos = randomGalderak.nextInt(galderakArruntak.size());
                galdera = galderakArruntak.remove(galderaPos);
                zuzena = zuzenakArruntak.remove(galderaPos);
            }
            if (contador == 7) {
                galderaPos = randomGalderak.nextInt(galderakArruntak.size());
                galdera = galderakArruntak.remove(galderaPos);
                zuzena = zuzenakArruntak.remove(galderaPos);
            }
            if (contador == 8) {
                galderaPos = randomGalderak.nextInt(galderakArruntak.size());
                galdera = galderakArruntak.remove(galderaPos);
                zuzena = zuzenakArruntak.remove(galderaPos);
            }
            if (contador == 9) {
                galderaPos = randomGalderak.nextInt(galderakArruntak.size());
                galdera = galderakArruntak.remove(galderaPos);
                zuzena = zuzenakArruntak.remove(galderaPos);
            }
            if (contador == 10) {
                galderaPos = randomGalderak.nextInt(galderakArruntak.size());
                galdera = galderakArruntak.remove(galderaPos);
                zuzena = zuzenakArruntak.remove(galderaPos);
            }

            System.out.println("\nGaldera " + contador);
            System.out.println(galdera);
            System.out.print("Erantzuna (1-3): ");
            String entrada = sc.nextLine().trim();

            if (entrada.equals(zuzena)) {
                System.out.println("\nZuzena! +10 puntu");
                puntuak += 10;
            } else {
                System.out.println("Okerra.");
            }

            contador++;
        } // while itxi

        System.out.println("\n5 galdera geratzen dira eta hau pixka bat zaiagoa bihurtuko dugu.");

        // WHILE GALDERAK ZAIEKIN
        while (contador <= 15) {

            if (contador == 11) {
                galderaPos = randomGalderak.nextInt(galderakZaia.size());
                galdera = galderakZaia.remove(galderaPos);
                zuzena = zuzenakZaia.remove(galderaPos);
            }
            if (contador == 12) {
                galderaPos = randomGalderak.nextInt(galderakZaia.size());
                galdera = galderakZaia.remove(galderaPos);
                zuzena = zuzenakZaia.remove(galderaPos);
            }
            if (contador == 13) {
                galderaPos = randomGalderak.nextInt(galderakZaia.size());
                galdera = galderakZaia.remove(galderaPos);
                zuzena = zuzenakZaia.remove(galderaPos);
            }
            if (contador == 14) {
                galderaPos = randomGalderak.nextInt(galderakZaia.size());
                galdera = galderakZaia.remove(galderaPos);
                zuzena = zuzenakZaia.remove(galderaPos);
            }
            if (contador == 15) {
                galderaPos = randomGalderak.nextInt(galderakZaia.size());
                galdera = galderakZaia.remove(galderaPos);
                zuzena = zuzenakZaia.remove(galderaPos);
            }

            System.out.println("\nGaldera " + contador + " (zaila)");
            System.out.println(galdera);
            System.out.print("Erantzuna (1-3): ");
            String entrada = sc.nextLine().trim();

            if (entrada.equals(zuzena)) {
                System.out.println("\nZuzena! +20 puntu");
                puntuak += 20;
            } else {
                System.out.println("Okerra.");
            }

            contador++;
        }//while itxi

        System.out.println("\nZure puntuazioa: " + puntuak + " / 200");
    }//metodo itxi

    public static void jolastu(Scanner irakurri) {
        Scanner sc = new Scanner(System.in);
        String erantzuna;

        //JARRAIBIDEAK + INSTRUKZIOAK
        System.out.println("===Ongi etorri Kultura Orokorreko Quizera===");
        System.out.println();
        System.out.println("Jokoaren Funtzionamendua:");
        System.out.println();
        System.out.println("1: Kultura orokorreko 15 galdera egongo dira, eta bata bestearen atzetik erantzun behar dituzu.");
        System.out.println();
        System.out.println("2: Galdera bakoitzean 3 aukera izango dituzu, eta teklatuan zuzena iruditzen zaizun erantzunaren zenbakia idatzi beharko duzu.");
        System.out.println();
        System.out.println("3: Galdera arruntek 10 puntu ematen dituzte eta zailek 20; erantzun oker bakoitzak 0 puntu. Gehienez 200 puntu lor ditzakezu.");
        System.out.println();
        System.out.println("4: Lehenengo 10 galderak arruntak izango dira eta azken 5ak zailak.");
        System.out.println();

        System.out.print("Jokatzen hasi nahi baduzu idatzi (bai): ");
        erantzuna = sc.nextLine().trim().toLowerCase();

        if (erantzuna.equals("bai")) {
            do {
                Jokoa(sc);

                System.out.print("\nBerriro jolastu nahi duzu? (bai/ez): ");
                erantzuna = sc.nextLine().trim().toLowerCase();
            } while (erantzuna.equals("bai"));

            System.out.println("\nEskerrik asko jolasteagatik! Agur!");
        } else {
            System.out.println("Hurrengoan jokatuko dugu, Aguurr!!");
        }

    } // MAIN ITXI
}
