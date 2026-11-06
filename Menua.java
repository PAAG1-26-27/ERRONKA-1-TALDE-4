/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Erronka1;

import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
/**
 *
 * @author Arrese.Nahia & Agudo.Adrian & Estevez.JonAnder
 */

//Bazkideak monitoreak langikeak , ekintzak
public class Menua 
{
    Scanner irakurri = new Scanner(System.in);
    
    ArrayList<Partaide> partaideak = new ArrayList<>();
    ArrayList<Administratibo> administratiboak = new ArrayList<>();
    ArrayList<Monitorea> monitoreak = new ArrayList<>();
    ArrayList<Arrunta> arruntak = new ArrayList<>();
    ArrayList<Pernoktazio> pernoktaziozkoak = new ArrayList<>();
    
    DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    
    public String SartuBerba(Scanner irakurri, String mezua) 
    {
        String berba=null;
        boolean arazorik;

        do {//Zenbaki oso bat sartu arte errepikatu du eskaria
            arazorik = false;
            try 
            {
                System.out.print(mezua);
                berba = irakurri.nextLine().trim();
            } 
            catch (Exception error) 
            {
                System.out.println("Sartutako berbak ez dauka formatu egokia.\n");
                irakurri.nextLine(); // Sarrera desegokia garbitu
                arazorik = true;
            }
        } while (arazorik);
        
        return berba;
    }
    
