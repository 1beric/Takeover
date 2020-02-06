package views;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;

import controller.TakeoverController;
import constants.Backgrounds;
import constants.Colors;
import constants.Fonts;
import containers.SelectorContainer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import messages.EndTurnMessage;
import messages.PlaceTileMessage;
import messages.SetPlayerColorMessage;

public class TakeoverView extends Application implements Observer {

	private TakeoverController con;
	
	private Rectangle currSelector;
	private GridPane tilePane;
	private Rectangle pauseTint;
	private Label pauseLabel;
	private SelectorContainer selectors;
	private Rectangle player1Color;
	private Rectangle player2Color;
	
	private ServerSocket serverSocket;
	private Socket socket;
	

	@Override
	public void start(Stage stage) throws Exception {
		con = new TakeoverController(this);

		VBox menu = new VBox();
		HBox playAgainstHBox = new HBox();
		menu.getChildren().add(playAgainstHBox);
		Label playAgainstLabel = new Label("Play against: ");
		playAgainstHBox.getChildren().add(playAgainstLabel);
		
		ToggleGroup playAgainst = new ToggleGroup();
		
		RadioButton computerChoice = new RadioButton("Computer");
		playAgainstHBox.getChildren().add(computerChoice);
		computerChoice.setToggleGroup(playAgainst);
		
		RadioButton playerChoice = new RadioButton("Another Player");
		playAgainstHBox.getChildren().add(playerChoice);
		playerChoice.setToggleGroup(playAgainst);
		
		Label sizeLabel = new Label("Board size: ");
		TextField sizeField = new TextField();
		menu.getChildren().add(new HBox(sizeLabel,sizeField));
		
		
		Button playButton = new Button("Play!");
		menu.getChildren().add(playButton);
		
		playButton.setOnAction((event) -> {
			if (playAgainst.getSelectedToggle() != null && !sizeField.getText().equals("")) {
				processMultiplayer(playAgainst.getSelectedToggle().equals(playerChoice));
				int size = Integer.valueOf(sizeField.getText());
				con.setSize(size,size);
				con.setPlayer(1);
				con.initBoard();
				gameScene(stage);
			}
		});
		
		stage.setTitle("Takeover");
		stage.setScene(new Scene(menu));
		stage.show();
	}
	
