package fr.icom.info.m1.balleauprisonnier_fx.model.projectile;

import fr.icom.info.m1.balleauprisonnier_fx.model.Boundaries;
import fr.icom.info.m1.balleauprisonnier_fx.model.Team;
import fr.icom.info.m1.balleauprisonnier_fx.util.mvc.Publisher;
import fr.icom.info.m1.balleauprisonnier_fx.view.collidable.CollisionConsequences;
import fr.icom.info.m1.balleauprisonnier_fx.view.collidable.CollisionVisitor;
import fr.icom.info.m1.balleauprisonnier_fx.view.collidable.ProjectileCollisionVisitor;

public class Projectile extends Publisher implements CollisionConsequences {
	private double direction;
	private double speed;
	private double x;
	private double y;
	private boolean stuck = true;
	private Boundaries boundaries;

	private Team ownerTeam;

	public Projectile(double x, double y, double direction, double speed, Boundaries boundaries, Team ownerTeam) {
		this.speed = speed;
		this.x = x;
		this.y = y;
		this.direction = direction;
		this.boundaries = boundaries;
		this.ownerTeam = ownerTeam;
	}

	public Projectile(Boundaries boundaries, double speed) {
		this.x = 0;
		this.y = 0;
		this.direction = 0;
		this.ownerTeam = null;
		this.boundaries = boundaries;
		this.speed = speed;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
		notifySubscribers();
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
		notifySubscribers();
	}

	private double getDeltaX() {
		return Math.cos(Math.toRadians(direction));

	}

	private double getDeltaY() {
		return Math.sin(Math.toRadians(direction));
	}

	private boolean verifyIfInBoundaries() {
		boolean outOfBoundaries = ! boundaries.isPointInside(x, y);
		if (outOfBoundaries) {
			this.stuck = true;
		}
		if (x > boundaries.getRightLimit()-speed) {
			this.direction = Math.toDegrees(mirrorYAngle(direction));
			setX(getX()-speed);
		}
		if (x < boundaries.getLeftLimit()+speed) {
			this.direction = Math.toDegrees(mirrorYAngle(direction));
			setX(getX()+speed);
		}
		return this.stuck;

	}
	
	private double mirrorYAngle(double angleInDegree) {
		double angle = Math.toRadians(angleInDegree);
		if(Math.sin(angle) > 0)
			return Math.acos(-Math.cos(angle));
		else {
			return Math.acos(Math.cos(angle)) + Math.PI;
		}
			
	}

	public boolean isStucked() {
		return this.stuck;
	}

	public void moveThrowDirection() {
		if (!isStucked()) {
			verifyIfInBoundaries();
			setX(getX() + getDeltaX() * speed);
			setY(getY() + getDeltaY() * speed);
		}
		notifySubscribers();
	}
	
	public void unverifiedMoveThrowDirection(int step) {
		setX(getX() + getDeltaX() * step);
		setY(getY() + getDeltaY() * step);
		notifySubscribers();
	}

	public void setStuck(boolean stuck) {
		this.stuck = stuck;
	}

	public void setDirection(double direction) {
		this.direction = direction;
	}

	public Team getOwnerTeam() {
		return this.ownerTeam;
	}
	
	public void setOwnerTeam(Team team) {
		this.ownerTeam = team;
	}

	@Override
	public CollisionVisitor getCollisionVisitor() {
		return new ProjectileCollisionVisitor(this);
	}

	@Override
	public void youHaveCollidedA(CollisionVisitor collisionVisitor) {
		collisionVisitor.collideWithProjectile(this);
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}
}