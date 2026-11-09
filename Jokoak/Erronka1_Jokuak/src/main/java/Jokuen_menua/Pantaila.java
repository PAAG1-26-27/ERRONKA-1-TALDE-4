package Jokuen_menua;

import java.util.List;

/**
 * Pantailako marrazkiak eta elkarrekintzako mezuak.
 *
 * <p>C-ko jatorrizko fitxategiko {@code printf()} sorta osoaren baliokidea da:
 * ASCII artea, menuak, galderak eta eraso-animazioak.</p>
 */
public final class Pantaila {

    /** {@code -Djoko.azkarra=true} emanez gero, itxaronaldirik ez da egiten (proba egiteko). */
    private static final boolean AZKARRA =
            "true".equalsIgnoreCase(System.getProperty("joko.azkarra", "false"));

    private Pantaila() {
        // utilitate klasea: ezin da instantziatu
    }

    /** {@code printf()} baliokidea. */
    public static void p(String testua) {
        System.out.print(testua);
    }

    /** {@code system("cls")} baliokidea (kontsola erreala denean soilik garbitzen du). */
    public static void garbitu() {
        if (System.console() != null) {
            p("\033[H\033[2J");
            System.out.flush();
        }
    }

    /** {@code Sleep()} baliokidea; ms milisegundotan. */
    public static void itxaron(long ms) {
        if (AZKARRA || ms <= 0L) {
            return;
        }
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // ------------------------------------------------------------------
    // Sarrera eta aukeraketak
    // ------------------------------------------------------------------

    /** Euitimonaren izena eskatzen du eta objektuan gordetzen du ({@code poke_izena_sartu}). */
    public static void pokeIzenaSartu(Pokemona pokemona) {
        p("Izena: ");
        System.out.flush();
        pokemona.setIzena(Teklatua.lerroa());
    }

    /** Taldeko zein aukeratu nahi den galdetzen du ({@code taldeko_zein}). */
    public static int taldekoZein(List<Pokemona> taldea, Gimnasioa gimnasio) {
        int b;
        int kopurua = Math.min(gimnasio.getPokeKop(), taldea.size());
        for (b = 0; b < kopurua; b++) {
            if (taldea.get(b).getOP() > 0) {
                p(String.format("\n -->    %d=%s              \n", b + 1, taldea.get(b).getIzena()));
            }
        }
        p("\n");
        p("\n   ___________________     ");
        p("\n  |                   |    ");
        p("\n  |     AUKERATU:     |===>");
        p("\n  |___________________|  (ZENB.)->");
        System.out.flush();

        int hautatua = Teklatua.osoa();
        // Jatorrizko kodeak ez zuen egiaztapenik; hemen indizeak mugen barruan
        // eta hautatutako pokemona bizirik egon dadin ziurtatzen da, borrokan
        // hildako euitimon bat berriro sar ez dadin.
        while (hautatua < 1 || hautatua > kopurua
                || taldea.get(hautatua - 1).getOP() <= 0) {
            p("\n  |___________________|  (ZENB.)->");
            System.out.flush();
            hautatua = Teklatua.osoa();
        }
        return hautatua;
    }

    /** Borrokara zein joango den galdetzeko koadroa ({@code borrokara_zein}). */
    public static void borrokaraZein() {
        garbitu();
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

    /** Taldeko hildakoen zerrenda aurkezteko ({@code taldeko_hildakuek}). */
    public static void taldekoHildakuek(List<Pokemona> taldea, Gimnasioa gimnasio) {
        int b;
        int kopurua = Math.min(gimnasio.getPokeKop(), taldea.size());
        p("\n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n");
        for (b = 0; b < kopurua; b++) {
            if (taldea.get(b).getOP() <= 0) {
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
        itxaron(3000);
        garbitu();
    }

    /** Jokoaren azalpena ({@code menu}). */
    public static void menu() {
        char jarraitu;

        do {
            p("\n   ___________________________________________________________________    ");
            p("\n  |                                                                   |   ");
            p("\n  |                                                                   |   ");
            p("\n  |       Ba al dakizu zein den jokuaren funtzioanamendua?(b/e)       |   ");
            p("\n  |                                                                   |   ");
            p("\n  |___________________________________________________________________|-->");
            System.out.flush();
            jarraitu = Teklatua.karakterea();
            garbitu();
        } while ((jarraitu != 'b') && (jarraitu != 'e'));

        if (jarraitu == 'e') {
            do {
                p("\n\tJoko hau bi bandoren arteko burruka bat da. Bertan lagun baten edo ordenagailuaren");
                p("\n\t\taurka leiatzeko aukera izango duzu, noski, txandak errespetatuz!");
                p("\n \n Hauek dira zure leiakidea jokoz kanpo uzteko izango dituzun erasoak:");
                p("\n \n\tAtake normala: Zure PC-a izango da eraso indarra.");
                p("\n\tAtake fuertie: Gimnasioaren jabegoa zure taldeak badauka, gimnasioaren ");
                p("\n     aurrerapenaren eta zure PC-aren balditzatan izango da eraso indarra.");
                p("\n\tAusazko atakie: Atake honekin, zure eraso indarra zoriz erabakiko da.");
                p("\n\tOsatu: Txanpon bat erabiltzearen truke zure osasun puntuak(OP) 0.6 areagotuko dira.");
                p("\n \n\t\tTurnoak bukatzean--> OP gehien duen eutimona izango da garaile.");
                p("\n\t\tGaraipen kopurua hasieran aukeratu den taldekide kopurura iristean--> Burruka bukatutzat emango da.");
                p("\n\n\n\tPartida bukatu nahi baduzu gorde eta irtetzeko aukera duzu.");
                p("\n");
                p("\n ____________________________");
                p("\n|                            |");
                p("\n|   Dena ulertu duzu(b/e)?   |");
                p("\n|____________________________|-->");
                System.out.flush();
                jarraitu = Teklatua.karakterea();
                garbitu();
            } while (jarraitu == 'e');
        }
    }

    // ------------------------------------------------------------------
    // Taldeetako euitimonen aurkezpena
    // ------------------------------------------------------------------

    /** 1. bandoko euitimonaren datuak aurkezteko ({@code poke_ez}). */
    public static void pokeEz(Pokemona taldea1Poke) {
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
        pokeIzenaSartu(taldea1Poke);
        p("\n                         ");
        System.out.flush();
    }

    /** 2. bandoko euitimonaren datuak aurkezteko ({@code poke_es}). */
    public static void pokeEs(Pokemona taldea2Poke) {
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
        pokeIzenaSartu(taldea2Poke);
        p("\n                         ");
        System.out.flush();
    }

    /** Burrukako datuak aurkezteko ({@code poke_poke}). */
    public static void pokePoke(Gimnasioa gimnasio, Pokemona taldea1Poke, Pokemona taldea2Poke) {
        p(String.format("\n                             Aurrerapena:%d                              ",
                gimnasio.getAurrerapena() - 4));
        p("\n                           Gimnasioko jabeak:                            ");
        if (gimnasio.getJabegoa() == 1) {
            p("\n                              <-                                         ");
        } else {
            p("\n                                         ->                              ");
        }
        p("\n    Bandoa:1                                       Bandoa:2              ");
        p("\n                                                                         ");
        p("\n            ");
        p(taldea1Poke.getIzena());
        p("                             ");
        p(taldea2Poke.getIzena());
        p(String.format("\n    OP:(%.2f/3)                                   OP:(%.2f/3)                GARAIPENAK:",
                taldea1Poke.getOP(), taldea2Poke.getOP()));
        p(String.format("\n    PC:(%.2f/1)                                   PC:(%.2f/1)                1.B.:%d",
                taldea1Poke.getPC(), taldea2Poke.getPC(), gimnasio.getGkont1()));
        p(String.format("\n    %d TXANPON                                     %d TXANPON                  2.B.:%d",
                taldea1Poke.getTxanpon(), taldea2Poke.getTxanpon(), gimnasio.getGkont2()));
        p("\n                                                                         ");
        if ((gimnasio.getT1burua() == gimnasio.getI()) || (gimnasio.getT2burua() == gimnasio.getJ())) {
            if (gimnasio.getT1burua() == gimnasio.getI()) {
                p("\n THE BOSS  ^    ^                                      ^    ^            ");
                p("\n */\\^/\\*  / |__/ |                                    / |__/ |           ");
                p("\n* |_o_| *|   o  o |                                  | o  o   |          ");
                p("\n *     * |      -  )                                ( -       |          ");
                p("\n   O      |   ____/                                  \\____    /     ___  ");
            }
            if (gimnasio.getT2burua() == gimnasio.getJ()) {
                p("\n           ^    ^                                      ^    ^   THE BOSS ");
                p("\n          / |__/ |                                    / |__/ |   */\\^/\\* ");
                p("\n         |   o  o |                                  | o  o   | * |_o_| *");
                p("\n         |      -  )                                ( -       |  *     * ");
                p("\n   O      |   ____/                                  \\____    /     ___  ");
            }
        } else {
            p("\n           ^    ^                                      ^    ^            ");
            p("\n          / |__/ |                                    / |__/ |           ");
            p("\n         |   o  o |                                  | o  o   |          ");
            p("\n         |      -  )                                ( -       |          ");
            p("\n   O      |   ____/                                  \\____    /     ___  ");
        }
        p("\n  o|o     /   _____)                                /___     (    _| __| ");
        p("\n   |___  /   /      )                              (____)     |  | _|    ");
        p("\n      |__|  (_______/                               )______   |_|_|      ");
        p("\n          |_____)___)                              (___(_____/           ");
        p("\n                                                                         ");
        System.out.flush();
    }

    // ------------------------------------------------------------------
    // Eraso animazioak
    // ------------------------------------------------------------------

    /** Ezkerreko euitimonaren atake normala ({@code poke_ez_Normala}). */
    public static void pokeEzNormala(Pokemona taldea1Poke, Pokemona taldea2Poke) {
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

    /** Eskumako euitimonaren atake normala ({@code poke_es_Normala}). */
    public static void pokeEsNormala(Pokemona taldea1Poke, Pokemona taldea2Poke) {
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

    /** Ezkerreko euitimonaren atake fuertie ({@code poke_ez_Fuertie}). */
    public static void pokeEzFuertie(Pokemona taldea1Poke, Pokemona taldea2Poke) {
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

    /** Eskumako euitimonaren atake fuertie ({@code poke_es_Fuertie}). */
    public static void pokeEsFuertie(Pokemona taldea1Poke, Pokemona taldea2Poke) {
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

    /** Ezkerreko euitimonaren ausazko atakie ({@code poke_ez_Ausazko}). */
    public static void pokeEzAusazko(Pokemona taldea1Poke, Pokemona taldea2Poke) {
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

    /** Eskumako euitimonaren ausazko atakie ({@code poke_es_Ausazko}). */
    public static void pokeEsAusazko(Pokemona taldea1Poke, Pokemona taldea2Poke) {
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

    /** Ezkerreko euitimona osatzeko erasoaren animazioa ({@code poke_ez_Osatu}). */
    public static void pokeEzOsatu(Pokemona taldea1Poke, Pokemona taldea2Poke) {
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

    /** Eskumako euitimona osatzeko erasoaren animazioa ({@code poke_es_Osatu}). */
    public static void pokeEsOsatu(Pokemona taldea1Poke, Pokemona taldea2Poke) {
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

    /** Ezkerreko euitimona diru barik dagoela ({@code poke_ez_dirubaik}). */
    public static void pokeEzDirubaik(Pokemona taldea1Poke, Pokemona taldea2Poke) {
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

    /** Eskumako euitimona diru barik dagoela ({@code poke_es_dirubaik}). */
    public static void pokeEsDirubaik(Pokemona taldea1Poke, Pokemona taldea2Poke) {
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

    /** Ezkerreko euitimona hilda dagoela ({@code poke_ez_hil}). */
    public static void pokeEzHil(Pokemona taldea2Poke) {
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

    /** Eskumako euitimona hilda dagoela ({@code poke_es_hil}). */
    public static void pokeEsHil(Pokemona taldea1Poke) {
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

    /** Enpatea egon dela ({@code poke_empate}). */
    public static void pokeEmpate(Pokemona taldea1Poke, Pokemona taldea2Poke) {
        p("\n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n");
        p("\n                                                                         ");
        if (taldea1Poke.getOP() > taldea2Poke.getOP()) {
            p("\n    ");
            p(taldea1Poke.getIzena());
            p(" izan da irabazle!");
            p("\n                                                                         ");
            p("\n           ^    ^                                      ^    ^            ");
            p("\n          / |__/ |                                    / |__/ |           ");
            p("\n         |   >  < |                                  | -  -   |          ");
            p("\n         |      o  )                                ( -       |          ");
        } else {
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

    /** Partida bukatu dela ({@code poke_bukatute}). */
    public static void pokeBukatute(Gimnasioa gimnasio) {
        p("\n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n");
        p("\n                                                                         ");
        if (gimnasio.getGkont1() > gimnasio.getGkont2()) {
            p("\n    ");
            p("1. Taldea izan da irabazle!");
        } else {
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

    // ------------------------------------------------------------------
    // Ekintza-aukerak
    // ------------------------------------------------------------------

    /** Ezkerreko euitimonaren azpiko testua eta aukera ({@code text_ez}). */
    public static char textEz(Pokemona taldea1Poke, Gimnasioa gimnasio) {
        char n;
        p("\n                                                                          ");
        p("\n   Zer egingo du ");
        p(taldea1Poke.getIzena());
        p(" EUITIMONak?");
        p("\n   ___________________________________________________________________    ");
        p("\n  |                                                                   |   ");
        p("\n  |                         Atake normala (W)                         |   ");
        p("\n  |      Atake fuertie (A)                     Ausazko atakie (D)     |   ");
        if (taldea1Poke.getTxanpon() != 0) {
            p("\n  |                            Osatzeko (S)                           |   ");
        } else {
            p("\n  |                                                                   |   ");
        }
        p("\n  |                                                                   |   ");
        p(String.format("\n  |   T:%d               ((Partidatik irten[.]))                       |   ",
                gimnasio.getKont() + 1));
        p("\n  |___________________________________________________________________|-->");
        System.out.flush();
        do {
            n = Teklatua.karakterea();
        } while ((n != 'w') && (n != 'a') && (n != 's') && (n != 'd') && (n != '.'));
        p("\n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n");
        return n;
    }

    /** Eskumako euitimonaren azpiko testua eta aukera ({@code text_es}). */
    public static int textEs(Pokemona taldea2Poke, Gimnasioa gimnasio) {
        int n;
        p("\n                                                                          ");
        p("\n   Zer egingo du ");
        p(taldea2Poke.getIzena());
        p(" EUITIMONak?");
        p("\n   ___________________________________________________________________    ");
        p("\n  |                                                                   |   ");
        p("\n  |                         Atake normala (8)                         |   ");
        p("\n  |      Atake fuertie (4)                     Ausazko atakie (6)     |   ");
        if (taldea2Poke.getTxanpon() != 0) {
            p("\n  |                            Osatzeko (5)                           |   ");
        } else {
            p("\n  |                                                                   |   ");
        }
        p("\n  |                                                                   |   ");
        p(String.format("\n  |   T:%d               ((Partidatik irten[9]))                       |   ",
                gimnasio.getKont() + 1));
        p("\n  |___________________________________________________________________|-->");
        System.out.flush();
        do {
            n = Teklatua.osoa();
        } while ((n != 8) && (n != 4) && (n != 5) && (n != 6) && (n != 9));
        p("\n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n");
        return n;
    }

    // ------------------------------------------------------------------
    // Partidaren hasierako galderak
    // ------------------------------------------------------------------

    /** Aurretiko partida kargatu nahi den galdetzen du. */
    public static char partidaKargatuGaldera() {
        char jarraitu;
        do {
            p("\n   ___________________________________________________________________    ");
            p("\n  |                                                                   |   ");
            p("\n  |                                                                   |   ");
            p("\n  |              Aurretiko partida kargatu nahi duzu?(b/e)            |   ");
            p("\n  |                                                                   |   ");
            p("\n  |___________________________________________________________________|-->");
            System.out.flush();
            jarraitu = Teklatua.karakterea();
            garbitu();
        } while ((jarraitu != 'b') && (jarraitu != 'e'));
        return jarraitu;
    }

    /** Noren aurka borrokatuko den galdetzen du. */
    public static void norenAurka() {
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

    /** Taldeko pokemon kopurua galdetzen du. */
    public static void pokeKopKopurua() {
        p("\n   ___________________________________________________________________    ");
        p("\n  |                                                                   |   ");
        p("\n  |                                                                   |   ");
        p("\n  |      Zein izango da taldeko eduki beharreko pokemon kopurua?      |   ");
        p("\n  |                                                                   |   ");
        p("\n  |___________________________________________________________________|-->");
        System.out.flush();
    }

    /** Taldeko burua nor izango den galdetzen du ("Hauetako zein..."). */
    public static void buruaAukeratu() {
        p("\n   ___________________________________________________________________    ");
        p("\n  |                                                                   |   ");
        p("\n  |                                                                   |   ");
        p("\n  |            Hauetako zein izango da zure taldeko burua?            |   ");
        p("\n  |                                                                   |   ");
        p("\n  |___________________________________________________________________|   ");
        p("\n                                                                          ");
        p("\n                                                                          ");
        System.out.flush();
    }

    /** Partida berriro jokatu nahi den galdetzen du. */
    public static char berriroJolastu() {
        char jarraitu;
        do {
            p("\n   ___________________________________________________________________    ");
            p("\n  |                                                                   |   ");
            p("\n  |                                                                   |   ");
            p("\n  |                 Berriro jolastu nahi al duzu?(b/e)                |   ");
            p("\n  |                                                                   |   ");
            p("\n  |___________________________________________________________________|-->");
            System.out.flush();
            jarraitu = Teklatua.karakterea();
            garbitu();
        } while ((jarraitu != 'b') && (jarraitu != 'e'));
        return jarraitu;
    }
}