	private void processMultiplayer(boolean multiplayer) {
		con.setMultiplayer(multiplayer);
		if (multiplayer) {
			try {
				serverSocket = new ServerSocket();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
	}

	public void gameScene(Stage stage) {
		BorderPane bp = new BorderPane();
		bp.setBackground(Backgrounds.MAIN);
		
		Label title = new Label("TAKEOVER");
		bp.setTop(title);
		setUpTop(title);
				
		VBox controlPane = new VBox();
		bp.setRight(controlPane);
		setUpRight(controlPane);
		
		StackPane center = new StackPane();
		bp.setCenter(center);
		setUpCenter(center);
		stage.setScene(new Scene(bp));
	}

	private void setUpTop(Label title) {
		title.setFont(Fonts.IMPACT45);
		title.setTextFill(Color.WHITE);
	}

	private void setUpRight(VBox controlPane) {

		HBox buttonPane = new HBox();
		controlPane.getChildren().add(buttonPane);
		setUpButtonPane(buttonPane);
		
		HBox playersColorsPane = new HBox();
		controlPane.getChildren().add(playersColorsPane);
		setUpPlayersColorsPane(playersColorsPane);
		
		GridPane tileSelectPane = new GridPane();
		controlPane.getChildren().add(tileSelectPane);
		setUpTileSelectPane(tileSelectPane);
	}

	private void setUpPlayersColorsPane(HBox pane) {
		pane.setSpacing(8);
		pane.setPadding(new Insets(0,8,4,8));
		
		player1Color = new Rectangle(124,124,Color.BLACK);
		pane.getChildren().add(player1Color);
		setUpPlayerColor(player1Color);
		
		player2Color = new Rectangle(124,124,Color.BLACK);
		pane.getChildren().add(player2Color);
		setUpPlayerColor(player2Color);
	}

	private void setUpPlayerColor(Rectangle rect) {
		rect.setStrokeWidth(4);
		rect.setStroke(Color.BLACK);
	}

	private void setUpTileSelectPane(GridPane pane) {
		pane.setPadding(new Insets(4,8,8,8));
		pane.setHgap(8);
		pane.setVgap(8);
		
		Rectangle redSelector = new Rectangle(124,124,Colors.RED);
		pane.add(redSelector, 0, 0);
		setUpSelector(redSelector,"red");
		
		Rectangle blueSelector = new Rectangle(124,124,Colors.BLUE);
		pane.add(blueSelector, 1, 0);
		setUpSelector(blueSelector,"blue");

		Rectangle greenSelector = new Rectangle(124,124,Colors.GREEN);
		pane.add(greenSelector, 0, 1);
		setUpSelector(greenSelector,"green");

		Rectangle yellowSelector = new Rectangle(124,124,Colors.YELLOW);
		pane.add(yellowSelector, 1, 1);
		setUpSelector(yellowSelector,"yellow");

		Rectangle purpleSelector = new Rectangle(124,124,Colors.PURPLE);
		pane.add(purpleSelector, 0, 2);
		setUpSelector(purpleSelector,"purple");

		Rectangle pinkSelector = new Rectangle(124,124,Colors.PINK);
		pane.add(pinkSelector, 1, 2);				
		setUpSelector(pinkSelector,"pink");
		
		selectors = new SelectorContainer(redSelector, blueSelector,
				greenSelector, yellowSelector, purpleSelector, pinkSelector);
	}

	private void setUpSelector(Rectangle selector, String color) {
		selector.setStrokeWidth(4);
		selector.setStroke(Color.TRANSPARENT);
		selector.setOnMouseEntered((event) -> {
			selector.setFill(Colors.valueOf(color + "h"));
		});
		selector.setOnMouseExited((event) -> {
			if (selector.isDisable()) selector.setFill(Colors.valueOf(color+"d"));
			else selector.setFill(Colors.valueOf(color));
		});
		selector.setOnMouseClicked((event) -> {
			con.setActiveColor(color);
		});
	}

	private void setUpButtonPane(HBox pane) {
		pane.setSpacing(8);
		pane.setPadding(new Insets(0,8,4,8));
		
		Button restartButton = new Button("+");
		pane.getChildren().add(restartButton);
		setUpButton(restartButton,"restart");
		
		Button menuButton = new Button("-");
		pane.getChildren().add(menuButton);
		setUpButton(menuButton,"menu");
	}

	private void setUpButton(Button button, String type) {
		if (type.equals("restart"))	setUpRestartButton(button);
		else						setUpMenuButton(button);
		button.setFont(Fonts.IMPACT60);
		button.setTextFill(Color.WHITE);
		button.setBackground(Backgrounds.BUTTON);
		button.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,null,new BorderWidths(4))));
		button.setPrefSize(128, 128);
		button.setOnMouseEntered((event) -> {
			button.setBackground(Backgrounds.BUTTONH);
		});
		button.setOnMouseExited((event) -> {
			button.setBackground(Backgrounds.BUTTON);
		});
	}

	private void setUpMenuButton(Button button) {
		button.setOnMouseClicked((event) -> {
			if (pauseLabel.isDisable()) {
				pauseLabel.setDisable(false);
				pauseLabel.setVisible(true);
				pauseTint.setDisable(false);
				pauseTint.setVisible(true);
			} else {
				pauseLabel.setDisable(true);
				pauseLabel.setVisible(false);
				pauseTint.setDisable(true);
				pauseTint.setVisible(false);
			}
		});
	}

	private void setUpRestartButton(Button button) {
		button.setOnMouseClicked((event) -> {
			con.clearBoard();
			con.initBoard();
		});
	}

	private void setUpCenter(StackPane center) {
		center.setBorder(new Border(new BorderStroke(
				Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(2))));
		center.setAlignment(Pos.CENTER);

		GridPane tilePane = new GridPane();
		center.getChildren().add(tilePane);
		setUpTilePane(tilePane);
		
		pauseTint = new Rectangle(64*con.size(),64*con.size(),Color.BLACK);
		center.getChildren().add(pauseTint);
		pauseTint.setOpacity(0.8);
		pauseTint.setDisable(true);
		pauseTint.setVisible(false);
		
		pauseLabel = new Label("PAUSED");
		center.getChildren().add(pauseLabel);
		pauseLabel.setFont(Fonts.IMPACT75);
		pauseLabel.setTextFill(Color.WHITE);
		pauseLabel.setDisable(true);
		pauseLabel.setVisible(false);
	}

	private void setUpTilePane(GridPane tilePane) {
		this.tilePane = tilePane;
		con.setActiveColor("red");
		currSelector = selectors.redSelector();
		currSelector.setStroke(Color.BLACK);
		currSelector.setDisable(true);
		currSelector.setFill(Colors.REDD);
		player2Color.setFill(Colors.valueOf("blue"));
		selectors.blueSelector().setDisable(true);
	}

	@Override
	public void update(Observable model, Object message) {
		Platform.runLater(() -> {
			if (message instanceof PlaceTileMessage) {
				placeTile((PlaceTileMessage)message);
			} else if (message instanceof SetPlayerColorMessage) {
				setPlayerColor((SetPlayerColorMessage)message);
			} else if (message instanceof EndTurnMessage) {
				processEndTurn((EndTurnMessage)message);
			}
		});
	}

	private void processEndTurn(EndTurnMessage message) {
		if (message.multiplayer()) {
			
		} else {
			con.setPlayer(message.nextPlayer());
			if (message.nextPlayer()==2) {
				new Thread(() -> {
					try {
						Thread.sleep(1000);
						con.selectRandomColor();
						Thread.sleep(1000);
						con.selectRandomTile();
						con.endTurn();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}).start();
			}
		}
		System.out.println(message);
	}

	private void setPlayerColor(SetPlayerColorMessage message) {
		while (selectors==null);
		Rectangle newSelector = selectors.valueOf(message.color());
		Rectangle prevSelector = selectors.valueOf(message.previousColor());

		prevSelector.setDisable(false);
		prevSelector.setFill(Colors.valueOf(message.previousColor()));
		newSelector.setFill(Colors.valueOf(message.color() + "d"));
		currSelector.setStroke(Color.TRANSPARENT);
		currSelector = newSelector;
		currSelector.setStroke(Color.BLACK);
		newSelector.setDisable(true);
		
		if (message.player()==1) player1Color.setFill(Colors.valueOf(message.color()));
		else					 player2Color.setFill(Colors.valueOf(message.color()));
		
		System.out.println(message);
	}

	private void placeTile(PlaceTileMessage message) {
		while (tilePane == null);
		Rectangle rect = new Rectangle(64,64,Colors.valueOf(message.color()));
		tilePane.add(rect, message.col(), message.row());
		rect.setOnMouseClicked((event) -> {
			if (con.selectTile(message.row(), message.col()))
				con.endTurn();
		});
		
		System.out.println(message);
	}

}
