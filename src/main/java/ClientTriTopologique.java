import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ClientTriTopologique {
    public static void main(String[] args) {
        // 1. Création de l'objet VoisinsTab
        VoisinsTab voisins = new VoisinsTab();

        // 2. Charger les distances depuis un fichier
        String cheminFichier = "pokemon_appli_data\\distances.txt"; // Assure-toi que ce fichier est dans le bon dossier
        voisins.lireDistances(cheminFichier);

        // 3. Initialiser TriTopologique
        TriTopologique tri = new TriTopologique();

        // 4. Initialiser les variables dans TriTopologique
        tri.ChListeVille = voisins;
        tri.ChVilleDeDepart = voisins;
        tri.ChVilleViste = new ArrayList<>();

        // 5. Appeler la méthode ExploV2
        tri.ExploV2();

        // 6. Afficher la liste des villes visitées après ExploV2
        System.out.println("Villes visitées : " + tri.ChVilleViste);
    }


}