package fr.icom.info.m1.balleauprisonnier_fx.model.player.ai.strategy;

import fr.icom.info.m1.balleauprisonnier_fx.model.player.ai.AIPlayer;
import fr.icom.info.m1.balleauprisonnier_fx.util.strategy.Strategy;

public class AutoShootStrategy implements Strategy {
	
	private AIPlayer ai;
	
	public AutoShootStrategy(AIPlayer ai) {
		this.ai = ai;
	}

	@Override
	public void execute() {
		if(ai.hasTheBall()) {
			ai.shoot();
		}
	}

}
