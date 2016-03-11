package me.gfresh.playground.carpark;

import org.joda.time.DateTime;

public final class Ticket {

	private final DateTime expirationTime;

	public Ticket(DateTime expirationTime) {
		this.expirationTime = expirationTime;
	}

	public DateTime getExpirationTime() {
		return expirationTime;
	}
}
