package me.gfresh.playground.carpark.gateway;

import me.gfresh.playground.carpark.Ticket;
import me.gfresh.playground.carpark.clock.Clock;

import java.util.Optional;

public class Exit {

	private final Clock clock;

	public Exit(Clock clock) {
		this.clock = clock;
	}

	public Optional<Ticket> offerTicket(Ticket ticket) {
		if (! isExpired(ticket)) {
			return Optional.empty();
		} else {
			return Optional.of(ticket);
		}
	}

	private boolean isExpired(Ticket ticket) {
		return clock.now().isAfter(ticket.getExpirationTime());
	}
}
