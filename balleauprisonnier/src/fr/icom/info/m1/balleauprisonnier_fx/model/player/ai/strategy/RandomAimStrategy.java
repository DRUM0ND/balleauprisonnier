package fr.icom.info.m1.balleauprisonnier_fx.model.player.ai.strategy;

import fr.icom.info.m1.balleauprisonnier_fx.model.player.ai.AIPlayer;
import fr.icom.info.m1.balleauprisonnier_fx.util.strategy.Strategy;

public class RandomAimStrategy implements Strategy {
	
	private AIPlayer ai;
	private boolean rotationToLeft;
	
	public RandomAimStrategy(AIPlayer ai) {
		this.ai = ai;
	}

	@Override
	public void execute() {
		if (ai.probabilityToChange())
			rotationToLeft = !rotationToLeft;
		if (rotationToLeft) {
			//TODO remove hardcoded direction to top of the game, this wont work anymore if ai move to top of the game
			if(ai.getAngle() > 225)
				ai.turnLeft();
		} else {
			if(ai.getAngle() < 315)
				ai.turnRight();
		}
	}

}
