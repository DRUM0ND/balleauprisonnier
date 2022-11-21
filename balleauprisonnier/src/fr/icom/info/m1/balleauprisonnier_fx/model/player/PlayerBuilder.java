package fr.icom.info.m1.balleauprisonnier_fx.model.player;

import java.util.Random;

import fr.icom.info.m1.balleauprisonnier_fx.model.Boundaries;
import fr.icom.info.m1.balleauprisonnier_fx.model.Team;
import fr.icom.info.m1.balleauprisonnier_fx.model.player.ai.AIPlayer;
import fr.icom.info.m1.balleauprisonnier_fx.model.player.ai.strategy.AutoAimStrategy;
import fr.icom.info.m1.balleauprisonnier_fx.model.player.ai.strategy.AutoMoveStrategy;
import fr.icom.info.m1.balleauprisonnier_fx.model.player.ai.strategy.AutoShootStrategy;
import fr.icom.info.m1.balleauprisonnier_fx.model.player.ai.strategy.RandomAimStrategy;
import fr.icom.info.m1.balleauprisonnier_fx.model.player.ai.strategy.RandomMoveStrategy;
import fr.icom.info.m1.balleauprisonnier_fx.model.player.ai.strategy.RandomShootStrategy;
import fr.icom.info.m1.balleauprisonnier_fx.model.projectile.Projectile;

public class PlayerBuilder {
	private double x;
	private double y;
	private double angle;
	private double speed;
	private Boundaries boundaries;
	private boolean alive;
	private Projectile projectile;
	private Projectile requiredProjectile;
	private Team team;
	public PlayerBuilder() {
		this.x = 0;
		this.y = 0;
		this.angle = 0;
		this.speed = 1;
		this.boundaries = null;
		this.alive = true;
		this.projectile = null;
		this.team = null;
	}
	
	public PlayerBuilder setX(double x) {
		this.x = x;
		return this;
	}
	
	public PlayerBuilder setY(double y) {
		this.y = y;
		return this;
	}
	
	public PlayerBuilder setAngle(double angle) {
		this.angle = angle;
		return this;
	}
	
	public PlayerBuilder setSpeed(double speed) {
		this.speed = speed;
		return this;
	}
	
	public PlayerBuilder setRandomSpeed() {
		Random randomGenerator = new Random();
		this.speed = randomGenerator.nextFloat();
		return this;
	}
	
	public PlayerBuilder setBoundaries(Boundaries boundaries) {
		this.boundaries = boundaries;
		return this;
	}
	
	public PlayerBuilder setAlive(boolean alive) {
		this.alive = alive;
		return this;
	}
	
	public PlayerBuilder setProjectile(Projectile projectile) {
		this.projectile = projectile;
		return this;
	}
	
	public PlayerBuilder setTeam(Team team) {
		this.team = team;
		return this;
	}
	
	public PlayerBuilder setRequiredProjectile(Projectile requiredProjectile) {
		this.requiredProjectile = requiredProjectile;
		return this;
	}
	
	
	public Player buildPlayer() {
		return new Player(x, y, angle, speed, alive, boundaries, team, projectile);
	}
	
	public HumanPlayer buildHumanPlayer() {
		return new HumanPlayer(x, y, angle, speed, alive, boundaries, team, projectile);
	}
	
	public AIPlayer buildAutoAIPlayer() {
		AIPlayer aiPlayer =  new AIPlayer(x, y, angle, speed, alive, boundaries, team, projectile);
		aiPlayer.setMoveStrategy(new AutoMoveStrategy(aiPlayer, requiredProjectile));
		aiPlayer.setAimStrategy(new AutoAimStrategy(aiPlayer));
		aiPlayer.setShootStrategy(new AutoShootStrategy(aiPlayer));
		return aiPlayer;
	}
	
	public AIPlayer buildRandomAIPlayer() {
		AIPlayer aiPlayer =  new AIPlayer(x, y, angle, speed, alive, boundaries, team, projectile);
		aiPlayer.setMoveStrategy(new RandomMoveStrategy(aiPlayer));
		aiPlayer.setAimStrategy(new RandomAimStrategy(aiPlayer));
		aiPlayer.setShootStrategy(new RandomShootStrategy(aiPlayer));
		return aiPlayer;
	}
	
}
