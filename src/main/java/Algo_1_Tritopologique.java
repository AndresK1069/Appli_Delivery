import java.io.FileNotFoundException;
import java.util.*;
import java.lang.Math;

public class Algo_1_Tritopologique {
    // Déclarations des attributs nécessaires
    private String ChSource; // Ville de départ (ex: Velizy)
    private ScenarioLoader ChScenarioLoader; // Pour charger le scénario
    private Membres ChMembres; // Liste des membres avec leur ville
    private VoisinsTab ChTabDistance; // Tableau des distances entre villes

    // Constructeur avec initialisation de tous les fichiers nécessaires
    public Algo_1_Tritopologique(String source, String PathForMember, String PathForDistances, String PathForScenario) throws FileNotFoundException {
        this.ChSource = source;

        // Charger les données du scénario
        this.ChScenarioLoader = new ScenarioLoader();
        this.ChScenarioLoader.lireScenario(PathForScenario);

        // Charger les données des membres
        this.ChMembres = new Membres();
        this.ChMembres.lireMembres(PathForMember);

        // Charger les distances entre les villes
        this.ChTabDistance = new VoisinsTab();
        this.ChTabDistance.lireDistances(PathForDistances);
    }

    // Fonction principale qui exécute le tri topologique
    public void TriTopologique() throws FileNotFoundException {
        // Récupérer les expéditeurs et destinataires à partir du scénario
        ArrayList<ArrayList<String>> scenarioList = ChScenarioLoader.getScenario();
        ArrayList<String> expediteurs = scenarioList.get(0);
        ArrayList<String> destinataires = scenarioList.get(1);

        // Obtenir les villes des expéditeurs et des destinataires
        ArrayList<String> VilleEXp = new ArrayList<>();
        ArrayList<String> VilleDE = new ArrayList<>();

        for (String expediteur : expediteurs){
            VilleEXp.add(ChMembres.getCity(expediteur));
        }
        for (String destinataire : destinataires){
            VilleDE.add(ChMembres.getCity(destinataire));
        }

        // Ajouter la ville source (départ) aux deux listes
        VilleEXp.add(ChSource);
        VilleDE.add(ChSource);

        // Récupérer les index des villes
        Map<String, Integer> indexMAP = ChTabDistance.getIndexMap();
        ArrayList<Integer> testindex = new ArrayList<>();

        for (String ville : VilleEXp){
            testindex.add(indexMAP.get(ville));
        }
        testindex.add(indexMAP.get(ChSource));

        // Éliminer les doublons dans la liste des sommets
        final List<Integer> listdessommet = new ArrayList<>(new LinkedHashSet<>(testindex));

        // Créer une liste contenant les identifiants positifs et négatifs des villes
        ArrayList<Integer> listwithduplicates = new ArrayList<>();
        for (int ville : listdessommet){
            listwithduplicates.add(ville);      // ville+
            listwithduplicates.add(-ville);     // ville-
        }

        // Construction du graphe : arcs de ville+ (vendeur) vers ville- (acheteur)
        Map<Integer, List<Integer>> graphe = new HashMap<>();
        for (int i = 0; i < expediteurs.size(); i++) {
            int vendeur = indexMAP.get(VilleEXp.get(i));
            int acheteur = indexMAP.get(VilleDE.get(i));

            int vendeurPlus = vendeur;
            int acheteurMoins = -acheteur;

            graphe.putIfAbsent(vendeurPlus, new ArrayList<>());
            graphe.get(vendeurPlus).add(acheteurMoins);
        }

        // Ajouter les arcs ville+ -> ville- (logique de livraison)
        for (int ville : listdessommet) {
            int villePlus = ville;
            int villeMoins = -ville;

            graphe.putIfAbsent(villePlus, new ArrayList<>());
            graphe.get(villePlus).add(villeMoins);
        }

        // Calcul du degré sortant (nombre de voisins sortants) — utilisé pour débogage éventuel
        Map<Integer, Integer> degreSortant = new HashMap<>();
        for (int sommet : graphe.keySet()) {
            degreSortant.put(sommet, graphe.get(sommet).size());
        }

        // Calcul du degré entrant pour l'algorithme de Kahn
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

        // Initialisation de la file pour le tri topologique
        Queue<Integer> queue = new LinkedList<>();
        int velizyPlus = indexMAP.get(ChSource);

        // Prioriser la ville source si elle a un degré entrant nul
        if (degreEntrant.getOrDefault(velizyPlus, -1) == 0) {
            queue.add(velizyPlus);
        }

        // Ajouter les autres sommets avec degré entrant nul
        for (int sommet : degreEntrant.keySet()) {
            if (sommet != velizyPlus && degreEntrant.get(sommet) == 0) {
                queue.add(sommet);
            }
        }

        // Tri topologique (algorithme de Kahn)
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

        // Génération de la liste finale des villes visitées (éviter doublons)
        List<String> ordreVisite = new ArrayList<>();
        Set<String> villesVisitees = new HashSet<>();

        ordreVisite.add(ChSource); // Départ
        villesVisitees.add(ChSource);

        for (int sommet : ordre) {
            String ville = ChTabDistance.getKeyByValue(indexMAP, Math.abs(sommet));
            if (!villesVisitees.contains(ville)) {
                ordreVisite.add(ville);
                villesVisitees.add(ville);
            }
        }

        ordreVisite.add(ChSource); // Retour

        // Affichage du chemin trouvé
        System.out.println("Ordre de visite :");
        System.out.println(String.join(" -> ", ordreVisite));

        // Calcul de la distance totale du parcours
        int distanceTotale = 0;
        for (int i = 0; i < ordreVisite.size() - 1; i++) {
            String from = ordreVisite.get(i);
            String to = ordreVisite.get(i + 1);
            try {
                distanceTotale += ChTabDistance.getDistance(from, to);
            } catch (Exception e) {
                System.err.println("Distance inconnue entre " + from + " et " + to);
            }
        }

        // Affichage de la distance totale
        System.out.println("Distance totale parcourue : " + distanceTotale + " km");
    }
}
