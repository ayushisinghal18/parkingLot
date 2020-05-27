package parkinglot;

import parkinglot.enums.ParkingSpotType;

public abstract class ParkingSpot {

	private int spotNumber;
	private ParkingSpotType type;
	private boolean isVacant;
	private Vehicle vehicle;

	public ParkingSpot(int spotNo, ParkingSpotType type) {
		this.spotNumber = spotNo;
		this.type = type;
		isVacant = true;
	}

	public ParkingSpot(ParkingSpotType type) {
		this.type = type;
	}

	public boolean isVacant() {
		return isVacant;
	}

	public void addVehicle(Vehicle vehicle) {
		this.isVacant = false;
		this.vehicle = vehicle;
	}

	public void removeVehicle() {
		this.isVacant = true;
		this.vehicle = null;
	}

	public int getSpotNumber() {
		return spotNumber;
	}

	public void setSpotNumber(int spotNumber) {
		this.spotNumber = spotNumber;
	}

	public ParkingSpotType getType() {
		return type;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

}
