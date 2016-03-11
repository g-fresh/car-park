package me.gfresh.playground.carpark;

import me.gfresh.playground.carpark.clock.TestClock;
import me.gfresh.playground.carpark.gateway.*;
import me.gfresh.playground.carpark.paystation.*;
import org.joda.time.DateTime;
import org.junit.*;

import java.util.Optional;

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
		Ticket ticket = entry.requestTicket();
		clock.plusMinutes(30);
		assertEquals(Price.Free, payStation.insert(ticket).showPrice());
	}

	@Test
	public void parking_costs_one_euro_per_hour_or_partial_hour() throws Exception {
		Ticket ticket = entry.requestTicket();
		clock.plusMinutes(30).plusMinutes(150);
		assertEquals(new Price(3), payStation.insert(ticket).showPrice());
	}

	@Test
	public void can_exit_within_thirty_minutes_without_going_to_the_pay_station() throws Exception {
		Ticket ticket = entry.requestTicket();
		clock.plusMinutes(30);
		assertFalse(exit.offerTicket(ticket).isPresent()); // Keeps ticket; opens barrier ...
	}

	@Test
	public void cannot_exit_after_thirty_minutes_without_paying() throws Exception {
		Ticket ticket = entry.requestTicket();
		clock.plusMinutes(31);
		Optional<Ticket> returnedTicket = exit.offerTicket(ticket);
		assertTrue(returnedTicket.isPresent()); // Returns ticket; keeps barrier closed ...
	}

	@Test
	public void can_exit_after_paying() throws Exception {
		Ticket entryTicket = entry.requestTicket();
		clock.plusMinutes(31);
		Ticket exitTicket = payStation.insert(entryTicket).accept(new Money());
		assertFalse(exit.offerTicket(exitTicket).isPresent()); // Keeps ticket; opens barrier ...
	}
}
