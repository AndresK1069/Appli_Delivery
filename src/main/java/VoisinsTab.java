import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class VoisinsTab{
    public static ArrayList<ArrayList<String>> ChTabAdjacent;
    public static Map<String, Integer> ChIndexMap;

    public static ArrayList<ArrayList<String>> lireDistances(String cheminFichier) throws FileNotFoundException {
        File fichier = new File(cheminFichier);
        ChTabAdjacent = new ArrayList<>();

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

    public static Map<String, Integer> getIndexMap(){
        ChIndexMap = new HashMap<>();
        String key = new String();
        for (int i = 0; i < ChTabAdjacent.size(); i++) {
            for (int j = 0; j < ChTabAdjacent.get(i).size(); j++) {
                key = String.valueOf(ChTabAdjacent.get(i).getFirst());
            }
            ChIndexMap.put(key, i+1);
        }
        return ChIndexMap;
    }


    public static String getDistance(String City01, String City02){
        Map<String, Integer> tmp = getIndexMap();
        String distance = ChTabAdjacent.get(tmp.get(City01)-1).get(tmp.get(City02)-1);
        return distance;

    }


}
