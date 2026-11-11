package Jokuen_menua;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Poxamon: bi taldeen arteko borroka (beste pertsona baten aurka edo
 * ordenagailuaren aurka). Joko osoa klase honetan dago; Pokemona klasea
 * bere fitxategian geratzen da.
 *
 * @author Arrese.Nahia
 */
public class Poxamon
{
    // Talde bakoitzeko pokemon kopuru maximoa
    public static final int N = 50;
    // Borroka bakoitzak irauten duen txanda kopurua (hori bete eta gero, OP gehien duena da garaile)
    public static final int TXANDAK = 10;
    // Jokalariek aukeratu dezaketen garaipen kopuru maximoa
    public static final int MAX_GARAIPENAK = 99;
    // Atake fuertiaren indarra: pokemonaren PC-ak biderkatzen duen balioa
    // Aurreko borroka irabazi bazuen:
    public static final float ATAKE_FUERTIE_IRABAZITA = 1.5f;
    // Aurreko borroka irabazi ez bazuen (hasierako balioa bi taldeentzat):
    public static final float ATAKE_FUERTIE_IRABAZI_GABE = 0.2f;

    Scanner irakurri = new Scanner(System.in);

    ArrayList<Pokemona> Taldea1 = new ArrayList<>();
    ArrayList<Pokemona> Taldea2 = new ArrayList<>();

    int posEz = 0;      // Borrokan dagoen ezkerreko (1. taldeko) pokemonaren posizioa
    int posEs = 0;      // Borrokan dagoen eskumako (2. taldeko) pokemonaren posizioa
    int pokeKop = 0;    // Talde bakoitzeko pokemon kopurua
    int kont = 0;       // Borrokaren txanden kontagailua (TXANDAK-era iristean, OP gehien duena irabazten du)
    int turnue = 0;     // 1: ezkerreko taldearen txanda, 0: eskumakoarena
    int Gkont1 = 0;     // 1. taldearen garaipenak
    int Gkont2 = 0;     // 2. taldearen garaipenak
    int autoMan = 0;    // 0: beste pertsona baten aurka, 1: ordenagailuaren aurka
    int garaipenKop = 1;      // Zenbat garaipenera jokatuko den (jokalariek aukeratzen dute)
    int irabazlea = 0;        // Partidaren irabazlea: 1 edo 2
    boolean irabazi1 = false; // 1. taldeak aurreko borroka irabazi zuen
    boolean irabazi2 = false; // 2. taldeak aurreko borroka irabazi zuen
    boolean irten = false;    // Jokalariak partida utzi nahi du (gorde gabe)

    // ------------------------------------------------------------------
    // Laguntzaileak: pantaila, itxaronaldia eta ausazko zenbakiak
    // ------------------------------------------------------------------

    // Testua pantailan idazteko metodoa
    public void p(String testua)
    {
        System.out.print(testua);
    }

    // Pantaila garbitzeko metodoa (kontsola erreala denean soilik)
    public void Garbitu()
    {
        if (System.console() != null)
        {
            p("\033[H\033[2J");
            System.out.flush();
        }
    }

    // Milisegundo batzuk itxaroteko metodoa
    public void Itxaron(long ms)
    {
        if (ms <= 0L)
        {
            return;
        }
        try
        {
            Thread.sleep(ms);
        }
        catch (InterruptedException error)
        {
            Thread.currentThread().interrupt();
        }
    }

    // x eta y arteko zenbaki hamartar bat ausaz lortzeko metodoa (x barne, y kanpo), 100-ekin zatitua
    public float RandomXy(int x, int y)
    {
        int tartea = y - x;
        if (tartea <= 0)
        {
            return ((float) x) / 100.0f;
        }
        return ((float) (x + (int) (Math.random() * tartea))) / 100.0f;
    }

    // x eta y arteko zenbaki osoa ausaz lortzeko metodoa (x barne, y kanpo)
    public int Random01(int x, int y)
    {
        int tartea = y - x;
        if (tartea <= 0)
        {
            return x;
        }
        return x + (int) (Math.random() * tartea);
    }

    // ------------------------------------------------------------------
    // Datuak teklatutik sartzeko metodoak
    // ------------------------------------------------------------------

    // Zenbaki osoak eskaneatzeko metodoa
    public int SartuZenbakia(String mezua)
    {
        int zenbakia = 0;
        boolean arazorik;

        do {//Zenbaki oso bat sartu arte errepikatu du eskaria
            arazorik = false;
            try
            {
                System.out.print(mezua);
                System.out.flush();
                zenbakia = Integer.parseInt(irakurri.nextLine().trim());
            }
            catch (NumberFormatException error)
            {
                System.out.println("Sartutako zenbakiak ez dauka formatu egokia.\n");
                arazorik = true;
            }
        } while (arazorik);

        return zenbakia;
    }

    // Testua sartzeko metodoa
    public String SartuBerba(String mezua)
    {
        System.out.print(mezua);
        System.out.flush();
        return irakurri.nextLine().trim();
    }

    // Lerroko lehen karakterea sartzeko metodoa (letra xehe bihurtuta; lerro hutsa bada, zuriunea)
    public char SartuKaraktera()
    {
        String lerroa = irakurri.nextLine().trim();

        if (lerroa.isEmpty())
        {
            return ' ';
        }
        return Character.toLowerCase(lerroa.charAt(0));
    }

    // Euitimonaren izena eskatzen du eta objektuan gordetzen du
    public void PokeIzenaSartu(Pokemona pokemona)
    {
        pokemona.setIzena(SartuBerba("Izena: "));
    }

    // Taldeko zein pokemon aukeratu nahi den galdetzen du (bizirik daudenen artean)
    public int TaldekoZein(ArrayList<Pokemona> taldea)
    {
        int b, hautatua;
        int kopurua = taldea.size();

        for (b=0;b<kopurua;b++)
        {
            if (taldea.get(b).getOP() > 0)
            {
                p(String.format("\n -->    %d=%s              \n", b + 1, taldea.get(b).getIzena()));
            }
        }
        p("\n");
        p("\n   ___________________     ");
        p("\n  |                   |    ");
        p("\n  |     AUKERATU:     |===>");

        hautatua = SartuZenbakia("\n  |___________________|  (ZENB.)->");
        // Mugen barruan eta bizirik dagoen pokemon bat aukeratu arte errepikatu
        while ((hautatua<1)||(hautatua>kopurua)||(taldea.get(hautatua-1).getOP()<=0))
        {
            hautatua = SartuZenbakia("\n  |___________________|  (ZENB.)->");
        }
        return hautatua;
    }

