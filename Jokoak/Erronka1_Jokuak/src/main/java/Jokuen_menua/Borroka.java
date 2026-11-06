package Jokuen_menua;

import java.util.List;

/**
 * Borrokaren motorra: txandak, erasoak, heriotzak, enpateak eta garaipenak.
 *
 * <p>
 * C-ko {@code main()}-eko {@code while(bukatu!=9)} begiztaren baliokidea da,
 * eragiketen ordena zehatz-mehatz mantenduz.</p>
 */
public class Borroka {

    private final List<Pokemona> taldea1;
    private final List<Pokemona> taldea2;
    private final Gimnasioa gimnasio;
    private final FitxategiKudeatzailea fitxategiak;

    /**
     * {@code bukatu} aldagaia: 9 balioak borroka amaitu dela adierazten du.
     */
    private int bukatu;

    public Borroka(List<Pokemona> taldea1, List<Pokemona> taldea2,
            Gimnasioa gimnasio, FitxategiKudeatzailea fitxategiak) {
        this.taldea1 = taldea1;
        this.taldea2 = taldea2;
        this.gimnasio = gimnasio;
        this.fitxategiak = fitxategiak;
    }

    /**
     * Borrokan dagoen ezkerreko (1. taldeko) euitimona.
     */
    private Pokemona ezker() {
        return taldea1.get(gimnasio.getI());
    }

    /**
     * Borrokan dagoen eskumako (2. taldeko) euitimona.
     */
    private Pokemona eskuin() {
        return taldea2.get(gimnasio.getJ());
    }

    /**
     * Borroka osoa jokatzen du partida amaitu arte (edo jokalariak gorde eta
     * irtetzea erabakitzen duen arte).
     */
    public void hasi() {
        bukatu = 0;
        while (bukatu != 9) {
            if (gimnasio.getTurnue() == 1) {
                txandaEzkerra();
            }

            if ((gimnasio.getGkont1() == gimnasio.getPokeKop())
                    || (gimnasio.getGkont2() == gimnasio.getPokeKop())) {
                gimnasio.setTurnue(gimnasio.getTurnue() + 1);
                Pantaila.taldekoHildakuek(taldea1, gimnasio);
                Pantaila.itxaron(2000);
                Pantaila.taldekoHildakuek(taldea2, gimnasio);
                Pantaila.itxaron(2000);
                Pantaila.pokeBukatute(gimnasio);
                bukatu = 9;
            }

            if (gimnasio.getTurnue() == 0) {
                txandaEskuma();
            }

            if (((gimnasio.getGkont1() == gimnasio.getPokeKop())
                    || (gimnasio.getGkont2() == gimnasio.getPokeKop())) && (bukatu != 9)) {
                Pantaila.taldekoHildakuek(taldea1, gimnasio);
                Pantaila.itxaron(2000);
                Pantaila.taldekoHildakuek(taldea2, gimnasio);
                Pantaila.itxaron(2000);
                Pantaila.pokeBukatute(gimnasio);
                bukatu = 9;
            }
        }
    }

