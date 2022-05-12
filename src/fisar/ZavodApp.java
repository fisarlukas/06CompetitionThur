package fisar;

import java.util.Scanner;

/**
 *
 * @author lukas.fisar
 */
public class ZavodApp {
    static Scanner sc = new Scanner(System.in);
    static Zavod zavod;
    
    public static void main(String[] args) {
        zavod = new Zavod("Jiz50");
        addCompetitor();
    }
    
    public static void addCompetitors() {
        System.out.println("Jmeno:");
        String name = sc.next();
        System.out.println("Prijmeni:");
        String surname = sc.next();
        System.out.println("Rocnik:");
        int year = sc.nextInt();
        System.out.println("Pohlavi:");
        char gender = sc.next().charAt(0);
        System.out.println("Klub:");
        String club = sc.next();
        zavod.addCompetitor(name, surname, year, gender, club);
    }
    
    public static void setStartTime(int hours, int minutes, int seconds) {
        System.out.println("Hodiny, minuty, sekundy");
        zavod.setStartTimeAll(sc.nextInt(),sc.nextInt(),sc.nextInt());
    }
}