    // ------------------------------------------------------------------
    // Jokoaren fluxua
    // ------------------------------------------------------------------

    // Ausazko estatistikak dituen pokemon berri bat sortzen du
    public Pokemona PokemonaSortu()
    {
        Pokemona pokemona = new Pokemona();

        pokemona.setOP(RandomXy(120, 201));
        Itxaron(500);
        pokemona.setPC(RandomXy(40, 61));
        pokemona.setTxanpon(3);
        pokemona.setRand(0);
        return pokemona;
    }

    // Partida berri baten datuak eskatzen ditu eta bi taldeak sortzen ditu
    public void PartidaBerriaPrestatu()
    {
        int k;
        Pokemona pokemona;

        // Noren aurka jokatu
        do
        {
            NorenAurka();
            autoMan = SartuZenbakia("");
            Garbitu();
        } while ((autoMan != 0) && (autoMan != 1));

        // Taldeko pokemon kopurua
        do
        {
            PokeKopKopurua();
            pokeKop = SartuZenbakia("");
            Garbitu();
        } while ((pokeKop < 1) || (pokeKop > N));

        // Zenbat garaipenera jokatuko den (talde bat guztiz kanporatzen ez bada)
        do
        {
            GaraipenKopKopurua();
            garaipenKop = SartuZenbakia("");
            Garbitu();
        } while ((garaipenKop < 1) || (garaipenKop > MAX_GARAIPENAK));

        Taldea1.clear();
        Taldea2.clear();

        // 1. taldeko pokemonen datuak
        for (k=0;k<pokeKop;k++)
        {
            pokemona = PokemonaSortu();
            PokeEz(pokemona);
            Taldea1.add(pokemona);
        }

        BorrokaraZein();
        posEz = TaldekoZein(Taldea1) - 1;
        Garbitu();

        // 2. taldeko pokemonen datuak
        for (k=0;k<pokeKop;k++)
        {
            pokemona = PokemonaSortu();
            PokeEs(pokemona);
            Taldea2.add(pokemona);
        }

        if (autoMan == 0)
        {
            BorrokaraZein();
            posEs = TaldekoZein(Taldea2) - 1;
        }
        else
        {
            posEs = 0;
        }

        // Nork hasiko duen ausaz aukeratu
        turnue = Random01(0, 2);
        kont = 0;
        Gkont1 = 0;
        Gkont2 = 0;
        irabazlea = 0;
        irabazi1 = false;
        irabazi2 = false;
        irten = false;
        Garbitu();
    }

    // Borrokan dagoen ezkerreko (1. taldeko) pokemona
    public Pokemona Ezker()
    {
        return Taldea1.get(posEz);
    }

    // Borrokan dagoen eskumako (2. taldeko) pokemona
    public Pokemona Eskuin()
    {
        return Taldea2.get(posEs);
    }

    // Talde batean pokemon bizirik dagoen konprobatzen du
    public boolean BizirikDago(ArrayList<Pokemona> taldea)
    {
        int b;

        for (b=0;b<taldea.size();b++)
        {
            if (taldea.get(b).getOP() > 0)
            {
                return true;
            }
        }
        return false;
    }

    // Partida bukatu den konprobatzen du: talde bat guztiz kanporatu da edo garaipen kopurura iritsi da
    public boolean JokoaBukatuta()
    {
        return ((Gkont1 >= garaipenKop) || (Gkont2 >= garaipenKop)
                || (!BizirikDago(Taldea1)) || (!BizirikDago(Taldea2)));
    }

    // Partidaren irabazlea zehazten du
    public void IrabazleaZehaztu()
    {
        if (!BizirikDago(Taldea2))
        {
            irabazlea = 1;
        }
        else if (!BizirikDago(Taldea1))
        {
            irabazlea = 2;
        }
        else if (Gkont1 > Gkont2)
        {
            irabazlea = 1;
        }
        else
        {
            irabazlea = 2;
        }
    }

    // Hurrengo pokemon bizirik dagoenaren posizioa itzultzen du (zirkularki); bakarrik oraingoa bada, hori
    public int HurrengoBizirik(ArrayList<Pokemona> taldea, int pos)
    {
        int k, kandidatua;

        for (k=1;k<=taldea.size();k++)
        {
            kandidatua = (pos + k) % taldea.size();
            if (taldea.get(kandidatua).getOP() > 0)
            {
                return kandidatua;
            }
        }
        return pos;
    }

    // Borroka osoa jokatzen du, talde batek garaipen guztiak lortu arte (edo jokalariak utzi arte)
    public void Borroka()
    {
        boolean bukatu = false;

        while (!bukatu)
        {
            if (turnue == 1)
            {
                TxandaEzkerra();
                turnue = 0;

                if (irten)
                {
                    bukatu = true;
                }
                else if (JokoaBukatuta())
                {
                    Amaiera();
                    bukatu = true;
                }
            }

            if ((!bukatu) && (turnue == 0))
            {
                TxandaEskuma();
                turnue = 1;

                if (irten)
                {
                    bukatu = true;
                }
                else if (JokoaBukatuta())
                {
                    Amaiera();
                    bukatu = true;
                }
            }
        }

        if (irten)
        {
            Garbitu();
            p("\n  Partida utzi duzu (ez da gordeko).\n");
            Itxaron(2000);
        }
    }

    // Borroka bukatzean hildakoak eta irabazlea erakusten ditu
    public void Amaiera()
    {
        IrabazleaZehaztu();
        TaldekoHildakuek(Taldea1);
        Itxaron(2000);
        TaldekoHildakuek(Taldea2);
        Itxaron(2000);
        PokeBukatute();
    }

