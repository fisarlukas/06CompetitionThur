package fisar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

/**
 *
 * @author lukas.fisar
 */
public class Zavodnik {
    private String jmeno;
    private String prijmeni;
    private String zavod;
    private int rokNarozeni;
    private String pohlavi;
    private String klub;
    private int startovniCislo;
    private String startovniVlna;
    private boolean zaplatil;
    private int start;
    private int cil;
    private int time;
    private int poradi;

    public Zavodnik(String jmeno, String prijmeni, int rokNarozeni, String pohlavi, String zavod) {
        this.jmeno = jmeno;
        this.prijmeni = prijmeni;
        this.zavod = zavod;
        this.rokNarozeni = rokNarozeni;
        this.pohlavi = pohlavi;
    }

    //kopie zavodnika
    public Zavodnik(Zavodnik z) {
        this.jmeno = z.jmeno;
        this.prijmeni = z.prijmeni;
        this.zavod = z.zavod;
        this.rokNarozeni = z.rokNarozeni;
        this.pohlavi = z.pohlavi;
        this.startovniCislo = z.startovniCislo;
        
    }
    
    public void setClub(String club) {
        club = checkClub(club);
    }

    public String getJmeno() {
        return jmeno;
    }

    public String getPrijmeni() {
        return prijmeni;
    }

    public String getZavod() {
        return zavod;
    }

    public int getRokNarozeni() {
        return rokNarozeni;
    }
    
    public int getVek() {
        LocalDate = current_date = LocalDate.now();
        int year = current_date.getYear();
        return year - rokNarozeni;
    }
    
    public int getTime() {
        return time = TimeTools.GetTime(start, cil);
    }

    public String getPohlavi() {
        return pohlavi;
    }

    public String getKlub() {
        return klub;
    }

    public int getStartovniCislo() {
        return startovniCislo;
    }

    public String getStartovniVlna() {
        return startovniVlna;
    }

    public boolean isZaplatil() {
        return zaplatil;
    }

    public int getStart() {
        return start;
    }

    public int getCil() {
        return cil;
    }

    public int getTime() {
        if(getStavZavodnika() == StavZavodnika.ukoncen) {
            time = TimeTools.GetTime(start, cil);
        }
        return time;
    }

    public int getPoradi() {
        return poradi;
    }

    public void setStart(int h, int m, int s) {
        this.start = start;
    }

    public void setCil(int cil) {
        this.cil = cil;
    }
    
    
    
    public String toString() {
        return String.format("%5d %10s %10s %2d %1s %10s %10s %10s",
                            this.startovniCislo, this.jmeno, this.prijmeni,
                            this.getVek(), this.pohlavi, TimeTools.SecondsToTime(this.start),
                            TimeTools.SecondsToTime(this.cil), TimeTools.SecondsToTime(this.getTime()));
    }
    
    public static void main(String[] args) {
        Zavodnik z = new Zavodnik("Alice", "Mala", "ZavodLiberec", 1980, "Zena");
        System.out.println(z);
        z.setStart(9,0,0);
        System.out.println(z);
        z.setCil("10:02:05");
        System.out.println(z);
    }
    
    public int compareTo(Zavodnik o) {
        return this.getTime() < o.getTime();
    }
}
