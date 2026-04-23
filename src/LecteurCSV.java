import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// Classe de lecture du fichier CSV et stockage des donnees
public class LecteurCSV {

    // Encapsulation : attributs prives
    private String cheminFichier;
    private List<String> entetes;
    private List<String> etiquettes;          // Premiere colonne (texte)
    private List<List<Double>> donnees;       // Colonnes numeriques

    // Constructeur
    public LecteurCSV(String cheminFichier) {
        this.cheminFichier = cheminFichier;
        this.entetes = new ArrayList<>();
        this.etiquettes = new ArrayList<>();
        this.donnees = new ArrayList<>();
    }

    // Methode d'instance : lecture du fichier CSV
    public void lireFichier() throws IOException {
        BufferedReader lecteur = new BufferedReader(new FileReader(cheminFichier));
        String ligne;
        boolean premiereLigne = true;

        while ((ligne = lecteur.readLine()) != null) {
            if (ligne.trim().isEmpty()) continue;

            String[] valeurs = ligne.split(",");

            if (premiereLigne) {
                // Sauvegarder les en-tetes
                for (String v : valeurs) {
                    entetes.add(v.trim());
                }
                premiereLigne = false;
            } else {
                // Premiere colonne = etiquette
                etiquettes.add(valeurs[0].trim());

                // Autres colonnes = nombres
                for (int i = 1; i < valeurs.length; i++) {
                    // Creer la colonne si elle n'existe pas encore
                    if (donnees.size() < i) {
                        donnees.add(new ArrayList<>());
                    }
                    donnees.get(i - 1).add(Double.parseDouble(valeurs[i].trim()));
                }
            }
        }
        lecteur.close();
    }

    // Methodes d'acces (getters) - encapsulation
    public List<String> getEntetes() {
        return entetes;
    }

    public List<String> getEtiquettes() {
        return etiquettes;
    }

    public List<List<Double>> getDonnees() {
        return donnees;
    }
}