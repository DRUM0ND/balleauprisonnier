package fr.icom.info.m1.balleauprisonnier_fx.view.component;

import fr.icom.info.m1.balleauprisonnier_fx.SetAutoAICommand;
import fr.icom.info.m1.balleauprisonnier_fx.SetRandomAICommand;
import fr.icom.info.m1.balleauprisonnier_fx.model.Game;
import fr.icom.info.m1.balleauprisonnier_fx.model.command.Command;
import fr.icom.info.m1.balleauprisonnier_fx.model.command.ExitCommand;
import fr.icom.info.m1.balleauprisonnier_fx.model.command.PauseGameCommand;
import fr.icom.info.m1.balleauprisonnier_fx.model.command.StartGameCommand;
import fr.icom.info.m1.balleauprisonnier_fx.view.GameTimer;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class GameMenuBar extends MenuBar {
	
	public GameMenuBar(Game game, GameTimer gameTimer) {
		super();
		Menu gameMenu = new Menu("Game");
		MenuItem startMenuItem = new MenuItem("Start");
		MenuItem pauseMenuItem = new MenuItem("Pause");
		MenuItem quitMenuItem = new MenuItem("Quit");
		
		Command startCommand = new StartGameCommand(gameTimer);
		startMenuItem.setOnAction(startCommand);
		Command pauseCommand = new PauseGameCommand(gameTimer);
		pauseMenuItem.setOnAction(pauseCommand);
		Command exitCommand = new ExitCommand();
		quitMenuItem.setOnAction(exitCommand);
		
		gameMenu.getItems().addAll(startMenuItem, pauseMenuItem, quitMenuItem);
		
		
		Menu aiMenu = new Menu("AI");
		MenuItem randomAIMenuItem = new MenuItem("Random AI");
		MenuItem autoAIMenuItem = new MenuItem("Auto AI");
		
		Command setRandomAICommand = new SetRandomAICommand(game);
		randomAIMenuItem.setOnAction(setRandomAICommand);
		Command setAutoAICommand = new SetAutoAICommand(game);
		autoAIMenuItem.setOnAction(setAutoAICommand);
		
		aiMenu.getItems().addAll(randomAIMenuItem, autoAIMenuItem);

		
		getMenus().addAll(gameMenu, aiMenu);
	}

}
