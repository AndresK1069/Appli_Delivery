import java.io.FileNotFoundException;
import java.util.*;
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

        ArrayList VilleEXp = new ArrayList();
        ArrayList VilleDE = new ArrayList();

        for(int i=0; i<expediteurs.size(); i++){
            VilleEXp.add(test.getCity(expediteurs.get(i)));
        }

        for(int i=0; i<destinataires.size(); i++){
            VilleDE.add(test.getCity(destinataires.get(i)));
        }
        VilleEXp.add("Velizy");
        VilleDE.add("Velizy");

        VoisinsTab voisins = new VoisinsTab();

        // Lire les distances (les erreurs sont gérées dans la méthode)
        voisins.lireDistances("pokemon_appli_data\\distances.txt");

        List<Integer> distances = voisins.getTabDistance().get("Velizy");
        Map<String, Integer> indexMAP = voisins.getIndexMap();

        ArrayList testindex = new ArrayList();

        for( int i=0; i<VilleEXp.size(); i++){
            testindex.add(indexMAP.get(VilleEXp.get(i)));
        }



        final List<Integer> listWithoutDuplicates = new ArrayList<>(new LinkedHashSet<>(testindex));

        List<Map.Entry<String, Integer>> villesEtDistances = new ArrayList<>();

        for (Integer index : listWithoutDuplicates) {
            for (Map.Entry<String, Integer> entry : indexMAP.entrySet()) {
                if (entry.getValue().equals(index)) {
                    if (index >= 0 && index < distances.size()) {
                        int distance = distances.get(index);
                        villesEtDistances.add(new AbstractMap.SimpleEntry<>(entry.getKey(), distance));
                    }
                    break;
                }
            }
        }

        // Trier du plus grand au plus petit
        villesEtDistances.sort(Comparator.comparingInt(Map.Entry::getValue));

        ArrayList<String> VoyageFinael = new ArrayList<>();
        int DistanceKilometre = 0;

        // Afficher les résultats triés
        for (Map.Entry<String, Integer> entry : villesEtDistances) {
            DistanceKilometre = DistanceKilometre + entry.getValue();
            VoyageFinael.add(entry.getKey());
        }

        // algo pour le retour
        List<Integer> distancesBack = voisins.getTabDistance().get(villesEtDistances.get(villesEtDistances.size()-1).getKey());
        ArrayList testindex2 = new ArrayList();

        for( int i=0; i<VilleDE.size(); i++){
            testindex2.add(indexMAP.get(VilleDE.get(i)));
        }


        final List<Integer> listWithoutDuplicates2 = new ArrayList<>(new LinkedHashSet<>(testindex2));


        // Associer les distances retour et les noms de villes
        List<Map.Entry<String, Integer>> villesEtDistancesRetour = new ArrayList<>();

        for (Integer index : listWithoutDuplicates2) {
            for (Map.Entry<String, Integer> entry : indexMAP.entrySet()) {
                if (entry.getValue().equals(index)) {
                    if (index >= 0 && index < distancesBack.size()) {
                        int distance = distancesBack.get(index);
                        villesEtDistancesRetour.add(new AbstractMap.SimpleEntry<>(entry.getKey(), distance));
                    }
                    break;
                }
            }
        }

        // Retirer Velizy si présente
        Map.Entry<String, Integer> velizyEntry = null;
        Iterator<Map.Entry<String, Integer>> it = villesEtDistancesRetour.iterator();
        while (it.hasNext()) {
            Map.Entry<String, Integer> entry = it.next();
            if (entry.getKey().equals("Velizy")) {
                velizyEntry = entry;
                it.remove();
                break;
            }
        }

        // Trier du plus petit au plus grand
        villesEtDistancesRetour.sort(Comparator.comparingInt(Map.Entry::getValue));

        // Ajouter Velizy à la fin
        if (velizyEntry != null) {
            villesEtDistancesRetour.add(velizyEntry);
        }

        // Affichage des résultats
        for (Map.Entry<String, Integer> entry : villesEtDistancesRetour) {
            DistanceKilometre = DistanceKilometre + entry.getValue();
            VoyageFinael.add(entry.getKey());
        }

        System.out.println("Chemin:"+VoyageFinael + " " +DistanceKilometre + " Kilometre");
    }


}
