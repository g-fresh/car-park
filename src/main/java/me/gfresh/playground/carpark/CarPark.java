package me.gfresh.playground.carpark;

import me.gfresh.playground.carpark.clock.Clock;
import me.gfresh.playground.carpark.gateway.*;
import me.gfresh.playground.carpark.paystation.PayStation;

public final class CarPark {

	public final Entry entry;
	public final Exit exit;
	public final PayStation payStation;

	public CarPark(Clock clock) {
		entry = new Entry(clock);
		exit = new Exit(clock);
		payStation = new PayStation(clock);
	}


	private Capacity capacity = new Capacity(100);

	// Show free / occupied
	class Capacity {

		private int total;
		private int used;

		Capacity(int total) {
			this.total = total;
		}

		boolean isSpaceAvailable() {
			return used < total;
		}

		void less() {
			used++;
		}

		void more() {
			used--;
		}
	}
}

