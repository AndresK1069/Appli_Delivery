import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class ScenarioLoader {

    public static Map<String, String> lireScenario(String cheminFichier) throws FileNotFoundException {
        File fichier = new File(cheminFichier);
        try (Scanner scan = new Scanner(fichier)) {
            Map<String, String> scenarioMap = new LinkedHashMap<>();

            while (scan.hasNextLine()) {
                String ligne = scan.nextLine();
                String[] parties = ligne.split(" ");

                if (parties.length >= 3) {
                    scenarioMap.put(parties[0], parties[2]);
                }
            }

            return scenarioMap;
        }
    }
}
