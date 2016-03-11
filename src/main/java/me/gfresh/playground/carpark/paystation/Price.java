package me.gfresh.playground.carpark.paystation;

public final class Price {

	public static final Price Free = new Price(0);

	private final int amount;

	public Price(int amount) {
		this.amount = amount;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		Price other = (Price) obj;
		return amount == other.amount;
	}

	@Override
	public int hashCode() {
		return amount;
	}

	@Override
	public String toString() {
		return String.valueOf(amount);
	}
}

