import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

// Classe qui genere le rapport
 
public class RapportGenerateur {

    // Encapsulation : attribut prive
    private String cheminSortie;

    // Constructeur par defaut
    public RapportGenerateur() {
        this.cheminSortie = "resultats/statistiques.txt";
    }

    // Surcharge : constructeur avec chemin personnalise
    public RapportGenerateur(String cheminSortie) {
        this.cheminSortie = cheminSortie;
    }

    // Methode d'instance : genere le rapport
    public void genererRapport(List<String> entetes, List<List<Double>> donnees)
            throws IOException {

        FileWriter ecrivain = new FileWriter(cheminSortie);

        ecrivain.write("=== RAPPORT STATISTIQUE ===\n\n");

        // entetes.get(0) = nom de la colonne d'etiquettes (ex: "produit")
        // donnees.get(i) correspond a entetes.get(i+1)
        for (int i = 0; i < donnees.size(); i++) {
            String nomColonne = entetes.get(i + 1);
            List<Double> colonne = donnees.get(i);

            double moyenne = Statistiques.calculerMoyenne(colonne);
            double min = Statistiques.calculerMin(colonne);
            double max = Statistiques.calculerMax(colonne);

            ecrivain.write("Colonne : " + nomColonne + "\n");
            ecrivain.write(String.format("  Moyenne : %.2f%n", moyenne));
            ecrivain.write(String.format("  Minimum : %.2f%n", min));
            ecrivain.write(String.format("  Maximum : %.2f%n", max));
            ecrivain.write("---------------------------\n");
        }

        ecrivain.close();
        System.out.println("Rapport genere : " + cheminSortie);
    }
}