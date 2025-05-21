import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class VoisinsTab {
    private ArrayList<ArrayList<String>> ChTabAdjacent;
    private Map<String, Integer> ChIndexMap;

    // Constructeur
    public VoisinsTab() {
        this.ChTabAdjacent = new ArrayList<>();
        this.ChIndexMap = new HashMap<>();
    }

    // Méthode pour lire les distances depuis un fichier
    public ArrayList<ArrayList<String>> lireDistances(String cheminFichier) throws FileNotFoundException {
        File fichier = new File(cheminFichier);

        try (Scanner scan = new Scanner(fichier)) {
            while (scan.hasNextLine()) {
                String ligne = scan.nextLine();
                String[] elements = ligne.split(" ");
                ArrayList<String> ligneListe = new ArrayList<>();
                for (String element : elements) {
                    ligneListe.add(element);
                }
                ChTabAdjacent.add(ligneListe);
            }
        }

        return ChTabAdjacent;
    }

    // Méthode pour obtenir la carte des indices des villes
    public Map<String, Integer> getIndexMap() {
        ChIndexMap.clear();  // Réinitialiser la carte avant de la remplir
        String key;

        for (int i = 0; i < ChTabAdjacent.size(); i++) {
            // Assumons que chaque ligne dans ChTabAdjacent commence par le nom de la ville
            key = ChTabAdjacent.get(i).get(0);  // Prendre le premier élément comme clé (ville)
            ChIndexMap.put(key, i + 1);  // La clé est la ville, la valeur est son index (commence à 1)
        }

        return ChIndexMap;
    }

    // Méthode pour obtenir la distance entre deux villes
    public String getDistance(String City01, String City02) {
        // Créer la carte des indices des villes
        Map<String, Integer> tmp = getIndexMap();

        // Récupérer l'index des villes et récupérer la distance depuis ChTabAdjacent
        int indexCity01 = tmp.get(City01) - 1;
        int indexCity02 = tmp.get(City02) - 1;

        // Retourner la distance sous forme de String
        return ChTabAdjacent.get(indexCity01).get(indexCity02);
    }
}
