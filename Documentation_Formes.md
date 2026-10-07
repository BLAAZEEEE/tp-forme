# Projet Formes – TP MVC Java

## Objectif

Application Java (Swing) qui calcule le périmètre et la surface d'un carré ou d'un rond, garde la liste des formes calculées et permet d'en supprimer. Le code est organisé en MVC.

## Ouvrir et lancer dans IntelliJ

1. *File → Open…* → choisir le dossier `Formes`.
2. JDK du projet : `openjdk-25`.
3. Lancer la configuration **Controle** (bouton ▶). Le `main` est dans `controleur.Controle`.

## Structure

```
Formes/
└── src/
    ├── modele/      Forme, Carre, Rond
    ├── vue/         FrmFormes
    └── controleur/  Controle
```

| Package | Rôle |
|---|---|
| `modele` | Données et calculs |
| `vue` | Fenêtre : saisie, affichage, liste |
| `controleur` | Point d'entrée, lien entre la vue et le modèle, collection des formes |

## Package modele

| Classe | Propriété | Périmètre | Surface |
|---|---|---|---|
| `Forme` (interface) | – | `perimetre()` | `surface()` |
| `Carre` | `cote` | 4 × côté | côté² |
| `Rond` | `rayon` | 2 × π × rayon | π × rayon² |

`Carre` et `Rond` implémentent `Forme` et ont un getter (`getCote()`, `getRayon()`).

## Package vue – FrmFormes

| Composant | Nom |
|---|---|
| Champ côté / rayon | `txtCote`, `txtRayon` |
| Résultats carré | `lblPerimetreCarre`, `lblSurfaceCarre` |
| Résultats rond | `lblPerimetreRond`, `lblSurfaceRond` |
| Liste (dans un `JScrollPane`) | `lstFormes` + `contenuLstFormes` (`DefaultListModel`) |
| Boutons | Calculer (carré), Calculer (rond), Supprimer |

| Méthode | Rôle |
|---|---|
| `cmdCalculCarre()` / `cmdCalculRond()` | Lit la valeur saisie et appelle `controle.demandeFrmFormesValeurs` |
| `cmdDel()` | Supprime les lignes sélectionnées (de la dernière à la première) |
| `afficheResultCarre(p, s)` / `afficheResultRond(p, s)` | Affiche périmètre et surface |
| `majLstFormes(lesFormes)` | Vide puis remplit la liste |

Le constructeur reçoit le contrôleur : `FrmFormes(Controle controle)`. Le `main` de la fenêtre est mis en commentaire.

## Package controleur – Controle

| Élément | Rôle |
|---|---|
| `frmFormes` | La fenêtre |
| `lesFormes` (`ArrayList<Forme>`) | Collection des formes calculées |
| `Controle()` | Crée et affiche la fenêtre |
| `main` | `new Controle();` |
| `demandeFrmFormesValeurs(typeForme, valeur)` | Crée un `Carre` ou un `Rond`, affiche le résultat, l'ajoute à `lesFormes`, met à jour la liste |
| `demandeFrmFormesDel(indice)` | Retire la forme de `lesFormes`, met à jour la liste |

## Fonctionnement (calcul d'un carré)

1. L'utilisateur saisit le côté et clique sur **Calculer**.
2. La vue appelle `controle.demandeFrmFormesValeurs("carre", valeur)`.
3. Le contrôleur crée `new Carre(valeur)`.
4. Le contrôleur appelle `frmFormes.afficheResultCarre(perimetre, surface)`.
5. La forme est ajoutée à `lesFormes`, puis `frmFormes.majLstFormes(lesFormes)` met à jour la liste.

La vue ne crée jamais de forme : elle passe toujours par le contrôleur.

## Différences avec le tuto

| Tuto | Projet |
|---|---|
| Fenêtre créée avec WindowBuilder (Eclipse) | Fenêtre écrite en code (IntelliJ) |
| Images dans un dossier `media` | Carré et rond dessinés en Swing (images facultatives) |
| `typeForme == carre` | `typeForme.equals("carre")` |
| – | Virgule acceptée, message si la saisie n'est pas un nombre |