    // ------------------------------------------------------------------
    // Txandak
    // ------------------------------------------------------------------
    /**
     * Ezkerreko taldearen txanda ({@code if(gimnasio.turnue==1)} blokea).
     */
    private void txandaEzkerra() {
        Pantaila.garbitu();
        Pantaila.pokePoke(gimnasio, ezker(), eskuin());
        char n = Pantaila.textEz(ezker(), gimnasio);

        if (n == 'w') {
            // atake normala
            eskuin().setOP(eskuin().getOP() - ezker().getPC());
            Pantaila.pokeEzNormala(ezker(), eskuin());
        }

        if (n == 'a') {
            // atake fuertie
            if (gimnasio.getJabegoa() != 1) {
                ezker().setIT(ezker().getPC() / 5);
            } else {
                ezker().setIT((ezker().getPC() * gimnasio.getAurrerapena()) / 5);
            }
            eskuin().setOP(eskuin().getOP() - ezker().getIT());
            Pantaila.pokeEzFuertie(ezker(), eskuin());
        }

        if (n == 'd') {
            // ausazko atakea
            ezker().setIT(Zorizkoa.randomXy(10, 101 + ezker().getRand()));
            eskuin().setOP(eskuin().getOP() - ezker().getIT());
            Pantaila.pokeEzAusazko(ezker(), eskuin());
        }

        if (n == 's') {
            // osatzeko
            if (ezker().getTxanpon() == 0) {
                Pantaila.pokeEzDirubaik(ezker(), eskuin());
            } else {
                ezker().setOP((float) (ezker().getOP() + 0.6));
                ezker().setTxanpon(ezker().getTxanpon() - 1);
                Pantaila.pokeEzOsatu(ezker(), eskuin());
            }
        }

        if (n == '.') {
            // partida gorde eta bukatu
            Pantaila.garbitu();
            Pantaila.taldekoHildakuek(taldea1, gimnasio);
            Pantaila.itxaron(2000);
            Pantaila.taldekoHildakuek(taldea2, gimnasio);

            fitxategiak.gorde(gimnasio, taldea1, taldea2);

            gimnasio.setTurnue(gimnasio.getTurnue() + 1);
            bukatu = 9;
            gimnasio.setKont(gimnasio.getKont() - 1);
        }

        // pokemonaren OP maximoa 3 da
        if (ezker().getOP() > 3) {
            ezker().setOP(3);
        }
        gimnasio.setKont(gimnasio.getKont() + 1);
        Pantaila.itxaron(2000);

        if (eskuin().getOP() <= 0) {
            // eskumako euitimona hilda dago
            taldea1Garaipena();
            Pantaila.garbitu();
            Pantaila.pokeEsHil(ezker());
            gimnasio.setKont(0);
            Pantaila.itxaron(3000);

            if ((gimnasio.getGkont1() != gimnasio.getPokeKop())
                    && (gimnasio.getGkont2() != gimnasio.getPokeKop())) {
                hurrengoEskuinBorrokalaria();
            }
        }
        Pantaila.garbitu();

        if (gimnasio.getKont() == 10) {
            // 10 txandak bete dira: enpatea
            if (ezker().getOP() > eskuin().getOP()) {
                Pantaila.garbitu();
                Pantaila.pokeEmpate(ezker(), eskuin());
                taldea1Garaipena();

                if ((gimnasio.getGkont1() != gimnasio.getPokeKop())
                        && (gimnasio.getGkont2() != gimnasio.getPokeKop())) {
                    hurrengoEskuinBorrokalaria();
                }
            } else {
                taldea2Garaipena();

                if ((gimnasio.getGkont1() != gimnasio.getPokeKop())
                        && (gimnasio.getGkont2() != gimnasio.getPokeKop())) {
                    hurrengoEzkerBorrokalaria();
                }
            }
            gimnasio.setKont(0);
            Pantaila.itxaron(3000);
        }

        gimnasio.setTurnue(gimnasio.getTurnue() - 1);
        jabegoaEgiaztatu();
    }

    /**
     * Eskumako taldearen txanda ({@code if(gimnasio.turnue==0)} blokea).
     */
    private void txandaEskuma() {
        Pantaila.garbitu();

        Pantaila.pokePoke(gimnasio, ezker(), eskuin());

        int n;
        if (gimnasio.getAutoMan() == 0) {
            // beste pertsona baten kontra: atake mota eskatu
            n = Pantaila.textEs(eskuin(), gimnasio);
        } else {
            // ordenagailuaren kontra: atakea ausaz aukeratu
            Pantaila.itxaron(2000);
            Pantaila.garbitu();
            p("\n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n");
            n = Zorizkoa.random01(4, 8);
            if (n == 7) {
                n = 8;
            }
        }

        if (n == 8) {
            // atake normala
            ezker().setOP(ezker().getOP() - eskuin().getPC());
            Pantaila.pokeEsNormala(ezker(), eskuin());
        }

        if (n == 4) {
            // atake fuertie
            if (gimnasio.getJabegoa() != 0) {
                eskuin().setIT(eskuin().getPC() / 5);
            } else {
                eskuin().setIT((eskuin().getPC() * gimnasio.getAurrerapena()) / 5);
            }
            ezker().setOP(ezker().getOP() - eskuin().getIT());
            Pantaila.pokeEsFuertie(ezker(), eskuin());
        }

        if (n == 6) {
            // ausazko atakie
            eskuin().setIT(Zorizkoa.randomXy(10, 101 + eskuin().getRand()));
            ezker().setOP(ezker().getOP() - eskuin().getIT());
            Pantaila.pokeEsAusazko(ezker(), eskuin());
        }

        if (n == 5) {
            // osatzeko
            if (eskuin().getTxanpon() == 0) {
                Pantaila.pokeEsDirubaik(ezker(), eskuin());
            } else {
                eskuin().setOP((float) (eskuin().getOP() + 0.6));
                eskuin().setTxanpon(eskuin().getTxanpon() - 1);
                Pantaila.pokeEsOsatu(ezker(), eskuin());
            }
        }

        if (n == 9) {
            // partida gorde eta bukatu
            Pantaila.garbitu();
            Pantaila.taldekoHildakuek(taldea1, gimnasio);
            Pantaila.itxaron(2000);
            Pantaila.taldekoHildakuek(taldea2, gimnasio);

            fitxategiak.gorde(gimnasio, taldea1, taldea2);

            gimnasio.setTurnue(gimnasio.getTurnue() - 1);
            bukatu = 9;
            gimnasio.setKont(gimnasio.getKont() - 1);
        }

        // OP maximoa 3 da
        if (eskuin().getOP() > 3) {
            eskuin().setOP(3);
        }
        gimnasio.setKont(gimnasio.getKont() + 1);
        Pantaila.itxaron(2000);

        if (ezker().getOP() <= 0) {
            // ezkerreko euitimona hilda dago
            taldea2Garaipena();
            Pantaila.garbitu();
            Pantaila.pokeEzHil(eskuin());
            gimnasio.setKont(0);
            Pantaila.itxaron(3000);

            if ((gimnasio.getGkont1() != gimnasio.getPokeKop())
                    && (gimnasio.getGkont2() != gimnasio.getPokeKop())) {
                hurrengoEzkerBorrokalaria();
            }
        }

        if (gimnasio.getKont() == 10) {
            // 10 txandak bete dira: enpatea
            if (ezker().getOP() > eskuin().getOP()) {
                Pantaila.garbitu();
                Pantaila.pokeEmpate(ezker(), eskuin());
                taldea1Garaipena();

                if ((gimnasio.getGkont1() != gimnasio.getPokeKop())
                        && (gimnasio.getGkont2() != gimnasio.getPokeKop())) {
                    hurrengoEskuinBorrokalaria();
                }
            } else {
                taldea2Garaipena();

                if ((gimnasio.getGkont1() != gimnasio.getPokeKop())
                        && (gimnasio.getGkont2() != gimnasio.getPokeKop())) {
                    hurrengoEzkerBorrokalaria();
                }
            }
            gimnasio.setKont(0);
            Pantaila.itxaron(3000);
        }

        gimnasio.setTurnue(gimnasio.getTurnue() + 1);
        jabegoaEgiaztatu();
    }

