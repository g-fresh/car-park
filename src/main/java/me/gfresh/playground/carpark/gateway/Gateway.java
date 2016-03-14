package me.gfresh.playground.carpark.gateway;

import me.gfresh.playground.carpark.clock.Clock;
import static me.gfresh.playground.carpark.gateway.Signal.*;

abstract class Gateway {

	protected final Clock clock;
	private Signal signal;
	private final Barrier barrier;
	private final Sensors sensors;

	protected Gateway(Clock clock) {
		this(clock, new Sensors());
	}

	protected Gateway(Clock clock, Sensors sensors) {
		this.clock = clock;
		this.signal = Red;
		this.barrier = new Barrier();
		this.sensors = sensors;
	}

	protected final boolean isPassable() {
		return sensors.isVehicleWaiting();
	}

	protected final void allowPassage() {
		barrier.open();
		signal = Green;
	}

	public final Signal getSignal() {
		return signal;
	}

	public final Barrier.State getBarrierState() {
		return barrier.state();
	}
}
