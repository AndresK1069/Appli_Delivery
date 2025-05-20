import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Membres {
    private static Map<String, String> ChDictionnaireMembres;

    public static Map<String, String> lireMembres(String cheminFichier) throws FileNotFoundException {
        File fichierMembres = new File(cheminFichier);
        ChDictionnaireMembres = new HashMap<>();

        try (Scanner scan = new Scanner(fichierMembres)) {
            while (scan.hasNextLine()) {
                String ligne = scan.nextLine();
                String[] parties = ligne.split(" ");
                if (parties.length >= 2) {
                    ChDictionnaireMembres.put(parties[0], parties[1]);
                }
            }
        }

        return ChDictionnaireMembres;
    }

    public static String getCity(String username) {
        return ChDictionnaireMembres.get(username);
    }

}
