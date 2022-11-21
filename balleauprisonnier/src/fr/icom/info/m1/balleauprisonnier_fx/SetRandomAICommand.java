package fr.icom.info.m1.balleauprisonnier_fx;

import fr.icom.info.m1.balleauprisonnier_fx.model.Game;
import fr.icom.info.m1.balleauprisonnier_fx.model.command.Command;

public class SetRandomAICommand implements Command {

	private Game game;
	public SetRandomAICommand(Game game) {
		this.game = game;
	}

	@Override
	public void handle() {
		game.setAIStrategyToRandom();
	}

}
