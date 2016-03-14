package me.gfresh.playground.carpark.gateway;

import me.gfresh.playground.carpark.Ticket;
import me.gfresh.playground.carpark.clock.Clock;
import org.joda.time.*;

import java.util.Optional;

public class Entry extends Gateway {

	private static final Period freeParkingPeriod = Period.minutes(30);

	public Entry(Clock clock) {
		super(clock);
	}

	public Optional<Ticket> requestTicket() {
		if (isPassable() && isSpaceAvailable()) {
			Ticket ticket = createTicket();
			allowPassage();
			return Optional.of(ticket);
		} else {
			return Optional.empty();
		}
	}

	private boolean isSpaceAvailable() {
		return true; // TODO
	}

	private Ticket createTicket() {
		DateTime timeEntered = clock.now();
		DateTime expirationTime = timeEntered.plus(freeParkingPeriod);
		return new Ticket(expirationTime);
	}
}
