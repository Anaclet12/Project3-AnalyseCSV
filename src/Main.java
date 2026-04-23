import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe principale du programme.
 * Orchestre la lecture du fichier CSV, les calculs et la génération du rapport.
 */
public class Main {

    public static void main(String[] args) {
        // Chemins des fichiers
        String fichierEntree = "data/donnees.csv";
        String fichierSortie = "resultats/statistiques.txt";

        try {
            // 1. Lecture du fichier CSV
            LecteurCSV lecteur = new LecteurCSV(fichierEntree);
            lecteur.lireFichier();

            List<String> entetes = lecteur.getEntetes();
            System.out.println("Fichier lu avec succes : " + entetes.size() + " colonnes detectees.\n");

            // 2. Calcul des statistiques pour chaque colonne
            List<List<Double>> donneesParColonne = new ArrayList<>();

            System.out.println("=== STATISTIQUES PAR COLONNE ===\n");

            for (int i = 0; i < entetes.size(); i++) {
                List<Double> colonne = lecteur.getColonneNumerique(i);
                donneesParColonne.add(colonne);

                // Ignorer les colonnes non numeriques
                if (!Statistiques.estColonneNumerique(colonne)) {
                    System.out.println("Colonne '" + entetes.get(i) + "' : non numerique, ignoree.\n");
                    continue;
                }

                double moyenne = Statistiques.calculerMoyenne(colonne);
                double min = Statistiques.calculerMin(colonne);
                double max = Statistiques.calculerMax(colonne);

                System.out.println("Colonne : " + entetes.get(i));
                System.out.printf("  Moyenne : %.2f%n", moyenne);
                System.out.printf("  Minimum : %.2f%n", min);
                System.out.printf("  Maximum : %.2f%n", max);
                System.out.println();
            }

            // 3. Generation du rapport (bonus)
            RapportGenerateur rapport = new RapportGenerateur(fichierSortie);
            rapport.genererRapport(entetes, donneesParColonne);

        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture/ecriture du fichier : " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erreur inattendue : " + e.getMessage());
        }
    }
}