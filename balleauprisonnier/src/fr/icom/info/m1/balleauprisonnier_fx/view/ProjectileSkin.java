package fr.icom.info.m1.balleauprisonnier_fx.view;

import fr.icom.info.m1.balleauprisonnier_fx.App;
import fr.icom.info.m1.balleauprisonnier_fx.view.collidable.Collidable;
import javafx.geometry.BoundingBox;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class ProjectileSkin implements Repaintable, Collidable {

	private GraphicsContext graphicsContext;
	private Image sprite;
	private double x;
	private double y;
	private double width;
	private double height;

	public ProjectileSkin(GraphicsContext graphicsContext) {
		this.graphicsContext = graphicsContext;
		sprite = App.ressourceLoader.fromRelativePath("ball.png");
		//TODO remove hardcoded size
		this.width = 20;
		this.height = 20;
	}

	@Override
	public void repaint() {
		graphicsContext.drawImage(sprite, x, y, width, height);
	}

	public Image getSprite() {
		return this.sprite;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getWidth() {
		return this.width;
	}

	public double getHeight() {
		return this.height;
	}

	@Override
	public BoundingBox getBoundingBox() {
		return new BoundingBox(x, y, getWidth(), getHeight());
	}

}
