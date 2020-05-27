package parkinglot.parkingspots;

import parkinglot.ParkingSpot;
import parkinglot.enums.ParkingSpotType;

public class CompactSpot extends ParkingSpot {

	public CompactSpot() {
		super(ParkingSpotType.COMPACT);
	}

	public CompactSpot(int spotNo) {
		super(spotNo, ParkingSpotType.COMPACT);
	}
}
