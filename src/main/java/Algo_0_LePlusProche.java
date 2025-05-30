import java.io.FileNotFoundException;
import java.util.*;

public class Algo_0_LePlusProche {
    private VoisinsTab ChTabDistance; // Objet contenant les distances entre villes
    private String ChSource; // Ville de départ
    private Membres ChListeMembre; // Objet contenant les membres (avec leurs villes)
    private ScenarioLoader ChScenarioLoader; // Objet permettant de charger un scénario

    public Algo_0_LePlusProche(String source, String PathForMember, String PathForDistances, String PathForScenario) throws FileNotFoundException {

        // Input pour la source du graph
        this.ChSource = source;

        // Chargement des données membres
        this.ChListeMembre = new Membres();
        this.ChListeMembre.lireMembres(PathForMember);

        // Chargement des distances entre les villes
        this.ChTabDistance = new VoisinsTab();
        this.ChTabDistance.lireDistances(PathForDistances);

        // Chargement du scénario à exécuter
        this.ChScenarioLoader = new ScenarioLoader();
        this.ChScenarioLoader.lireScenario(PathForScenario);
    }

    public void LePlusProche() throws FileNotFoundException {
        // Récupération des listes d'expéditeurs et de destinataires
        ArrayList<ArrayList<String>> scenarioList = ChScenarioLoader.getScenario();
        ArrayList<String> expediteurs = scenarioList.get(0);
        ArrayList<String> destinataires = scenarioList.get(1);

        // Listes des villes des expéditeurs et des destinataires
        ArrayList<String> VilleEXp = new ArrayList<String>();
        ArrayList<String> VilleDE = new ArrayList<String>();

        // Conversion des noms d'expéditeurs en villes
        for (String expediteur : expediteurs) {
            VilleEXp.add(ChListeMembre.getCity(expediteur));
        }

        // Conversion des noms de destinataires en villes
        for (String destinataire : destinataires) {
            VilleDE.add(ChListeMembre.getCity(destinataire));
        }

        // Ajout de la ville source dans les deux listes
        VilleEXp.add(ChSource);
        VilleDE.add(ChSource);

        // Récupère la liste des distances à partir de la ville source
        List<Integer> distances = ChTabDistance.getTabDistance().get(ChSource);
        Map<String, Integer> indexMAP = ChTabDistance.getIndexMap();

        // Conversion des villes en index dans le tableau des distances
        ArrayList<Integer> testindex = new ArrayList<Integer>();
        for (String s : VilleEXp) {
            testindex.add(indexMAP.get(s));
        }

        // Suppression des doublons tout en conservant l'ordre
        final List<Integer> listWithoutDuplicates = new ArrayList<>(new LinkedHashSet<Integer>(testindex));

        // Création de la liste des villes avec leur distance depuis la source
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

        // Tri des villes par distance croissante à partir de la source
        villesEtDistances.sort(Comparator.comparingInt(Map.Entry::getValue));

        // Construction de l'itinéraire aller
        ArrayList<String> VoyageFinale = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : villesEtDistances) {
            VoyageFinale.add(entry.getKey());
        }

        // Préparation du calcul du chemin retour
        List<Integer> distancesBack = ChTabDistance.getTabDistance().get(villesEtDistances.getLast().getKey());
        ArrayList<Integer> testindex2 = new ArrayList<Integer>();
        for (String s : VilleDE) {
            testindex2.add(indexMAP.get(s));
        }

        final List<Integer> listWithoutDuplicates2 = new ArrayList<>(new LinkedHashSet<>(testindex2));

        // Construction de la liste des villes pour le retour avec leurs distances
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

        // Retirer Velizy temporairement pour bien gérer son positionnement final
        Map.Entry<String, Integer> velizyEntry = null;
        Iterator<Map.Entry<String, Integer>> it = villesEtDistancesRetour.iterator();
        while (it.hasNext()) {
            Map.Entry<String, Integer> entry = it.next();
            if (entry.getKey().equals(ChSource)) {
                velizyEntry = entry;
                it.remove();
                break;
            }
        }

        // Tri du chemin retour par distance croissante
        villesEtDistancesRetour.sort(Comparator.comparingInt(Map.Entry::getValue));

        // Réintégrer Velizy à la fin du trajet
        if (velizyEntry != null) {
            villesEtDistancesRetour.add(velizyEntry);
        }

        // Ajout du chemin retour dans l'itinéraire final
        for (Map.Entry<String, Integer> entry : villesEtDistancesRetour) {
            VoyageFinale.add(entry.getKey());
        }

        // Calcul total de la distance parcourue
        int DistanceKilometre = 0;
        for (int i = 0; i < VoyageFinale.size(); i++) {
            if (i == VoyageFinale.size() - 1) {
                break;
            }
            String Ville1 = VoyageFinale.get(i);
            String Ville2 = VoyageFinale.get(i + 1);
            DistanceKilometre = DistanceKilometre + ChTabDistance.getDistance(Ville1, Ville2);
        }

        // Affichage final du chemin et de la distance totale
        System.out.println("Chemin:" + VoyageFinale + " " + DistanceKilometre + " Kilometre");
    }
}
