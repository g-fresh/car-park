package me.gfresh.playground.carpark;

import org.joda.time.DateTime;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TicketTest {

	@Test
	public void ticket_is_coded_with_expiration_time() throws Exception {
		DateTime expirationTime = DateTime.now();
		Ticket ticket = new Ticket(expirationTime);
		assertEquals(expirationTime, ticket.getExpirationTime());
	}
}
