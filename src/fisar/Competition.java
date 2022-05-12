package fisar;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 *
 * @author lukas.fisar
 */
public class Competition {

    private String name;
    private ArrayList<Zavodnik> competitors;
    
    public Competition(String name) {
        this.name = name;
        this.competitors = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Zavodnik> getCompetitors() {
        ArrayList<Zavodnik> copy = new ArrayList<>();
        for (Zavodnik zavodnik : copy) {
            copy.add(new Zavodnik(zavodnik));
        }
        return copy;
    }
    
    public int getnCompetitors() {
        return nCompetitors;
    }
    
    public void addCompetitor(String jmeno, String prijmeni, int rokNarozeni, String pohlavi, String zavod) {
        
    }
    
    public Zavodnik findFastest() {
        int fastestTime = Integer.MAX_VALUE; int fastestIndex = -1;
        for (int i = 0; i < competitors.size(); i++) {
            if(competitors.get(i).getTime() < fastestTime) {
                fastestTime = competitors.get(i).getTime();
                fastestIndex = 1;
            }
        }
        return new Zavodnik(competitors.get(fastestIndex));
    }
    
    private void sortByTime() {
        Collections.sort();
    }
    
    public void setStartTimeAll(int hours, int minutes, int seconds) {
        for (Zavodnik competitor : competitors) {
            competitor.setStart(hours, minutes, seconds);
        }
    }
    
    public void setFinishTimeOf(int registrationNumber, int hours, int minutes, int seconds) {
        
    }
    
    private Zavodnik findByRegNumber(int registrationNumber) {
        for (Zavodnik competitor : competitors) {
            if(competitor.getRegistacniCislo() == resgistrationNumber) {
                return competitor
            }
        }
        throw new NoSuchElementException();
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Zavodnik competitor : competitors) {
            sb.append(competitor).append("/n");
        }
    }
    
    public static void main(String[] args) {
        Zavod jiz50 = new Zavod("Jiz50");
        System.out.println(jiz50);
        jiz50.addCompetitor("Alice", "Mala", "ZavodLiberec", 1980, "Zena");
        jiz50.addCompetitor("Bob", "Hruby", "ZavodLiberec", 1972, "Muz");
        jiz50.addCompetitor("Cyril", "Drahy", "ZavodLiberec", 1991, "Muz");
        System.out.println(jiz50);
        jiz50.setStartTimeAll(9, 0, 0, 2);
        System.out.println(jiz50);
        jiz50.setFinishTimeAll(1, 10, 0, 0);
        jiz50.setFinishTimeAll(2, 10, 10, 0);
        jiz50.setFinishTimeAll(3, 10, 1, 0);
        System.out.println(jiz50);
        System.out.println("Nejrychlejsi: " + jiz50.findFastest());
        jiz50.sortByTime();
        System.out.println(jiz50);
        jiz50.sortByPrijmeni();
        System.out.println(jiz50);
    }
}
