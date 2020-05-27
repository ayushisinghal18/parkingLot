package parkinglot.parkingspots;

import parkinglot.ParkingSpot;
import parkinglot.enums.ParkingSpotType;

public class BigSpot extends ParkingSpot {

	public BigSpot() {
		super(ParkingSpotType.BIG);
	}

	public BigSpot(int spotNo) {
		super(spotNo, ParkingSpotType.BIG);
	}
}