    // Ezkerreko taldearen txanda
    public void TxandaEzkerra()
    {
        char n;

        Garbitu();
        PokePoke(Ezker(), Eskuin());
        n = TextEz(Ezker());

        switch (n)
        {
            case 'w':
            {
                // atake normala
                Eskuin().setOP(Eskuin().getOP() - Ezker().getPC());
                PokeEzNormala(Ezker(), Eskuin());
            }break;
            case 'a':
            {
                // atake fuertie
                Ezker().setIT(AtakeFuertiarenIndarra(Ezker(), irabazi1));
                Eskuin().setOP(Eskuin().getOP() - Ezker().getIT());
                PokeEzFuertie(Ezker(), Eskuin());
            }break;
            case 'd':
            {
                // ausazko atakie
                Ezker().setIT(RandomXy(10, 101 + Ezker().getRand()));
                Eskuin().setOP(Eskuin().getOP() - Ezker().getIT());
                PokeEzAusazko(Ezker(), Eskuin());
            }break;
            case 's':
            {
                // osatzeko
                if (Ezker().getTxanpon() == 0)
                {
                    PokeEzDirubaik(Ezker(), Eskuin());
                }
                else
                {
                    Ezker().setOP((float) (Ezker().getOP() + 0.6));
                    Ezker().setTxanpon(Ezker().getTxanpon() - 1);
                    PokeEzOsatu(Ezker(), Eskuin());
                }
            }break;
            case '.':
            {
                // partida utzi (gorde gabe)
                irten = true;
            }break;
        }

        if (irten)
        {
            return;
        }

        // pokemonaren OP maximoa 3 da
        if (Ezker().getOP() > 3)
        {
            Ezker().setOP(3);
        }
        kont++;
        Itxaron(2000);

        if (Eskuin().getOP() <= 0)
        {
            // eskumako pokemona hilda dago
            Taldea1Garaipena();
            Garbitu();
            PokeEsHil(Ezker());
            BorrokaBerria();
            Itxaron(3000);

            if (!JokoaBukatuta())
            {
                HurrengoEskuinBorrokalaria();
            }
        }
        Garbitu();

        if (kont == TXANDAK)
        {
            Berdinketa();
        }
    }

    // Eskumako taldearen txanda
    public void TxandaEskuma()
    {
        int n;

        Garbitu();
        PokePoke(Ezker(), Eskuin());

        if (autoMan == 0)
        {
            // beste pertsona baten kontra: atake mota eskatu
            n = TextEs(Eskuin());
        }
        else
        {
            // ordenagailuaren kontra: atakea ausaz aukeratu
            Itxaron(2000);
            Garbitu();
            p("\n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n");
            n = Random01(4, 8);
            if (n == 7)
            {
                n = 8;
            }
        }

        switch (n)
        {
            case 8:
            {
                // atake normala
                Ezker().setOP(Ezker().getOP() - Eskuin().getPC());
                PokeEsNormala(Ezker(), Eskuin());
            }break;
            case 4:
            {
                // atake fuertie
                Eskuin().setIT(AtakeFuertiarenIndarra(Eskuin(), irabazi2));
                Ezker().setOP(Ezker().getOP() - Eskuin().getIT());
                PokeEsFuertie(Ezker(), Eskuin());
            }break;
            case 6:
            {
                // ausazko atakie
                Eskuin().setIT(RandomXy(10, 101 + Eskuin().getRand()));
                Ezker().setOP(Ezker().getOP() - Eskuin().getIT());
                PokeEsAusazko(Ezker(), Eskuin());
            }break;
            case 5:
            {
                // osatzeko
                if (Eskuin().getTxanpon() == 0)
                {
                    PokeEsDirubaik(Ezker(), Eskuin());
                }
                else
                {
                    Eskuin().setOP((float) (Eskuin().getOP() + 0.6));
                    Eskuin().setTxanpon(Eskuin().getTxanpon() - 1);
                    PokeEsOsatu(Ezker(), Eskuin());
                }
            }break;
            case 9:
            {
                // partida utzi (gorde gabe)
                irten = true;
            }break;
        }

        if (irten)
        {
            return;
        }

        // pokemonaren OP maximoa 3 da
        if (Eskuin().getOP() > 3)
        {
            Eskuin().setOP(3);
        }
        kont++;
        Itxaron(2000);

        if (Ezker().getOP() <= 0)
        {
            // ezkerreko pokemona hilda dago
            Taldea2Garaipena();
            Garbitu();
            PokeEzHil(Eskuin());
            BorrokaBerria();
            Itxaron(3000);

            if (!JokoaBukatuta())
            {
                HurrengoEzkerBorrokalaria();
            }
        }

        if (kont == TXANDAK)
        {
            Berdinketa();
        }
    }

    // TXANDAK bete direnean: OP gehien duen pokemonak irabazten du (borroka amaitzen da, baina partida ez)
    public void Berdinketa()
    {
        if (Ezker().getOP() > Eskuin().getOP())
        {
            Garbitu();
            PokeEmpate(Ezker(), Eskuin());
            Taldea1Garaipena();

            if (!JokoaBukatuta())
            {
                HurrengoEskuinBorrokalaria();
            }
        }
        else
        {
            Taldea2Garaipena();

            if (!JokoaBukatuta())
            {
                HurrengoEzkerBorrokalaria();
            }
        }
        BorrokaBerria();
        Itxaron(3000);
    }

    // Atake fuertiaren indarra: aurreko borroka irabazi bazuen handiagoa, bestela txikiagoa
    public float AtakeFuertiarenIndarra(Pokemona erasotzailea, boolean irabazita)
    {
        if (irabazita)
        {
            return erasotzailea.getPC() * ATAKE_FUERTIE_IRABAZITA;
        }
        return erasotzailea.getPC() * ATAKE_FUERTIE_IRABAZI_GABE;
    }

    // Borroka berri bat hasten da: txanda kontagailua berrabiarazten da
    public void BorrokaBerria()
    {
        kont = 0;
    }

