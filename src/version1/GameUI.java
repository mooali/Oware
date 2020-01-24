package version1;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import version1.Game.gameStage;


public class GameUI extends Application {

	// Diese Golbal Variablen wird in der Laufzeit des Programms ge�ndert
	public Player player1;
	public Player player2;
	Game game = new Game(player1, player2);
	Label playStatus = new Label("Let's play: Player1");
	Label player1Point = new Label();
	Label player2Point = new Label();
	Label player1Name = new Label();
	Label player2Name = new Label();
	HBox field1 = new HBox(20);
	HBox field2 = new HBox(20);
	VBox playField = new VBox();
	Label[] fieldLabel = new Label[12];
	boolean gameMode = false;
	private static final String HOVERED_BUTTON_STYLE  = "-fx-background-color: #9ACD32;";



	@Override
	public void start(Stage primaryStage) throws Exception {

		// Ein Standard-Game zwei Spieler wird generiert
		// Game UI beginnt hier
		
		this.newGame("Player 2");
		BorderPane pane = new BorderPane();
		Scene gameScene = new Scene(pane,800,800);

		StackPane homePane = new StackPane();
		StackPane infosPane = new StackPane();

		Text infosText = new Text("\u00a9 Bobby Lien \n"+"\u00a9 Mohammed Ali \n"+"\u00a9 Mac Müller\n");
		infosText.setStyle("-fx-font-size: 20; -fx-font-family: 'Lucida Sans'");
		Label homeLabel = new Label("Welcome to Oware");
		Button singlePlayerBu = new Button("Single Player");
		Button multiPlayerBu = new Button("Multiplayer");
		Button infoBu = new Button("Infos");
		singlePlayerBu.setMaxWidth(Double.MAX_VALUE);
		multiPlayerBu.setMaxWidth(Double.MAX_VALUE);
		infoBu.setMaxWidth(Double.MAX_VALUE);
		singlePlayerBu.setStyle("    -fx-background-color: \n" +
				"        #090a0c,\n" +
				"        linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%),\n" +
				"        linear-gradient(#20262b, #191d22),\n" +
				"        radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));\n" +
				"    -fx-background-radius: 5,4,3,5;\n" +
				"    -fx-background-insets: 0,1,2,0;\n" +
				"    -fx-text-fill: white;\n" +
				"    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );\n" +
				"    -fx-font-family: \"Arial\";\n" +
				"    -fx-text-fill: linear-gradient(white, #d0d0d0);\n" +
				"    -fx-padding: 10 20 10 20;" +
				"-fx-font-size: 20");
		multiPlayerBu.setStyle("    -fx-background-color: \n" +
				"        #090a0c,\n" +
				"        linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%),\n" +
				"        linear-gradient(#20262b, #191d22),\n" +
				"        radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));\n" +
				"    -fx-background-radius: 5,4,3,5;\n" +
				"    -fx-background-insets: 0,1,2,0;\n" +
				"    -fx-text-fill: white;\n" +
				"    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );\n" +
				"    -fx-font-family: \"Arial\";\n" +
				"    -fx-text-fill: linear-gradient(white, #d0d0d0);\n" +
				"-fx-font-size: 20;");

		infoBu.setStyle("    -fx-background-color: \n" +
				"        #090a0c,\n" +
				"        linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%),\n" +
				"        linear-gradient(#20262b, #191d22),\n" +
				"        radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));\n" +
				"    -fx-background-radius: 5,4,3,5;\n" +
				"    -fx-background-insets: 0,1,2,0;\n" +
				"    -fx-text-fill: white;\n" +
				"    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );\n" +
				"    -fx-font-family: \"Arial\";\n" +
				"    -fx-text-fill: linear-gradient(white, #d0d0d0);\n" +
				"    -fx-padding: 10 20 10 20;" +
				"-fx-font-size: 20");

		homeLabel.setStyle("-fx-font-family: Impact; -fx-font-size: 30");
		homeLabel.setPadding(new Insets(10,100,60,100));

		VBox homeBox = new VBox();
		VBox infosBox = new VBox();

		homeBox.getChildren().addAll(homeLabel,singlePlayerBu,multiPlayerBu,infoBu);
		homePane.getChildren().add(homeBox);
		Scene homeScene = new Scene(homePane,800,800);
		Scene infoScene = new Scene(infosPane,800,800);

		homePane.setStyle("-fx-background-image: url(oware.png)");
		homeBox.setAlignment(Pos.CENTER);
		homeBox.setPadding(new Insets(0, 100, 10, 100));
		homeBox.setSpacing(20);



		VBox player1Status = new VBox();
		VBox player2Status = new VBox();
		HBox allPlayersStatus = new HBox();
		HBox gameStatus = new HBox();
		VBox gameOption = new VBox();
		gameOption.setPadding(new Insets(0, 5, 0, 5));
		gameOption.setSpacing(10);
		Button changeMode = new Button("Single Player");
		Button homeBu = new Button("Main Menu");
		homeBu.setStyle("    -fx-background-color: \n" +
				"        #090a0c,\n" +
				"        linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%),\n" +
				"        linear-gradient(#20262b, #191d22),\n" +
				"        radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));\n" +
				"    -fx-background-radius: 5,4,3,5;\n" +
				"    -fx-background-insets: 0,1,2,0;\n" +
				"    -fx-text-fill: white;\n" +
				"    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );\n" +
				"    -fx-font-family: \"Arial\";\n" +
				"    -fx-text-fill: linear-gradient(white, #d0d0d0);\n" +
				"-fx-font-size: 20;");

		changeMode.setStyle("    -fx-background-color: \n" +
				"        #090a0c,\n" +
				"        linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%),\n" +
				"        linear-gradient(#20262b, #191d22),\n" +
				"        radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));\n" +
				"    -fx-background-radius: 5,4,3,5;\n" +
				"    -fx-background-insets: 0,1,2,0;\n" +
				"    -fx-text-fill: white;\n" +
				"    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );\n" +
				"    -fx-font-family: \"Arial\";\n" +
				"    -fx-text-fill: linear-gradient(white, #d0d0d0);\n" +
				"-fx-font-size: 20;");


		player1Status.getChildren().addAll(this.player1Name, this.player1Point);
		player2Status.getChildren().addAll(this.player2Name, this.player2Point);
		allPlayersStatus.getChildren().addAll(player1Status, player2Status);		
		gameStatus.getChildren().addAll(this.playStatus);
		gameStatus.setAlignment(Pos.CENTER);
		
		//Style die Labels mit Hilf-Methode 
		this.fontSet(this.playStatus, this.player1Name, this.player2Name, this.player1Point, this.player2Point);
		this.widthSet(this.player1Point, this.player2Point, this.player1Name, this.player2Name);
		this.centerSet(this.player1Point, this.player2Point, this.player1Name, this.player2Name);

		this.field1.setAlignment(Pos.CENTER);
		this.field2.setAlignment(Pos.CENTER); 
		this.playField.setAlignment(Pos.CENTER); 
		this.playField.getChildren().addAll(this.field1, this.field2);

		pane.setTop(gameStatus);
		pane.setCenter(this.playField); 
		pane.setBottom(allPlayersStatus);
		pane.setLeft(gameOption);



		primaryStage.centerOnScreen();
		primaryStage.setScene(homeScene);
		primaryStage.setTitle("Oware Game");
		primaryStage.setResizable(true);
		primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("oware.png")));
		primaryStage.show();

		primaryStage.setScene(homeScene);

		infoBu.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				infosBox.getChildren().addAll(homeBu,infosText);
				infosBox.setAlignment(Pos.CENTER);
				infosPane.getChildren().add(infosBox);
				primaryStage.setScene(infoScene);


			}
		});

		homeBu.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				infosBox.getChildren().clear();
				infosPane.getChildren().clear();
				gameOption.getChildren().clear();
				primaryStage.setScene(homeScene);


			}
		});
		// Play mode Single player"Play against computer" or Multiplayer "play with another person"
			changeMode.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					if (!gameMode) {
						changeMode.setText("Multiplayer");
						newGame("Computer");
						gameMode = true;
					} else {
						changeMode.setText("Single Player");
						newGame("Player 2");
						gameMode = false;

					}
				}
			});
		singlePlayerBu.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				gameOption.getChildren().addAll(homeBu,changeMode);
				newGame("Player 2");
				primaryStage.setScene(gameScene);
			}
		});

		multiPlayerBu.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				gameOption.getChildren().addAll(homeBu,changeMode);
				newGame("Computer");
				primaryStage.setScene(gameScene);
				System.out.println("test");

			}
		});

		
	}
	
	/// Hilf-Methode nur f�r Labels
	private void fontSet(Label... labels) {
		for(Label label: labels)
			label.setFont(new Font("Serif", 24));
	}
	private void widthSet(Label... labels) {
		for(Label label: labels)
			label.setMinWidth(350);
	}
	private void centerSet(Label... labels) {
		for(Label label: labels)
			label.setAlignment(Pos.CENTER);
	}
	
	// Methode: ein neues Spiel zu generieren und alle Labels werden hiermit angepasst 
	private void newGame(String name2) {
		this.player1 = new Player("Player 1");
		this.player2 = new Player(name2);
		this.game = new Game(player1, player2);
		this.field1.getChildren().clear();
		this.field2.getChildren().clear();
		
		// Set labels in the first line and fill them with seeds
		// to Beginn has player1 6*4 total seeds
		for (int i = 5; i >= 0; i--) {
			Label pitLabel = new Label();
			this.setGameLabel(pitLabel, i);
			fieldLabel[i].setStyle("    -fx-background-color: \n" +
					"        #090a0c,\n" +
					"        linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%),\n" +
					"        linear-gradient(#20262b, #D8C515),\n" +
					"        radial-gradient(center 50% 0%, radius 100%, rgba(170,62,0,0.9), rgba(176,23,0,0.76));\n" +
					"    -fx-background-radius: 30;\n" +
					"    -fx-background-insets: 0,1,2,0;\n" +
					"    -fx-text-fill: white;\n" +
					"    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );\n" +
					"    -fx-font-family: \"Arial\";\n" +
					"    -fx-text-fill: linear-gradient(white, #d0d0d0);\n" +
					"-fx-font-size: 20; \n");
			field1.setSpacing(15);
			this.field1.getChildren().addAll(this.fieldLabel[i]);
			this.player1.addTotalSeed(4); 
		}
		// Set labels in the second line and fill them with seeds
		for (int i = 6; i < 12; i++) {
			Label pitLabel = new Label();
			this.setGameLabel(pitLabel, i);
			fieldLabel[i].setStyle("    -fx-background-color: \n" +
					"        #090a0c,\n" +
					"        linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%),\n" +
					"        linear-gradient(#20262b, #D8C515),\n" +
					"        radial-gradient(center 50% 0%, radius 100%, rgba(170,62,0,0.9), rgba(176,23,0,0.76));\n" +
					"    -fx-background-radius: 30;\n" +
					"    -fx-background-insets: 0,1,2,0;\n" +
					"    -fx-text-fill: white;\n" +
					"    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );\n" +
					"    -fx-font-family: \"Arial\";\n" +
					"    -fx-text-fill: linear-gradient(white, #d0d0d0);\n" +
					"-fx-font-size: 20; \n");
			field2.setSpacing(15);
			field2.setPadding(new Insets(10));
			this.field2.getChildren().addAll(this.fieldLabel[i]);
			this.player2.addTotalSeed(4);
		}
		
		//Erster Spieler ist Player1
		this.playField.setDisable(false);
		this.field1.setDisable(false);
		this.field2.setDisable(true);
		Label fieldName1 = new Label(this.player1.getName());
		Label fieldName2 = new Label(this.player2.getName());
		fieldName1.setStyle("    -fx-background-color: \n" +
				"        #090a0c,\n" +
				"        linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%),\n" +
				"        linear-gradient(#20262b, #D8C515),\n" +
				"        radial-gradient(center 50% 0%, radius 100%, rgba(170,62,0,0.9), rgba(176,23,0,0.76));\n" +
				"    -fx-background-radius: 5,4,3,5;\n" +
				"    -fx-background-insets: 0,1,2,0;\n" +
				"    -fx-text-fill: white;\n" +
				"    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );\n" +
				"    -fx-font-family: \"Arial\";\n" +
				"    -fx-text-fill: linear-gradient(white, #d0d0d0);\n" +
				"-fx-font-size: 20; \n");
		fieldName2.setStyle("    -fx-background-color: \n" +
				"        #090a0c,\n" +
				"        linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%),\n" +
				"        linear-gradient(#20262b, #D8C515),\n" +
				"        radial-gradient(center 50% 0%, radius 100%, rgba(170,62,0,0.9), rgba(176,23,0,0.76));\n" +
				"    -fx-background-radius: 5,4,3,5;\n" +
				"    -fx-background-insets: 0,1,2,0;\n" +
				"    -fx-text-fill: white;\n" +
				"    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );\n" +
				"    -fx-font-family: \"Arial\";\n" +
				"    -fx-text-fill: linear-gradient(white, #d0d0d0);\n" +
				"-fx-font-size: 20; \n");
		fieldName1.setMinWidth(80);
		fieldName2.setMinWidth(80);
		this.field1.getChildren().addAll(fieldName1);
		this.field2.getChildren().addAll(fieldName2);

		this.player1Point.setText("Score: " + this.player2.getPoint());
		player1Point.setStyle("    -fx-background-color: \n" +
				"        #090a0c,\n" +
				"        linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%),\n" +
				"        linear-gradient(#20262b, #D8C515),\n" +
				"        radial-gradient(center 50% 0%, radius 100%, rgba(170,62,0,0.9), rgba(176,23,0,0.76));\n" +
				"    -fx-background-radius: 5,4,3,5;\n" +
				"    -fx-background-insets: 0,1,2,0;\n" +
				"    -fx-text-fill: white;\n" +
				"    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );\n" +
				"    -fx-font-family: \"Arial\";\n" +
				"    -fx-text-fill: linear-gradient(white, #d0d0d0);\n" +
				"-fx-font-size: 20; \n");
		this.player2Point.setText("Score: " + this.player2.getPoint());
		player2Point.setStyle("    -fx-background-color: \n" +
				"        #090a0c,\n" +
				"        linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%),\n" +
				"        linear-gradient(#20262b, #D8C515),\n" +
				"        radial-gradient(center 50% 0%, radius 100%, rgba(170,62,0,0.9), rgba(176,23,0,0.76));\n" +
				"    -fx-background-radius: 5,4,3,5;\n" +
				"    -fx-background-insets: 0,1,2,0;\n" +
				"    -fx-text-fill: white;\n" +
				"    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );\n" +
				"    -fx-font-family: \"Arial\";\n" +
				"    -fx-text-fill: linear-gradient(white, #d0d0d0);\n" +
				"-fx-font-size: 20; \n");
		this.player1Name.setText(this.player1.getName());
		this.player2Name.setText(this.player2.getName());
	}



	// Alle Labels f�r das Spielfeld werden mit dieser Methode generiert.
	// EventHandler wird in dem Label intergriert. 
	private Label setGameLabel(Label label, int field){
		playStatus.setStyle("    -fx-background-color: \n" +
				"        #090a0c,\n" +
				"        linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%),\n" +
				"        linear-gradient(#20262b, #D8C515),\n" +
				"        radial-gradient(center 50% 0%, radius 100%, rgba(170,62,0,0.9), rgba(176,23,0,0.76));\n" +
				"    -fx-background-radius: 5,4,3,5;\n" +
				"    -fx-background-insets: 0,1,2,0;\n" +
				"    -fx-text-fill: white;\n" +
				"    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );\n" +
				"    -fx-font-family: \"Arial\";\n" +
				"    -fx-text-fill: linear-gradient(white, #d0d0d0);\n" +
				"-fx-font-size: 20; \n");
		playStatus.setPadding(new Insets(5));
		label.setText(Integer.toString(this.game.getSeeds(field)));
		label.setMinSize(50.00, 50.00);
		label.setFont(new Font("Serif", 24));
		label.setAlignment(Pos.CENTER);
		label.setOnMouseClicked(new EventHandler<Event>(){
			@Override
			public void handle(Event event) {
				game.tapField(field);
				if(game.getCurrentPlayer() != player1) {
					field1.setDisable(true);
					field2.setDisable(false);
				} else {
					field1.setDisable(false);
					field2.setDisable(true);
				}
				label.setText(Integer.toString(game.getSeeds(field)));

				for(int i = 0; i < fieldLabel.length; i++) {
					fieldLabel[i].setText(Integer.toString(game.getSeeds(i)));
					if (game.getSeeds(i) == 0) {
						fieldLabel[i].setDisable(true);
					} else {
						fieldLabel[i].setDisable(false);
					}
				}
				game.checkGameStage();
				if (game.currentGameStage == gameStage.GAMEOVER) {
					String gameOver = player1.getPoint() > player2.getPoint() ? player1.getName()+" wins\n"+"Total Points: "+player1.getPoint() : player2.getName()+" wins\n"+"Total Points: "+ player2.getPoint();
					playStatus.setText("Game Over \n"+ gameOver);
					playField.setDisable(true);

				} else {
					playStatus.setText("Playing");
				}
				player1Point.setText("Score: "+ Integer.toString(player1.getPoint())+"  ");
				player2Point.setText("Score: "+ Integer.toString(player2.getPoint())+"  ");
				
				//// Nur zur Kontrolle mit Sysout
				int s1 =0;	
				int s2 =0;
				for (int l = 5; l >= 0; l--) {			
					s1+= game.getSeeds(l);
				}
				for (int l = 6; l < 12; l++) {	
					s2+= game.getSeeds(l);	
				}
				System.out.println("p1 = " +s1);
				System.out.println("p1 = " +s2);
				//// bis hier
			}
		});
		this.fieldLabel[field] = label;
		return label;
	}
	

	///// Main :) /////
	public static void main(String[] args) {
		launch(args);
	}
}
