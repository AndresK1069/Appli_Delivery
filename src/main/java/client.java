import java.io.Console;
import java.io.File;

public class client {
    public static void main(String[] args) {
        try {

            GrapheOrienter grapheTotal = new GrapheOrienter("pokemon_appli_data" + File.separator + "distances.txt");
        }
        catch (Exception e) {
            e.printStackTrace();
        }




    }
}
