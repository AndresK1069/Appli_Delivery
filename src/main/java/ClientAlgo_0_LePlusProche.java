import java.io.FileNotFoundException;

public static void main(String[] args) throws FileNotFoundException {

        String source = "Velizy";
        String membre = "pokemon_appli_data/membres_APPLI.txt";
        String distance = "pokemon_appli_data\\distances.txt";
        String scenario = "pokemon_appli_data/scenario_0.txt";

        Algo_0_LePlusProche topo = new Algo_0_LePlusProche(source, membre, distance, scenario);
        topo.LePlusProche();
}

