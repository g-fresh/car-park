package me.gfresh.playground.carpark.gateway;

import me.gfresh.playground.carpark.Ticket;
import me.gfresh.playground.carpark.clock.Clock;
import org.joda.time.DateTime;

public class Entry {

	private final Clock clock;

	public Entry(Clock clock) {
		this.clock = clock;
	}

	public Ticket requestTicket() {
		return createTicket();
	}

	private Ticket createTicket() {
		DateTime timeEntered = clock.now();
		DateTime expirationTime = timeEntered.plusMinutes(30); // TODO convert magic number to constant
		return new Ticket(expirationTime);
	}
}
