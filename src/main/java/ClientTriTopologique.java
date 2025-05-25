import java.util.ArrayList;

public class ClientTriTopologique {
    public static void main(String[] args) {
        VoisinsTab vt = new VoisinsTab();
        vt.lireDistances("pokemon_appli_data\\distances.txt");

        TriTopologique tt = new TriTopologique();
        tt.setListeVille(vt);

        tt.ExploV2();; // Lancement du parcours depuis Velizy
    }
}


