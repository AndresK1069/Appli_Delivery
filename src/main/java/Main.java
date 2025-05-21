import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        
       
        //Test methode lireMembres
        /* 
        
        try {
            Map<String, String> membres = Membres.lireMembres("pokemon_appli_data/membres_APPLI.txt");
            System.out.println(Membres.getCity("Kabutops"));
            
            

        } catch (FileNotFoundException e) {
            System.out.println("Fichier non trouvé.");
        }*/


        //Test méthode lireDistances


        try {
            ArrayList<ArrayList<String>> distances = VoisinsTab.lireDistances("pokemon_appli_data/distances.txt");
            System.out.println(VoisinsTab.getDistance("Brest","Clermond_Fd"));
        } catch (FileNotFoundException e) {
            System.out.println("Fichier non trouvé.");
        }

        //Test méthode lireScenario
        
        //TODO

        /* 

        try {
            Map<String, String> scenarioMap = ScenarioLoader.lireScenario("pokemon_appli_data/scenario_0.txt");
            ArrayList<ArrayList<String>> scenarioList = scenarioMap.getScenario();
            ArrayList<String> expediteurs = scenarioList.get(0);
            ArrayList<String> destinataires = scenarioList.get(1);
            System.out.println("Expéditeurs : " + expediteurs);
            System.out.println("Destinataires : " + destinataires);

        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }*/



          

    }
}
