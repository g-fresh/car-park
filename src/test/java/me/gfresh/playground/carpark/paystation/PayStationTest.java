package me.gfresh.playground.carpark.paystation;

import me.gfresh.playground.carpark.Ticket;
import me.gfresh.playground.carpark.clock.TestClock;
import org.joda.time.DateTime;
import org.junit.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PayStationTest {

	private TestClock clock;

	private PayStation station;

	@Before
	public void setUp() {
		clock = new TestClock(DateTime.now());
		station = new PayStation(clock);
	}

	@Test
	public void price_is_one_euro_per_hour_after_the_first_thirty_minutes() throws Exception {
		DateTime expirationTime = clock.now().plusMinutes(30); // TODO replace magic number
		Ticket entryTicket = new Ticket(expirationTime);
		assertEquals(Price.Free,   station.insert(entryTicket).showPrice());
		clock.plusMinutes(30);
		assertEquals(Price.Free,   station.insert(entryTicket).showPrice());
		clock.plusMinutes(1);
		assertEquals(new Price(1), station.insert(entryTicket).showPrice());
		clock.plusMinutes(59);
		assertEquals(new Price(1), station.insert(entryTicket).showPrice());
		clock.plusMinutes(1);
		assertEquals(new Price(2), station.insert(entryTicket).showPrice());
		clock.plusMinutes(59);
		assertEquals(new Price(2), station.insert(entryTicket).showPrice());
		clock.plusMinutes(1);
		assertEquals(new Price(3), station.insert(entryTicket).showPrice());
	}

	@Test
	public void pay_station_returns_exit_ticket() throws Exception {
		Ticket entryTicket = new Ticket(clock.now().minusHours(2));
		Ticket exitTicket = station.insert(entryTicket).accept(new Money());
		assertNotNull(exitTicket);
	}
}
