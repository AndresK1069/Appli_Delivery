import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Membres {

    private Map<String, String> ChDictionnaireMembres;

    public Membres() {
        ChDictionnaireMembres = new HashMap<>();
    }

    public void lireMembres(String cheminFichier) throws FileNotFoundException {
        File fichierMembres = new File(cheminFichier);

        if (!fichierMembres.exists()) {
            throw new FileNotFoundException("Le fichier spécifié est introuvable : " + cheminFichier);
        }

        try (Scanner scan = new Scanner(fichierMembres)) {
            while (scan.hasNextLine()) {
                String ligne = scan.nextLine();
                String[] parties = ligne.split(" ");

                if (parties.length >= 2) {
                    ChDictionnaireMembres.put(parties[0], parties[1]);
                } else {
                    System.out.println("Ligne mal formatée, ignorée: " + ligne);
                }
            }
        }
    }

    public String getCity(String username) {
        return ChDictionnaireMembres.get(username);
    }
}
