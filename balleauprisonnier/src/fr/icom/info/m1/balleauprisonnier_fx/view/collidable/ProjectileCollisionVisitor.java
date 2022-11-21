package fr.icom.info.m1.balleauprisonnier_fx.view.collidable;

import fr.icom.info.m1.balleauprisonnier_fx.model.player.Player;
import fr.icom.info.m1.balleauprisonnier_fx.model.projectile.Projectile;

public class ProjectileCollisionVisitor implements CollisionVisitor {

	private Projectile projectile;

	public ProjectileCollisionVisitor(Projectile projectile) {
		this.projectile = projectile;
	}

	// what append if a projectile collide a ...
	@Override
	public void collideWithPlayer(Player player) {
		if (player.getTeam().equals(projectile.getOwnerTeam())) {
			if (projectile.isStucked() && !player.getTeam().aPlayerHasTheBall())
				player.setProjectile(projectile);
		} else {
			if (projectile.isStucked()) {
				projectile.setOwnerTeam(projectile.getOwnerTeam().getEnnemyTeam());
				player.setProjectile(projectile);
			} else {
				player.setAlive(false);
				projectile.setOwnerTeam(projectile.getOwnerTeam().getEnnemyTeam());
				try {
					projectile.getOwnerTeam().giveTheProjectile(projectile);
				} catch (IllegalStateException e) {
					//in the end of the game all peaple in a team are dead, so we doesn't give the ball
					// the game will be ended
				}
			}
		}
			
	}


	@Override
	public void collideWithProjectile(Projectile projectile) {
		// do nothing
	}

}
