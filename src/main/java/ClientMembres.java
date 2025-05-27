import java.io.FileNotFoundException;
import java.util.Map;

public class ClientMembres {
    public static void main(String[] args) throws FileNotFoundException {
        //Test methode lireMembres

        Membres test = new Membres();
        test.lireMembres("pokemon_appli_data/membres_APPLI.txt");
        System.out.println(test.getCity("Tortank"));


    }
}
