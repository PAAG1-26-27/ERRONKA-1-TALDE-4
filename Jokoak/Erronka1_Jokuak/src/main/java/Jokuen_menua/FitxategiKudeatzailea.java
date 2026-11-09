package Jokuen_menua;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Partidaren datuak fitxategietan gordetzeko eta handik ateratzeko.
 *
 * <p>C-ko jatorrizko funtzioen baliokidea: {@code gimnasioa_fitxategian_gorde()},
 * {@code taldeak_fitxategian_gorde()}, {@code gimnasioa_fitxategitik_atera()} eta
 * {@code taldeak_fitxategitik_atera()}. Fitxategi-formatua berbera da, baina
 * lerroz lerro irakurtzen da ({@code fscanf} zuriuneak saltzen dituenez,
 * portaera berdina da).</p>
 */
public class FitxategiKudeatzailea {

    /** Gimnasioaren datuen fitxategia. */
    public static final String GIMNASIO_FITXATEGIA = "gimnasioagordetzeko.txt";
    /** 1. taldearen datuen fitxategia. */
    public static final String TALDEA1_FITXATEGIA = "taldea1gordetzeko.txt";
    /** 2. taldearen datuen fitxategia. */
    public static final String TALDEA2_FITXATEGIA = "taldea2gordetzeko.txt";

    /**
     * Aurretik gordetako partidarik ote dagoen esaten du (fitxategia existitzen
     * eta hutsik ez badago). C-ko {@code fopen} + {@code feof} egiaztapenaren
     * baliokidea, baina seguruagoa.
     *
     * @return {@code true} gordetako partida bat badago.
     */
    public boolean partidaDago() {
        File fitxategia = new File(GIMNASIO_FITXATEGIA);
        if (!fitxategia.isFile() || fitxategia.length() == 0L) {
            return false;
        }
        try (BufferedReader irakurlea = ireki(GIMNASIO_FITXATEGIA)) {
            String lehenLerroa = irakurlea.readLine();
            if (lehenLerroa == null || lehenLerroa.trim().isEmpty()) {
                return false;
            }
            Integer.parseInt(lehenLerroa.trim());
            return true;
        } catch (IOException | NumberFormatException e) {
            return false;
        }
    }

    /**
     * Gimnasioaren eta bi taldeen datuak fitxategietan gordetzen ditu.
     *
     * @param gimnasio gimnasioaren egoera.
     * @param taldea1  lehen taldea.
     * @param taldea2  bigarren taldea.
     */
    public void gorde(Gimnasioa gimnasio, List<Pokemona> taldea1, List<Pokemona> taldea2) {
        gordeGimnasioa(gimnasio);
        gordeTaldea(TALDEA1_FITXATEGIA, taldea1, gimnasio.getPokeKop());
        gordeTaldea(TALDEA2_FITXATEGIA, taldea2, gimnasio.getPokeKop());
    }

    /** Gimnasioaren datuak fitxategian gordetzen ditu ({@code gimnasioa_fitxategian_gorde}). */
    public void gordeGimnasioa(Gimnasioa gimnasio) {
        try (PrintWriter idazlea = new PrintWriter(new OutputStreamWriter(
                new FileOutputStream(GIMNASIO_FITXATEGIA), StandardCharsets.UTF_8))) {
            idazlea.printf("%d%n", gimnasio.getI());
            idazlea.printf("%d%n", gimnasio.getJ());
            idazlea.printf("%d%n", gimnasio.getPokeKop());
            idazlea.printf("%d%n", gimnasio.getKont());
            idazlea.printf("%d%n", gimnasio.getTurnue());
            idazlea.printf("%d%n", gimnasio.getJabegoa());
            idazlea.printf("%d%n", gimnasio.getAurrerapena());
            idazlea.printf("%d%n", gimnasio.getGkont1());
            idazlea.printf("%d%n", gimnasio.getGkont2());
            idazlea.printf("%d%n", gimnasio.getT1burua());
            idazlea.printf("%d%n", gimnasio.getT2burua());
            idazlea.printf("%d%n", gimnasio.getAutoMan());
            idazlea.flush();
        } catch (IOException e) {
            System.err.println("Ezin izan da gimnasioaren fitxategia gorde: " + e.getMessage());
        }
    }

    /** Talde baten datuak fitxategian gordetzen ditu ({@code taldeak_fitxategian_gorde}). */
    private void gordeTaldea(String fitxategiIzena, List<Pokemona> taldea, int pokeKop) {
        try (PrintWriter idazlea = new PrintWriter(new OutputStreamWriter(
                new FileOutputStream(fitxategiIzena), StandardCharsets.UTF_8))) {
            for (int i = 0; i < pokeKop && i < taldea.size(); i++) {
                Pokemona pokemona = taldea.get(i);
                idazlea.printf("%s%n", pokemona.getIzena());
                idazlea.printf("%d%n", pokemona.getTxanpon());
                idazlea.printf("%d%n", pokemona.getRand());
                idazlea.printf("%f%n", pokemona.getOP());
                idazlea.printf("%f%n", pokemona.getPC());
                idazlea.printf("%f%n", pokemona.getIT());
            }
            idazlea.flush();
        } catch (IOException e) {
            System.err.println("Ezin izan da taldearen fitxategia gorde: " + e.getMessage());
        }
    }

