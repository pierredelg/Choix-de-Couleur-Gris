package projet;

import java.util.Random;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class FenetreBlocagePlusieureCouleur extends Application {

	int nombreDeCercle = 0;

	int intervalleDeCouleur = 0;

	int niveauDeGris;

	int decalageGris = 0;

	int decalageCouleur = 0;

	Circle[] listeCercle = new Circle[10];

	Rectangle[] listeRectangle = new Rectangle[10];

	Label[] listeDonneeCouleur = new Label[10];

	CheckBox[] listeCheckBox = new CheckBox[10];

	CheckBox checkBox;
	
	RadioButton radioButtonRGB;

	Spinner<Integer> spinner = new Spinner<Integer>(0, 10, 0, 1);

	Random rand = new Random();

	public static void main(String[] args) {

		Application.launch(args);

	}

	@Override
	public void start(Stage stage) throws Exception {

		GridPane gridPaneAffichageResultat = new GridPane();

		Group groupNombreCouleur = new Group();

		BorderPane fenetrePrincipale = new BorderPane();

		Label labelRadio = new Label(" Affichage du résultat en :  ");

		labelRadio.setLayoutX(450);

		labelRadio.setLayoutY(15);

		radioButtonRGB = new RadioButton("RGB");

		radioButtonRGB.setSelected(true);

		radioButtonRGB.setLayoutX(625);

		radioButtonRGB.setLayoutY(15);

		radioButtonRGB.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (radioButtonRGB.isSelected()) {

					for (int i = 0; i < listeCercle.length; i++) {

						if (listeCercle[i] != null) {

							listeDonneeCouleur[i]
									.setText("R : " + (int) (((Color) listeCercle[i].getFill()).getRed() * 255)
											+ " G : " + (int) (((Color) listeCercle[i].getFill()).getGreen() * 255)
											+ " B : " + (int) (((Color) listeCercle[i].getFill()).getBlue() * 255));
						}
					}

				}
			}
		});

		RadioButton radioButtonHexa = new RadioButton("Héxadecimal");

		radioButtonHexa.setLayoutX(700);

		radioButtonHexa.setLayoutY(15);

		radioButtonHexa.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (radioButtonHexa.isSelected()) {

					for (int i = 0; i < listeCercle.length; i++) {

						if (listeCercle[i] != null) {

							listeDonneeCouleur[i].setText("#"
									+ Integer.toHexString((int) (((Color) listeCercle[i].getFill()).getRed() * 255))
											.toUpperCase()
									+ Integer.toHexString((int) (((Color) listeCercle[i].getFill()).getGreen() * 255))
											.toUpperCase()
									+ Integer.toHexString((int) (((Color) listeCercle[i].getFill()).getBlue() * 255))
											.toUpperCase());
						}
					}

				}
			}
		});

		ToggleGroup groupRadio = new ToggleGroup();

		radioButtonHexa.setToggleGroup(groupRadio);
		radioButtonRGB.setToggleGroup(groupRadio);

		Button randomButton = new Button("Couleur au hasard");

		randomButton.setTranslateX(280);
		randomButton.setTranslateY(10);

		Label labelSpinner = new Label("Nombre de couleur  :  ");

		labelSpinner.setTranslateX(30);

		labelSpinner.setLayoutY(15);

		spinner.setPrefWidth(60);

		spinner.setLayoutY(10);

		spinner.setLayoutX(180);

		spinner.valueProperty().addListener(new ChangeListener<Integer>() {

			int nbLignePremiereColonne = 0;

			int nbLigneDeuxiemeColonne = 0;

			@Override
			public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {

				
				if (newValue > oldValue) {

					VBox vBoxRectangle = new VBox();

					Rectangle rectangle = new Rectangle();

					rectangle.setFill(Color.TRANSPARENT);

					rectangle.setStrokeWidth(2.0);

					rectangle.setStroke(Color.BLACK);

					rectangle.setWidth(130);

					rectangle.setHeight(50);

					GridPane.setMargin(rectangle, new Insets(10));

					Label donneeCouleur = new Label();

					vBoxRectangle.getChildren().add(rectangle);

					vBoxRectangle.getChildren().add(donneeCouleur);

					Circle cercle = new Circle();

					cercle.setRadius(40);

					cercle.setFill(Color.TRANSPARENT);

					cercle.setStroke(Color.BLACK);

					cercle.setStrokeWidth(2.0);

					GridPane.setMargin(cercle, new Insets(10, 10, 10, 30));

					GridPane.setMargin(vBoxRectangle, new Insets(10, 10, 10, 10));

					checkBox = new CheckBox("Valider");

					checkBox.setOnAction(new EventHandler<ActionEvent>() {

						@Override
						public void handle(ActionEvent event) {
							
							boolean selected = false;
							
							for(int i = 0;i< listeCheckBox.length;i++) {
								
								if (listeCheckBox[i] != null && listeCheckBox[i].isSelected()) {

									selected = true;
								}
							}
							if(selected) {
								spinner.setDisable(true);
							}else {
								spinner.setDisable(false);
							}
						}
					});

					GridPane.setMargin(checkBox, new Insets(10, 10, 10, 10));

					if (nombreDeCercle < 5) {

						gridPaneAffichageResultat.add(cercle, 0, nbLignePremiereColonne);

						gridPaneAffichageResultat.add(vBoxRectangle, 1, nbLignePremiereColonne);
						gridPaneAffichageResultat.add(checkBox, 2, nbLignePremiereColonne);

						nbLignePremiereColonne++;

					} else {

						gridPaneAffichageResultat.add(cercle, 3, nbLigneDeuxiemeColonne);

						gridPaneAffichageResultat.add(vBoxRectangle, 4, nbLigneDeuxiemeColonne);

						gridPaneAffichageResultat.add(checkBox, 5, nbLigneDeuxiemeColonne);

						nbLigneDeuxiemeColonne++;
					}

					// Lorsque le bouton "Couleur Aleatoire" est actionné
					randomButton.setOnAction(new EventHandler<ActionEvent>() {

						@Override
						public void handle(ActionEvent event) {
							
							modifierCouleur();
						}
					});

					// On ajoute le cercle crée dans une liste de cercle
					listeCercle[nombreDeCercle] = cercle;

					// On ajoute le rectangle cree dans une liste de rectangle
					listeRectangle[nombreDeCercle] = rectangle;

					listeDonneeCouleur[nombreDeCercle] = donneeCouleur;

					listeCheckBox[nombreDeCercle] = checkBox;

					// On incremente le nombre de cercle
					nombreDeCercle++;
					
					modifierCouleur();

				}

				// Si la nouvelle valeur du spinner est plus petite que l'ancienne
				if (newValue < oldValue) {
					
					
					modifierCouleur();
					
					// On diminue le nombre de cercle
					nombreDeCercle--;
					

					// On retire les 3 derniers elements créés sur la ligne (le cercle et la VBox)
					gridPaneAffichageResultat.getChildren()
							.remove((gridPaneAffichageResultat.getChildren().size() - 1));

					gridPaneAffichageResultat.getChildren()
							.remove((gridPaneAffichageResultat.getChildren().size() - 1));

					gridPaneAffichageResultat.getChildren()
							.remove((gridPaneAffichageResultat.getChildren().size() - 1));

					// Si le nombre de cercle est inferieur a 5 on decremente le i qui represente
					// les lignes de la premiere colonne
					if (nombreDeCercle < 5) {

						nbLignePremiereColonne--;
					}
					// Si le nombre de cercle est superieur a 5 on decremente le j qui represente
					// les lignes de la deuxieme colonne
					if (nombreDeCercle >= 5) {

						nbLigneDeuxiemeColonne--;
					}
					// On supprime le cercle, le rectangle et le colorpicker de leurs tableau
					// respectif

					listeCercle[nombreDeCercle] = null;

					listeRectangle[nombreDeCercle] = null;

					listeDonneeCouleur[nombreDeCercle] = null;

				}
			}
		});

		groupNombreCouleur.getChildren().add(labelSpinner);

		groupNombreCouleur.getChildren().add(spinner);

		groupNombreCouleur.getChildren().add(randomButton);

		groupNombreCouleur.getChildren().add(labelRadio);

		groupNombreCouleur.getChildren().add(radioButtonRGB);

		groupNombreCouleur.getChildren().add(radioButtonHexa);

		fenetrePrincipale.setTop(groupNombreCouleur);

		fenetrePrincipale.setCenter(gridPaneAffichageResultat);

		Scene scene = new Scene(fenetrePrincipale, 800, 560);

		stage.setResizable(false);

		stage.setTitle("Couleur/Gris");

		stage.setScene(scene);

		stage.show();
	}

	public Color randomColor() {

		Color couleur;

		couleur = Color.rgb(rand.nextInt(intervalleDeCouleur) + decalageCouleur,
				rand.nextInt(intervalleDeCouleur) + decalageCouleur,
				rand.nextInt(intervalleDeCouleur) + decalageCouleur);

		decalageCouleur += intervalleDeCouleur;

		return couleur;
	}

	public Color degradeGris() {

		Color couleur;

		couleur = Color.rgb(intervalleDeCouleur / 2 + decalageGris, intervalleDeCouleur / 2 + decalageGris,
				intervalleDeCouleur / 2 + decalageGris);

		decalageGris += intervalleDeCouleur;

		return couleur;
	}
	
	public void modifierCouleur() {
		
		if (nombreDeCercle != 0) {

			// On cherche l'intervalle entre chaque couleur par rapport au nombre de cercle
			// présent

			intervalleDeCouleur = 255 / nombreDeCercle;

			Color coul;

			Color comparaison;

			// On parcour la liste des cercles afin de changer la couleur
			for (int i = 0; i < listeCercle.length; i++) {

				// Si le cercle n'est pas instancié c'est qu'il n'est pas présent
				if (listeCercle[i] != null) {

					// On cree un gris qui fera parti d'un degradé afin que cela soit visible en
					// impression
					comparaison = degradeGris();

					do {

						// On calul le decalage par rapport a l'intervalle
						decalageCouleur = intervalleDeCouleur * i;

						// On cherche une couleur aleatoirement dans l'intervalle de couleur
						// correspondant
						coul = randomColor();

						// On calcul le niveau de gris de cette couleur
						niveauDeGris = ((int) (0.2125 * (coul.getRed() * 255)))
								+ ((int) (0.7154 * (coul.getGreen() * 255)))
								+ ((int) (0.0721 * (coul.getBlue() * 255)));

						// tant que la couleur trouvée aléatoirement n'a pas le meme niveau de gris
						// que
						// le degradé visible on recommence
					} while ((niveauDeGris != ((int) (comparaison.getBlue() * 255))));

					// On modifie la couleur du cercle par la couleur aleatoire

					if (!listeCheckBox[i].isSelected()) {
						listeCercle[i].setFill(coul);

						if (radioButtonRGB.isSelected()) {

							listeDonneeCouleur[i].setText("R : "
									+ (int) (((Color) listeCercle[i].getFill()).getRed() * 255)
									+ " G : "
									+ (int) (((Color) listeCercle[i].getFill()).getGreen() * 255)
									+ " B : "
									+ (int) (((Color) listeCercle[i].getFill()).getBlue() * 255));
						} else {

							listeDonneeCouleur[i].setText("#" + Integer.toHexString(
									(int) (((Color) listeCercle[i].getFill()).getRed() * 255))
									.toUpperCase()
									+ Integer.toHexString(
											(int) (((Color) listeCercle[i].getFill()).getGreen()
													* 255))
											.toUpperCase()
									+ Integer.toHexString(
											(int) (((Color) listeCercle[i].getFill()).getBlue()
													* 255))
											.toUpperCase());

						}
						// On modifie la couleur du rectangle avec la valeur du niveau de gris de la
						// couleur aleatoire
						listeRectangle[i]
								.setFill(Color.rgb(niveauDeGris, niveauDeGris, niveauDeGris));
					}

				}
			}
			// On remet les valeur des decalages a 0 pour un futur tirage aléatoire
			decalageGris = 0;
			decalageCouleur = 0;
		}
	}
	
}