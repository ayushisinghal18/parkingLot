package parkinglot;

public class ParkingRate {

	private double rate;
	private int hours;

	public ParkingRate(double rate, int hours) {
		this.rate = rate;
		this.hours = hours;
	}

	@Override
	public String toString() {
		return rate + " for " + hours + " hours";
	}

}
