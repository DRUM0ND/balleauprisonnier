package fr.icom.info.m1.balleauprisonnier_fx.model.command;

import javafx.application.Platform;

public class ExitCommand implements Command {

	@Override
	public void handle() {
		Platform.exit();
		System.exit(0);
	}

}
