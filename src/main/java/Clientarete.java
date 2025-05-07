import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Clientarete {
    public static void main(String[] args) throws FileNotFoundException {
        File distance = new File("pokemon_appli_data/distances.txt");
        Scanner scan = new Scanner(distance);


        ArrayList chSource = new ArrayList();
        ArrayList chDestination = new ArrayList();
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            String[] split = line.split(" ");
            chSource.add(split[0]);
        }


        for (int i = 2; i < chSource.size(); i++) {
            chDestination = chSource.get(i).toString();
            System.out.println(chDestination);
        }

    });


    }





}
