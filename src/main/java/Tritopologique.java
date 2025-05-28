import java.io.FileNotFoundException;
import java.util.*;
    public class Tritopologique {

        public void letry() throws FileNotFoundException {

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
            testindex.add(indexMAP.get("Velizy"));

            final List<Integer> listdessommet = new ArrayList<>(new LinkedHashSet<>(testindex));
            ArrayList<Integer> listwithduplicates = new ArrayList();

            for (int i =0; i<listdessommet.size(); i++){
                listwithduplicates.add(listdessommet.get(i));
                listwithduplicates.add(listdessommet.get(i)*-1);
            }

            ArrayList source =  new ArrayList();
            source.add(testindex.add("Velizy"));
            listwithduplicates.remove(indexMAP.get("Velizy"));


            System.out.println(listwithduplicates);
            System.out.println(source);

            Map<String, Integer> degreEntrant = new HashMap<>();














        }

    }

    public void main() throws FileNotFoundException {
        Tritopologique t = new Tritopologique();
        t.letry();
    }

