package Jokuen_menua;

import java.util.Random;

/**
 * Jatorrizko C kodeko {@code random_xy()} eta {@code random_0_1()} funtzioen baliokidea.
 *
 * <p><b>Oharra:</b> jatorrizko bertsioak deialdi bakoitzean {@code srand((unsigned) time(NULL))}
 * egiten zuen (segundo bateko bereizmenarekin), eta horrek elkarren ondoz ondoko deiek
 * balio bera itzultzea eragiten zuen. Porteko funtzioek formula bera erabiltzen dute,
 * baina ausazko sortzaile bakarra partekatzen dute ({@link #AUSAZKOA}), balioak benetan
 * ausazkoak izan daitezen. Banaketa bera da: {@code x} barne, {@code y} kanpo.</p>
 */
public final class Zorizkoa {

    /** Joko osoan erabiltzen den ausazko sortzailea. */
    private static final Random AUSAZKOA = new Random(System.currentTimeMillis());

    private Zorizkoa() {
        // utilitate klasea: ezin da instantziatu
    }

    /**
     * Bi balioen arteko ausazko zenbaki hamartarra kalkulatzen du.
     *
     * <p>C-ko jatorrizkoa: {@code random=(float)(x+rand()%(y-x))/100;}</p>
     *
     * @param x beheko muga (barne)
     * @param y goiko muga (kanpo)
     * @return x eta y arteko zenbakia, 100-arekin zatitua.
     */
    public static synchronized float randomXy(int x, int y) {
        int tartea = y - x;
        if (tartea <= 0) {
            return ((float) x) / 100.0f;
        }
        return ((float) (x + AUSAZKOA.nextInt(tartea))) / 100.0f;
    }

    /**
     * Bi balioen arteko zenbaki oso bat ausaz aukeratzen du.
     *
     * <p>C-ko jatorrizkoa: {@code random=(int)(x+rand()%(y-x));}</p>
     *
     * @param x beheko muga (barne)
     * @param y goiko muga (kanpo)
     * @return x eta y arteko zenbaki osoa.
     */
    public static synchronized int random01(int x, int y) {
        int tartea = y - x;
        if (tartea <= 0) {
            return x;
        }
        return x + AUSAZKOA.nextInt(tartea);
    }
}
