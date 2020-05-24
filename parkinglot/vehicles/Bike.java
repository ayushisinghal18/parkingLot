package parkinglot.vehicles;

import parkinglot.Vehicle;
import parkinglot.VehicleSize;

public class Bike extends Vehicle {

	public Bike() {
		this.setSize(VehicleSize.SMALL.getSize());
		// this.setSpotsNeeded(1);
	}
}