    /**
     * Gimnasioaren datuak fitxategitik ateratzen ditu
     * ({@code gimnasioa_fitxategitik_atera}) eta emandako objektuan jartzen.
     *
     * @param gimnasio datuak jasoko dituen objektua.
     */
    public void kargatuGimnasioa(Gimnasioa gimnasio) {
        try (BufferedReader irakurlea = ireki(GIMNASIO_FITXATEGIA)) {
            gimnasio.setI(irakurriOsoa(irakurlea));
            gimnasio.setJ(irakurriOsoa(irakurlea));
            gimnasio.setPokeKop(irakurriOsoa(irakurlea));
            gimnasio.setKont(irakurriOsoa(irakurlea));
            gimnasio.setTurnue(irakurriOsoa(irakurlea));
            gimnasio.setJabegoa(irakurriOsoa(irakurlea));
            gimnasio.setAurrerapena(irakurriOsoa(irakurlea));
            gimnasio.setGkont1(irakurriOsoa(irakurlea));
            gimnasio.setGkont2(irakurriOsoa(irakurlea));
            gimnasio.setT1burua(irakurriOsoa(irakurlea));
            gimnasio.setT2burua(irakurriOsoa(irakurlea));
            gimnasio.setAutoMan(irakurriOsoa(irakurlea));
        } catch (IOException e) {
            System.err.println("Ezin izan da gimnasioaren fitxategia irakurri: " + e.getMessage());
        }
    }

    /**
     * Bi taldeen datuak fitxategietatik ateratzen ditu
     * ({@code taldeak_fitxategitik_atera}).
     *
     * @param gimnasio gimnasioaren egoera (poke kopurua ematen du).
     * @param taldea1  lehen taldea beteko duen zerrenda.
     * @param taldea2  bigarren taldea beteko duen zerrenda.
     */
    public void kargatuTaldeak(Gimnasioa gimnasio, List<Pokemona> taldea1, List<Pokemona> taldea2) {
        kargatuTaldea(TALDEA1_FITXATEGIA, taldea1, gimnasio.getPokeKop());
        kargatuTaldea(TALDEA2_FITXATEGIA, taldea2, gimnasio.getPokeKop());
    }

    /** Talde bat fitxategitik ateratzen du. */
    private void kargatuTaldea(String fitxategiIzena, List<Pokemona> taldea, int pokeKop) {
        try (BufferedReader irakurlea = ireki(fitxategiIzena)) {
            for (int i = 0; i < pokeKop; i++) {
                Pokemona pokemona = new Pokemona();
                pokemona.setIzena(irakurriHitza(irakurlea));
                pokemona.setTxanpon(irakurriOsoa(irakurlea));
                pokemona.setRand(irakurriOsoa(irakurlea));
                pokemona.setOP(irakurriHamartarra(irakurlea));
                pokemona.setPC(irakurriHamartarra(irakurlea));
                pokemona.setIT(irakurriHamartarra(irakurlea));
                taldea.add(pokemona);
            }
        } catch (IOException e) {
            System.err.println("Ezin izan da taldearen fitxategia irakurri: " + e.getMessage());
        }
    }

    // ------------------------------------------------------------------
    // Irakurketa-laguntzaileak
    // ------------------------------------------------------------------

    private static BufferedReader ireki(String fitxategiIzena) throws IOException {
        return new BufferedReader(new InputStreamReader(
                new FileInputStream(fitxategiIzena), StandardCharsets.UTF_8));
    }

    /**
     * Lerro bat irakurtzen du; fitxategia amaituta badago, 0 itzultzen du
     * ({@code fscanf}-ek baliorik aldatu ez balu bezala).
     */
    private static String hurrengoLerroa(BufferedReader irakurlea) throws IOException {
        String lerroa = irakurlea.readLine();
        return lerroa == null ? "" : lerroa;
    }

    /** Zenbaki oso bat irakurtzen du ({@code fscanf("%d")}). */
    private static int irakurriOsoa(BufferedReader irakurlea) throws IOException {
        String lerroa = hurrengoLerroa(irakurlea).trim();
        if (lerroa.isEmpty()) {
            return 0;
        }
        try {
            return Integer.parseInt(lerroa.split("\\s+")[0]);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    /** Zenbaki hamartar bat irakurtzen du ({@code fscanf("%f")}). */
    private static float irakurriHamartarra(BufferedReader irakurlea) throws IOException {
        String lerroa = hurrengoLerroa(irakurlea).trim();
        if (lerroa.isEmpty()) {
            return 0.0f;
        }
        try {
            return Float.parseFloat(lerroa.split("\\s+")[0].replace(',', '.'));
        } catch (NumberFormatException e) {
            return 0.0f;
        }
    }

    /**
     * Hitzen bat irakurtzen du ({@code fscanf("%s")}: lehen zuriunera arte).
     */
    private static String irakurriHitza(BufferedReader irakurlea) throws IOException {
        String lerroa = hurrengoLerroa(irakurlea).trim();
        if (lerroa.isEmpty()) {
            return "";
        }
        return lerroa.split("\\s+")[0];
    }
}
