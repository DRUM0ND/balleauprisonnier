package fr.icom.info.m1.balleauprisonnier_fx.view;

import javafx.beans.property.SimpleDoubleProperty;

public class FPSCounter {
	
	private int framePrecision;
    private int frameIndex = 0 ;
    private long firstNanoTime = 0;
    private SimpleDoubleProperty frameRate;
    
    public FPSCounter(int framePrecision) {
    	this.framePrecision = framePrecision;
    	this.frameRate = new SimpleDoubleProperty(60); /* while frameRate not calculated we set it to 60 by default*/
    }
    
    public void addFrame(long currentNanoTime) {
    	if (frameIndex == 0) {
			firstNanoTime = currentNanoTime;
		}
    	frameIndex++;
    	if (frameIndex == framePrecision) {
    		frameIndex = 0;
    		
			long elapsedNanos = currentNanoTime - firstNanoTime;
            long elapsedNanosPerFrame = elapsedNanos / framePrecision;
            frameRate.setValue(1_000_000_000.0 / elapsedNanosPerFrame);
		}
    }
    
    public SimpleDoubleProperty getFrameRate() {
		return frameRate;
	}
}
