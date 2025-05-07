import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class arete {
    private ArrayList chSource;
    private String chDestination;
    private int chPoid;

    public arete() throws FileNotFoundException {
        File distance = new File("pokemon_appli_data/distances.txt");
        Scanner scan = new Scanner(distance);


        chSource = new ArrayList();
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            String[] split = line.split(" ");
            chSource.add(split[0]);
        }


        for (int i = 0; i < chSource.size(); i++) {
            int comp = 0;

            chDestination = chSource.get(i).toString();
            System.out.println(chDestination);
        }

    }

}


//https://www.reddit.com/r/learnprogramming/comments/6tse11/how_to_implement_a_directed_weighted_graph_java/?tl=fr