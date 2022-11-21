package fr.icom.info.m1.balleauprisonnier_fx.model.player.ai;

import java.util.Random;

import fr.icom.info.m1.balleauprisonnier_fx.model.Boundaries;
import fr.icom.info.m1.balleauprisonnier_fx.model.Team;
import fr.icom.info.m1.balleauprisonnier_fx.model.player.Player;
import fr.icom.info.m1.balleauprisonnier_fx.model.projectile.Projectile;
import fr.icom.info.m1.balleauprisonnier_fx.util.strategy.Context;
import fr.icom.info.m1.balleauprisonnier_fx.util.strategy.Strategy;

public class AIPlayer extends Player {
	
	private Context moveContext;
	private Context aimContext;
	private Context shootContext;
	private Random random;
	
	public AIPlayer(double xInit, double yInit, double angle, double speed, boolean alive, Boundaries boundaries, Team team, Projectile projectile) {
		super(xInit, yInit, angle, speed, alive, boundaries, team, projectile);
		this.moveContext = new Context();
		this.aimContext = new Context();
		this.shootContext = new Context();
		this.random = new Random();
	}
	
	public void setMoveStrategy(Strategy moveStrategy) {
		moveContext.setStrategy(moveStrategy);
	}
	
	public void setAimStrategy(Strategy aimStrategy) {
		aimContext.setStrategy(aimStrategy);
	}
	
	public void setShootStrategy(Strategy shootStrategy) {
		shootContext.setStrategy(shootStrategy);
	}
	
	public void execute() {
		moveContext.execute();
		aimContext.execute();
		shootContext.execute();
	}
	
	public boolean probabilityToChange() {
		return 0 == random.nextInt(100);
	}
}
