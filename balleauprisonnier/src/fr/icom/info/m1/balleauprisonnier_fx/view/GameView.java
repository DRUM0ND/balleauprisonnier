package fr.icom.info.m1.balleauprisonnier_fx.view;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import fr.icom.info.m1.balleauprisonnier_fx.controler.PlayerController;
import fr.icom.info.m1.balleauprisonnier_fx.model.player.Player;
import fr.icom.info.m1.balleauprisonnier_fx.model.projectile.Projectile;
import fr.icom.info.m1.balleauprisonnier_fx.util.mvc.Subscriber;
import fr.icom.info.m1.balleauprisonnier_fx.view.collidable.Collidable;
import fr.icom.info.m1.balleauprisonnier_fx.view.collidable.CollisionConsequences;
import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Pair;

public class GameView extends Group implements Subscriber {
	
	private Set<KeyCode> inputs;

	private Set<Collidable> collidables = new HashSet<>();
	/**
	 * do the link between collidable (view) and the collisionConsequences (model)
	 * so when two collidable collide the consequences of the collision can be applied in model
	 */
	private Map<Collidable, CollisionConsequences> collidable2collisionConsequences;
	private Map<Player, PlayerSkin> player2playerSkin;
	private Set<Collidable> collidableToRemove;
	private Set<PlayerController> playerControllers;
	private Collection<PlayerSkin> playerSkins;

	private Set<KeyCode> alreadyPressedKey = new HashSet<>();
	private Projectile projectile;
	private ProjectileSkin projectileSkin;
	private Field field;

	public GameView(Field field, Map<Collidable, CollisionConsequences> collidable2collisionConsequences, Map<Player, PlayerSkin> player2playerSkin, Pair<Projectile, ProjectileSkin> projectile2projectileSkin, Set<PlayerController> playerControllers) {
		this.inputs = new HashSet<>();
		this.collidable2collisionConsequences = collidable2collisionConsequences;
		this.player2playerSkin = player2playerSkin;
		this.collidableToRemove = new HashSet<>();
		this.playerSkins = player2playerSkin.values();
		this.field = field;
		getChildren().add(field);
		this.playerControllers = playerControllers;
		
		this.projectile = projectile2projectileSkin.getKey();
		this.projectileSkin = projectile2projectileSkin.getValue();
		initCollidable();
		addPlayersToField();

		this.setOnKeyPressed(this::handleOnKeyPressed);
		this.setOnKeyReleased(this::handleOnKeyRealeased);
	}
	
	private void handleOnKeyPressed(KeyEvent event) {
		KeyCode code = event.getCode();
		inputs.add(code);
	}
	
	private void handleOnKeyRealeased(KeyEvent event)
	{
		KeyCode code = event.getCode();
		alreadyPressedKey.remove(code);
		inputs.remove(code);
	}

	private void initCollidable() {
		for (Collidable playerSkin : playerSkins) {
			collidables.add(playerSkin);
		}
		collidables.add(projectileSkin);
	}

	private void addPlayersToField() {
		for (PlayerSkin playerSkin : playerSkins) {
			getChildren().add(playerSkin.getSprite().asImageView());
		}
	}
	
	private void applyMovement(Player player) {
		if (alreadyPressedKey.contains(KeyCode.SPACE))
			inputs.remove(KeyCode.SPACE);
		for (PlayerController playerController : playerControllers) {
			for (KeyCode input : inputs) {
				playerController.process(input);
			}
		}
		alreadyPressedKey.addAll(inputs);
	}


	
	private void applyCollisionConsequences() {
		for (Collidable collidable : collidables) {
			for (Collidable collidable2 : collidables) {
//				to debug the bounding box
//				field.getGraphicsContext2D().setFill(Color.BLUE);
//	            field.getGraphicsContext2D().fillRect(collidable.getBoundingBox().getMinX(),collidable.getBoundingBox().getMinY(),collidable.getBoundingBox().getWidth(),collidable.getBoundingBox().getHeight());
				if (collidable.getBoundingBox().intersects(collidable2.getBoundingBox()) && !collidable.equals(collidable2)) {
					collidable2collisionConsequences.get(collidable).youHaveCollidedA(collidable2collisionConsequences.get(collidable2).getCollisionVisitor());
				}
			}
		}
		for (Collidable collidable : collidableToRemove) {
			collidables.remove(collidable);
			collidable2collisionConsequences.remove(collidable);
		}
		collidableToRemove.clear();
	}
	
	public void nextFrame() {
		field.clearField();
		applyCollisionConsequences();
		for (Player player : player2playerSkin.keySet()) {
			applyMovement(player);
		}
		for (PlayerSkin playerSkin : playerSkins) {
			playerSkin.getPlayerDirectionArrow().display();
		}
		projectileSkin.repaint();
	}


	@Override
	public void update() {
		removeDeadPlayers();
		updatePlayerPosition();
		handleProjectile();
	}

	private void removeDeadPlayers() {
		for (Player player : player2playerSkin.keySet()) {
			if (!player.isAlive()) {
				player2playerSkin.get(player).setAlive(false);
				collidableToRemove.add(player2playerSkin.get(player));
			}
		}
	}

	private void updatePlayerPosition() {
		for (Player player : player2playerSkin.keySet()) {
			player2playerSkin.get(player).setX(player.getX());
			player2playerSkin.get(player).setY(player.getY());
			player2playerSkin.get(player).getPlayerDirectionArrow().setX(player.getX());
			player2playerSkin.get(player).getPlayerDirectionArrow().setY(player.getY());
			player2playerSkin.get(player).getPlayerDirectionArrow().setAngle(player.getAngle());
			player2playerSkin.get(player).updateSpritePosition();
		}
	}

	private void handleProjectile() {
		projectileSkin.setX(projectile.getX());
		projectileSkin.setY(projectile.getY());
	}
}