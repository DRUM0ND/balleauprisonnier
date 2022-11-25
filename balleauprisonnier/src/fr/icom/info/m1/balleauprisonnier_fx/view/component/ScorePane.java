package fr.icom.info.m1.balleauprisonnier_fx.view.component;

import fr.icom.info.m1.balleauprisonnier_fx.model.Game;
import fr.icom.info.m1.balleauprisonnier_fx.util.mvc.Subscriber;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class ScorePane extends BorderPane implements Subscriber {
	
	private Text scoreTeam1;
	private Text scoreTeam2;
	private Game game;
	
	public ScorePane(Game game, SimpleDoubleProperty frameRate) {
		scoreTeam1 = new Text();
		scoreTeam2 = new Text();
		Text fps = new Text();
		
		this.game = game;
		
		applyStyleForText(scoreTeam1);
		applyStyleForText(scoreTeam2);
		applyStyleForText(fps);
		
		fps.textProperty().bind(Bindings.concat(frameRate.asString("%.0f"), " fps"));
		
		setTop(scoreTeam1);
		setBottom(scoreTeam2);
		setRight(fps);
//		setBackground(new Background(new BackgroundFill(Color.BLUE, new CornerRadii(10), getInsets())));
	}


	private void applyStyleForText(Text text) {
		text.setFill(Color.WHITE);
		text.setStroke(Color.BLACK);
		text.setFont(Font.font("arial", FontWeight.EXTRA_BOLD, 30.));
		text.setStrokeWidth(0.5);
	}


	@Override
	public void update() {
		scoreTeam1.setText(String.format("Equipe 1 : joueurs restants : %d", game.getTeam1().getAlivePlayers().size()));
		scoreTeam2.setText(String.format("Equipe 2 : joueurs restants : %d", game.getTeam2().getAlivePlayers().size()));
	}
}