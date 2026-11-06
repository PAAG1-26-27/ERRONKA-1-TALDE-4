package Jokuen_menua;

import java.util.ArrayList;
import java.util.List;

/**
 * Jokoaren fluxua: partida berria sortu edo gordetako bat kargatu, taldeak
 * eraiki eta borroka abiarazi.
 *
 * <p>C-ko {@code main()}-ren baliokidea da, baina egoera ArrayList-etan eta
 * {@link Gimnasioa}/{@link Pokemona} klaseetan antolatuta.</p>
 */
public class Jokoa {

    /** Jatorrizko {@code #define N 50}: talde bakoitzeko pokemon kopuru maximoa. */
    public static final int N = 50;

    private final List<Pokemona> taldea1 = new ArrayList<>();
    private final List<Pokemona> taldea2 = new ArrayList<>();
    private final Gimnasioa gimnasio = new Gimnasioa();
    private final FitxategiKudeatzailea fitxategiak = new FitxategiKudeatzailea();

    /** Partida berria edo kargatua jokatzen du; bukatzean berriro jokatzeko galdetzen du. */
    public void jolastu() {
        char jarraitu;

        Pantaila.garbitu();

        do {
            boolean partidaDago = fitxategiak.partidaDago();
            Pantaila.menu();

            if (partidaDago) {
                jarraitu = Pantaila.partidaKargatuGaldera();
            } else {
                jarraitu = 'e';
            }

            if (jarraitu == 'e') {
                partidaBerriaPrestatu();
            } else {
                partidaKargatu();
            }

            new Borroka(taldea1, taldea2, gimnasio, fitxategiak).hasi();

            jarraitu = Pantaila.berriroJolastu();
            Pantaila.garbitu();
        } while (jarraitu == 'b');
    }

    // ------------------------------------------------------------------
    // Partida berria
    // ------------------------------------------------------------------

    /** Partida berri baten datuak eskatzen ditu eta bi taldeak sortzen ditu. */
    private void partidaBerriaPrestatu() {
        // Noren aurka jokatu
        do {
            Pantaila.norenAurka();
            gimnasio.setAutoMan(Teklatua.osoa());
            Pantaila.garbitu();
        } while ((gimnasio.getAutoMan() != 0) && (gimnasio.getAutoMan() != 1));

        // Taldeko pokemon kopurua
        do {
            Pantaila.pokeKopKopurua();
            gimnasio.setPokeKop(Teklatua.osoa());
            Pantaila.garbitu();
        } while ((gimnasio.getPokeKop() < 1) || (gimnasio.getPokeKop() > N));

        taldea1.clear();
        taldea2.clear();

        // Gimnasioaren jabea ausaz aukeratu (0 edo 1)
        gimnasio.setJabegoa(Zorizkoa.random01(0, 2));

        // 1. taldeko pokemonen datuak
        for (int k = 0; k < gimnasio.getPokeKop(); k++) {
            Pokemona pokemona = new Pokemona();
            pokemona.setOP(Zorizkoa.randomXy(120, 201));
            Pantaila.itxaron(500);
            pokemona.setPC(Zorizkoa.randomXy(40, 61));
            pokemona.setTxanpon(3);
            pokemona.setRand(0);
            Pantaila.pokeEz(pokemona);
            taldea1.add(pokemona);
        }

        if (gimnasio.getJabegoa() == 1) {
            // gimnasioaren jabea 1. taldea bada, talde burua aukeratu
            Pantaila.garbitu();
            Pantaila.buruaAukeratu();
            gimnasio.setT1burua(Pantaila.taldekoZein(taldea1, gimnasio) - 1);
        } else {
            gimnasio.setT1burua(-1);
        }

        Pantaila.borrokaraZein();
        gimnasio.setI(Pantaila.taldekoZein(taldea1, gimnasio) - 1);
        Pantaila.garbitu();

        gimnasio.setAurrerapena(Zorizkoa.random01(4, 7));

        // 2. taldeko pokemonen datuak
        for (int k = 0; k < gimnasio.getPokeKop(); k++) {
            Pokemona pokemona = new Pokemona();
            pokemona.setOP(Zorizkoa.randomXy(120, 201));
            Pantaila.itxaron(500);
            pokemona.setPC(Zorizkoa.randomXy(40, 61));
            pokemona.setTxanpon(3);
            pokemona.setRand(0);
            Pantaila.pokeEs(pokemona);
            taldea2.add(pokemona);
        }

        if (gimnasio.getJabegoa() == 0) {
            // gimnasioaren jabea 2. taldea bada, talde burua aukeratu
            Pantaila.garbitu();
            Pantaila.buruaAukeratu();
            gimnasio.setT2burua(Pantaila.taldekoZein(taldea2, gimnasio) - 1);
        } else {
            gimnasio.setT2burua(-1);
        }

        if (gimnasio.getAutoMan() == 0) {
            Pantaila.borrokaraZein();
            gimnasio.setJ(Pantaila.taldekoZein(taldea2, gimnasio) - 1);
        } else {
            gimnasio.setJ(0);
        }

        gimnasio.setTurnue(Zorizkoa.random01(0, 2));
        gimnasio.setKont(0);
        gimnasio.setGkont1(0);
        gimnasio.setGkont2(0);
        Pantaila.garbitu();
    }

    // ------------------------------------------------------------------
    // Gordetako partida
    // ------------------------------------------------------------------

    /** Gordetako partida fitxategietatik kargatzen du. */
    private void partidaKargatu() {
        taldea1.clear();
        taldea2.clear();
        fitxategiak.kargatuGimnasioa(gimnasio);
        fitxategiak.kargatuTaldeak(gimnasio, taldea1, taldea2);
    }
}