    // Borroka irabazten duen pokemonari hobekuntzak ematen zaizkio
    public void HobekuntzakEman(Pokemona irabazlea)
    {
        irabazlea.setPC(Math.min(1.0f, (float) (irabazlea.getPC() + 0.1)));
        irabazlea.setRand(irabazlea.getRand() + 10);
        irabazlea.setOP(Math.min(3.0f, (float) (irabazlea.getOP() + 0.5)));
        irabazlea.setTxanpon(irabazlea.getTxanpon() + 1);
    }

    // 1. taldeak borroka bat irabazten du
    public void Taldea1Garaipena()
    {
        HobekuntzakEman(Ezker());
        Gkont1++;
        irabazi1 = true;
        irabazi2 = false;
    }

    // 2. taldeak borroka bat irabazten du
    public void Taldea2Garaipena()
    {
        HobekuntzakEman(Eskuin());
        Gkont2++;
        irabazi1 = false;
        irabazi2 = true;
    }

    // Eskumako borrokalari berria aukeratzen du (ordenagailuaren aurka automatikoa)
    public void HurrengoEskuinBorrokalaria()
    {
        if (autoMan == 0)
        {
            BorrokaraZein();
            posEs = TaldekoZein(Taldea2) - 1;
        }
        else
        {
            posEs = HurrengoBizirik(Taldea2, posEs);
        }
    }

    // Ezkerreko borrokalari berria aukeratzen du
    public void HurrengoEzkerBorrokalaria()
    {
        BorrokaraZein();
        posEz = TaldekoZein(Taldea1) - 1;
    }

    // Partida berriro jokatzeko aukera ematen duen programa nagusia
    public void Jokatu()
    {
        char jarraitu;

        Garbitu();

        do {
            Menu();
            PartidaBerriaPrestatu();
            Borroka();

            jarraitu = BerriroJolastu();
            Garbitu();
        } while (jarraitu == 'b');
    }

    // Programa nagusia
    public static void jolastu(Scanner irakurri)
    {
        try
        {
            new Poxamon().Jokatu();
        }
        catch (NoSuchElementException sarreraAmaiera)
        {
            // Teklatua agortu da (EOF): jokoa lasai amaitzen da
            System.out.println();
        }
    }

    // ------------------------------------------------------------------
    // Pantailako marrazkiak eta mezuak
    // ------------------------------------------------------------------

    // Jokoaren azalpena
    public void Menu()
    {
        char jarraitu;

        do
        {
            p("\n   ___________________________________________________________________    ");
            p("\n  |                                                                   |   ");
            p("\n  |                                                                   |   ");
            p("\n  |       Ba al dakizu zein den jokuaren funtzioanamendua?(b/e)       |   ");
            p("\n  |                                                                   |   ");
            p("\n  |___________________________________________________________________|-->");
            System.out.flush();
            jarraitu = SartuKaraktera();
            Garbitu();
        } while ((jarraitu != 'b') && (jarraitu != 'e'));

        if (jarraitu == 'e')
        {
            do
            {
                p("\n\tJoko hau bi bandoren arteko burruka bat da. Bertan lagun baten edo ordenagailuaren");
                p("\n\t\taurka leiatzeko aukera izango duzu, noski, txandak errespetatuz!");
                p("\n\nOP: Osasun Puntuak");
                p("\nPC: Indar Puntuak");
                p("\n \n \tHauek dira zure leiakidea jokoz kanpo uzteko izango dituzun erasoak:");
                p("\n \n\tAtake normala: Zure PC-a izango da eraso indarra.");
                p("\n\tAtake fuertie: Aurreko borroka irabazi bazenuen, zure PC-aren 1,5 halako izango da eraso indarra;");
                p("\n\t\tbestela, zure PC-aren bostena.");
                p("\n\tAusazko atakie: Atake honekin, zure eraso indarra zoriz erabakiko da.");
                p("\n\tOsatu: Txanpon bat erabiltzearen truke zure osasun puntuak(OP) 0.6 areagotuko dira.");
                p("\n \n\t\t10 txanda betetzean--> OP gehien duen eutimona izango da borrokaren garaile (eta partidak jarraitu dezake).");
                p("\n\t\tAurkariaren talde osoa kanporatzen baduzu, garaile zara; bestela, hasieran aukeratutako");
                p("\n\t\tgaraipen kopurura iristen den lehen taldea izango da garaile.");
                p("\n\n\n\tPartida bukatu nahi baduzu irteteko aukera duzu (partida ez da gordeko).");
                p("\n");
                p("\n ____________________________");
                p("\n|                            |");
                p("\n|   Dena ulertu duzu(b/e)?   |");
                p("\n|____________________________|-->");
                System.out.flush();
                jarraitu = SartuKaraktera();
                Garbitu();
            } while (jarraitu == 'e');
        }
    }

    // Borrokara zein joango den galdetzeko koadroa
    public void BorrokaraZein()
    {
        Garbitu();
        p("\n   ___________________________________________________________________    ");
        p("\n  |                                                                   |   ");
        p("\n  |                                                                   |   ");
        p("\n  |                 Hauetako zein joango da borrokara?                |   ");
        p("\n  |                                                                   |   ");
        p("\n  |___________________________________________________________________|   ");
        p("\n                                                                          ");
        p("\n                                                                          ");
        System.out.flush();
    }

    // Taldeko hildakoen zerrenda aurkezteko
    public void TaldekoHildakuek(ArrayList<Pokemona> taldea)
    {
        int b;
        int kopurua = taldea.size();
        p("\n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n");
        for (b = 0; b < kopurua; b++)
        {
            if (taldea.get(b).getOP() <= 0)
            {
                p(String.format("\n -->    %d=%s              \n", b + 1, taldea.get(b).getIzena()));
            }
        }
        p("\n         ___                                                     ^    ^            ");
        p("\n        |   |                                                   / |__/ |           ");
        p("\n   _____|   |_____                                             | >  <   |          ");
        p("\n  |_____ RIP _____|                                           ( A    `. |          ");
        p("\n        |   |               __________________________         \\____  ` /     ___  ");
        p("\n        |   |              |                          |       /___     (    _| __| ");
        p("\n        |   |              |  BURRUKAN ERORI DIRENAK  |      (____)     |  | _|    ");
        p("\n        |   |              |__________________________|       )______   |_|_|      ");
        p("\n ___-__------__----__                                        (___(_____/           ");
        p("\n                                                                                   ");
        System.out.flush();
        Itxaron(3000);
        Garbitu();
    }

