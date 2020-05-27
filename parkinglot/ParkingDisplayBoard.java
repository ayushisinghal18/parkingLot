package parkinglot;

import parkinglot.parkingspots.BigSpot;
import parkinglot.parkingspots.BikeSpot;
import parkinglot.parkingspots.CompactSpot;

public class ParkingDisplayBoard {

	private int id;
	private ParkingSpot bikeVacantSpot;
	private ParkingSpot compactVacantSpot;
	private ParkingSpot bigVacantSpot;
	private String message;

	public ParkingDisplayBoard(int id) {
		this.id = id;
		bikeVacantSpot = new BikeSpot();
		compactVacantSpot = new CompactSpot();
		bigVacantSpot = new BigSpot();
	}

	public void setBikeVacantSpot(ParkingSpot bikeVacantSpot) {
		this.bikeVacantSpot = bikeVacantSpot;
	}

	public void setCompactVacantSpot(ParkingSpot compactVacantSpot) {
		this.compactVacantSpot = compactVacantSpot;
	}

	public void setBigVacantSpot(ParkingSpot bigVacantSpot) {
		this.bigVacantSpot = bigVacantSpot;
	}

	public void displayVacantSpots() {
		message = "";
		if (bikeVacantSpot != null && bikeVacantSpot.isVacant()) {
			this.message += "Free Bike Spot := " + bikeVacantSpot.getSpotNumber();
		} else {
			this.message += "Bike Spot is Full!";
		}
		this.message += System.lineSeparator();
		if (compactVacantSpot != null && compactVacantSpot.isVacant()) {
			this.message += "Free Compact Spot := " + compactVacantSpot.getSpotNumber();
		} else {
			this.message += "Compact Spot is Full!";
		}
		this.message += System.lineSeparator();
		if (bigVacantSpot != null && bigVacantSpot.isVacant()) {
			this.message += "Free Big Spot := " + bigVacantSpot.getSpotNumber();
		} else {
			this.message += "Big Spot is Full!";
		}

		displayMessage(this.message);
	}

	// display board
	private void displayMessage(String message) {
		System.out.println(message);
	}

	// display board when parking is full
	public void displayParkingFullMessage() {
		System.out.println("Sorry, Parking is Full!");
	}

}
