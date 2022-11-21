package fr.icom.info.m1.balleauprisonnier_fx.util.strategy;

public class Context {

	private Strategy strategy;
	
	public void setStrategy(Strategy strategy) {
		this.strategy = strategy;
	}
	
	public void execute() {
		if (strategy == null)
			throw new IllegalStateException("Trying to execute context but strategy is null");
		strategy.execute();
	}
}
