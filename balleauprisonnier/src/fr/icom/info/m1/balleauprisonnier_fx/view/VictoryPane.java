package fr.icom.info.m1.balleauprisonnier_fx.view;

import fr.icom.info.m1.balleauprisonnier_fx.App;
import fr.icom.info.m1.balleauprisonnier_fx.model.Game;
import fr.icom.info.m1.balleauprisonnier_fx.util.mvc.Subscriber;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class VictoryPane extends Pane implements Subscriber {
	
	private App app;
	private Game game;
	
	public VictoryPane(App app, Game game) {
		super();
		this.app = app;
		this.game = game;
	}

	@Override
	public void update() {
		if (game.isFinished()) {
			app.setGameRoot(createWinnerScreen());
		}
	}
	

	private Pane createWinnerScreen() {
		
		Text text = new Text("Winner is " + game.getWinner().toString());
		Stop[] stops = new Stop[] { new Stop(0, Color.BLUE), new Stop(1, Color.RED)};
		LinearGradient lg1 = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops);
		text.setFill(lg1);
		text.setStroke(Color.BLACK);
		text.setFont(Font.font("arial", FontWeight.EXTRA_BOLD, 72.));
		text.setStrokeWidth(0.5);
		BorderPane winnerPane = new BorderPane();
		winnerPane.setCenter(text);
		return winnerPane;
	}
}
