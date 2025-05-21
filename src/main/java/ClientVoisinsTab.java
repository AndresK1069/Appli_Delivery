import java.io.FileNotFoundException;
import java.util.ArrayList;

public class ClientVoisinsTab {
    public static void main(String[] args) {
        try {
            ArrayList<ArrayList<String>> distances = VoisinsTab.lireDistances("pokemon_appli_data/distances.txt");
            System.out.println(VoisinsTab.getDistance("Brest","Clermond_Fd"));
        } catch (FileNotFoundException e) {
            System.out.println("Fichier non trouvé.");
        }
    }
}
