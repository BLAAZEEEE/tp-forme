package vue;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controleur.Controle;
import modele.Carre;
import modele.Forme;
import modele.Rond;

public class FrmFormes extends JFrame {

    private Controle controle;

    private JPanel contentPane;
    private JTextField txtCote;
    private JTextField txtRayon;
    private JLabel lblPerimetreCarre;
    private JLabel lblSurfaceCarre;
    private JLabel lblPerimetreRond;
    private JLabel lblSurfaceRond;
    private JList<String> lstFormes;
    private DefaultListModel<String> contenuLstFormes = new DefaultListModel<String>(); // gère le contenu de la liste

    /*
     * Le main est maintenant dans controleur.Controle
    public static void main(String[] args) {
        new FrmFormes().setVisible(true);
    }
    */

    public FrmFormes(Controle controle) {
        this.controle = controle;

        setTitle("Formes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 620, 460);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        Font titre = new Font("SansSerif", Font.BOLD, 16);

        // ---------- Carré ----------
        JLabel lblCarre = new JLabel("Carré");
        lblCarre.setFont(titre);
        lblCarre.setBounds(20, 15, 200, 25);
        contentPane.add(lblCarre);

        JLabel lblImgCarre = new JLabel();
        lblImgCarre.setOpaque(true);
        lblImgCarre.setBackground(new Color(70, 130, 180));
        lblImgCarre.setBounds(20, 50, 60, 60);
        contentPane.add(lblImgCarre);

        JLabel lblCote = new JLabel("Côté :");
        lblCote.setBounds(100, 50, 60, 20);
        contentPane.add(lblCote);

        txtCote = new JTextField();
        txtCote.setBounds(160, 50, 80, 22);
        contentPane.add(txtCote);

        JButton btnCalculCarre = new JButton("Calculer");
        btnCalculCarre.addActionListener(e -> cmdCalculCarre());
        btnCalculCarre.setBounds(160, 80, 100, 25);
        contentPane.add(btnCalculCarre);

        JLabel lblTxtPerimetreCarre = new JLabel("Périmètre :");
        lblTxtPerimetreCarre.setBounds(20, 120, 80, 20);
        contentPane.add(lblTxtPerimetreCarre);

        lblPerimetreCarre = new JLabel("");
        lblPerimetreCarre.setBounds(100, 120, 170, 20);
        contentPane.add(lblPerimetreCarre);

        JLabel lblTxtSurfaceCarre = new JLabel("Surface :");
        lblTxtSurfaceCarre.setBounds(20, 145, 80, 20);
        contentPane.add(lblTxtSurfaceCarre);

        lblSurfaceCarre = new JLabel("");
        lblSurfaceCarre.setBounds(100, 145, 170, 20);
        contentPane.add(lblSurfaceCarre);

        // ---------- Rond ----------
        JLabel lblRond = new JLabel("Rond");
        lblRond.setFont(titre);
        lblRond.setBounds(320, 15, 200, 25);
        contentPane.add(lblRond);

        JLabel lblImgRond = new JLabel("●");
        lblImgRond.setFont(new Font("SansSerif", Font.PLAIN, 64));
        lblImgRond.setForeground(new Color(205, 92, 92));
        lblImgRond.setBounds(320, 45, 70, 70);
        contentPane.add(lblImgRond);

        JLabel lblRayon = new JLabel("Rayon :");
        lblRayon.setBounds(400, 50, 60, 20);
        contentPane.add(lblRayon);

        txtRayon = new JTextField();
        txtRayon.setBounds(460, 50, 80, 22);
        contentPane.add(txtRayon);

        JButton btnCalculRond = new JButton("Calculer");
        btnCalculRond.addActionListener(e -> cmdCalculRond());
        btnCalculRond.setBounds(460, 80, 100, 25);
        contentPane.add(btnCalculRond);

        JLabel lblTxtPerimetreRond = new JLabel("Périmètre :");
        lblTxtPerimetreRond.setBounds(320, 120, 80, 20);
        contentPane.add(lblTxtPerimetreRond);

        lblPerimetreRond = new JLabel("");
        lblPerimetreRond.setBounds(400, 120, 170, 20);
        contentPane.add(lblPerimetreRond);

        JLabel lblTxtSurfaceRond = new JLabel("Surface :");
        lblTxtSurfaceRond.setBounds(320, 145, 80, 20);
        contentPane.add(lblTxtSurfaceRond);

        lblSurfaceRond = new JLabel("");
        lblSurfaceRond.setBounds(400, 145, 170, 20);
        contentPane.add(lblSurfaceRond);

        // ---------- Liste ----------
        JLabel lblListe = new JLabel("Formes calculées");
        lblListe.setFont(titre);
        lblListe.setBounds(20, 180, 200, 25);
        contentPane.add(lblListe);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 210, 570, 160);
        contentPane.add(scrollPane);

        lstFormes = new JList<String>(contenuLstFormes);
        scrollPane.setViewportView(lstFormes);

        JButton btnDel = new JButton("Supprimer");
        btnDel.addActionListener(e -> cmdDel());
        btnDel.setBounds(470, 380, 120, 25);
        contentPane.add(btnDel);
    }

    // ---------- Événements ----------

    public void cmdCalculCarre() {
        try {
            controle.demandeFrmFormesValeurs("carre", Float.parseFloat(txtCote.getText().replace(',', '.')));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Saisir un nombre pour le côté.");
        }
    }

    public void cmdCalculRond() {
        try {
            controle.demandeFrmFormesValeurs("rond", Float.parseFloat(txtRayon.getText().replace(',', '.')));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Saisir un nombre pour le rayon.");
        }
    }

    public void cmdDel() {
        int[] lesIndices = lstFormes.getSelectedIndices();
        for (int k = lesIndices.length - 1; k >= 0; k--) {
            controle.demandeFrmFormesDel(lesIndices[k]);
        }
    }

    // ---------- Affichage des résultats ----------

    public void afficheResultCarre(float perimetre, float surface) {
        lblPerimetreCarre.setText("" + perimetre); // modifie le label périmètre carré avec le résultat
        lblSurfaceCarre.setText("" + surface); // modifie le label surface carré avec le résultat
    }

    public void afficheResultRond(float perimetre, float surface) {
        lblPerimetreRond.setText("" + perimetre);
        lblSurfaceRond.setText("" + surface);
    }

    public void majLstFormes(ArrayList<Forme> lesFormes) {
        // vider la liste
        contenuLstFormes.clear();
        // boucle pour remplir la liste
        for (Forme uneForme : lesFormes) {
            String ligne;
            if (uneForme instanceof Carre) {
                ligne = "Carré : côté = " + ((Carre) uneForme).getCote();
            } else {
                ligne = "Rond : rayon = " + ((Rond) uneForme).getRayon();
            }
            ligne += "  périmètre = " + uneForme.perimetre() + "  surface = " + uneForme.surface();
            // ajout dans la liste
            contenuLstFormes.addElement(ligne);
        }
    }
}
