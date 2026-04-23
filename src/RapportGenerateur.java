import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Classe qui genere un rapport statistique au format texte.
 * Bonus : sauvegarde les resultats dans un fichier statistiques.txt.
 */
public class RapportGenerateur {

    private String cheminSortie;

    public RapportGenerateur(String cheminSortie) {
        this.cheminSortie = cheminSortie;
    }

    /**
     * Genere un fichier texte contenant les statistiques par colonne.
     */
    public void genererRapport(List<String> entetes, List<List<Double>> donneesNumeriques)
            throws IOException {

        FileWriter ecrivain = new FileWriter(cheminSortie);

        ecrivain.write("=== RAPPORT STATISTIQUE ===\n\n");

        for (int i = 0; i < entetes.size(); i++) {
            if (i >= donneesNumeriques.size() || donneesNumeriques.get(i).isEmpty()) {
                continue;
            }

            List<Double> colonne = donneesNumeriques.get(i);
            double moyenne = Statistiques.calculerMoyenne(colonne);
            double min = Statistiques.calculerMin(colonne);
            double max = Statistiques.calculerMax(colonne);

            ecrivain.write("Colonne : " + entetes.get(i) + "\n");
            ecrivain.write(String.format("  Moyenne : %.2f%n", moyenne));
            ecrivain.write(String.format("  Minimum : %.2f%n", min));
            ecrivain.write(String.format("  Maximum : %.2f%n", max));
            ecrivain.write("---------------------------\n");
        }

        ecrivain.close();
        System.out.println("Rapport genere : " + cheminSortie);
    }
}