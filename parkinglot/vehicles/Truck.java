package parkinglot.vehicles;

import parkinglot.Vehicle;
import parkinglot.VehicleSize;

public class Truck extends Vehicle {

	public Truck() {
		this.setSize(VehicleSize.BIG.getSize());
	}
}
