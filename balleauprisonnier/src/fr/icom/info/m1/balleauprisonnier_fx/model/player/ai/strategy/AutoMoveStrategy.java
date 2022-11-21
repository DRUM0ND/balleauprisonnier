package fr.icom.info.m1.balleauprisonnier_fx.model.player.ai.strategy;

import fr.icom.info.m1.balleauprisonnier_fx.model.player.ai.AIPlayer;
import fr.icom.info.m1.balleauprisonnier_fx.model.projectile.Projectile;
import fr.icom.info.m1.balleauprisonnier_fx.util.strategy.Strategy;

public class AutoMoveStrategy implements Strategy {
	
	private AIPlayer ai;
	private boolean goLeft = true;
	private Projectile requiredProjectile;
	
	public AutoMoveStrategy(AIPlayer ai, Projectile requiredProjectile) {
		if(requiredProjectile == null)
			throw new IllegalArgumentException("trying to create AutoMoveStrategy with requiredProjectile as null but it is required");
		this.requiredProjectile = requiredProjectile;
		this.ai = ai;
	}

	@Override
	public void execute() {
		if(projectileCanBePickedUp())
			pickUpProjectile();
		else
			autoDisplacement();
	}
	
	private boolean projectileCanBePickedUp() {
		return Math.abs(requiredProjectile.getY()-ai.getY())<75 && requiredProjectile.isStucked() && !ai.hasTheBall();
	}
	
	private void pickUpProjectile() {
		if (requiredProjectile.getX() < ai.getX())
			ai.moveLeft();
		else
			ai.moveRight();
	}
	
	private void autoDisplacement() {
		if(ai.getX() < ai.getBoundaries().getLeftLimit() + ai.getMoveSpeed())
			goLeft = false;
		if(ai.getX() > ai.getBoundaries().getRightLimit() - ai.getMoveSpeed())
			goLeft = true;
		if (goLeft)
			ai.moveLeft();
		else
			ai.moveRight();
	}
}
