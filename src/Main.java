import java.io.IOException;
import java.util.List;

// Calsse principal qui lit le CSV, affiche un tableau et genere un rapport.

public class Main {

    public static void main(String[] args) {
        String fichierEntree = "data/donnees.csv";

        try {
            // 1. Instanciation et lecture
            LecteurCSV lecteur = new LecteurCSV(fichierEntree);
            lecteur.lireFichier();

            List<String> entetes = lecteur.getEntetes();
            List<String> etiquettes = lecteur.getEtiquettes();
            List<List<Double>> donnees = lecteur.getDonnees();

            // 2. Affichage du tableau
            System.out.println("=== TABLEAU DES DONNEES ===\n");

            // Ligne des en-tetes
            for (String en : entetes) {
                System.out.printf("%-12s", en);
            }
            System.out.println();

            // Lignes de donnees
            for (int ligne = 0; ligne < etiquettes.size(); ligne++) {
                System.out.printf("%-12s", etiquettes.get(ligne));
                for (int col = 0; col < donnees.size(); col++) {
                    System.out.printf("%-12.2f", donnees.get(col).get(ligne));
                }
                System.out.println();
            }

            // 3. Calcul et affichage des statistiques
            System.out.println("\n=== STATISTIQUES ===\n");

            for (int i = 0; i < donnees.size(); i++) {
                String nomColonne = entetes.get(i + 1);
                List<Double> colonne = donnees.get(i);

                double moyenne = Statistiques.calculerMoyenne(colonne);
                double min = Statistiques.calculerMin(colonne);
                double max = Statistiques.calculerMax(colonne);

                System.out.println("Colonne : " + nomColonne);
                System.out.printf("  Moyenne : %.2f%n", moyenne);
                System.out.printf("  Minimum : %.2f%n", min);
                System.out.printf("  Maximum : %.2f%n", max);
                System.out.println();
            }

            // 4. Generation du rapport 
            RapportGenerateur rapport = new RapportGenerateur();
            rapport.genererRapport(entetes, donnees);

        } catch (IOException e) {
            System.err.println("Erreur de lecture/ecriture : " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Erreur : valeur non numerique detectee dans les colonnes numeriques.");
        }
    }
}