    // ------------------------------------------------------------------
    // Laguntzaileak
    // ------------------------------------------------------------------
    /**
     * {@code printf} huts bat erabiltzeko laguntzailea (pantaila garbitzeko
     * lerroak).
     */
    private static void p(String testua) {
        Pantaila.p(testua);
    }

    /**
     * 1. taldeak borroka bat irabazten duenean: aurrerapena doitzen da eta
     * irabazleari hobekuntzak ematen zaizkio. C-ko hiru bloke berdinen batura.
     */
    private void taldea1Garaipena() {
        if (gimnasio.getJabegoa() == 0) {
            gimnasio.setAurrerapena(gimnasio.getAurrerapena() - 1);
        } else {
            gimnasio.setAurrerapena(gimnasio.getAurrerapena() + 1);
        }
        if (gimnasio.getAurrerapena() > 14) {
            gimnasio.setAurrerapena(14);
        }

        Pokemona irabazlea = ezker();
        irabazlea.setPC(Math.min(1.0f, (float) (irabazlea.getPC() + 0.1)));
        irabazlea.setRand(irabazlea.getRand() + 10);
        irabazlea.setOP(Math.min(3.0f, (float) (irabazlea.getOP() + 0.5)));
        irabazlea.setTxanpon(irabazlea.getTxanpon() + 1);
        gimnasio.setGkont1(gimnasio.getGkont1() + 1);
    }

    /**
     * 2. taldeak borroka bat irabazten duenean: aurrerapena doitzen da eta
     * irabazleari hobekuntzak ematen zaizkio.
     */
    private void taldea2Garaipena() {
        if (gimnasio.getJabegoa() == 1) {
            gimnasio.setAurrerapena(gimnasio.getAurrerapena() - 1);
        } else {
            gimnasio.setAurrerapena(gimnasio.getAurrerapena() + 1);
        }
        if (gimnasio.getAurrerapena() > 14) {
            gimnasio.setAurrerapena(14);
        }

        Pokemona irabazlea = eskuin();
        irabazlea.setPC(Math.min(1.0f, (float) (irabazlea.getPC() + 0.1)));
        irabazlea.setRand(irabazlea.getRand() + 10);
        irabazlea.setOP(Math.min(3.0f, (float) (irabazlea.getOP() + 0.5)));
        irabazlea.setTxanpon(irabazlea.getTxanpon() + 1);
        gimnasio.setGkont2(gimnasio.getGkont2() + 1);
    }

    /**
     * Eskumako borrokalari berria aukeratzen du (ordenagailuaren aurka
     * automatikoa).
     */
    private void hurrengoEskuinBorrokalaria() {
        if (gimnasio.getAutoMan() == 0) {
            Pantaila.borrokaraZein();
            gimnasio.setJ(Pantaila.taldekoZein(taldea2, gimnasio) - 1);
        } else {
            gimnasio.setJ(gimnasio.getJ() + 1);
        }
    }

    /**
     * Ezkerreko borrokalari berria aukeratzen du.
     */
    private void hurrengoEzkerBorrokalaria() {
        Pantaila.borrokaraZein();
        gimnasio.setI(Pantaila.taldekoZein(taldea1, gimnasio) - 1);
    }

    /**
     * Gimnasioaren jabegoa aldatzen da aurrerapena 4 baino txikiagoa bada.
     */
    private void jabegoaEgiaztatu() {
        if (gimnasio.getAurrerapena() < 4) {
            if (gimnasio.getJabegoa() == 0) {
                gimnasio.setJabegoa(1);
            } else {
                gimnasio.setJabegoa(0);
            }
            gimnasio.setAurrerapena(Zorizkoa.random01(4, 7));
        }
    }
}