    // 1. bandoko euitimonaren datuak aurkezteko
    public void PokeEz(Pokemona taldea1Poke)
    {
        p("\n                         ");
        p("\n           ^    ^        ");
        p("\n          / |__/ |       ");
        p("    Bandoa:1");
        p("\n         |   o  o |      ");
        p("\n         |      -  )     ");
        p(String.format("OP:(%.2f/3)", taldea1Poke.getOP()));
        p("\n   O      |   ____/      ");
        p(String.format("PC:(%.2f/1)", taldea1Poke.getPC()));
        p("\n  o|o     /   _____)     ");
        p("\n   |___  /   /      )    ");
        p(String.format("%d TXANPON", taldea1Poke.getTxanpon()));
        p("\n      |__|  (_______/    ");
        p("\n          |_____)___)    ");
        PokeIzenaSartu(taldea1Poke);
        p("\n                         ");
        System.out.flush();
    }

    // 2. bandoko euitimonaren datuak aurkezteko
    public void PokeEs(Pokemona taldea2Poke)
    {
        p("\n                         ");
        p("\n       ^    ^            ");
        p("\n      / |__/ |           ");
        p("    Bandoa:2");
        p("\n     | o  o   |          ");
        p("\n    ( -       |          ");
        p(String.format("OP:(%.2f/3)", taldea2Poke.getOP()));
        p("\n     \\____    /     ___  ");
        p(String.format("PC:(%.2f/1)", taldea2Poke.getPC()));
        p("\n    /___     (    _| __| ");
        p("\n   (____)     |  | _|    ");
        p(String.format("%d TXANPON", taldea2Poke.getTxanpon()));
        p("\n    )______   |_|_|      ");
        p("\n   (___(_____/           ");
        PokeIzenaSartu(taldea2Poke);
        p("\n                         ");
        System.out.flush();
    }

    // Burrukako datuak aurkezteko
    public void PokePoke(Pokemona taldea1Poke, Pokemona taldea2Poke)
    {
        p("\n    Bandoa:1                                       Bandoa:2              ");
        p("\n                                                                         ");
        p("\n            ");
        p(taldea1Poke.getIzena());
        p("                             ");
        p(taldea2Poke.getIzena());
        p(String.format("\n    OP:(%.2f/3)                                   OP:(%.2f/3)                GARAIPENAK (%d arte):",
                taldea1Poke.getOP(), taldea2Poke.getOP(), garaipenKop));
        p(String.format("\n    PC:(%.2f/1)                                   PC:(%.2f/1)                1.B.:%d",
                taldea1Poke.getPC(), taldea2Poke.getPC(), Gkont1));
        p(String.format("\n    %d TXANPON                                     %d TXANPON                  2.B.:%d",
                taldea1Poke.getTxanpon(), taldea2Poke.getTxanpon(), Gkont2));
        p("\n                                                                         ");
        p("\n           ^    ^                                      ^    ^            ");
        p("\n          / |__/ |                                    / |__/ |           ");
        p("\n         |   o  o |                                  | o  o   |          ");
        p("\n         |      -  )                                ( -       |          ");
        p("\n   O      |   ____/                                  \\____    /     ___  ");
        p("\n  o|o     /   _____)                                /___     (    _| __| ");
        p("\n   |___  /   /      )                              (____)     |  | _|    ");
        p("\n      |__|  (_______/                               )______   |_|_|      ");
        p("\n          |_____)___)                              (___(_____/           ");
        p("\n                                                                         ");
        System.out.flush();
    }

    // Ezkerreko euitimonaren atake normala
    public void PokeEzNormala(Pokemona taldea1Poke, Pokemona taldea2Poke)
    {
        p("\n                                                                         ");
        p("\n      ");
        p(taldea1Poke.getIzena());
        p("                                   ");
        p(taldea2Poke.getIzena());
        p("\n                                                                         ");
        p("\n                                                     ");
        p(String.format("-%.2f", taldea1Poke.getPC()));
        p("\n           ^    ^                                       ^    ^           ");
        p("\n          / |__/ |                                     / |__/ |          ");
        p("\n         |   o  o |                                   |  x  x  |         ");
        p("\n         |      -  )       BANG!                      (   A    |         ");
        p("\n   O      |   ____/   ,_____ .  ..  ...   ....         \\____   /     ___ ");
        p("\n  o|o     /   _____)__|D    `                        _/__   o` |   _| __|");
        p("\n   |___  /   /      )                               (____)  ` /   | _|   ");
        p("\n      |__|  (_______/                               )______   |__|_|     ");
        p("\n          |_____)___)                              (___(_____/           ");
        p("\n                                                                         ");
        System.out.flush();
    }

    // Eskumako euitimonaren atake normala
    public void PokeEsNormala(Pokemona taldea1Poke, Pokemona taldea2Poke)
    {
        p("\n                                                                         ");
        p("\n      ");
        p(taldea1Poke.getIzena());
        p("                                   ");
        p(taldea2Poke.getIzena());
        p("\n                                                                         ");
        p("\n       ");
        p(String.format("-%.2f", taldea2Poke.getPC()));
        p("\n          ^    ^                                      ^    ^             ");
        p("\n         / |__/ |                                    / |__/ |            ");
        p("\n        |   x  x |                                  | o  o   |           ");
        p("\n        |     A   )                    BANG!       ( -       |           ");
        p("\n   O     |   ____/       ....   ...  ..  . _____,   \\____    /     ___   ");
        p("\n  o|o    / o` ____)                       `    C|__/___     (    _| __|  ");
        p("\n   |___  | ` /     )                              (____)     |  | _|     ");
        p("\n      |__|  (_______)                              )______   |_|_|       ");
        p("\n          |_____)___)                              (___(_____/           ");
        p("\n                                                                         ");
        System.out.flush();
    }

