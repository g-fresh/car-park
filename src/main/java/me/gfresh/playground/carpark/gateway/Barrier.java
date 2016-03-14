package me.gfresh.playground.carpark.gateway;

import static me.gfresh.playground.carpark.gateway.Barrier.State.*;

public final class Barrier {

	public enum State { Open, Closed }

	private State state = Closed;

	public void close() {
		state = Closed;
	}

	public void open() {
		state = Open;
	}

	public State state() {
		return state;
	}
}
