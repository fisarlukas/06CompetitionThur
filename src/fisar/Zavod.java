package fisar;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author lukas.fisar
 */
public class Zavod {
    
    public void loadStart(File startFile) throws IOException {
        List<Integer> errorLines = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(startFile))) {
            String line;
            String[] parts;
            Zavodnik r;
            br.readLine(); //preskoceni hlavicky
            int lineNumber = 2;
            while ((line = br.readLine()) != null) {
                parts = line.split("[ ]+");
                r = new Zavodnik(parts[0], parts[1], Integer.parseInt(parts[2]), parts[3].charAt(0));
                try {
                    r.setClub(parts[4]);
                } catch(IllegalArgumentException e) {
                    errorLines.add(lineNumber);
                }
                competitors.add(r);
                lineNumber++;
            }
        }
        if(!errorLines.isEmpty()) {
            throw new ErrorLinesException("Nevalidni klub na radcich " + errorLines.toString());
        }
    }
    
    public void loadFinish(File finishFile) throws FileNotFoundException {
        try(Scanner in = new Scanner(finishFile)) {
            int number;
            String casDobehu;
            Zavodnik r;
            in.nextLine();
            while(in.hasNext()) {
                number = in.nextInt();
                casDobehu = in.next();
                r = findByRegNumber(number);
                r.setCil(casDobehu);
            }
        }
    }
    
    public void saveToFile(File results) throws IOException {
        //new PrintWriter(new OutputStreamWriter()) pouzit kdyz chci kodovani
        try(PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(results, true)))) {
            pw.println(String.format("%5s %5s %10s %10s %5s %1s %10s %10s %10s", "Por.", "Cis.", "Jmeno", "Prijmeni", "Vek", "p", "Start", "Finish", "Doba"));
            int rank = 1;
            for (Zavodnik competitor : competitors) {
                pw.print(String.format("%4d.", rank));
                pw.println(competitor);
                rank++;
            }
        }
    }
    
    public void saveToBinaryFile(File results) {
        try(DataOutputStream out = new DataOutputStream(new FileOutputStream(results))) {
            int nLetters;
            for (Zavodnik competitor : competitors) {
                out.writeInt(competitor.getRegistracniCislo());
                nLetters = competitor.getJmeno().length();
                out.writeInt(competitor.getJmeno().length());
                for (int i = 0; i < nLetters; i++) {
                    out.writeChar(competitor.getJmeno().charAt(i));
                }
                out.writeUTF(competitor.getPrijmeni());
                out.writeInt(competitor.getTime());
            }
        }
    }
    
    public String readFromBinaryResults(File results) throws FileNotFoundException, IOException {
        StringBuilder sb = new StringBuilder();
        int nCompetitors, number, lengthName, time, rank;
        String name = "", surname;
        try(DataInputStream in = new DataInputStream(new FileInputStream(results))) {
            boolean end = false;
            while(!end) {
                try{
                    rank = 1;
                    nCompetitors = in.readInt();
                    for (int i = 0; i < nCompetitors; i++) {
                        number = in.readInt();
                        lengthName = in.readInt();
                        name = "";
                        for (int j = 0; j < lengthName; j++) {
                            name += in.readChar();
                        }
                        surname = in.readUTF();
                        time = in.readInt();
                        sb.append(String.format("%3d. %10s %10s %3d %10s%n", rank, name, surname, number, time))
                                rank++;
                    }
                    sb.append("\n");
                }catch(EOFException e) {
                    end = true;
                }
            }
        }
    }
    
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Zavod jiz50 = new Zavod("Jiz50");
        System.out.println(jiz50);
        String parent = System.getProperty("user.dir") + File.separator + "data";
        
        try {
            while (true) {
                try {
                    System.out.println("Zadej nazev vstupniho souboru");
                    jiz50.loadStart(new File(sc.nextInt()));
                    break;
                } catch(FileNotFoundException e) {
                    System.out.println(e.getMessage());
                    System.out.println("Zadej znova");
                } catch(RuntimeException e) {
                    jiz50 = new Zavod("Jiz50");
                    System.out.println(e.getMessage());
                    System.out.println("Nektery udaj v souboru je spatne. Oprav ho.");
                }
            }
        }
        
        
        System.out.println("Zadej soubor pro vysledky");
        String file = sc.next();
        jiz50.saveToFile(file);
        jiz50.saveToBinaryFile(new File("results.dat"));
    }
    
}