    // Ezkerreko euitimonaren atake fuertie
    public void PokeEzFuertie(Pokemona taldea1Poke, Pokemona taldea2Poke)
    {
        p("\n                                                                         ");
        p("\n      ");
        p(taldea1Poke.getIzena());
        p("                                   ");
        p(taldea2Poke.getIzena());
        p("\n                                                                         ");
        p("\n                                                     ");
        p(String.format("-%.2f", taldea1Poke.getIT()));
        p("\n           ^    ^                                          ^    ^        ");
        p("\n          / |__/ |                  -  - -   --   ooo     / |__/ |       ");
        p("\n         |   o  o |        -  - --- - ---  - --oooo oooo |  x  x  |      ");
        p("\n         |      O - ---  --- -- ------ ----- oooo -O- oooo  A     /      ");
        p("\n   O      |   ____/      - --  -- -  --  ----  oooo oooo_\\____  _/   ___ ");
        p("\n  o|o     /   _____)            -    -  - --  -   ooo_/__      (   _| __|");
        p("\n   |___  /   /      )                               (____)     |  | _|   ");
        p("\n      |__|  (_______/                               /______   |__|_|     ");
        p("\n          |_____)___)                              (___(_____/           ");
        p("\n                                                                         ");
        System.out.flush();
    }

    // Eskumako euitimonaren atake fuertie
    public void PokeEsFuertie(Pokemona taldea1Poke, Pokemona taldea2Poke)
    {
        p("\n                                                                         ");
        p("\n      ");
        p(taldea1Poke.getIzena());
        p("                                   ");
        p(taldea2Poke.getIzena());
        p("\n                                                                         ");
        p("\n       ");
        p(String.format("-%.2f", taldea2Poke.getIT()));
        p("\n        ^    ^                                         ^    ^            ");
        p("\n       / |__/ |_    ooo   --   - -  -                 / |__/ |           ");
        p("\n       |   x  x |oooo oooo-- -  -- - --- -  -        | o  o   |          ");
        p("\n        |    A oooo -o- oooo ---- ------ -- ---  -- - O       |          ");
        p("\n  O      |   ____oooo oooo  ---  --  - --  -- -      \\____    /     ___  ");
        p("\n o|o     /   _____) ooo   -  -- -  -    -           /___     (    _| __| ");
        p("\n  |___  |   /      |                               (____)     |  | _|    ");
        p("\n      |__|  |_______)                               )______   |_|_|      ");
        p("\n          )_____)___)                              (___(_____/           ");
        p("\n                                                                         ");
        System.out.flush();
    }

    // Ezkerreko euitimonaren ausazko atakie
    public void PokeEzAusazko(Pokemona taldea1Poke, Pokemona taldea2Poke)
    {
        p("\n                                                                         ");
        p("\n      ");
        p(taldea1Poke.getIzena());
        p("                                   ");
        p(taldea2Poke.getIzena());
        p("\n                                                                         ");
        p("\n                                                     ");
        p(String.format("-%.2f", taldea1Poke.getIT()));
        p("\n           ^    ^                                      ^    ^            ");
        p("\n          / |__/ |                  .____________,    / |__/ |           ");
        p("\n         |   o  o |                 ||?   )      )   | x  x   |          ");
        p("\n         |      -  )           __   ||   (BANG!!(   (   A     |          ");
        p("\n   O      |   ____/           |__| * *    )______)   \\____    /     ___  ");
        p("\n  o|o     /   _____)         (|__|*  * *            /___     (    _| __| ");
        p("\n   |___  /   /      )         |__|_*__*__ *        (____)     |  | _|    ");
        p("\n      |__|  (_______/            |  |?|  |          )______   |_|_|      ");
        p("\n          |_____)___)            |__|_|__|         (___(_____/           ");
        p("\n                                                                         ");
        System.out.flush();
    }

    // Eskumako euitimonaren ausazko atakie
    public void PokeEsAusazko(Pokemona taldea1Poke, Pokemona taldea2Poke)
    {
        p("\n                                                                         ");
        p("\n      ");
        p(taldea1Poke.getIzena());
        p("                                   ");
        p(taldea2Poke.getIzena());
        p("\n                                                                         ");
        p("\n       ");
        p(String.format("-%.2f", taldea2Poke.getIT()));
        p("\n           ^    ^                                      ^    ^            ");
        p("\n          / |__/ |    ,____________.                  / |__/ |           ");
        p("\n         |   x  x |   (      (   ?||                 | o  o   |          ");
        p("\n         |     A   )   )BANG!!)   ||   __           ( -       |          ");
        p("\n   O      |   ____/   (______(    * * |__|           \\____    /     ___  ");
        p("\n  o|o     /   _____)            * *  *|__|)         /___     (    _| __| ");
        p("\n   |___  /   /      )        * __*__*_|__|         (____)     |  | _|    ");
        p("\n      |__|  (_______/         |  |?|  |             )______   |_|_|      ");
        p("\n          |_____)___)         |__|_|__|            (___(_____/           ");
        p("\n                                                                         ");
        System.out.flush();
    }

    // Ezkerreko euitimona osatzeko erasoaren animazioa
    public void PokeEzOsatu(Pokemona taldea1Poke, Pokemona taldea2Poke)
    {
        p("\n                                                                         ");
        p("\n      ");
        p(taldea1Poke.getIzena());
        p("                                   ");
        p(taldea2Poke.getIzena());
        p("\n                                                                         ");
        p("\n            +0.6                                                         ");
        p("\n           ^    ^                                      ^    ^            ");
        p("\n       *  / |__/ |*                                   / |__/ |           ");
        p("\n         | * -  - |  *                               | o  o   |          ");
        p("\n    *   *|      o  )*                               ( -       |          ");
        p("\n   O  *   | * ____/   *                              \\____    /     ___  ");
        p("\n  o|o   * /   *____) *                              /___     (    _| __| ");
        p("\n   |___* /  */    * )  *                           (____)     |  | _|    ");
        p("\n      |__|  (_______/*                              )______   |_|_|      ");
        p("\n    *     |_____)___)  *                           (___(_____/           ");
        p("\n                                                                         ");
        System.out.flush();
    }

