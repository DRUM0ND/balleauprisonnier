package fr.icom.info.m1.balleauprisonnier_fx.view.collidable;

import fr.icom.info.m1.balleauprisonnier_fx.model.player.Player;
import fr.icom.info.m1.balleauprisonnier_fx.model.projectile.Projectile;

public class PlayerCollisionVisitor implements CollisionVisitor {

	private Player player;

	public PlayerCollisionVisitor(Player player) {
		this.player = player;
	}

	@Override
	public void collideWithPlayer(Player player) {
		// do nothing
	}

	@Override
	public void collideWithProjectile(Projectile projectile) {
//		when a projectile hit a player, a player also hit a projectile
//		all the treatment is in ProjectileCollisionVisitor because we can't do treatment separatly
	}

}
