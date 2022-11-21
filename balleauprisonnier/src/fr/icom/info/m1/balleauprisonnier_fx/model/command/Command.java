package fr.icom.info.m1.balleauprisonnier_fx.model.command;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

// le extends n'est pas obligatoire mais permet d'utiliser button.setOnAction(Command)
public interface Command extends EventHandler<ActionEvent>{
	
	public void handle();
	
	public default void handle(ActionEvent arg0) {
		handle();
	}
	
	public default void execute() {
		handle();
	}
}
