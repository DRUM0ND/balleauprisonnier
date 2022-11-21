package fr.icom.info.m1.balleauprisonnier_fx.view;

import fr.icom.info.m1.balleauprisonnier_fx.model.Boundaries;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

/**
 * Classe gerant le terrain de jeu.
 * 
 */
public class Field extends Canvas {

	private Boundaries boundaries;
	private int thickness;
	private double middleX;
	private double middleY;
	
	public Field(double w, double h, Boundaries boundaries) {
		super(w, h);
		/** permet de capturer le focus et donc les evenements clavier et souris */
		this.setFocusTraversable(true);
		this.boundaries = boundaries;
	}
	
	public Field(Boundaries boundaries) {
		setFocusTraversable(true);
		this.boundaries = boundaries;
	}

	public void clearField() {
		this.thickness = 10;
		this.middleX = (boundaries.getLeftLimit()+boundaries.getRightLimit()-thickness+64)/2;
		this.middleY = (boundaries.getTopLimit()+boundaries.getBottomLimit()-thickness+64)/2;
		paintNeutralBackground();
		paintGrass();
		paintExternalLine();
		
		paintCircle();
		
		getGraphicsContext2D().setFill(Color.BLACK);
		getGraphicsContext2D().fillRect(boundaries.getLeftLimit(), middleY-thickness, boundaries.getWidth()+64, thickness);//middle
		
	}

	private void paintCircle() {
		double circleWidth = boundaries.getHeight()/4;
		getGraphicsContext2D().setFill(Color.BLACK);
		getGraphicsContext2D().fillOval(middleX-(circleWidth+thickness*2)/2, middleY-(circleWidth+thickness*2)/2, circleWidth, circleWidth);
		getGraphicsContext2D().setFill(Color.LIGHTGREEN);
		getGraphicsContext2D().fillOval(middleX-circleWidth/2, middleY-circleWidth/2, circleWidth-thickness*2, circleWidth-thickness*2);
	}

	private void paintExternalLine() {
		getGraphicsContext2D().setFill(Color.BLACK);
		getGraphicsContext2D().fillRect(boundaries.getLeftLimit(), boundaries.getTopLimit(), boundaries.getWidth()+64, thickness); //left 
		getGraphicsContext2D().fillRect(boundaries.getLeftLimit(), boundaries.getTopLimit(), thickness, boundaries.getHeight()+64);//right
		getGraphicsContext2D().fillRect(boundaries.getLeftLimit(), boundaries.getBottomLimit()-thickness+64, boundaries.getWidth()+64, thickness);//bottom
		getGraphicsContext2D().fillRect(boundaries.getRightLimit()-thickness+64, boundaries.getTopLimit(), thickness, boundaries.getHeight()+64);//top
	}

	private void paintGrass() {
		getGraphicsContext2D().setFill(Color.LIGHTGREEN);
		//TODO remove hardcoded 64 player size
		getGraphicsContext2D().fillRect(boundaries.getLeftLimit(), boundaries.getTopLimit(), boundaries.getWidth()+64, boundaries.getHeight()+64);
	}

	private void paintNeutralBackground() {
		getGraphicsContext2D().setFill(Color.LIGHTGRAY);
		getGraphicsContext2D().fillRect(0, 0, super.getWidth(), super.getHeight());
	}
}
