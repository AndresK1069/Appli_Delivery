import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        
       
        //Test methode lireMembres
        /* 
        try {
            Dictionary<String, String> membres = Membres.lireMembres("pokemon_appli_data/membres_APPLI.txt");
            System.out.println(membres);
        } catch (FileNotFoundException e) {
            System.out.println("Fichier non trouvé.");
        }*/


        //Test méthode lireDistances
        /* 

        try {
            ArrayList<ArrayList<String>> distances = VoisinsTab.lireDistances("pokemon_appli_data/distances.txt");
            System.out.println(distances);
        } catch (FileNotFoundException e) {
            System.out.println("Fichier non trouvé.");
        }*/

        //Test méthode lireScenario
        /* 

        try {
            Map<String, String> scenario = ScenarioLoader.lireScenario("pokemon_appli_data/scenario_0.txt");
            System.out.println(scenario);
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }*/


          

    }
}
