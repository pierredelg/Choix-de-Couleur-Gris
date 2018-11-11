package interfaceProjet;

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
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class CouleurGrisFinal extends Application {
	
	RadioButton radioButtonRGB;

	Circle[] listeCercle = new Circle[10];

	Rectangle[] listeRectangle = new Rectangle[10];

	TextField[] listeDonneeCouleur = new TextField[10];

	CheckBox[] listeCheckBox = new CheckBox[10];

	Random rand = new Random();
	
	int nombreDeCercle;

	int intervalleDeCouleur;

	int niveauDeGris;

	int decalageGris;

	int decalageCouleur;
	
	int nbLignePremiereColonne = 0;

	int nbLigneDeuxiemeColonne = 0;

	public static void main(String[] args) {

		Application.launch(args);

	}

	@Override
	public void start(Stage stage) throws Exception {
		
		//On cree le borderPane de la fenetre 
		BorderPane fenetrePrincipale = new BorderPane();
		
		//On cree la Hbox pour les éléments du haut de l'application
		Group groupNombreCouleur = new Group();
		
		//On cree le gridPane pour les éléments constituants le résultat
		GridPane gridPaneAffichageResultat = new GridPane();
		
		//On cree le label avant le spinner
		Label labelSpinner = new Label("Nombre de couleur  :  ");

		labelSpinner.setTranslateX(30);

		labelSpinner.setLayoutY(15);
		
		//On cree le spinner
		Spinner<Integer> spinner = new Spinner<Integer>(0, 10, 0, 1);
		
		spinner.setPrefWidth(60);

		spinner.setLayoutY(10);

		spinner.setLayoutX(180);
		
		//On cree le bouton reset
		Button reset = new Button("Reset");
		
		reset.setTranslateX(250);
		
		reset.setTranslateY(10);
		
		//On cree le bouton random
		Button randomButton = new Button("Couleur au hasard");

		randomButton.setTranslateX(310);
		
		randomButton.setTranslateY(10);
		
		//On cree le Label placé avant les boutons radios
		Label labelRadio = new Label(" Affichage du résultat en :  ");

		labelRadio.setLayoutX(450);

		labelRadio.setLayoutY(15);
		
		//On cree le bouton radio RGB
		radioButtonRGB = new RadioButton("RGB");

		radioButtonRGB.setSelected(true);

		radioButtonRGB.setLayoutX(625);

		radioButtonRGB.setLayoutY(15);
		
		//On cree le bouton radio hexa
		RadioButton radioButtonHexa = new RadioButton("Héxadecimal");

		radioButtonHexa.setLayoutX(700);

		radioButtonHexa.setLayoutY(15);
		
		//On cree le ToggleGroup pour lier les deux boutons radios
		ToggleGroup groupRadio = new ToggleGroup();

		radioButtonHexa.setToggleGroup(groupRadio);
		
		radioButtonRGB.setToggleGroup(groupRadio);

		//On cree le bouton quitter
		Button quitter = new Button("Quitter");
		
		quitter.setTranslateX(815);
		
		quitter.setTranslateY(10);
		
		
		//Listenner sur la valeur du spinner
		spinner.valueProperty().addListener(new ChangeListener<Integer>() {

			@Override
			public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
				
				
				if (newValue > oldValue) {
					
					//On cree le cercle
					Circle cercle = new Circle();

					cercle.setRadius(40);

					cercle.setFill(Color.TRANSPARENT);

					cercle.setStroke(Color.BLACK);

					cercle.setStrokeWidth(2.0);
					
					GridPane.setMargin(cercle, new Insets(10, 10, 10, 30));
					
					
					//On cree le Rectangle
					Rectangle rectangle = new Rectangle();

					rectangle.setFill(Color.TRANSPARENT);

					rectangle.setStrokeWidth(2.0);

					rectangle.setStroke(Color.BLACK);

					rectangle.setWidth(130);

					rectangle.setHeight(50);
					
					GridPane.setMargin(rectangle, new Insets(10));
					
					//On cree le TextField
					TextField donneeCouleur = new TextField();
					
					donneeCouleur.setEditable(false);
					
					//On cree la VBox dans laquelle on met le Rectangle et le TextField
					VBox vBoxRectangle = new VBox();
					
					GridPane.setMargin(vBoxRectangle, new Insets(10, 10, 10, 10));
					
					vBoxRectangle.getChildren().add(rectangle);

					vBoxRectangle.getChildren().add(donneeCouleur);

					//On cree la checkBox
					CheckBox checkBox = new CheckBox("Bloquer");
					
					GridPane.setMargin(checkBox, new Insets(10, 10, 10, 10));
					
					
					checkBox.setOnAction(new EventHandler<ActionEvent>() {

						@Override
						public void handle(ActionEvent event) {

							boolean selected = false;
							
							//On parcourt la liste des CheckBox crées et on vérifie si elles sont selectionnées
							for (int i = 0; i < listeCheckBox.length; i++) {

								if (listeCheckBox[i] != null && listeCheckBox[i].isSelected()) {

									selected = true;
								}
							}
							//Si au moins une checkbox est sélectionnée ont desactive le spinner afin de ne pas changer le nombre de couleur
							if (selected) {
								spinner.setDisable(true);
							} else {
								spinner.setDisable(false);
							}
						}
					});
					
					//Jusqu'a 5 couleurs selectionnées on ajoute le cercle la Vbox et la checkBox sur la partie gauche du GridPane
					//Aprés 5 couleurs on ajoute les éléments sur la partie droite
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
		
		//Lorsque le bouton reset est actionné
		reset.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				
				//On décremente la valeur du spinner jusque 0
				while(nombreDeCercle != 0) {
					
					spinner.getValueFactory().decrement(1);
				}
				//Si le spinner est desactivé on l'active
				if(spinner.isDisable()) {
					
					spinner.setDisable(false);
				}
			}
			
			
		});
		
		// Lorsque le bouton "Couleur Aleatoire" est actionné
		randomButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				modifierCouleur();
			}
		});
		
	
		radioButtonRGB.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
		
				//Lorsque le RadioButton RGB est selectionné on change la valeur du TextField par la valeur RGB 
				//pour tous les TextField créés 
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
		
		radioButtonHexa.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				//Lorsque le RadioButton Hexadecimal est selectionné on change la valeur du TextField par la valeur hexadecimal 
				//pour tous les TextField créés 
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
		
		//Lorsque le bouton quitter est actionner
		quitter.setOnAction(e->{
			
			System.exit(0);
		});
		
		//On ajoute les éléments de la barre du haut dans le group
		groupNombreCouleur.getChildren().add(labelSpinner);

		groupNombreCouleur.getChildren().add(spinner);

		groupNombreCouleur.getChildren().add(randomButton);
		
		groupNombreCouleur.getChildren().add(reset);

		groupNombreCouleur.getChildren().add(labelRadio);

		groupNombreCouleur.getChildren().add(radioButtonRGB);

		groupNombreCouleur.getChildren().add(radioButtonHexa);
		
		groupNombreCouleur.getChildren().add(quitter);
		
		//On ajoute le Group et le GridPane au BorderPane
		fenetrePrincipale.setTop(groupNombreCouleur);

		fenetrePrincipale.setCenter(gridPaneAffichageResultat);

		
		Scene scene = new Scene(fenetrePrincipale, 860, 560);

		stage.setResizable(false);

		stage.setTitle("Couleur/Gris");

		stage.setScene(scene);

		stage.show();
	}

	public Color randomColor() {

		Color couleur;
		
		//On cree une couleur avec pour limite l'intervalle de couleur (255/nombre de couleur)
		//auquel on ajoute de décalage de couleur qui correspond à 0 au départ puis aux intervalles ajoutés les uns aprés les autres
		couleur = Color.rgb(rand.nextInt(intervalleDeCouleur) + decalageCouleur,
				rand.nextInt(intervalleDeCouleur) + decalageCouleur,
				rand.nextInt(intervalleDeCouleur) + decalageCouleur);

		decalageCouleur += intervalleDeCouleur;

		return couleur;
	}

	public Color degradeGris() {

		Color couleur;
		
		//Pour creer le gris on divise l'intervalle de couleur (255/nombre de couleur) par deux 
		//auquel on ajoute le décalage (0 au départ puis aux intervalles ajoutés les uns aprés les autres)
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
						// que le degradé visible on recommence
					} while ((niveauDeGris < ((int) (comparaison.getRed() * 255))));

					// On modifie la couleur du cercle par la couleur aleatoire si la checkbox n'est pas selectionner

					if (!listeCheckBox[i].isSelected()) {
						listeCercle[i].setFill(coul);

						if (radioButtonRGB.isSelected()) {

							listeDonneeCouleur[i]
									.setText("R : " + (int) (((Color) listeCercle[i].getFill()).getRed() * 255)
											+ " G : " + (int) (((Color) listeCercle[i].getFill()).getGreen() * 255)
											+ " B : " + (int) (((Color) listeCercle[i].getFill()).getBlue() * 255));
						} else {

							listeDonneeCouleur[i].setText("#"
									+ Integer.toHexString((int) (((Color) listeCercle[i].getFill()).getRed() * 255))
											.toUpperCase()
									+ Integer.toHexString((int) (((Color) listeCercle[i].getFill()).getGreen() * 255))
											.toUpperCase()
									+ Integer.toHexString((int) (((Color) listeCercle[i].getFill()).getBlue() * 255))
											.toUpperCase());

						}
						// On modifie la couleur du rectangle avec la valeur du niveau de gris de la
						// couleur aleatoire
						listeRectangle[i].setFill(Color.rgb(niveauDeGris, niveauDeGris, niveauDeGris));
					}

				}
			}
			// On remet les valeur des decalages a 0 pour un futur tirage aléatoire
			decalageGris = 0;
			decalageCouleur = 0;
		}
	}

}