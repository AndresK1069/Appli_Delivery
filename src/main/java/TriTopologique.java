import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class TriTopologique {

    private String ChVilleDArrive;
    public ArrayList<String> ChVilleViste;
    public VoisinsTab ChListeVille;
    private VoisinsTab ChVilleDeDepart;
    
    //TODO 
    private String ChVilleDeDepartTMP;

    public TriTopologique() {
        this.ChVilleViste = new ArrayList<>();
        this.ChListeVille = null; // à définir depuis l'extérieur via setListeVille
        // this.ChVilleDeDepart.getIndexMap().get(ChListeVille);
      
        
    }

    public void setListeVille(VoisinsTab listeVille) {
        this.ChListeVille = listeVille;
    }

    public void Explo() {
        int lepluspetit = 9999;
        String villeMin = null;

        for (Entry<String, List<Integer>> entry : ChListeVille.getTabDistance().entrySet()) {
            String key = entry.getKey();
            List<Integer> value = entry.getValue();

            if (ChVilleViste.contains(key)) {
                continue;
            }

            for (Integer distance : value) {
                if (distance < lepluspetit && distance != 0) {
                    lepluspetit = distance;
                    villeMin = key;
                }
            }
        }

        if (villeMin != null && !ChVilleViste.contains(villeMin)) {
            ChVilleViste.add(villeMin);
        }

        System.out.println("Villes visitées : " + ChVilleViste);
    }

    public void ExploV2(){
        int setupValue = 9999;
        int indexV2 = 0;
        String idk = null; //nom de la Prochaine Ville
        ArrayList<String> villevue;

        List<Integer> distances = ChVilleDeDepart.getTabDistance().get("Velizy");
        Map<String, Integer> indexMAP = ChVilleDeDepart.getIndexMap();



        for (int i=0 ; i < distances.size(); i++){
            if (distances.get(i) < setupValue &&  distances.get(i) != 0){
                setupValue = distances.get(i);
                indexV2 = i;
            }
        }

        for (Map.Entry<String, Integer> entry : indexMAP.entrySet()) {
            if (entry.getValue() == indexV2) {
                idk = entry.getKey();  // Found the city name
                ChVilleViste.add(idk);
            }
        

            System.out.println(ChVilleViste);


    }   





    }


}
