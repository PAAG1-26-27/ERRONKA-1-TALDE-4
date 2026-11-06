package Jokuen_menua;

/**
 *
 * @author Arrese.Nahia
 */
public class Gimnasioa 
{
    private int i, j, pokeKop, kont, turnue, jabegoa, aurrerapena;
    private int Gkont1, Gkont2, t1burua, t2burua, autoMan;

    public Gimnasioa() 
    {
        i = 0; j = 0; pokeKop = 0; kont = 0; turnue = 0;
        jabegoa = 0; aurrerapena = 4; Gkont1 = 0; Gkont2 = 0;
        t1burua = -1; t2burua = -1; autoMan = 0;
    }

    public Gimnasioa(int i, int j, int pokeKop, int kont, int turnue, int jabegoa, int aurrerapena, int Gkont1, int Gkont2, int t1burua, int t2burua, int autoMan) 
    {
        this.i = i; this.j = j; this.pokeKop = pokeKop; this.kont = kont;
        this.turnue = turnue; this.jabegoa = jabegoa; this.aurrerapena = aurrerapena;
        this.Gkont1 = Gkont1; this.Gkont2 = Gkont2; this.t1burua = t1burua;
        this.t2burua = t2burua; this.autoMan = autoMan;
    }

    public int getI() { return i; } public void setI(int i) { this.i = i; }
    public int getJ() { return j; } public void setJ(int j) { this.j = j; }
    public int getPokeKop() { return pokeKop; } public void setPokeKop(int pokeKop) { this.pokeKop = pokeKop; }
    public int getKont() { return kont; } public void setKont(int kont) { this.kont = kont; }
    public int getTurnue() { return turnue; } public void setTurnue(int turnue) { this.turnue = turnue; }
    public int getJabegoa() { return jabegoa; } public void setJabegoa(int jabegoa) { this.jabegoa = jabegoa; }
    public int getAurrerapena() { return aurrerapena; } public void setAurrerapena(int aurrerapena) { this.aurrerapena = aurrerapena; }
    public int getGkont1() { return Gkont1; } public void setGkont1(int Gkont1) { this.Gkont1 = Gkont1; }
    public int getGkont2() { return Gkont2; } public void setGkont2(int Gkont2) { this.Gkont2 = Gkont2; }
    public int getT1burua() { return t1burua; } public void setT1burua(int t1burua) { this.t1burua = t1burua; }
    public int getT2burua() { return t2burua; } public void setT2burua(int t2burua) { this.t2burua = t2burua; }
    public int getAutoMan() { return autoMan; } public void setAutoMan(int autoMan) { this.autoMan = autoMan; }
}
