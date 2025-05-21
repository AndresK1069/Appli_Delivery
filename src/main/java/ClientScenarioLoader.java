import java.util.ArrayList;
import java.util.Map;

public class ClientScenarioLoader {
    public static void main(String[] args) {
        try {
            ScenarioLoader loader = new ScenarioLoader();
            Map<String, String> scenarioMap = loader.lireScenario("pokemon_appli_data/scenario_0.txt");
            ArrayList<ArrayList<String>> scenarioList = loader.getScenario();
            ArrayList<String> expediteurs = scenarioList.get(0);
            ArrayList<String> destinataires = scenarioList.get(1);
            System.out.println("Expéditeurs : " + expediteurs);
            System.out.println("Destinataires : " + destinataires);

        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }

    }
}
