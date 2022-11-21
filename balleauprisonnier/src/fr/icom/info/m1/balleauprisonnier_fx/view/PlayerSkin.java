package fr.icom.info.m1.balleauprisonnier_fx.view;

import fr.icom.info.m1.balleauprisonnier_fx.view.collidable.Collidable;
import fr.icom.info.m1.balleauprisonnier_fx.view.skin_variation.SkinVariation;
import fr.icom.info.m1.balleauprisonnier_fx.view.skin_variation.SkinVariationCreator;
import javafx.geometry.BoundingBox;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class PlayerSkin implements Collidable {
	private PlayerDirectionArrow playerDirectionArrow;
	private PlayerSprite playerSprite;
	private double x;
	private double y;

	public PlayerSkin(GraphicsContext graphicsContext, String side, SkinVariation skinVariation) {
		this.playerDirectionArrow = new PlayerDirectionArrow(graphicsContext);

		Image tilesheetImage = new SkinVariationCreator(skinVariation).getTileSheetImage();
		playerSprite = new PlayerSprite(tilesheetImage, Duration.seconds(.2), side);
		x = 0;
		y = 0;
	}

	public void updateSpritePosition() {
		playerSprite.setX(x);
		playerSprite.setY(y);
	}

	public void shootAnimation() {
		playerSprite.shoot();
	}

	public PlayerDirectionArrow getPlayerDirectionArrow() {
		return this.playerDirectionArrow;
	}

	public PlayerSprite getSprite() {
		return this.playerSprite;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	@Override
	public BoundingBox getBoundingBox() {
		return new BoundingBox(x, y, getSprite().getWidth(), getSprite().getHeight());
	}

	public void setAlive(boolean alive) {
		getSprite().setVisible(alive);
		getPlayerDirectionArrow().setVisible(alive);
	}
}