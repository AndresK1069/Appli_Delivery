import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class VoisinsTab{

    public static ArrayList<ArrayList<String>> lireDistances(String cheminFichier) throws FileNotFoundException {
        File fichier = new File(cheminFichier);
        ArrayList<ArrayList<String>> tableau = new ArrayList<>();

        try (Scanner scan = new Scanner(fichier)) {
            while (scan.hasNextLine()) {
                String ligne = scan.nextLine();
                String[] elements = ligne.split(" ");
                ArrayList<String> ligneListe = new ArrayList<>();
                for (String element : elements) {
                    ligneListe.add(element);
                }
                tableau.add(ligneListe);
            }
        }

        return tableau;
    }
}
