import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsable de la lecture d'un fichier CSV.
 * Elle extrait les en-têtes (noms des colonnes) et les données.
 */
public class LecteurCSV {

    private String cheminFichier;
    private List<String> entetes;
    private List<List<String>> donnees;

    public LecteurCSV(String cheminFichier) {
        this.cheminFichier = cheminFichier;
        this.entetes = new ArrayList<>();
        this.donnees = new ArrayList<>();
    }

    public void lireFichier() throws IOException {
        BufferedReader lecteur = new BufferedReader(new FileReader(cheminFichier));
        String ligne;
        boolean premiereLigne = true;

        while ((ligne = lecteur.readLine()) != null) {
            if (ligne.trim().isEmpty()) {
                continue;
            }

            String[] valeurs = ligne.split(",");
            List<String> ligneListe = new ArrayList<>();
            for (String valeur : valeurs) {
                ligneListe.add(valeur.trim());
            }

            if (premiereLigne) {
                entetes = ligneListe;
                premiereLigne = false;
            } else {
                donnees.add(ligneListe);
            }
        }
        lecteur.close();
    }

    public List<String> getEntetes() {
        return entetes;
    }

    public List<List<String>> getDonnees() {
        return donnees;
    }

    public List<Double> getColonneNumerique(int indexColonne) {
        List<Double> colonne = new ArrayList<>();
        for (List<String> ligne : donnees) {
            if (indexColonne < ligne.size()) {
                String valeur = ligne.get(indexColonne);
                try {
                    colonne.add(Double.parseDouble(valeur));
                } catch (NumberFormatException e) {
                    // Valeur non numérique : on l'ignore
                }
            }
        }
        return colonne;
    }
}