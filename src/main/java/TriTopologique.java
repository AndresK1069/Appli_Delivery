import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class TriTopologique {

    private String ChVilleDArrive;
    public ArrayList<String> ChVilleViste;
    public VoisinsTab ChListeVille;
    VoisinsTab ChVilleDeDepart;


    
    //TODO 
    private String ChVilleDeDepartTMP;

    public TriTopologique() {
        this.ChVilleViste = new ArrayList<>();
        this.ChListeVille = null; // à définir depuis l'extérieur via setListeVille
        // this.ChVilleDeDepart.getIndexMap().get(ChListeVille);
      
        
    }


    public void ExploV2(){
        int setupValue = 9999;
        int indexV2 = 0;
        int DistanceKilometre = 0;
        String idk = null; //nom de la Prochaine Ville
        ArrayList<String> villevue;

        List<Integer> distances = ChVilleDeDepart.getTabDistance().get("Velizy");
        Map<String, Integer> indexMAP = ChVilleDeDepart.getIndexMap();

        ChVilleViste.add("Velizy");

        for (int i=0 ; i < distances.size(); i++){
            if (distances.get(i) < setupValue &&  distances.get(i) != 0){
                setupValue = distances.get(i);
                indexV2 = i;
            }
            DistanceKilometre = DistanceKilometre + distances.get(i); //Finish Distance Calculation
        }

        for (Entry<String, Integer> entry : indexMAP.entrySet()) {
            if (entry.getValue() == indexV2) {
                idk = entry.getKey();  // Found the city name
                ChVilleViste.add(idk);
            }

    }   


    }
public static void main(String[] args) throws FileNotFoundException {
        TriTopologique topo = new TriTopologique();
        topo.LePlusProche();
}
    public void LePlusProche() throws FileNotFoundException {

        // Load le Scenario

        ScenarioLoader loader = new ScenarioLoader();
        Map<String, String> scenarioMap = loader.lireScenario("pokemon_appli_data/scenario_0.txt");
        ArrayList<ArrayList<String>> scenarioList = loader.getScenario();
        ArrayList<String> expediteurs = scenarioList.get(0);
        ArrayList<String> destinataires = scenarioList.get(1);

        //load les membre

        Membres test = new Membres();
        test.lireMembres("pokemon_appli_data/membres_APPLI.txt");
        System.out.println(test.getCity("Tortank"));



        ArrayList VilleEXp = new ArrayList();
        ArrayList VilleDE = new ArrayList();

        for(int i=0; i<expediteurs.size(); i++){
            VilleEXp.add(test.getCity(expediteurs.get(i)));

        }

        for(int i=0; i<destinataires.size(); i++){
            VilleDE.add(test.getCity(destinataires.get(i)));
        }

        System.out.println(VilleEXp);
        System.out.println(VilleDE);


        VoisinsTab voisins = new VoisinsTab();

        // Lire les distances (les erreurs sont gérées dans la méthode)
        voisins.lireDistances("pokemon_appli_data\\distances.txt");



        List<Integer> distances = voisins.getTabDistance().get("Velizy");
        Map<String, Integer> indexMAP = voisins.getIndexMap();

        ArrayList testindex = new ArrayList();



        for( int i=0; i<VilleEXp.size(); i++){



            // testindex.add(indexMAP.get(VilleEXp.get(i)));
        }

        System.out.println(testindex);

        final List<Integer> listWithoutDuplicates = new ArrayList<>(new LinkedHashSet<>(testindex));
        System.out.println(listWithoutDuplicates);













    }

}
