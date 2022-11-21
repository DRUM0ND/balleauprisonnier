package fr.icom.info.m1.balleauprisonnier_fx.view;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class PlayerSprite {

	private int widthWalkAnimationLine;

	private TimelineAnimatedImage currentTimelineAnimatedImage;
	private AnimationPosition walk;
	private AnimationPosition shoot;

	private int numCellShoot = 13;
	
	private double width;
	private double height;

	public PlayerSprite(Image animationImage, Duration frameTime, String side) {
		final double cellWidth = 64;
		final double cellHeight = 64;

		widthWalkAnimationLine = 9;

		int lineWhereTheAnimationIs = 8;
		if (side.equals("top")) {
			lineWhereTheAnimationIs += 2;
		}

		this.walk = new AnimationPosition(cellWidth, cellHeight, lineWhereTheAnimationIs, widthWalkAnimationLine);
		lineWhereTheAnimationIs += 8;
		this.shoot = new AnimationPosition(cellWidth, cellHeight, lineWhereTheAnimationIs, numCellShoot);

		this.currentTimelineAnimatedImage = new TimelineAnimatedImage(new ImageView(animationImage), walk, frameTime);
		this.width = this.currentTimelineAnimatedImage.asImageView().getBoundsInLocal().getWidth();
		this.height = this.currentTimelineAnimatedImage.asImageView().getBoundsInLocal().getHeight();
		walk();
	}

	public ImageView asImageView() {
		return this.currentTimelineAnimatedImage.asImageView();
	}

	public double getWidth() {
		return this.width;
	}

	public double getHeight() {
		return this.height;
	}

	public void shoot() {
		this.currentTimelineAnimatedImage.setTimeline(shoot);
		this.currentTimelineAnimatedImage.play();
		this.currentTimelineAnimatedImage.setOnFinished(e -> walk());
	}

	public void setVisible(boolean visible) {
		this.currentTimelineAnimatedImage.asImageView().setVisible(visible);
	}

	public void setX(double x) {
		this.currentTimelineAnimatedImage.asImageView().setX(x);
	}

	public void setY(double y) {
		this.currentTimelineAnimatedImage.asImageView().setY(y);
	}

	public void walk() {
		this.currentTimelineAnimatedImage.setTimeline(walk);
		this.currentTimelineAnimatedImage.playContinuously();
	}

}

class TimelineAnimatedImage {
	private ImageView imageView;
	private Timeline timeline;
	private Duration frameTime;
	private AnimationPosition animationPosition;
	private boolean isRunning = false;

	public TimelineAnimatedImage(ImageView imageView, AnimationPosition animationPosition, Duration frameTime) {
		this.imageView = imageView;
		this.frameTime = frameTime;
		this.animationPosition = animationPosition;
		setTimeline(animationPosition);
		imageView.setViewport(animationPosition.nextPosition());
	}

	public void setTimeline(AnimationPosition animationPosition) {
		if (timeline != null)
			this.timeline.stop();
		this.isRunning = false;
		this.animationPosition = animationPosition;
		this.animationPosition.resetPosition();
		this.timeline = new Timeline(
				new KeyFrame(frameTime, event -> imageView.setViewport(animationPosition.nextPosition())));
	}

	public void playContinuously() {
		if (!isRunning) {
			isRunning = true;
			timeline.stop();
			timeline.setCycleCount(Animation.INDEFINITE);
			timeline.playFromStart();
		}

	}

	public void play() {
		timeline.stop();
		timeline.setCycleCount(animationPosition.getLineWidth());
		timeline.playFromStart();
	}

	public void stop() {
		timeline.stop();
	}

	public ImageView asImageView() {
		return this.imageView;
	}

	public void setOnFinished(EventHandler<ActionEvent> value) {
		timeline.setOnFinished(value);
	}
}

class AnimationPosition {
	private Iterator<Rectangle2D> frameIterator;
	private double cellWidth;
	private double cellHeight;
	private int lineWhereTheAnimationIs;
	private int lineWidth;
	private List<Rectangle2D> positions;

	public AnimationPosition(double cellWidth, double cellHeight, int lineWhereTheAnimationIs, int lineWidth) {
		this.cellWidth = cellWidth;
		this.cellHeight = cellHeight;
		this.lineWhereTheAnimationIs = lineWhereTheAnimationIs;
		this.lineWidth = lineWidth;
		this.positions = generateFramePositions();
		this.frameIterator = positions.iterator();
	}

	public void resetPosition() {
		this.frameIterator = positions.iterator();
	}

	private List<Rectangle2D> generateFramePositions() {
		List<Rectangle2D> frames = new ArrayList<>();
		for (int i = 0; i < lineWidth; i++) {
			frames.add(new Rectangle2D(i * cellWidth, cellHeight * lineWhereTheAnimationIs, cellWidth, cellHeight));
		}
		return frames;
	}

	public Rectangle2D nextPosition() {
		if (!frameIterator.hasNext()) {
			frameIterator = positions.iterator();
		}
		return frameIterator.next();
	}

	public int getLineWidth() {
		return this.lineWidth;
	}
}