    //Zenbaki osoak eskaneatzeko metodoa
    public int SartuZenbakia(Scanner irakurri, String mezua) 
    {
        int zenbakia = 0;
        boolean arazorik;

        do {//Zenbaki oso bat sartu arte errepikatu du eskaria
            arazorik = false;
            try 
            {
                System.out.print(mezua);
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
    
    //Data sartzeko metodoa
    public LocalDate SartuData(Scanner irakurri, String mezua) 
    {
        String sarrera;
        LocalDate fecha=null;
        boolean arazorik;

        do {//Zenbaki egoki bat sartu arte errepikatu du eskaria
            arazorik = false;           
            try 
            {
                System.out.print(mezua);
                sarrera = irakurri.nextLine().trim();
                fecha = LocalDate.parse(sarrera, formato);
            } 
            catch (DateTimeParseException error) 
            {
                System.out.println("Datarentzako formatu desegokia erabili duzu.");
                arazorik = true;
            }
        } while (arazorik);
        return fecha;
    }

    public void pantallaGarbitu()
    {
        //Pantaila garbitzen du
        for (int i = 0; i < 30; i++)
        {
            System.out.println();
        }//for
    }//pantallaGarbitu
    
    public void PartaideaGehitu()
    {
        String erantzuna;
        
        // Pertsonaren datuak
        String NAN;
        String Izena;
        String Abizena;
        LocalDate JaiotzeData;
        
        //Partaide datuak
        String Jaiot_herria;
        Boolean Erroldatua;
        Boolean Bazkidea;

        // Pertsonaren datuak eskatu
        NAN = SartuBerba(irakurri, "Sartu zure NAN-a: ");
        Izena = SartuBerba(irakurri, "Sartu zure Izena: ");
        Abizena = SartuBerba(irakurri, "Sartu zure Abizena: ");
        JaiotzeData = SartuData(irakurri, "Sartu Jaiotze data (uuuu/HH/ee) formatuan: ");
        
        //Partaide datuak eskatu
        Jaiot_herria = SartuBerba(irakurri, "Sartu zure jaiot-herria: ");
        do
        {
            erantzuna = SartuBerba(irakurri,"Erroldatua dago? (bai/ez): ");

            erantzuna = erantzuna.toLowerCase();

            if (!erantzuna.equals("bai") && !erantzuna.equals("ez"))
            {
                System.out.println("Sarturiko balioa ez da zuzena");
            }

        } while (!erantzuna.equals("bai") && !erantzuna.equals("ez"));
        
        Erroldatua = erantzuna.equals("bai");
        
        do
        {
            erantzuna = SartuBerba(irakurri,"Bazkidea da? (bai/ez): ");

            erantzuna = erantzuna.toLowerCase();

            if (!erantzuna.equals("bai") && !erantzuna.equals("ez"))
            {
                System.out.println("Sarturiko balioa ez da zuzena");
            }

        } while (!erantzuna.equals("bai") && !erantzuna.equals("ez"));
        
        Bazkidea = erantzuna.equals("bai");

        
        
        // Partaide objektua sortu
        Partaide p = new Partaide(NAN, Izena, Abizena, JaiotzeData, Jaiot_herria, Erroldatua, Bazkidea);

        // Objektua zerrendan gorde
        partaideak.add(p);

        System.out.println("Partaidea behar bezala sortu da.");
    }
    
    public void AdministrariaGehitu()
    {
        //Administrari datuak
        String Postua;

        //Langile datuak
        String LangileID;
        int Soldata;
        
        // Pertsonaren datuak
        String NAN;
        String Izena;
        String Abizena;
        LocalDate JaiotzeData;

        // Pertsonaren datuak eskatu
        NAN = SartuBerba(irakurri, "Sartu zure NAN-a: ");
        Izena = SartuBerba(irakurri, "Sartu zure Izena: ");
        Abizena = SartuBerba(irakurri, "Sartu zure Abizena: ");
        JaiotzeData = SartuData(irakurri, "Sartu Jaiotze data (uuuu/HH/ee) formatuan: ");

        LangileID = SartuBerba(irakurri, "Sartu Langile kodea: ");
        Soldata = SartuZenbakia(irakurri, "Sartu soldata: ");
        
        Postua = SartuBerba(irakurri, "Sartu lanpostua: ");

        Administratibo a = new Administratibo(NAN, Izena, Abizena, JaiotzeData, LangileID, Soldata, Postua);

        administratiboak.add(a);

        System.out.println("Langilea behar bezala sortu da.");
    }
    
    public void MonitoreaGehitu()
    {
        //Monitore
        String TituloID;
        
        //Langile datuak
        String LangileID;
        int Soldata;
        
        // Pertsonaren datuak
        String NAN;
        String Izena;
        String Abizena;
        LocalDate JaiotzeData;

        // Pertsonaren datuak eskatu
        NAN = SartuBerba(irakurri, "Sartu zure NAN-a: ");
        Izena = SartuBerba(irakurri, "Sartu zure Izena: ");
        Abizena = SartuBerba(irakurri, "Sartu zure Abizena: ");
        JaiotzeData = SartuData(irakurri, "Sartu Jaiotze data (uuuu/HH/ee) formatuan: ");

        //Langile datuak eskatu
        LangileID = SartuBerba(irakurri, "Sartu Langile kodea: ");
        Soldata = SartuZenbakia(irakurri, "Sartu soldata: ");

        //monitore datuak eskatu
        TituloID = SartuBerba(irakurri, "Sartu Monitore kodea: ");

        Monitorea m = new Monitorea(NAN, Izena, Abizena, JaiotzeData, LangileID, Soldata, TituloID);

        monitoreak.add(m);

        System.out.println("Monitorea behar bezala sortu da.");
    }
    
    public void EkintzaArruntaGehitu()
    {
        //Arrunt datuak
        int Iraupena;
        
        //Ekintza dtuak
        String ID;
        String EIzena;
        LocalDate HasieraData;

        ID = SartuBerba(irakurri, "Sartu ekintzaren ID-a: ");
        EIzena = SartuBerba(irakurri, "Sartu ekintzaren izena: ");
        HasieraData = SartuData(irakurri, "Sartu ekintzaren hasiera data (uuuu/HH/ee) formatuan: ");
        Iraupena = SartuZenbakia(irakurri, "Sartu ekintzaren iraupena: ");

        Arrunta ea = new Arrunta(ID, EIzena, HasieraData, Iraupena);

        arruntak.add(ea);

        System.out.println("Ekintza behar bezala sortu da.");
    }
    
    public void PernoktaziozkoEkintzaGehitu()
    {
        //Arrunt datuak
        String Lolekua;
        LocalDate BukaeraData;
        
        //Ekintza dtuak
        String ID;
        String EIzena;
        LocalDate HasieraData;

        ID = SartuBerba(irakurri, "Sartu ekintzaren ID-a: ");
        EIzena = SartuBerba(irakurri, "Sartu ekintzaren izena: ");
        HasieraData = SartuData(irakurri, "Sartu ekintzaren hasiera data (uuuu/HH/ee) formatuan: ");
        Lolekua = SartuBerba(irakurri, "Sartu lo lekuaren izena: ");
        BukaeraData = SartuData(irakurri, "Sartu ekintzaren bukaera data (uuuu/HH/ee) formatuan: ");

        Pernoktazio pe = new Pernoktazio(ID, EIzena, HasieraData, Lolekua, BukaeraData);

        pernoktaziozkoak.add(pe);

        System.out.println("Ekintza behar bezala sortu da.");
    }
    
    public void PartaideakIkusi()
    {
        if (partaideak.isEmpty())
        {
            System.out.println("Ez dago partaiderik.");
        }
        else
        {
            for (int i = 0; i < partaideak.size(); i++)
            {
                System.out.println("\n--- " + (i + 1) + ". PARTAIDEA ---");

                partaideak.get(i).getALL();
            }
        }
    }
    
    public void AdministratiboakIkusi()
    {
        if (administratiboak.isEmpty())
        {
            System.out.println("Ez dago administratiborik.");
        }
        else
        {
            for (int i = 0; i < administratiboak.size(); i++)
            {
                System.out.println("\n--- " + (i + 1) + ". ADMINISTRATIBOA ---");

                administratiboak.get(i).getALL();
            }
        }
    }
    
    public void MonitoreakIkusi()
    {
        if (monitoreak.isEmpty())
        {
            System.out.println("Ez dago monitorerik.");
        }
        else
        {
            for (int i = 0; i < monitoreak.size(); i++)
            {
                System.out.println("\n--- " + (i + 1) + ". MONITOREA ---");

                monitoreak.get(i).getALL();
            }
        }
    }
    
    public void LangileakIkusi()
    {
        System.out.println("\n========== LANGILEAK ==========");

        System.out.println("\n--- ADMINISTRATIBOAK ---");
        AdministratiboakIkusi();

        System.out.println("\n--- MONITOREAK ---");
        MonitoreakIkusi();
    }
    
    public void EkintzaArruntakIkusi()
    {
        if (arruntak.isEmpty())
        {
            System.out.println("Ez dago ekintza arruntarik.");
        }
        else
        {
            for (int i = 0; i < arruntak.size(); i++)
            {
                System.out.println("\n--- " + (i + 1) + ". EKINTZA ARRUNTA ---");

                arruntak.get(i).getALL();
            }
        }
    }
    
    public void PernoktaziozkoEkintzakIkusi()
    {
        if (pernoktaziozkoak.isEmpty())
        {
            System.out.println("Ez dago pernoktaziozko ekintzarik.");
        }
        else
        {
            for (int i = 0; i < pernoktaziozkoak.size(); i++)
            {
                System.out.println("\n--- " + (i + 1) + ". PERNOKTAZIOZKO EKINTZA ---");

                pernoktaziozkoak.get(i).getALL();
            }
        }
    }
    
    public void EkintzakIkusi()
    {
        System.out.println("\n========== EKINTZAK ==========");

        System.out.println("\n--- EKINTZA ARRUNTAK ---");
        EkintzaArruntakIkusi();

        System.out.println("\n--- PERNOKTAZIOZKO EKINTZAK ---");
        PernoktaziozkoEkintzakIkusi();
    }
    
    public int Aukeratu(int kopurua)
    {
        int aukera;

        if (kopurua == 0)
        {
            System.out.println("Ez dago daturik aukeratzeko.");
            return -1;
        }
        do
        {
            aukera = SartuZenbakia(irakurri,"Aukeratu pertsona bat (1-" + kopurua + "): ");

            if (aukera < 1 || aukera > kopurua)
            {
                System.out.println("Aukera ez da zuzena.");
            }

        } while (aukera < 1 || aukera > kopurua);

        // ArrayList-aren posizioak 0tik hasten dira.
        return aukera-1;
    }
    
    // Pertsona bat ezabatzeko metodoa
    public void PertsonaEzabatu(String PertsMota)
    {
        int posizioa;

        if (PertsMota.equalsIgnoreCase("Partaideak"))
        {
            if (partaideak.isEmpty())
            {
                System.out.println("Ez dago partaiderik ezabatzeko.");
                return;
            }

            PartaideakIkusi();

            posizioa = Aukeratu(partaideak.size());

            if (posizioa != -1)
            {
                partaideak.remove(posizioa);
                System.out.println("Partaidea behar bezala ezabatu da.");
            }
        }
        else if (PertsMota.equalsIgnoreCase("Administratiboak"))
        {
            if (administratiboak.isEmpty())
            {
                System.out.println("Ez dago administratiborik ezabatzeko.");
                return;
            }

            AdministratiboakIkusi();

            posizioa = Aukeratu(administratiboak.size());

            if (posizioa != -1)
            {
                administratiboak.remove(posizioa);
                System.out.println("Administratiboa behar bezala ezabatu da.");
            }
        }
        else if (PertsMota.equalsIgnoreCase("Monitoreak"))
        {
            if (monitoreak.isEmpty())
            {
                System.out.println("Ez dago monitorerik ezabatzeko.");
                return;
            }

            MonitoreakIkusi();

            posizioa = Aukeratu(monitoreak.size());

            if (posizioa != -1)
            {
                monitoreak.remove(posizioa);
                System.out.println("Monitorea behar bezala ezabatu da.");
            }
        }
    }
    
    // Pertsona baten datuak aldatzeko metodoa
    public void PertsonaAldatu(String PertsMota)
    {
        int posizioa;

        if (PertsMota.equalsIgnoreCase("Partaideak"))
        {
            if (partaideak.isEmpty())
            {
                System.out.println("Ez dago partaiderik aldatzeko.");
                return;
            }

            PartaideakIkusi();

            posizioa = Aukeratu(partaideak.size());

            if (posizioa != -1)
            {
                Partaide p = partaideak.get(posizioa);

                System.out.println("Sartu partaidearen datu berriak:");

                p.setNAN();
                p.setIzena();
                p.setAbizena();
                p.setJaiotzeData();
                p.setJaiot_herria();
                p.setErroldatua();
                p.setBazkide();

                System.out.println("Partaidearen datuak aldatu dira.");
            }
        }
        else if (PertsMota.equalsIgnoreCase("Administratiboak"))
        {
            if (administratiboak.isEmpty())
            {
                System.out.println("Ez dago administratiborik aldatzeko.");
                return;
            }

            AdministratiboakIkusi();

            posizioa = Aukeratu(administratiboak.size());

            if (posizioa != -1)
            {
                Administratibo a = administratiboak.get(posizioa);

                System.out.println("Sartu administratiboaren datu berriak:");

                a.setNAN();
                a.setIzena();
                a.setAbizena();
                a.setJaiotzeData();
                a.setLangileID();
                a.setSoldata();
                a.setPostua();

                System.out.println("Administratiboaren datuak aldatu dira.");
            }
        }
        else if (PertsMota.equalsIgnoreCase("Monitoreak"))
        {
            if (monitoreak.isEmpty())
            {
                System.out.println("Ez dago monitorerik aldatzeko.");
                return;
            }

            MonitoreakIkusi();

            posizioa = Aukeratu(monitoreak.size());

            if (posizioa != -1)
            {
                Monitorea m = monitoreak.get(posizioa);

                System.out.println("Sartu monitorearen datu berriak:");

                m.setNAN();
                m.setIzena();
                m.setAbizena();
                m.setJaiotzeData();
                m.setLangileID();
                m.setSoldata();
                m.setTituloID();

                System.out.println("Monitorearen datuak aldatu dira.");
            }
        }
    }
    
    // Ekintza bat ezabatzeko metodoa
    public void EkintzaEzabatu(String EkintzaMota)
    {
        int posizioa;

        if (EkintzaMota.equalsIgnoreCase("Arruntak"))
        {
            if (arruntak.isEmpty())
            {
                System.out.println("Ez dago ekintza arruntarik ezabatzeko.");
                return;
            }

            EkintzaArruntakIkusi();

            posizioa = Aukeratu(arruntak.size());

            if (posizioa != -1)
            {
                arruntak.remove(posizioa);
                System.out.println("Ekintza arrunta behar bezala ezabatu da.");
            }
        }
        else if (EkintzaMota.equalsIgnoreCase("Pernoktaziozkoak"))
        {
            if (pernoktaziozkoak.isEmpty())
            {
                System.out.println("Ez dago pernoktaziozko ekintzarik ezabatzeko.");
                return;
            }

            PernoktaziozkoEkintzakIkusi();

            posizioa = Aukeratu(pernoktaziozkoak.size());

            if (posizioa != -1)
            {
                pernoktaziozkoak.remove(posizioa);
                System.out.println("Pernoktaziozko ekintza behar bezala ezabatu da.");
            }
        }
    }

    // Ekintza baten datuak aldatzeko metodoa
    public void EkintzaAldatu(String EkintzaMota)
    {
        int posizioa;

        if (EkintzaMota.equalsIgnoreCase("Arruntak"))
        {
            if (arruntak.isEmpty())
            {
                System.out.println("Ez dago ekintza arruntarik aldatzeko.");
                return;
            }

            EkintzaArruntakIkusi();

            posizioa = Aukeratu(arruntak.size());

            if (posizioa != -1)
            {
                Arrunta ea = arruntak.get(posizioa);

                System.out.println("Sartu ekintza arruntaren datu berriak:");

                ea.setID(SartuBerba(irakurri, "Sartu ekintzaren ID berria: "));
                ea.setIzena(SartuBerba(irakurri, "Sartu ekintzaren izen berria: "));
                ea.setHasieraData(SartuData(irakurri,"Sartu ekintzaren hasiera data (uuuu/HH/ee) formatuan: "));
                ea.setIraupena(SartuZenbakia(irakurri,"Sartu ekintzaren iraupen berria: "));

                System.out.println("Ekintza arruntaren datuak behar bezala aldatu dira.");
            }
        }
        else if (EkintzaMota.equalsIgnoreCase("Pernoktaziozkoak"))
        {
            if (pernoktaziozkoak.isEmpty())
            {
                System.out.println("Ez dago pernoktaziozko ekintzarik aldatzeko.");
                return;
            }

            PernoktaziozkoEkintzakIkusi();

            posizioa = Aukeratu(pernoktaziozkoak.size());

            if (posizioa != -1)
            {
                Pernoktazio pe = pernoktaziozkoak.get(posizioa);

                System.out.println("Sartu pernoktaziozko ekintzaren datu berriak:");
                
                pe.setID(SartuBerba(irakurri,"Sartu ekintzaren ID berria: "));
                pe.setIzena(SartuBerba(irakurri,"Sartu ekintzaren izen berria: "));
                pe.setHasieraData(SartuData(irakurri,"Sartu ekintzaren hasiera data (uuuu/HH/ee) formatuan: "));
                pe.setLolekua(SartuBerba(irakurri,"Sartu lo lekuaren izen berria: "));
                pe.setBukaeraData(SartuData(irakurri,"Sartu ekintzaren bukaera data (uuuu/HH/ee) formatuan: "));
                
                System.out.println("Pernoktaziozko ekintzaren datuak behar bezala aldatu dira.");
            }
        }
    }
    
    public void menuPertsonak(String PertsMota)
    {
        pantallaGarbitu();

        String menuPertsona;

        PertsMota = PertsMota.toUpperCase();

        do
        {
            System.out.println();
            System.out.println("___" + PertsMota + "___");
            System.out.println("_________");
            System.out.println("Ikusi");
            System.out.println("Sartu");
            System.out.println("Kendu");
            System.out.println("Zuzendu");
            System.out.println("_________");
            System.out.println("\u001B[93mAtzera\u001B[0m");
            System.out.println();

            menuPertsona = SartuBerba(irakurri, "Zein kudeatu nahi duzu?");

            menuPertsona = menuPertsona.toLowerCase();

            switch (menuPertsona)
            {
                case "ikusi":
                {
                    pantallaGarbitu();

                    if (PertsMota.equalsIgnoreCase("Partaideak"))
                    {
                        PartaideakIkusi();
                    }
                    else if (PertsMota.equalsIgnoreCase("Administratiboak"))
                    {
                        AdministratiboakIkusi();
                    }
                    else if (PertsMota.equalsIgnoreCase("Monitoreak"))
                    {
                        MonitoreakIkusi();
                    }

                } break;

                case "sartu":
                {
                    pantallaGarbitu();

                    if (PertsMota.equalsIgnoreCase("Partaideak"))
                    {
                        PartaideaGehitu();
                    }
                    else if (PertsMota.equalsIgnoreCase("Administratiboak"))
                    {
                        AdministrariaGehitu();
                    }
                    else if (PertsMota.equalsIgnoreCase("Monitoreak"))
                    {
                        MonitoreaGehitu();
                    }

                } break;

                case "kendu":
                {
                    pantallaGarbitu();
                    PertsonaEzabatu(PertsMota);

                } break;

                case "zuzendu":
                {
                    pantallaGarbitu();
                    PertsonaAldatu(PertsMota);

                } break;

                case "atzera":
                {
                    pantallaGarbitu();
                    System.out.println(PertsMota + "-tik ATZERA.");

                } break;

                default:
                {
                    pantallaGarbitu();
                    System.out.println("Ez duzu aukera zuzena sartu.");
                }
            }

        } while (!menuPertsona.equals("atzera"));
    }
    
    public void menuLangileak()
    {
        pantallaGarbitu();
        String menuLangile;

        do
        {
            System.out.println();
            System.out.println("___LANGILEAK___");
            System.out.println("_________");
            System.out.println("Administratiboak");
            System.out.println("Monitoreak");
            System.out.println("Langile guztiak ikusi");
            System.out.println("_________");
            System.out.println("\u001B[93mAtzera\u001B[0m");
            System.out.println();

            menuLangile = SartuBerba(irakurri,
                    "Zein kudeatu nahi duzu? ");

            menuLangile = menuLangile.toLowerCase();

            switch (menuLangile)
            {
                case "administratiboak":
                {
                    pantallaGarbitu();
                    menuPertsonak("Administratiboak");

                } break;

                case "monitoreak":
                {
                    pantallaGarbitu();
                    menuPertsonak("Monitoreak");

                } break;

                case "langile guztiak ikusi":
                {
                    pantallaGarbitu();
                    LangileakIkusi();

                } break;

                case "atzera":
                {
                    pantallaGarbitu();
                    System.out.println("LANGILEAK MENUTIK ATZERA.");

                } break;

                default:
                {
                    pantallaGarbitu();
                    System.out.println("Ez duzu aukera zuzena sartu.");
                }
            }

        } while (!menuLangile.equals("atzera"));
    }
    
    public void menuEkintzaKudeatu(String EkintzaMota)
    {
        pantallaGarbitu();

        String menuEkintza;

        do
        {
            System.out.println();
            System.out.println("___" + EkintzaMota.toUpperCase() + "___");
            System.out.println("_________");
            System.out.println("Ikusi");
            System.out.println("Sartu");
            System.out.println("Kendu");
            System.out.println("Zuzendu");
            System.out.println("_________");
            System.out.println("\u001B[93mAtzera\u001B[0m");
            System.out.println();

            menuEkintza = SartuBerba(irakurri, "Zein kudeatu nahi duzu? ");

            menuEkintza = menuEkintza.toLowerCase();

            switch (menuEkintza)
            {
                case "ikusi":
                {
                    pantallaGarbitu();

                    if (EkintzaMota.equalsIgnoreCase("Arruntak"))
                    {
                        EkintzaArruntakIkusi();
                    }
                    else if (EkintzaMota.equalsIgnoreCase("Pernoktaziozkoak"))
                    {
                        PernoktaziozkoEkintzakIkusi();
                    }

                } break;

                case "sartu":
                {
                    pantallaGarbitu();

                    if (EkintzaMota.equalsIgnoreCase("Arruntak"))
                    {
                        EkintzaArruntaGehitu();
                    }
                    else if (EkintzaMota.equalsIgnoreCase("Pernoktaziozkoak"))
                    {
                        PernoktaziozkoEkintzaGehitu();
                    }

                } break;

                case "kendu":
                {
                    pantallaGarbitu();
                    EkintzaEzabatu(EkintzaMota);

                } break;

                case "zuzendu":
                {
                    pantallaGarbitu();
                    EkintzaAldatu(EkintzaMota);

                } break;

                case "atzera":
                {
                    pantallaGarbitu();
                    System.out.println(EkintzaMota + "-tik ATZERA.");

                } break;

                default:
                {
                    pantallaGarbitu();
                    System.out.println("Ez duzu aukera zuzena sartu.");
                }
            }

        } while (!menuEkintza.equals("atzera"));
    }
    
    public void menuEkintzak()
    {
        pantallaGarbitu();

        String menuEkintza;
        String EkintzaMota;

        do
        {
            System.out.println();
            System.out.println("___EKINTZAK___");
            System.out.println("_________");
            System.out.println("Arruntak");
            System.out.println("Pernoktaziozkoak");
            System.out.println("Denak Bistaratu (ikusi)");
            System.out.println("_________");
            System.out.println("\u001B[93mAtzera\u001B[0m");
            System.out.println();

            menuEkintza = SartuBerba(irakurri, "Zein ekintza kudeatu nahi duzu? ");

            menuEkintza = menuEkintza.toLowerCase();

            switch (menuEkintza)
            {
                case "arruntak":
                {
                    EkintzaMota = "Arruntak";
                    menuEkintzaKudeatu(EkintzaMota);

                } break;
                case "pernoktaziozkoak":
                {
                    EkintzaMota = "Pernoktaziozkoak";
                    menuEkintzaKudeatu(EkintzaMota);

                } break;
                case "ikusi":
                {
                    pantallaGarbitu();

                    EkintzakIkusi();

                } break;
                case "atzera":
                {
                    pantallaGarbitu();
                    System.out.println("EKINTZAK MENUTIK ATZERA.");

                } break;
                default:
                {
                    pantallaGarbitu();
                    System.out.println("Ez duzu aukera zuzena sartu.");
                }
            }

        } while (!menuEkintza.equals("atzera"));
    }
    
    public void menuNagusia(Scanner irakurri)
    {
        String menuNagusi;

        do
        {
            System.out.println();
            System.out.println("___MENU NAGUSIA___");
            System.out.println("Partaideak");
            System.out.println("Langileak");
            System.out.println("Ekintzak");
            System.out.println("_________");
            System.out.println("Irten");
            System.out.println();

            menuNagusi = SartuBerba(irakurri, "Zein kudeatu nahi duzu?");

            menuNagusi = menuNagusi.toLowerCase();

            switch (menuNagusi)
            {
                case "partaideak":
                {
                    pantallaGarbitu();
                    menuPertsonak("Partaideak");

                } break;
                case "langileak":
                {
                    pantallaGarbitu();
                    menuLangileak();

                } break;
                case "ekintzak":
                {
                    pantallaGarbitu();
                    menuEkintzak();

                } break;

                case "irten":
                {
                    pantallaGarbitu();
                    System.out.println("Programa itxi da.");

                } break;

                default:
                {
                    pantallaGarbitu();
                    System.out.println("Ez duzu aukera zuzena sartu.");
                }
            }

        } while (!menuNagusi.equals("irten"));
    }
    
    public static void main(String[] args)
    {
        Menua menu = new Menua();
        
        //Pantaila garbitzen da.
        menu.pantallaGarbitu();

        //Aldagaiak hasierarazi.
        menu.menuNagusia(menu.irakurri);
    }//main
}//Polikiroldegia