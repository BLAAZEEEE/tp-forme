package modele;

public class Carre implements Forme {

    private float cote;

    public Carre(float cote) {
        this.cote = cote;
    }

    // getter
    public float getCote() {
        return this.cote;
    }

    @Override
    public float perimetre() {
        return 4 * cote;
    }

    @Override
    public float surface() {
        return cote * cote;
    }
}
