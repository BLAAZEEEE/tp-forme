package modele;

public class Rond implements Forme {

    private float rayon;

    public Rond(float rayon) {
        this.rayon = rayon;
    }

    // getter
    public float getRayon() {
        return this.rayon;
    }

    @Override
    public float perimetre() {
        return (float) (2 * Math.PI * rayon);
    }

    @Override
    public float surface() {
        return (float) (Math.PI * rayon * rayon);
    }
}
