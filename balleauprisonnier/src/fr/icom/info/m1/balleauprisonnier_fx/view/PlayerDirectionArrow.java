package fr.icom.info.m1.balleauprisonnier_fx.view;

import fr.icom.info.m1.balleauprisonnier_fx.App;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;

public class PlayerDirectionArrow extends ImageView {
	private Image directionArrow;
	private GraphicsContext graphicsContext;
	private double angle;

	public void setAngle(double angle) {
		this.angle = angle;
	}

	public PlayerDirectionArrow(GraphicsContext graphicsContext) {
		this.graphicsContext = graphicsContext;
		directionArrow = App.ressourceLoader.fromRelativePath("PlayerArrowDown.png");
		setImage(directionArrow);
		setFitWidth(10);
		setPreserveRatio(true);
		setSmooth(true);
		setCache(true);
	}

	public void display() {
		if (isVisible()) {
			graphicsContext.save(); // saves the current state on stack, including the current transform
			rotate(graphicsContext, angle+270, getX() + directionArrow.getWidth() / 2, getY() + directionArrow.getHeight() / 2);
			graphicsContext.drawImage(directionArrow, getX(), getY());
			graphicsContext.restore(); // back to original state (before rotation)
		}
	}

	private void rotate(GraphicsContext gc, double angle, double px, double py) {
		Rotate r = new Rotate(angle, px, py);
		gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
	}
}
