import java.io.File;
import java.io.FileNotFoundException;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Scanner;

public class Membres {
    public static void main(String[] args) throws FileNotFoundException {
        File memeberliste = new File("pokemon_appli_data/membres_APPLI.txt");
        try (Scanner scan = new Scanner(memeberliste)) {
            Dictionary memberdic = new Hashtable();
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                String[] split = line.split(" ");
                memberdic.put(split[0], split[1]);
            }
            System.out.println(memberdic);
        }
    }
}
