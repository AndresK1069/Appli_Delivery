import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class ScenarioLoader {
    public static void main(String[] args) throws FileNotFoundException {
        File scenario = new File("pokemon_appli_data/scenario_0.txt");
        try (Scanner scan = new Scanner(scenario)) {
            Map<String, String> scenarioMap = new LinkedHashMap<>();

            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                String[] split = line.split(" ");
                if (split.length >= 3) {
                    scenarioMap.put(split[0], split[2]);
                }
            }
            System.out.println(scenarioMap);
        }
    }
}

