import java.io.FileNotFoundException;
import java.util.Map;

public class ClientMembres {
    public static void main(String[] args) {
        //Test methode lireMembres


        try {
            Map<String, String> membres = Membres.lireMembres("pokemon_appli_data/membres_APPLI.txt");
            System.out.println(Membres.getCity("Kabutops"));
        } catch (FileNotFoundException e) {
            System.out.println("Fichier non trouvé.");
        }
    }
}