    // Eskumako euitimona osatzeko erasoaren animazioa
    public void PokeEsOsatu(Pokemona taldea1Poke, Pokemona taldea2Poke)
    {
        p("\n                                                                         ");
        p("\n      ");
        p(taldea1Poke.getIzena());
        p("                                   ");
        p(taldea2Poke.getIzena());
        p("\n                                                                         ");
        p("\n                                                        +0.6             ");
        p("\n           ^    ^                                      ^    ^            ");
        p("\n          / |__/ |                                 *  / |__/ |  *        ");
        p("\n         |   o  o |                              *   | -  -   |*         ");
        p("\n         |      -  )                              * ( o *     |    *     ");
        p("\n   O      |   ____/                              *   \\____  * /  *  ___  ");
        p("\n  o|o     /   _____)                           *   */___  *  ( *  _| __| ");
        p("\n   |___  /   /      )                             *(____)*    |  | _|    ");
        p("\n      |__|  (_______/                           *   )______*  |_|_|  *   ");
        p("\n          |_____)___)                              (___(_____/           ");
        p("\n                                                                         ");
        System.out.flush();
    }

    // Ezkerreko euitimona diru barik dagoela
    public void PokeEzDirubaik(Pokemona taldea1Poke, Pokemona taldea2Poke)
    {
        p("\n                                                                         ");
        p("\n      ");
        p(taldea1Poke.getIzena());
        p("                                   ");
        p(taldea2Poke.getIzena());
        p("\n                                                                         ");
        p("\n                                                                         ");
        p("\n           ^    ^                                      ^    ^            ");
        p("\n          / |__/ |      /                             / |__/ |           ");
        p("\n         |   - -  |    $              Tooontoooo!!   | >  <   |          ");
        p("\n         |     _   )  /                 Pooobree!   ( o       |          ");
        p("\n   O      |   ____/                                  \\____    /     ___  ");
        p("\n  o|o     /   _____)                                /___     (    _| __| ");
        p("\n   |___  /   /      )                              (____)     |  | _|    ");
        p("\n      |__|  (_______/                               )______   |_|_|      ");
        p("\n          |_____)___)                              (___(_____/           ");
        p("\n                                                                         ");
        System.out.flush();
    }

    // Eskumako euitimona diru barik dagoela
    public void PokeEsDirubaik(Pokemona taldea1Poke, Pokemona taldea2Poke)
    {
        p("\n                                                                         ");
        p("\n      ");
        p(taldea1Poke.getIzena());
        p("                                   ");
        p(taldea2Poke.getIzena());
        p("\n                                                                         ");
        p("\n                                                                         ");
        p("\n           ^    ^                                      ^    ^            ");
        p("\n          / |__/ |                               /    / |__/ |           ");
        p("\n         |   >  < |  Tooontoooo!!               $    |  - -   |          ");
        p("\n         |      o  )   Pooobree!               /    (  _      |          ");
        p("\n   O      |   ____/                                  \\____    /     ___  ");
        p("\n  o|o     /   _____)                                /___     (    _| __| ");
        p("\n   |___  /   /      )                              (____)     |  | _|    ");
        p("\n      |__|  (_______/                               )______   |_|_|      ");
        p("\n          |_____)___)                              (___(_____/           ");
        p("\n                                                                         ");
        System.out.flush();
    }

    // Ezkerreko euitimona hilda dagoela
    public void PokeEzHil(Pokemona taldea2Poke)
    {
        p("\n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n");
        p("\n                                                                         ");
        p("\n    ");
        p(taldea2Poke.getIzena());
        p(" izan da irabazle!                   ");
        p("\n                                                                         ");
        p("\n                ___                                    ^    ^            ");
        p("\n               |   |                                  / |__/ |           ");
        p("\n          _____|   |_____                            | o  o   |          ");
        p("\n         |_____ RIP _____|                          ( -       |          ");
        p("\n               |   |                                 \\____   /      ___  ");
        p("\n               |   |                                /___     (    _| __| ");
        p("\n               |   |                               (____)     |  | _|    ");
        p("\n               |   |                                )______   |_|_|      ");
        p("\n        ___-__------__-----____                    (___(_____/           ");
        p("\n                                                                         ");
        System.out.flush();
    }

    // Eskumako euitimona hilda dagoela
    public void PokeEsHil(Pokemona taldea1Poke)
    {
        p("\n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n");
        p("\n                                                                         ");
        p("\n    ");
        p(taldea1Poke.getIzena());
        p(" izan da irabazle!                   ");
        p("\n                                                                         ");
        p("\n           ^    ^                                ___                     ");
        p("\n          / |__/ |                              |   |                    ");
        p("\n         |   o  o |                        _____|   |_____               ");
        p("\n         |      -  )                      |_____ RIP _____|              ");
        p("\n   O      |   ____/                             |   |                    ");
        p("\n  o|o     /   _____)                            |   |                    ");
        p("\n   |___  /   /      )                           |   |                    ");
        p("\n      |__|  (_______/                           |   |                    ");
        p("\n          |_____)___)                    ___-__------__-----____         ");
        p("\n                                                                         ");
        System.out.flush();
    }

    // Enpatea egon dela
    public void PokeEmpate(Pokemona taldea1Poke, Pokemona taldea2Poke)
    {
        p("\n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n");
        p("\n                                                                         ");
        if (taldea1Poke.getOP() > taldea2Poke.getOP())
        {
            p("\n    ");
            p(taldea1Poke.getIzena());
            p(" izan da irabazle!");
            p("\n                                                                         ");
            p("\n           ^    ^                                      ^    ^            ");
            p("\n          / |__/ |                                    / |__/ |           ");
            p("\n         |   >  < |                                  | -  -   |          ");
            p("\n         |      o  )                                ( -       |          ");
        }
        else
        {
            p("\n    ");
            p(taldea2Poke.getIzena());
            p(" izan da irabazle!");
            p("\n                                                                         ");
            p("\n           ^    ^                                      ^    ^            ");
            p("\n          / |__/ |                                    / |__/ |           ");
            p("\n         |   -  - |                                  | >  <   |          ");
            p("\n         |      -  )                                ( o       |          ");
        }
        p("\n   O      |   ____/                                  \\____    /     ___  ");
        p("\n  o|o     /   _____)                                /___     (    _| __| ");
        p("\n   |___  /   /      )                              (____)     |  | _|    ");
        p("\n      |__|  (_______/                               )______   |_|_|      ");
        p("\n          |_____)___)                              (___(_____/           ");
        p("\n                                                                         ");
        System.out.flush();
    }

