import java.io.File;
import java.io.FileNotFoundException;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Scanner;

public class Membres {

    public static Dictionary<String, String> lireMembres(String cheminFichier) throws FileNotFoundException {
        File fichierMembres = new File(cheminFichier);
        Dictionary<String, String> dictionnaireMembres = new Hashtable<>();

        try (Scanner scan = new Scanner(fichierMembres)) {
            while (scan.hasNextLine()) {
                String ligne = scan.nextLine();
                String[] parties = ligne.split(" ");
                if (parties.length >= 2) {
                    dictionnaireMembres.put(parties[0], parties[1]);
                }
            }
        }

        return dictionnaireMembres;
    }
}
