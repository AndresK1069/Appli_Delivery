import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class GrapheOrienter {
    TreeMap<String, TreeMap<String, Integer>> voisins;

    public GrapheOrienter(String path) throws FileNotFoundException {

        voisins = new TreeMap<>();
        ArrayList<String> cities = new ArrayList<>();

        File file = new File(path);
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] split_line = line.split(" ");
            cities.add(split_line[0]);
        }

        file = new File(path);
        scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] split_line = line.split(" ");
            voisins.put(split_line[0], new TreeMap<>());
            for(int i = 1; i < split_line.length - 1; i++) {
                voisins.get(split_line[0]).put(cities.get(i), Integer.parseInt(split_line[i]));
            }
        }
    }




}
