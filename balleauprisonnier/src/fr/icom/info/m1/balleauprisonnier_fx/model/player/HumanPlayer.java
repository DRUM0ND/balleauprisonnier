package fr.icom.info.m1.balleauprisonnier_fx.model.player;

import fr.icom.info.m1.balleauprisonnier_fx.model.Boundaries;
import fr.icom.info.m1.balleauprisonnier_fx.model.Team;
import fr.icom.info.m1.balleauprisonnier_fx.model.projectile.Projectile;

/**
 * 
 * Classe gerant un joueur
 *
 */
public class HumanPlayer extends Player {
	public HumanPlayer(double xInit, double yInit, double angle, double speed, boolean alive, Boundaries movementBoundaries, Team team, Projectile projectile) {
		super(xInit, yInit, angle, speed, alive, movementBoundaries, team, projectile);
	}
}
