package Jokuen_menua;

/**
 *
 * @author Arrese.Nahia
 */
public class Pokemona 
{
    private String izena;
    private int txanpon;
    private int rand;
    private float OP;
    private float PC;
    private float IT;

    public Pokemona() 
    {
        this.izena = "AAA";
        this.txanpon = 3;
        this.rand = 0;
        this.OP = 3.0f;
        this.PC = 0.5f;
        this.IT = 0.0f;
    }

    public Pokemona(String izena, int txanpon, int rand, float OP, float PC, float IT) 
    {
        this.izena = izena;
        this.txanpon = txanpon;
        this.rand = rand;
        this.OP = OP;
        this.PC = PC;
        this.IT = IT;
    }

    public String getIzena() { return izena; }
    public void setIzena(String izena) { this.izena = izena; }
    public int getTxanpon() { return txanpon; }
    public void setTxanpon(int txanpon) { this.txanpon = txanpon; }
    public int getRand() { return rand; }
    public void setRand(int rand) { this.rand = rand; }
    public float getOP() { return OP; }
    public void setOP(float OP) { this.OP = OP; }
    public float getPC() { return PC; }
    public void setPC(float PC) { this.PC = PC; }
    public float getIT() { return IT; }
    public void setIT(float IT) { this.IT = IT; }

    @Override
    public String toString() {
        return izena + " | OP: " + OP + " | PC: " + PC + " | Txanponak: " + txanpon;
    }
}
