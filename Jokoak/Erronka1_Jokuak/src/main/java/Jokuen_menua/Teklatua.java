package Jokuen_menua;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Teklatutik irakurtzeko laguntzailea.
 *
 * <p>C-ko jatorrizko kodeak {@code scanf("%c")}, {@code scanf("%d")} eta
 * {@code gets()} erabiltzen zituen, tartekatuta {@code fflush(stdin)} deialdiekin.
 * Hemen sarrera lerroz lerro irakurtzen da, horrela portaera bera lortzen da:
 * <ul>
 *   <li>{@code scanf("%c")} + {@code fflush(stdin)} → lerro bateko lehen karaktere
 *       ez-zuria ({@link #karakterea()}).</li>
 *   <li>{@code scanf("%d")} → lerroko lehen hitza zenbaki oso gisa ({@link #osoa()}).
 *       Lerro osoa kontsumitzen da, C-ko fflush-aren antzeko efektua lortuz.</li>
 *   <li>{@code gets()} → lerro osoa, zuriuneak barne ({@link #lerroa()}).</li>
 * </ul></p>
 */
public final class Teklatua {

    /** Sarrera agortu denean jaurtitzen den salbuespena (EOF). */
    public static class SarreraAmaiera extends RuntimeException {
        private static final long serialVersionUID = 1L;

        public SarreraAmaiera() {
            super("Sarrera amaitu da (EOF)");
        }
    }

    private static final BufferedReader SARRERA =
            new BufferedReader(new InputStreamReader(System.in));

    private Teklatua() {
        // utilitate klasea: ezin da instantziatu
    }

    /**
     * Lerro oso bat irakurtzen du ({@code gets()} baliokidea).
     *
     * @return irakurritako lerroa (amaierako \r kenduta).
     */
    public static String lerroa() {
        try {
            String lerroa = SARRERA.readLine();
            if (lerroa == null) {
                throw new SarreraAmaiera();
            }
            if (lerroa.endsWith("\r")) {
                lerroa = lerroa.substring(0, lerroa.length() - 1);
            }
            return lerroa;
        } catch (IOException e) {
            throw new SarreraAmaiera();
        }
    }

    /**
     * Hurrengo karaktere ez-zuria irakurtzen du ({@code scanf("%c")} +
     * {@code fflush(stdin)} baliokidea). Lerro huts bat jasotzen bada, lerro-jauzia
     * itzultzen da ({@code '\n'}), C-k egingo lukeen bezala.
     *
     * @return irakurritako karakterea.
     */
    public static char karakterea() {
        String lerroa = lerroa().trim();
        if (lerroa.isEmpty()) {
            return '\n';
        }
        return lerroa.charAt(0);
    }

    /**
     * Zenbaki oso bat irakurtzen du ({@code scanf("%d")} baliokidea). Lerroko
     * lehen hitza hartzen du; ezin bada zenbaki gisa interpretatu,
     * {@link Integer#MIN_VALUE} itzultzen da, deitzailearen begiztak berriro
     * galdetu dezan.
     *
     * @return irakurritako zenbaki osoa edo {@link Integer#MIN_VALUE}.
     */
    public static int osoa() {
        String lerroa = lerroa().trim();
        if (lerroa.isEmpty()) {
            return Integer.MIN_VALUE;
        }
        String lehenHitza = lerroa.split("\\s+")[0];
        try {
            return Integer.parseInt(lehenHitza);
        } catch (NumberFormatException e) {
            return Integer.MIN_VALUE;
        }
    }
}
