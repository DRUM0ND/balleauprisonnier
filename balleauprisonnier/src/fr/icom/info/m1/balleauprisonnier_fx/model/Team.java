package fr.icom.info.m1.balleauprisonnier_fx.model;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import fr.icom.info.m1.balleauprisonnier_fx.model.player.Player;
import fr.icom.info.m1.balleauprisonnier_fx.model.projectile.Projectile;

public class Team {
	private String name;
	private Set<Player> players;
	private Team ennemyTeam;

	public Team(String name) {
		this.name = name;
		players = new HashSet<>();
		ennemyTeam = null;
	}

	public Set<Player> getPlayers() {
		return players;
	}

	public void addPlayer(Player player) {
		players.add(player);
	}

	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		// redefined hashcode so we can use this class in a set properly
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	
	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Team other = (Team) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}



	public Team getEnnemyTeam() {
		if (ennemyTeam == null) {
			throw new IllegalStateException("Asked for ennemy team but it was not defined");
		}
		return ennemyTeam;
	}
	
	public void setEnnemyTeam(Team ennemyTeam) {
		this.ennemyTeam = ennemyTeam;
	}
	
	
	public void giveTheProjectile(Projectile projectile) {
		Player player = getRandomAlivePlayer();
		player.setProjectile(projectile);
	}
	
	public boolean isAlive() {
		return ! getAlivePlayers().isEmpty();
	}

	public Player getRandomAlivePlayer() {
		Random random = new Random();
		Set<Player> alivePlayers = getAlivePlayers();
		if (alivePlayers.isEmpty()) {
			throw new IllegalStateException("err002 trying to give the ball to a team but everyone is dead");
		}
		int res = random.nextInt(alivePlayers.size());
		return (Player) alivePlayers.toArray()[res];
	}
	
	
	
	public Set<Player> getAlivePlayers() {
		Set<Player> alivePlayers = new HashSet<>();
		for (Player player : players) {
			if (player.isAlive())
				alivePlayers.add(player);
		}
		return alivePlayers;
	}
	
	public boolean aPlayerHasTheBall() {
		for (Player player : getAlivePlayers()) {
			if (player.hasTheBall())
				return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
