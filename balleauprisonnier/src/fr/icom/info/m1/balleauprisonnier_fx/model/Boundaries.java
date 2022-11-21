package fr.icom.info.m1.balleauprisonnier_fx.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Boundaries {

	private double topLimit;
	private DoubleProperty rightLimit;
	private DoubleProperty bottomLimit;
	private double leftLimit;
	private double ratio;

	public Boundaries(double topLimit, double rightLimit, double bottomLimit, double leftLimit, double ratio) {
		this.topLimit = topLimit;
		this.rightLimit = new SimpleDoubleProperty(rightLimit);
		this.bottomLimit = new SimpleDoubleProperty(bottomLimit);
		this.leftLimit = leftLimit;
		this.ratio = ratio;
	}

	public double getTopLimit() {
		return topLimit + bottomLimit.doubleValue()*ratio;
	}

	public double getRightLimit() {
		return rightLimit.doubleValue() - rightLimit.doubleValue()*ratio;
	}
	
	public DoubleProperty rightLimitProperty() {
		return rightLimit;
	}

	public double getBottomLimit() {
		return bottomLimit.doubleValue() - bottomLimit.doubleValue()*ratio;
	}

	public double getLeftLimit() {
		return leftLimit + rightLimit.doubleValue()*ratio;
	}
	
	public boolean isPointInside(double x, double y) {
		return getLeftLimit() <= x && x <= getRightLimit()
				&& getBottomLimit() >= y && y >= getTopLimit();
	}

	public DoubleProperty bottomLimitProperty() {
		return bottomLimit;
	}
	
	public double getWidth() {
		return getRightLimit()-getLeftLimit();
	}
	
	public double getHeight() {
		return getBottomLimit()-getTopLimit();
	}
}
