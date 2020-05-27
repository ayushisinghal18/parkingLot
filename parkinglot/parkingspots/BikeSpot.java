package parkinglot.parkingspots;

import parkinglot.ParkingSpot;
import parkinglot.enums.ParkingSpotType;

public class BikeSpot extends ParkingSpot {

	public BikeSpot() {
		super(ParkingSpotType.BIKE);
	}

	public BikeSpot(int spotNo) {
		super(spotNo, ParkingSpotType.BIKE);
	}
}
