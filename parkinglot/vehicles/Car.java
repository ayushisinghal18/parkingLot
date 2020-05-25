package parkinglot.vehicles;

import parkinglot.Vehicle;
import parkinglot.VehicleSize;

public class Car extends Vehicle {

	public Car() {
		this.setSize(VehicleSize.MEDIUM.getSize());
	}
}
