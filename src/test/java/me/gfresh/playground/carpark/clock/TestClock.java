package me.gfresh.playground.carpark.clock;

import org.joda.time.DateTime;

public class TestClock extends Clock {

	private DateTime time;

	public TestClock(DateTime time) {
		this.time = time;
	}

	@Override
	public DateTime now() {
		return time;
	}

	public TestClock plusMinutes(int n) {
		time = time.plusMinutes(n);
		return this;
	}

	public TestClock plusHours(int n) {
		time = time.plusHours(n);
		return this;
	}
}

