package fr.icom.info.m1.balleauprisonnier_fx.view.component;

import fr.icom.info.m1.balleauprisonnier_fx.model.Game;
import fr.icom.info.m1.balleauprisonnier_fx.util.mvc.Subscriber;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class ScorePane extends BorderPane implements Subscriber {
	
	private Text scoreTeam1;
	private Text scoreTeam2;
	private Game game;
	
	public ScorePane(Game game) {
		scoreTeam1 = new Text();
		scoreTeam2 = new Text();
		this.game = game;
		
		scoreTeam1.setFill(Color.WHITE);
		scoreTeam1.setStroke(Color.BLACK);
		scoreTeam1.setFont(Font.font("arial", FontWeight.EXTRA_BOLD, 30.));
		scoreTeam1.setStrokeWidth(0.5);
		
		scoreTeam2.setFill(Color.WHITE);
		scoreTeam2.setStroke(Color.BLACK);
		scoreTeam2.setFont(Font.font("arial", FontWeight.EXTRA_BOLD, 30.));
		scoreTeam2.setStrokeWidth(0.5);
		
		setTop(scoreTeam1);
		setBottom(scoreTeam2);
//		setBackground(new Background(new BackgroundFill(Color.BLUE, new CornerRadii(10), getInsets())));
	}


	@Override
	public void update() {
		scoreTeam1.setText(String.format("Equipe 1 : joueurs restants : %d", game.getTeam1().getAlivePlayers().size()));
		scoreTeam2.setText(String.format("Equipe 2 : joueurs restants : %d", game.getTeam2().getAlivePlayers().size()));
	}
}