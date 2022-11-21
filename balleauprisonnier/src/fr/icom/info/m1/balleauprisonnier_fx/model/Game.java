package fr.icom.info.m1.balleauprisonnier_fx.model;

import java.util.HashSet;
import java.util.Set;

import fr.icom.info.m1.balleauprisonnier_fx.App;
import fr.icom.info.m1.balleauprisonnier_fx.model.command.PauseGameCommand;
import fr.icom.info.m1.balleauprisonnier_fx.model.player.HumanPlayer;
import fr.icom.info.m1.balleauprisonnier_fx.model.player.Player;
import fr.icom.info.m1.balleauprisonnier_fx.model.player.ai.AIPlayer;
import fr.icom.info.m1.balleauprisonnier_fx.model.player.ai.strategy.AutoAimStrategy;
import fr.icom.info.m1.balleauprisonnier_fx.model.player.ai.strategy.AutoMoveStrategy;
import fr.icom.info.m1.balleauprisonnier_fx.model.player.ai.strategy.AutoShootStrategy;
import fr.icom.info.m1.balleauprisonnier_fx.model.player.ai.strategy.RandomAimStrategy;
import fr.icom.info.m1.balleauprisonnier_fx.model.player.ai.strategy.RandomMoveStrategy;
import fr.icom.info.m1.balleauprisonnier_fx.model.player.ai.strategy.RandomShootStrategy;
import fr.icom.info.m1.balleauprisonnier_fx.model.projectile.Projectile;
import fr.icom.info.m1.balleauprisonnier_fx.util.mvc.Publisher;
import fr.icom.info.m1.balleauprisonnier_fx.util.mvc.Subscriber;

public class Game extends Publisher implements Subscriber {

	private Set<HumanPlayer> humanPlayers = new HashSet<>();
	private Set<AIPlayer> aiPlayers = new HashSet<>();
	private Team team1;
	private Team team2;
	private Projectile projectile;
	private App application;
	private boolean finished;
	
	public Game(Set<HumanPlayer> humanPlayers, Set<AIPlayer> aiPlayers, Team team1, Team team2, Projectile projectile, double speed, App application) {
		this.humanPlayers = humanPlayers;
		this.aiPlayers = aiPlayers;
		this.team1 = team1;
		this.team2 = team2;
		this.projectile = projectile;
		this.application = application;
		this.finished = false;
		setSpeed(speed);
		initPlayers();
		addListener();
	}
	
	private void addListener() {
		for (Player player : getPlayers()) {
			player.subscribe(this);
		}
		projectile.subscribe(this);
		team1.giveTheProjectile(projectile);
	}

	public Set<Player> getPlayers() {
		Set<Player> players = new HashSet<>();
		players.addAll(humanPlayers);
		players.addAll(aiPlayers);
		return players;
	}
	
	private boolean isGameWon() {
		return !team1.isAlive() || !team2.isAlive();
	}
	
	public Team getWinner() {
		if (!isFinished()) {
			return null;
		}
		if(team1.isAlive())
			return team1;
		return team2;
	}

	private void finishGame() {
		new PauseGameCommand(application.getGameTimer()).execute();
		this.finished = true;
		notifySubscribers();
	}
	
	public boolean isFinished() {
		return this.finished;
	}
	
	public void setSpeed(double speed) {
		for (Player player : getPlayers()) {
			player.setSpeed(speed);
		}
		projectile.setSpeed(speed*4);
	}
		

	private void initPlayers() {
		for (Player player : getPlayers()) {
			player.subscribe(this);
		}
	}
	
	private void removeDeadPlayers() {
		for (Player player : getPlayers()) {
			if (!player.isAlive()) {
				aiPlayers.remove(player);
				humanPlayers.remove(player);
			}
		}
	}
	
	public void nextFrame() {
		removeDeadPlayers();
		if (isGameWon()) {
			finishGame();
			return; // avoid executing game running code whereas game is finished
		}
		for (AIPlayer aiPlayer : aiPlayers) {
			aiPlayer.execute();
		}
		if (!projectile.isStucked()) {
			projectile.moveThrowDirection();
		}
	}

	@Override
	public void update() {
		notifySubscribers();
	}

	public void setAIStrategyToRandom() {
		for (AIPlayer aiPlayer : aiPlayers) {
			aiPlayer.setMoveStrategy(new RandomMoveStrategy(aiPlayer));
			aiPlayer.setAimStrategy(new RandomAimStrategy(aiPlayer));
			aiPlayer.setShootStrategy(new RandomShootStrategy(aiPlayer));
		}
	}

	public void setAIStrategyToAuto() {
		for (AIPlayer aiPlayer : aiPlayers) {
			aiPlayer.setMoveStrategy(new AutoMoveStrategy(aiPlayer, projectile));
			aiPlayer.setAimStrategy(new AutoAimStrategy(aiPlayer));
			aiPlayer.setShootStrategy(new AutoShootStrategy(aiPlayer));
		}
	}

	public Team getTeam1() {
		return team1;
	}
	
	public Team getTeam2() {
		return team2;
	}
}
