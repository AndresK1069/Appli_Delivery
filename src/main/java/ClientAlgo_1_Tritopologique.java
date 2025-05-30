import java.io.FileNotFoundException;


public void main(String[] args) throws FileNotFoundException {


    String source = "Velizy";
    String membre = "pokemon_appli_data/membres_APPLI.txt";
    String distance = "pokemon_appli_data\\distances.txt";
    String scenario = "pokemon_appli_data/scenario_0.txt";

    Algo_1_Tritopologique test = new Algo_1_Tritopologique(source, membre, distance, scenario);
    test.TriTopologique();
}

