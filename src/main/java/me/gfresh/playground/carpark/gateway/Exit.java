package me.gfresh.playground.carpark.gateway;

import me.gfresh.playground.carpark.Ticket;
import me.gfresh.playground.carpark.clock.Clock;

import java.util.Optional;

public class Exit extends Gateway {

	public Exit(Clock clock) {
		super(clock);
	}

	public Optional<Ticket> offerTicket(Ticket ticket) {
		if (isPassable() && ! isExpired(ticket)) {
			allowPassage();
			return Optional.empty();
		} else {
			return Optional.of(ticket);
		}
	}

	private boolean isExpired(Ticket ticket) {
		return clock.now().isAfter(ticket.getExpirationTime());
	}
}
