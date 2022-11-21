package fr.icom.info.m1.balleauprisonnier_fx.model.player;

import fr.icom.info.m1.balleauprisonnier_fx.model.Boundaries;
import fr.icom.info.m1.balleauprisonnier_fx.model.Team;
import fr.icom.info.m1.balleauprisonnier_fx.model.projectile.Projectile;
import fr.icom.info.m1.balleauprisonnier_fx.util.mvc.Publisher;
import fr.icom.info.m1.balleauprisonnier_fx.view.collidable.CollisionConsequences;
import fr.icom.info.m1.balleauprisonnier_fx.view.collidable.CollisionVisitor;
import fr.icom.info.m1.balleauprisonnier_fx.view.collidable.PlayerCollisionVisitor;

public class Player extends Publisher implements CollisionConsequences {

	protected double x;
	protected final double y;
	protected double angle;
	protected double moveSpeed;
	protected double rotationSpeed;
	protected Boundaries boundaries;
	protected boolean alive;
	protected Projectile projectile;
	protected Team team;

	public Player(double xInit, double yInit, double angle, double speed, boolean alive, Boundaries boundaries, Team team, Projectile projectile) {
		this.x = xInit;
		this.y = yInit;
		this.angle = angle;
		this.moveSpeed = speed;
		this.rotationSpeed = speed/2;
		this.boundaries = boundaries;
		
		this.team = team;
		team.addPlayer(this);
		
		this.alive = alive;
		this.projectile = projectile;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getAngle() {
		return angle;
	}

	private void setX(double x) {
		this.x = x;
		if (projectile != null)
			this.projectile.setX(x);
	}

	public void moveLeft() {
		if (x > boundaries.getLeftLimit() + 1) {
			setX(x - moveSpeed);
		}
		notifySubscribers();
	}

	public void moveRight() {
		if (x < boundaries.getRightLimit() - 1) {
			setX(x + moveSpeed);
		}
		notifySubscribers();
	}

	public void turnRight() {
		angle += rotationSpeed;
		if (angle > 360)
			angle = angle%360;
		notifySubscribers();
	}

	public void turnLeft() {
		angle -= rotationSpeed;
		if (angle < 0)
			angle = 360-angle;
		notifySubscribers();
	}

	public Projectile getProjectile() {
		return this.projectile;
	}

	public Team getTeam() {
		return this.team;
	}

	public boolean isAlive() {
		return this.alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
		notifySubscribers();
	}

	public void shoot() {
		if (hasTheBall()) {
			projectile.setDirection(angle);
			projectile.setStuck(false);
		}
		this.projectile = null;
		notifySubscribers();
	}

	public void setProjectile(Projectile projectile) {
		if (projectile == null) {
			this.projectile = null;
		} else {
			this.projectile = projectile;
			this.projectile.setX(x);
			this.projectile.setY(y);
			this.projectile.setOwnerTeam(team);
		}
	}
	
	public boolean hasTheBall() {
		return projectile != null;
	}
	
	//TODO revoir l'ordre des méthodes de tous les fichiers
	public Boundaries getBoundaries() {
		return this.boundaries;
	}
	
	public double getMoveSpeed() {
		return this.moveSpeed;
	}
	
	public double getRotationSpeed() {
		return this.rotationSpeed;
	}

	@Override
	public CollisionVisitor getCollisionVisitor() {
		return new PlayerCollisionVisitor(this);
	}

	@Override
	public void youHaveCollidedA(CollisionVisitor collisionVisitor) {
		collisionVisitor.collideWithPlayer(this);

	}

	public void setSpeed(double speed) {
		this.moveSpeed = speed;
	}

}