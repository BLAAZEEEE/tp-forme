package controleur;

import java.util.ArrayList;

import modele.Carre;
import modele.Forme;
import modele.Rond;
import vue.FrmFormes;

public class Controle {

    private FrmFormes frmFormes;
    private ArrayList<Forme> lesFormes = new ArrayList<Forme>(); // Collection de forme rond ou carré

    public Controle() {
        frmFormes = new FrmFormes(this);
        frmFormes.setVisible(true);
    }

    public static void main(String[] args) {
        new Controle();
    }

    // Demande de la vue pour obtenir périmètre et surface de la forme
    // typeForme : carre ou rond
    // valeur : cote ou rayon
    public void demandeFrmFormesValeurs(String typeForme, Object valeur) {
        Forme uneForme;
        if (typeForme.equals("carre")) {
            uneForme = new Carre((float) valeur);
            frmFormes.afficheResultCarre(uneForme.perimetre(), uneForme.surface());
        } else {
            uneForme = new Rond((float) valeur);
            frmFormes.afficheResultRond(uneForme.perimetre(), uneForme.surface());
        }
        // ajout de la nouvelle forme dans la collection
        lesFormes.add(uneForme);
        // demande de mise à jour de la liste
        frmFormes.majLstFormes(lesFormes);
    }

    public void demandeFrmFormesDel(int indice) {
        // suppression dans la collection
        lesFormes.remove(indice);
        // demande de mise à jour de la liste
        frmFormes.majLstFormes(lesFormes);
    }
}
