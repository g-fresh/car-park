package me.gfresh.playground.carpark.paystation;

import me.gfresh.playground.carpark.Ticket;
import me.gfresh.playground.carpark.clock.Clock;
import org.joda.time.*;

import static me.gfresh.playground.carpark.paystation.Price.*;

public final class PayStation {

	private final Clock clock;

	public PayStation(Clock clock) {
		this.clock = clock;
	}

	public AwaitingPayment insert(Ticket ticket) {
		return new AwaitingPayment(ticket, calculatePrice(ticket));
	}

	private Price calculatePrice(Ticket ticket) {
		DateTime now = clock.now();
		if (now.isAfter(ticket.getExpirationTime())) {
			Period parkingTime = calculateParkingTime(ticket.getExpirationTime(), now);
			return calculatePrice(parkingTime);
		} else {
			return Free;
		}
	}

	private Period calculateParkingTime(DateTime from, DateTime to) {
		return new Period(from, to);
	}

	private Price calculatePrice(Period parkingTime) {
		int hours = parkingTime.getHours() + (parkingTime.getMinutes() > 0 ? 1 : 0);
		int pricePerHour = 1; // TODO replace magic number
		return new Price(hours * pricePerHour);
	}

	public final class AwaitingPayment {

		private final Ticket entryTicket;
		private final Price price;

		private AwaitingPayment(Ticket ticket, Price price) {
			this.entryTicket = ticket;
			this.price = price;
		}

		public Price showPrice() {
			return price;
		}

		public Ticket accept(Money money) {
			DateTime timePayed = clock.now();
			DateTime expirationTime = timePayed.plusMinutes(30); // TODO replace magic number
			Ticket exitTicket = new Ticket(expirationTime);
			return exitTicket;
		}

		public Ticket abort() {
			return entryTicket;
		}
	}
}

