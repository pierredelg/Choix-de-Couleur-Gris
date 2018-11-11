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
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class FenetrePrincipale extends Application {

	int nombreDeCercle = 0;

	int intervalleDeCouleur = 0;

	int niveauDeGris;

	int decalageGris = 0;

	int decalageCouleur = 0;

	Circle[] listeCercle = new Circle[10];

	Rectangle[] listeRectangle = new Rectangle[10];

	ColorPicker[] listeColorPicker = new ColorPicker[10];

	Random rand = new Random();

	public static void main(String[] args) {

		Application.launch(args);

	}

	@Override
	public void start(Stage stage) throws Exception {

		GridPane gridPaneAffichageResultat = new GridPane();

		Group groupNombreCouleur = new Group();

		BorderPane fenetrePrincipale = new BorderPane();

		RadioButton radioButtonRGB = new RadioButton("RGB");

		radioButtonRGB.setLayoutX(625);

		radioButtonRGB.setLayoutY(15);

		RadioButton radioButtonHexa = new RadioButton("Héxadecimal");

		radioButtonHexa.setLayoutX(700);

		radioButtonHexa.setLayoutY(15);

		ToggleGroup toggleGroup = new ToggleGroup();

		radioButtonRGB.setToggleGroup(toggleGroup);
		radioButtonHexa.setToggleGroup(toggleGroup);

		Label labelRadio = new Label(" Affichage du résultat en :  ");

		labelRadio.setLayoutX(425);

		labelRadio.setLayoutY(15);

		Button randomButton = new Button("Couleur au hasard");

		randomButton.setTranslateX(260);
		randomButton.setTranslateY(15);

		Label labelSpinner = new Label("Nombre de couleur  :  ");

		labelSpinner.setTranslateX(30);

		labelSpinner.setLayoutY(15);

		Spinner<Integer> spinner = new Spinner<Integer>(0, 10, 0, 1);

		spinner.setPrefWidth(60);

		spinner.setLayoutY(10);

		spinner.setLayoutX(180);

		spinner.valueProperty().addListener(new ChangeListener<Integer>() {

			int i = 0;

			int j = 0;

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

					rectangle.setTranslateX(10);

					rectangle.setTranslateY(10);

					GridPane.setMargin(rectangle, new Insets(10));

					Label donneeCouleur = new Label();

					donneeCouleur.setTranslateY(10);

					vBoxRectangle.getChildren().add(rectangle);

					vBoxRectangle.getChildren().add(donneeCouleur);

					ColorPicker colorPicker = new ColorPicker();

					colorPicker.setPrefWidth(120);

					GridPane.setMargin(colorPicker, new Insets(10));

					Circle cercle = new Circle();

					cercle.setRadius(40);

					cercle.setFill(Color.TRANSPARENT);

					cercle.setStroke(Color.BLACK);

					cercle.setStrokeWidth(2.0);

					GridPane.setMargin(cercle, new Insets(10, 10, 10, 30));

					rectangle.fillProperty().addListener(new ChangeListener<Paint>() {

						public void changed(ObservableValue<? extends Paint> ov, Paint old_paint, Paint new_paint) {

							if (!radioButtonHexa.isSelected() && !radioButtonRGB.isSelected()) {

								radioButtonRGB.setSelected(true);
							}

							if (radioButtonRGB.isSelected()) {

								niveauDeGris = ((int) (0.2125 * (colorPicker.getValue().getRed() * 256)))
										+ ((int) (0.7154 * (colorPicker.getValue().getGreen() * 256)))
										+ ((int) (0.0721 * (colorPicker.getValue().getBlue() * 256)));

								donneeCouleur.setText(
										"R = " + niveauDeGris + " G = " + niveauDeGris + " B = " + niveauDeGris);
							}
							if (radioButtonHexa.isSelected()) {

								donneeCouleur.setText("#" + Integer.toHexString(niveauDeGris).toUpperCase()
										+ Integer.toHexString(niveauDeGris).toUpperCase()
										+ Integer.toHexString(niveauDeGris).toUpperCase());

								donneeCouleur.setTranslateX(35);
							}

						}
					});

					if (nombreDeCercle < 5) {

						gridPaneAffichageResultat.add(cercle, 0, i);

						gridPaneAffichageResultat.add(colorPicker, 1, i);

						gridPaneAffichageResultat.add(vBoxRectangle, 2, i);

						i++;

					} else {

						gridPaneAffichageResultat.add(cercle, 3, j);

						gridPaneAffichageResultat.add(colorPicker, 4, j);

						gridPaneAffichageResultat.add(vBoxRectangle, 5, j);

						j++;
					}

					colorPicker.setOnAction(new EventHandler<ActionEvent>() {

						@Override
						public void handle(ActionEvent event) {
							
							intervalleDeCouleur = 255/nombreDeCercle;

							Color couleur = colorPicker.getValue();

							cercle.setFill(couleur);

							niveauDeGris = ((int) (0.2125 * (couleur.getRed() * 256)))
									+ ((int) (0.7154 * (couleur.getGreen() * 256)))
									+ ((int) (0.0721 * (couleur.getBlue() * 256)));

							rectangle.setFill(Color.rgb(niveauDeGris, niveauDeGris, niveauDeGris));
							
							/*
							
							for (int i = 0; i < listeCercle.length; i++) {

								if (listeCercle[i] != null && !listeCercle[i].equals(cercle)) {

									Color couleurATester = (Color) listeCercle[i].getFill();

									int niveauDeGrisATester = ((int) (0.2125 * (couleurATester.getRed() * 256)))
											+ ((int) (0.7154 * (couleurATester.getGreen() * 256)))
											+ ((int) (0.0721 * (couleurATester.getBlue() * 256)));
									
									int differenceDeGris = niveauDeGrisATester - niveauDeGris;


									if (differenceDeGris >= 0) {
										
										if (differenceDeGris < intervalleDeCouleur) {

											while (differenceDeGris < intervalleDeCouleur) {
												if((decalageCouleur + intervalleDeCouleur) > 255) {
													decalageCouleur = 0;
												}
												couleurATester = randomColor();
											}
											colorPicker.setValue(couleurATester);
										}
									} else {
										
										differenceDeGris = niveauDeGris - niveauDeGrisATester; 

										if (differenceDeGris > intervalleDeCouleur) {
											
											while (differenceDeGris > intervalleDeCouleur) {
												
												if((decalageCouleur + intervalleDeCouleur) > 255) {
													decalageCouleur = 0;
												}
												couleurATester = randomColor();
											}
											colorPicker.setValue(couleurATester);
										}
									}
								}
							}*/

							if (!radioButtonHexa.isSelected() && !radioButtonRGB.isSelected()) {

								radioButtonRGB.setSelected(true);
							}

						}
					});

					toggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

						public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle,
								Toggle new_toggle) {

							if (toggleGroup.getSelectedToggle() != null) {

								if (radioButtonRGB.isSelected()) {

									niveauDeGris = ((int) (0.2125 * (colorPicker.getValue().getRed() * 256)))
											+ ((int) (0.7154 * (colorPicker.getValue().getGreen() * 256)))
											+ ((int) (0.0721 * (colorPicker.getValue().getBlue() * 256)));

									donneeCouleur.setText(
											"R = " + niveauDeGris + " G = " + niveauDeGris + " B = " + niveauDeGris);
								}
								if (radioButtonHexa.isSelected()) {

									donneeCouleur.setText("#" + Integer.toHexString(niveauDeGris).toUpperCase()
											+ Integer.toHexString(niveauDeGris).toUpperCase()
											+ Integer.toHexString(niveauDeGris).toUpperCase());

									donneeCouleur.setTranslateX(35);
								}

							}
						}
					});
					// Lorsque le bouton "Couleur Aleatoire" est actionné
					randomButton.setOnAction(new EventHandler<ActionEvent>() {

						@Override
						public void handle(ActionEvent event) {

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

										// tant que la couleur trouvée aléatoirement n'a pas le meme niveau de gris que
										// le degradé visible on recommence
									} while ((niveauDeGris != ((int) (comparaison.getBlue() * 255))));

									// On modifie la couleur du cercle par la couleur aleatoire
									listeCercle[i].setFill(coul);

									// On modifie la valeur du ColorPicker par la couleur aleatoire
									listeColorPicker[i].setValue(coul);

									// On modifie la couleur du rectangle avec la valeur du niveau de gris de la
									// couleur aleatoire
									listeRectangle[i].setFill(Color.rgb(niveauDeGris, niveauDeGris, niveauDeGris));

									// Si aucun boutton n'est enclenché
									if (!radioButtonHexa.isSelected() && !radioButtonRGB.isSelected()) {

										// On enclenche le boutton RGB
										radioButtonRGB.setSelected(true);
									}

								}
							}
							// On remet les valeur des decalages a 0 pour un futur tirage aléatoire
							decalageGris = 0;
							decalageCouleur = 0;
						}
					});

					// On ajoute le cercle crée dans une liste de cercle
					listeCercle[nombreDeCercle] = cercle;

					// On ajoute le rectangle cree dans une liste de rectangle
					listeRectangle[nombreDeCercle] = rectangle;

					// On ajoute le colorpicker crée dans une liste de ColorPicker
					listeColorPicker[nombreDeCercle] = colorPicker;

					// On incremente le nombre de cercle
					nombreDeCercle++;

				}

				// Si la nouvelle valeur du spinner est plus petite que l'ancienne
				if (newValue < oldValue) {

					// On diminue le nombre de cercle
					nombreDeCercle--;

					// On retire les 3 derniers elements créés sur la ligne (le cercle, le
					// colorPicker et la VBox)
					gridPaneAffichageResultat.getChildren()
							.remove((gridPaneAffichageResultat.getChildren().size() - 1));

					gridPaneAffichageResultat.getChildren()
							.remove((gridPaneAffichageResultat.getChildren().size() - 1));

					gridPaneAffichageResultat.getChildren()
							.remove((gridPaneAffichageResultat.getChildren().size() - 1));

					// Si le nombre de cercle est inferieur a 5 on decremente le i qui represente
					// les lignes de la premiere colonne
					if (nombreDeCercle < 5) {

						i--;
					}
					// Si le nombre de cercle est superieur a 5 on decremente le j qui represente
					// les lignes de la deuxieme colonne
					if (nombreDeCercle >= 5) {

						j--;
					}
					// On supprime le cercle, le rectangle et le colorpicker de leurs tableau
					// respectif

					listeCercle[nombreDeCercle] = null;

					listeRectangle[nombreDeCercle] = null;

					listeColorPicker[nombreDeCercle] = null;
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

		Scene scene = new Scene(fenetrePrincipale, 890, 560);

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
	
	public int chercheIntervalle() {
		
		int[] listeMaxIntervalle = new int[10];
		
		for(int i = 0; i <= nombreDeCercle; i++) {
			
			int valeur=intervalleDeCouleur;
			
			listeMaxIntervalle[i]= valeur;
			
			valeur += intervalleDeCouleur;
		}
		
		for(int i = 0;i < listeMaxIntervalle.length; i ++) {
			
//			if(listeCercle[i].getFill()
		}
		return 0;
	}
	
	public Color tirageUneCouleurAleatoire() {
		
	
		
		return null;
	}
}
