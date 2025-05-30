import java.io.FileNotFoundException;
import java.util.*;
import java.lang.Math;

public class Tritopologique {

    public void letry() throws FileNotFoundException {

        // Load le Scenario
        ScenarioLoader loader = new ScenarioLoader();
        Map<String, String> scenarioMap = loader.lireScenario("pokemon_appli_data/scenario_0.txt");
        ArrayList<ArrayList<String>> scenarioList = loader.getScenario();
        ArrayList<String> expediteurs = scenarioList.get(0);
        ArrayList<String> destinataires = scenarioList.get(1);
        VoisinsTab voisins = new VoisinsTab();

        // Load les membres
        Membres test = new Membres();
        test.lireMembres("pokemon_appli_data/membres_APPLI.txt");

        ArrayList<String> VilleEXp = new ArrayList<>();
        ArrayList<String> VilleDE = new ArrayList<>();

        for (String expediteur : expediteurs){
            VilleEXp.add(test.getCity(expediteur));
        }

        for (String destinataire : destinataires){
            VilleDE.add(test.getCity(destinataire));
        }

        VilleEXp.add("Velizy");
        VilleDE.add("Velizy");

        // Lire les distances (les erreurs sont gérées dans la méthode)
        voisins.lireDistances("pokemon_appli_data/distances.txt");

        Map<String, Integer> indexMAP = voisins.getIndexMap();

        ArrayList<Integer> testindex = new ArrayList<>();

        for (String ville : VilleEXp){
            testindex.add(indexMAP.get(ville));
        }
        testindex.add(indexMAP.get("Velizy"));

        final List<Integer> listdessommet = new ArrayList<>(new LinkedHashSet<>(testindex));
        ArrayList<Integer> listwithduplicates = new ArrayList<>();

        for (int ville : listdessommet){
            listwithduplicates.add(ville);       // ville+
            listwithduplicates.add(-ville);  // ville-
        }

        // Construire le graphe : arcs vendeur+ -> acheteur-
        Map<Integer, List<Integer>> graphe = new HashMap<>();

        for (int i = 0; i < expediteurs.size(); i++) {
            int vendeur = indexMAP.get(VilleEXp.get(i));
            int acheteur = indexMAP.get(VilleDE.get(i));

            int vendeurPlus = vendeur;
            int acheteurMoins = -acheteur;

            graphe.putIfAbsent(vendeurPlus, new ArrayList<>());
            graphe.get(vendeurPlus).add(acheteurMoins);
        }

        // Ajouter arcs ville+ -> ville-
        for (int ville : listdessommet) {
            int villePlus = ville;
            int villeMoins = -ville;

            graphe.putIfAbsent(villePlus, new ArrayList<>());
            graphe.get(villePlus).add(villeMoins);
        }

        // Calcul degré sortant (optionnel)
        Map<Integer, Integer> degreSortant = new HashMap<>();
        for (int sommet : graphe.keySet()) {
            degreSortant.put(sommet, graphe.get(sommet).size());
        }

        // Calcul degré entrant
        Map<Integer, Integer> degreEntrant = new HashMap<>();
        for (int sommet : graphe.keySet()) {
            degreEntrant.put(sommet, 0);
            for (int v : graphe.get(sommet)) {
                degreEntrant.putIfAbsent(v, 0);
            }
        }
        for (List<Integer> voisinsList : graphe.values()) {
            for (int v : voisinsList) {
                degreEntrant.put(v, degreEntrant.get(v) + 1);
            }
        }

        // Tri topologique de Kahn en priorisant Velizy+
        Queue<Integer> queue = new LinkedList<>();
        int velizyPlus = indexMAP.get("Velizy");

        if (degreEntrant.getOrDefault(velizyPlus, -1) == 0) {
            queue.add(velizyPlus);
        }
        for (int sommet : degreEntrant.keySet()) {
            if (sommet != velizyPlus && degreEntrant.get(sommet) == 0) {
                queue.add(sommet);
            }
        }

        List<Integer> ordre = new ArrayList<>();

        while (!queue.isEmpty()) {
            int current = queue.poll();
            ordre.add(current);

            for (int voisin : graphe.getOrDefault(current, Collections.emptyList())) {
                degreEntrant.put(voisin, degreEntrant.get(voisin) - 1);
                if (degreEntrant.get(voisin) == 0) {
                    queue.add(voisin);
                }
            }
        }

        // Construire la liste des villes visitées en évitant répétitions (même non consécutives)
        List<String> ordreVisite = new ArrayList<>();
        Set<String> villesVisitees = new HashSet<>();

        // Départ forcé
        ordreVisite.add("Velizy");
        villesVisitees.add("Velizy");

        for (int sommet : ordre) {
            String ville = voisins.getKeyByValue(indexMAP, Math.abs(sommet));
            if (!villesVisitees.contains(ville)) {
                ordreVisite.add(ville);
                villesVisitees.add(ville);
            }
        }

        // Retour à Velizy
        ordreVisite.add("Velizy");

        // Affichage du résultat
        System.out.println("Ordre de visite (Velizy en premier et dernier) :");
        System.out.println(String.join(" -> ", ordreVisite));

        // Calculer la distance totale parcourue
        int distanceTotale = 0;
        for (int i = 0; i < ordreVisite.size() - 1; i++) {
            String from = ordreVisite.get(i);
            String to = ordreVisite.get(i + 1);
            try {
                distanceTotale += voisins.getDistance(from, to);
            } catch (Exception e) {
                System.err.println("Distance inconnue entre " + from + " et " + to);
            }
        }

        // Affichage final avec distance totale
        System.out.println("Distance totale parcourue : " + distanceTotale + " km");
    }

}