    // Partida bukatu dela
    public void PokeBukatute()
    {
        p("\n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n");
        p("\n                                                                         ");
        if (irabazlea == 1)
        {
            p("\n    ");
            p("1. Taldea izan da irabazle!");
        }
        else
        {
            p("\n    ");
            p("2. Taldea izan da irabazle!");
        }
        p("\n                                                                         ");
        p("\n                                  *                                      ");
        p("\n           ^    ^                ***                   ^    ^            ");
        p("\n          / |__/ |              *****                 / |__/ |           ");
        p("\n         |   o  o |      *******************         | o  o   |          ");
        p("\n         |      -  )         ***********            ( -       |          ");
        p("\n   O      |   ____/            *******               \\____    /     ___  ");
        p("\n  o|o     /   _____)          *********             /___     (    _| __| ");
        p("\n   |___  /   /      )        ***     ***           (____)     |  | _|    ");
        p("\n      |__|  (_______/       *           *           )______   |_|_|      ");
        p("\n          |_____)___)                              (___(_____/           ");
        p("\n                                                                         ");
        System.out.flush();
    }

    // Ezkerreko euitimonaren azpiko testua eta aukera
    public char TextEz(Pokemona taldea1Poke)
    {
        char n;
        p("\n                                                                          ");
        p("\n   Zer egingo du ");
        p(taldea1Poke.getIzena());
        p(" POXAMONak?");
        p("\n   ___________________________________________________________________    ");
        p("\n  |                                                                   |   ");
        p("\n  |                         Atake normala (W)                         |   ");
        p("\n  |      Atake fuertie (A)                     Ausazko atakie (D)     |   ");
        if (taldea1Poke.getTxanpon() != 0)
        {
            p("\n  |                            Osatzeko (S)                           |   ");
        }
        else
        {
            p("\n  |                                                                   |   ");
        }
        p("\n  |                                                                   |   ");
        p(String.format("\n  |   T:%d               ((Partidatik irten[.]))                       |   ",
                kont + 1));
        p("\n  |___________________________________________________________________|-->");
        System.out.flush();
        do
        {
            n = SartuKaraktera();
        } while ((n != 'w') && (n != 'a') && (n != 's') && (n != 'd') && (n != '.'));
        p("\n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n");
        return n;
    }

    // Eskumako euitimonaren azpiko testua eta aukera
    public int TextEs(Pokemona taldea2Poke)
    {
        int n;
        p("\n                                                                          ");
        p("\n   Zer egingo du ");
        p(taldea2Poke.getIzena());
        p(" POXAMONak?");
        p("\n   ___________________________________________________________________    ");
        p("\n  |                                                                   |   ");
        p("\n  |                         Atake normala (8)                         |   ");
        p("\n  |      Atake fuertie (4)                     Ausazko atakie (6)     |   ");
        if (taldea2Poke.getTxanpon() != 0)
        {
            p("\n  |                            Osatzeko (5)                           |   ");
        }
        else
        {
            p("\n  |                                                                   |   ");
        }
        p("\n  |                                                                   |   ");
        p(String.format("\n  |   T:%d               ((Partidatik irten[9]))                       |   ",
                kont + 1));
        p("\n  |___________________________________________________________________|-->");
        System.out.flush();
        do
        {
            n = SartuZenbakia("");
        } while ((n != 8) && (n != 4) && (n != 5) && (n != 6) && (n != 9));
        p("\n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n");
        return n;
    }

    // Noren aurka borrokatuko den galdetzen du
    public void NorenAurka()
    {
        p("\n   ___________________________________________________________________    ");
        p("\n  |                                                                   |   ");
        p("\n  |                 Noren aurka egingo duzu burruka?                  |   ");
        p("\n  |                                                                   |   ");
        p("\n  |              Partida beste pertsona baten kontra (0)              |   ");
        p("\n  |                    Ordenagailuaren kontra (1)                     |   ");
        p("\n  |                                                                   |   ");
        p("\n  |___________________________________________________________________|-->");
        System.out.flush();
    }

    // Taldeko pokemon kopurua galdetzen du
    public void PokeKopKopurua()
    {
        p("\n   ___________________________________________________________________    ");
        p("\n  |                                                                   |   ");
        p("\n  |                                                                   |   ");
        p("\n  |      Zein izango da taldeko eduki beharreko poxamon kopurua?      |   ");
        p("\n  |                                                                   |   ");
        p("\n  |___________________________________________________________________|-->");
        System.out.flush();
    }

    // Zenbat garaipenera jokatuko den galdetzen du
    public void GaraipenKopKopurua()
    {
        p("\n   ___________________________________________________________________    ");
        p("\n  |                                                                   |   ");
        p("\n  |               Zenbat garaipenera jokatu nahi duzue?               |   ");
        p("\n  |           (talde bat guztiz kanporatzen ez bada)  (1-99)          |   ");
        p("\n  |                                                                   |   ");
        p("\n  |___________________________________________________________________|-->");
        System.out.flush();
    }

    // Partida berriro jokatu nahi den galdetzen du
    public char BerriroJolastu()
    {
        char jarraitu;
        do
        {
            p("\n   ___________________________________________________________________    ");
            p("\n  |                                                                   |   ");
            p("\n  |                                                                   |   ");
            p("\n  |                 Berriro jolastu nahi al duzu?(b/e)                |   ");
            p("\n  |                                                                   |   ");
            p("\n  |___________________________________________________________________|-->");
            System.out.flush();
            jarraitu = SartuKaraktera();
            Garbitu();
        } while ((jarraitu != 'b') && (jarraitu != 'e'));
        return jarraitu;
    }
}
