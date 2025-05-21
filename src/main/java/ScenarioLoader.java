import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class ScenarioLoader {
    private Map<String, String> ChScenarioMap;

    // Constructeur
    public ScenarioLoader() {
        ChScenarioMap = new LinkedHashMap<>();
    }

    public Map<String, String> lireScenario(String cheminFichier) throws FileNotFoundException {
        File fichier = new File(cheminFichier);
        try (Scanner scan = new Scanner(fichier)) {
            // Si le dictionnaire est réutilisé, réinitialisez-le
            ChScenarioMap.clear();

            while (scan.hasNextLine()) {
                String ligne = scan.nextLine();
                String[] parties = ligne.split(" ");

                if (parties.length >= 3) {
                    ChScenarioMap.put(parties[0], parties[2]);
                }
            }

            return ChScenarioMap;
        }
    }

    public ArrayList getScenario() {
        ArrayList expediteur = new ArrayList<>();
        ArrayList destinataire = new ArrayList<>();
        for (Map.Entry<String, String> entry : ChScenarioMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            expediteur.add(key);
            destinataire.add(value);
        }

        ArrayList<ArrayList<String>> result = new ArrayList<>();
        result.add(expediteur);
        result.add(destinataire);

        return result;
    }
}
