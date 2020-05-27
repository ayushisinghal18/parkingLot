package parkinglot.enums;

import java.util.stream.Stream;

public enum ParkingRateList {
	BIKE(10), COMPACT(20), BIG(40);

	private final double rate;

	private ParkingRateList(double rate) {
		this.rate = rate;
	}

	public double getParkingRate() {
		return rate;
	}

	public static double getParkingRate(ParkingSpotType type) {
		return Stream.of(ParkingRateList.values()).filter(s -> s.toString() == type.toString()).findFirst().get()
				.getParkingRate();
	}
}
