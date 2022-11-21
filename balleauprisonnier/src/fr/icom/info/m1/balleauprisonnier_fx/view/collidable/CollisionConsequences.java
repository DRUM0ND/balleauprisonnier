package fr.icom.info.m1.balleauprisonnier_fx.view.collidable;

public interface CollisionConsequences {

	public abstract CollisionVisitor getCollisionVisitor();

	public abstract void youHaveCollidedA(CollisionVisitor collisionVisitor);

}
