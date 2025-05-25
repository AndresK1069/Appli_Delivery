import java.io.File;
import java.util.*;

public class VoisinsTab {
    private Map<String, List<Integer>> ChTabAdjacent;
    private Map<String, Integer> ChIndexMap;


    public VoisinsTab() {
        this.ChTabAdjacent = new LinkedHashMap<>();
        this.ChIndexMap = new HashMap<>();
    }

    // Lire les distances et remplir le dictionnaire
    public Map<String, List<Integer>> lireDistances(String cheminFichier) {
        ChTabAdjacent.clear();
        File fichier = new File(cheminFichier);

        try (Scanner scan = new Scanner(fichier)) {
            while (scan.hasNextLine()) {
                String ligne = scan.nextLine().trim();
                if (!ligne.isEmpty()) {
                    String[] elements = ligne.split("\\s+");
                    String ville = elements[0];
                    List<Integer> distances = new ArrayList<>();
                    for (int i = 1; i < elements.length; i++) {
                        distances.add(Integer.parseInt(elements[i]));
                    }
                    ChTabAdjacent.put(ville, distances);
                }
            }
        } catch (Exception e) {
            System.err.println("Erreur lors de la lecture du fichier : " + e.getMessage());
        }

        return ChTabAdjacent;
    }

    public Map<String, List<Integer>> getTabDistance(){
        return ChTabAdjacent;
    }

    // Carte des indices des villes
    public Map<String, Integer> getIndexMap() {
        ChIndexMap.clear();
        int index = 0;
        for (String ville : ChTabAdjacent.keySet()) {
            ChIndexMap.put(ville, index++);
        }
        return ChIndexMap;
    }

    // Obtenir la distance entre deux villes
    public int getDistance(String ville1, String ville2) {
    
        if (ChIndexMap.isEmpty()) {
            getIndexMap();
        }

        int index2 = ChIndexMap.get(ville2);
        return ChTabAdjacent.get(ville1).get(index2);
    }

    
}
