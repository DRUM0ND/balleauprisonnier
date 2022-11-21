package fr.icom.info.m1.balleauprisonnier_fx.model.player.ai.strategy;

import fr.icom.info.m1.balleauprisonnier_fx.model.player.Player;
import fr.icom.info.m1.balleauprisonnier_fx.model.player.ai.AIPlayer;
import fr.icom.info.m1.balleauprisonnier_fx.util.strategy.Strategy;

public class AutoAimStrategy implements Strategy {
	
	private AIPlayer ai;
	
	public AutoAimStrategy(AIPlayer ai) {
		this.ai = ai;
	}

	@Override
	public void execute() {
		Player ennemy = ai.getTeam().getEnnemyTeam().getRandomAlivePlayer();
		double deltaX = ennemy.getX() - ai.getX();
		double deltaY = ennemy.getY() - ai.getY();
		double theta = Math.abs(360-Math.toDegrees(Math.atan2(deltaX, deltaY)))%360;
		if (theta < 0) 
			theta = 360 + theta; //range between 0 and 360
		theta+=90;
		if (Math.abs(theta-ai.getAngle()) >= ai.getRotationSpeed()) { // avoid arrow shaking because of angle rounded to int
			if (theta < ai.getAngle())
				ai.turnLeft();
			else
				ai.turnRight();
		}

	}

}
