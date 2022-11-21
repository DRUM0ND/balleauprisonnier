package fr.icom.info.m1.balleauprisonnier_fx.util.mvc;

import java.util.HashSet;
import java.util.Set;

public class Publisher {
	private Set<Subscriber> subscribers;

	public Publisher() {
		subscribers = new HashSet<>();
	}

	public void subscribe(Subscriber subscriber) {
		subscribers.add(subscriber);
	}

	public void unsubscribe(Subscriber subscriber) {
		subscribers.remove(subscriber);
	}

	public void notifySubscribers() {
		for (Subscriber subscriber : subscribers) {
			subscriber.update();
		}
	}
}
