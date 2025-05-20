import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class voisinstab {
    public static void main(String[] args) throws FileNotFoundException {
        File tabDistance = new File("pokemon_appli_data/distances.txt");
        try (Scanner scan = new Scanner(tabDistance)) {
            ArrayList ligne = new ArrayList<String>();
            while (scan.hasNextLine()) {
                ArrayList tmpArrayList = new ArrayList<String>();
                String line = scan.nextLine();
                String[] split = line.split(" ");
                for (String i: split){
                    tmpArrayList.add(i);
                }
                ligne.add(tmpArrayList);
            }
            System.out.println(ligne);
        }
        
    }
    
}
