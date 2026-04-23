import java.util.List;

/**
 * Classe qui calcule des statistiques sur une liste de nombres.
 * Utilise des methodes de classe (static).
 */
public class Statistiques {

    // Methode de classe : calcule la moyenne
    public static double calculerMoyenne(List<Double> valeurs) {
        double somme = 0.0;
        for (double v : valeurs) {
            somme += v;
        }
        return somme / valeurs.size();
    }

    // Methode de classe : trouve le minimum
    public static double calculerMin(List<Double> valeurs) {
        double min = valeurs.get(0);
        for (double v : valeurs) {
            if (v < min) min = v;
        }
        return min;
    }

    // Methode de classe : trouve le maximum
    public static double calculerMax(List<Double> valeurs) {
        double max = valeurs.get(0);
        for (double v : valeurs) {
            if (v > max) max = v;
        }
        return max;
    }
}