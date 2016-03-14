package me.gfresh.playground.carpark;

import me.gfresh.playground.carpark.clock.TestClock;
import me.gfresh.playground.carpark.gateway.*;
import me.gfresh.playground.carpark.paystation.*;
import org.joda.time.DateTime;
import org.junit.*;

import java.util.Optional;

import static me.gfresh.playground.carpark.gateway.Barrier.State.*;
import static me.gfresh.playground.carpark.gateway.Signal.*;
import static me.gfresh.playground.carpark.paystation.Price.Free;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CarParkTest {

	private TestClock clock;

	private CarPark carPark;
	private Entry entry;
	private Exit exit;
	private PayStation payStation;

	@Before
	public void setUp() {
		clock = new TestClock(DateTime.now());
		carPark = new CarPark(clock);
		entry = carPark.entry;
		exit = carPark.exit;
		payStation = carPark.payStation;
	}

	@Test
	public void parking_is_free_for_thirty_minutes() throws Exception {
		Optional<Ticket> ticket = requestTicket();
		assertTrue(ticket.isPresent());
		clock.plusMinutes(30);
		assertEquals(Free, showPrice(ticket.get()));
	}

	@Test
	public void parking_costs_one_euro_per_hour_or_partial_hour() throws Exception {
		Optional<Ticket> ticket = requestTicket();
		assertTrue(ticket.isPresent());
		clock.plusMinutes(30).plusHours(1).plusMinutes(5);
		assertEquals(new Price(2), showPrice(ticket.get()));
	}

	@Test
	public void can_exit_within_thirty_minutes_without_going_to_the_pay_station() throws Exception {
		Ticket ticket = requestTicket().get();
		clock.plusMinutes(30);
		assertCanExit(ticket);
	}

	@Test
	public void cannot_exit_after_thirty_minutes_without_paying() throws Exception {
		Ticket ticket = entry.requestTicket().get();
		clock.plusMinutes(31);
		assertCannotExit(ticket);
	}

	@Test
	public void can_exit_after_paying() throws Exception {
		Ticket entryTicket = entry.requestTicket().get();
		clock.plusMinutes(31);
		Ticket exitTicket = payStation.insert(entryTicket).accept(new Money());
		assertCanExit(exitTicket);
	}

	Optional<Ticket> requestTicket() {
		return entry.requestTicket();
	}

	Price showPrice(Ticket ticket) {
		return payStation.insert(ticket).showPrice();
	}

	void assertCanExit(Ticket ticket) {
		assertFalse(exit.offerTicket(ticket).isPresent());
		assertEquals(Open, exit.getBarrierState());
		assertEquals(Green, exit.getSignal());
	}

	void assertCannotExit(Ticket ticket) {
		Optional<Ticket> returnedTicket = exit.offerTicket(ticket);
		assertTrue(returnedTicket.isPresent());
		assertEquals(Closed, exit.getBarrierState());
		assertEquals(Red, exit.getSignal());
	}
}
