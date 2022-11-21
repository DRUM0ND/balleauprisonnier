package fr.icom.info.m1.balleauprisonnier_fx.view.collidable;

import fr.icom.info.m1.balleauprisonnier_fx.model.player.Player;
import fr.icom.info.m1.balleauprisonnier_fx.model.projectile.Projectile;

public interface CollisionVisitor {

	public void collideWithPlayer(Player player);

	public void collideWithProjectile(Projectile projectile);
}
