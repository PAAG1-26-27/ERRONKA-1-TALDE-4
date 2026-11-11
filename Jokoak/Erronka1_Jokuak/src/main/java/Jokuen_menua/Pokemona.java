package com.mycompany.poxamon_fv;
import java.util.Scanner;
/**
 *
 * @author Arrese.Nahia
 */
public class Pokemona 
{
    Scanner irakurri = new Scanner(System.in);
    
    private String Izena;
    private int Txanpon;
    private int Rand;
    private float OP;
    private float PC;
    private float IT;
    
    
    
    public Pokemona (String a, int b, int c, float d, float e, float f)
    {
        Izena=a;
        Txanpon=b;
        Rand=c;
        OP=d;
        PC=e;
        IT=f;
    }
    public Pokemona ()
    {
        Izena="AAA";
        Txanpon=3;
        Rand=0;
        OP=3.0f;
        PC=0.5f;
        IT=0.0f;
    }
    
    public void setIzena(String a) 
    {             
        Izena=a;
    }
    public void setIzena() 
    {       
        String a;
        System.out.print("\nSartu pokemonaren izena: ");
        a=irakurri.nextLine();       
        Izena=a;
    }
    
    public void setTxanpon(int a) 
    {             
        Txanpon=a;
    }
    public void setTxanpon() 
    {       
        String sarrera;
        System.out.print("\nSartu txanpon kopurua: ");
        sarrera=irakurri.nextLine();
        try 
        {
            Txanpon=Integer.parseInt(sarrera.trim());
        } catch (NumberFormatException e) 
        {
            System.out.println("Zenbakia formatu desegokian dago.");
        }
    }
    
    public void setRand(int a) 
    {             
        Rand=a;
    }
    public void setRand() 
    {       
        String sarrera;
        System.out.print("\nSartu Rand balioa: ");
        sarrera=irakurri.nextLine();
        try 
        {
            Rand=Integer.parseInt(sarrera.trim());
        } catch (NumberFormatException e) 
        {
            System.out.println("Zenbakia formatu desegokian dago.");
        }
    }
    
    public void setOP(float a) 
    {             
        OP=a;
    }
    public void setOP() 
    {       
        String sarrera;
        System.out.print("\nSartu osasun puntuak (OP): ");
        sarrera=irakurri.nextLine();
        try 
        {
            OP=Float.parseFloat(sarrera.trim().replace(',', '.'));
        } catch (NumberFormatException e) 
        {
            System.out.println("Zenbakia formatu desegokian dago.");
        }
    }
    
    public void setPC(float a) 
    {             
        PC=a;
    }
    public void setPC() 
    {       
        String sarrera;
        System.out.print("\nSartu eraso indarra (PC): ");
        sarrera=irakurri.nextLine();
        try 
        {
            PC=Float.parseFloat(sarrera.trim().replace(',', '.'));
        } catch (NumberFormatException e) 
        {
            System.out.println("Zenbakia formatu desegokian dago.");
        }
    }
    
    public void setIT(float a) 
    {             
        IT=a;
    }
    public void setIT() 
    {       
        String sarrera;
        System.out.print("\nSartu azken erasoaren indarra (IT): ");
        sarrera=irakurri.nextLine();
        try 
        {
            IT=Float.parseFloat(sarrera.trim().replace(',', '.'));
        } catch (NumberFormatException e) 
        {
            System.out.println("Zenbakia formatu desegokian dago.");
        }
    }
    
    // Balioa itzultzen duten get-ak (jokoak behar ditu)
    public String getIzena()
    {
        return Izena;
    }
    public int getTxanpon()
    {
        return Txanpon;
    }
    public int getRand()
    {
        return Rand;
    }
    public float getOP()
    {
        return OP;
    }
    public float getPC()
    {
        return PC;
    }
    public float getIT()
    {
        return IT;
    }
    
    // Balioak pantailan erakusten dituzten metodoak
    public void bistaratuIzena()
    {
        System.out.println("Pokemonaren Izena hau da: "+Izena);
    }
    public void bistaratuTxanpon()
    {
        System.out.println("Pokemonaren Txanpon kopurua hau da: "+Txanpon);
    }
    public void bistaratuRand()
    {
        System.out.println("Pokemonaren Rand balioa hau da: "+Rand);
    }
    public void bistaratuOP()
    {
        System.out.println("Pokemonaren OP hau da: "+OP);
    }
    public void bistaratuPC()
    {
        System.out.println("Pokemonaren PC hau da: "+PC);
    }
    public void bistaratuIT()
    {
        System.out.println("Pokemonaren IT hau da: "+IT);
    }
    
    public void getALL()
    {
        bistaratuIzena();
        bistaratuTxanpon();
        bistaratuRand();
        bistaratuOP();
        bistaratuPC();
        bistaratuIT();
    }
}
