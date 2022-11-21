package fr.icom.info.m1.balleauprisonnier_fx.model.player.ai.strategy;

import fr.icom.info.m1.balleauprisonnier_fx.model.player.ai.AIPlayer;
import fr.icom.info.m1.balleauprisonnier_fx.util.strategy.Strategy;

public class RandomMoveStrategy implements Strategy {
	
	private AIPlayer ai;
	private boolean displacement;
	private boolean displacementToLeft;
	
	public RandomMoveStrategy(AIPlayer ai) {
		this.ai = ai;
		this.displacement = false;
		this.displacementToLeft = true;
	}

	@Override
	public void execute() {
		if (ai.probabilityToChange())
			displacement = !displacement;
		if (ai.probabilityToChange())
			displacementToLeft = !displacementToLeft;
		
		if (displacement) {
			if (displacementToLeft)
				ai.moveLeft();
			else
				ai.moveRight();
		}
	}

}
