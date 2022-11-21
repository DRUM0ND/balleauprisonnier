package fr.icom.info.m1.balleauprisonnier_fx.model.player.ai.strategy;

import fr.icom.info.m1.balleauprisonnier_fx.model.player.ai.AIPlayer;
import fr.icom.info.m1.balleauprisonnier_fx.util.strategy.Strategy;

public class RandomShootStrategy implements Strategy {
	
	private AIPlayer ai;
	
	public RandomShootStrategy(AIPlayer ai) {
		this.ai = ai;
	}

	@Override
	public void execute() {
		if (ai.probabilityToChange()) {
			ai.shoot();
		}
	}

}
