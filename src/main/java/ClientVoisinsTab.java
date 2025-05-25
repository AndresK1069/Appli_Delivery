public class ClientVoisinsTab {
    public static void main(String[] args) {
        VoisinsTab voisins = new VoisinsTab();

        // Lire les distances (les erreurs sont gérées dans la méthode)
        voisins.lireDistances("pokemon_appli_data\\distances.txt");

        try {
            int distance = voisins.getDistance("Bordeaux", "Lille");
           System.out.println("Distance Bordeaux -> Lille : " + distance + " km");
        } catch (IllegalArgumentException e) {
            System.err.println("Erreur de ville : " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erreur inattendue : " + e.getMessage());
        }
         voisins.getTabDistance();

    }
}
