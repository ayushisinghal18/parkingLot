package parkinglot;

import java.util.HashMap;

import parkinglot.parkingspots.BigSpot;
import parkinglot.parkingspots.BikeSpot;
import parkinglot.parkingspots.CompactSpot;

public class ParkingFloor {

	private int floorNo;
	private HashMap<Integer, BikeSpot> bikeSpots;
	private HashMap<Integer, CompactSpot> compactSpots;
	private HashMap<Integer, BigSpot> bigSpots;
	private ParkingDisplayBoard displayBoard;

	private int freeBikeSpotCount;
	private int freeCompactSpotCount;
	private int freeBigSpotCount;

	public ParkingFloor(int floorNo) {
		this.floorNo = floorNo;
		displayBoard = new ParkingDisplayBoard(floorNo);

		bikeSpots = new HashMap<>();
		compactSpots = new HashMap<>();
		bigSpots = new HashMap<>();
	}

	public boolean addParkingSpot(ParkingSpot spot) {

		switch (spot.getType()) {
		case BIKE:
			freeBikeSpotCount++;
			return bikeSpots.put(spot.getSpotNumber(), (BikeSpot) spot) != null;
		case COMPACT:
			freeCompactSpotCount++;
			return compactSpots.put(spot.getSpotNumber(), (CompactSpot) spot) != null;
		case BIG:
			freeBigSpotCount++;
			return bigSpots.put(spot.getSpotNumber(), (BigSpot) spot) != null;
		default:
			System.out.println("Invalid Parking Spot Type!");
			return false;
		}
	}

	public ParkingSpot assignVehicleToSpot(Vehicle vehicle) {

		ParkingSpot spot = getParkingSpotForVehicle(vehicle);

		if (spot != null) {
			spot.addVehicle(vehicle);

			switch (spot.getType()) {
			case BIKE:
				freeBikeSpotCount--;
				this.displayBoard.setBikeVacantSpot(getNewBikeSpot());
				updateDisplayBoard();
				break;

			case COMPACT:
				freeCompactSpotCount--;
				this.displayBoard.setCompactVacantSpot(getNewCompactSpot());
				updateDisplayBoard();
				break;

			case BIG:
				freeBigSpotCount--;
				this.displayBoard.setBigVacantSpot(getNewBigSpot());
				updateDisplayBoard();
				break;
			}
		}

		return spot;
	}

	public int getFloorNo() {
		return floorNo;
	}

	public void setFloorNo(int floorNo) {
		this.floorNo = floorNo;
	}

	public ParkingSpot removeVehicleFromSpot(int spotNo, String vehicleNumber) {
		ParkingSpot spot = null;
		for (Integer num : bikeSpots.keySet()) {
			spot = bikeSpots.get(num);
			if (!spot.isVacant() && spot.getSpotNumber() == spotNo
					&& spot.getVehicle().getVehicleNumber().equals(vehicleNumber)) {
				freeBikeSpotCount++;
				makePayment(spot);
				return spot;
			}
		}
		for (Integer num : compactSpots.keySet()) {
			spot = compactSpots.get(num);
			if (!spot.isVacant() && spot.getSpotNumber() == spotNo
					&& spot.getVehicle().getVehicleNumber() == vehicleNumber) {
				freeCompactSpotCount++;
				makePayment(spot);
				return spot;
			}
		}
		for (Integer num : bigSpots.keySet()) {
			spot = bigSpots.get(num);
			if (!spot.isVacant() && spot.getSpotNumber() == spotNo
					&& spot.getVehicle().getVehicleNumber() == vehicleNumber) {
				freeBigSpotCount++;
				makePayment(spot);
				return spot;
			}
		}

		return null;
	}

	public ParkingSpot removeParkingSpot(int spotNo) {
		ParkingSpot spot = null;

		for (Integer num : bikeSpots.keySet()) {
			if (num == spotNo) {
				spot = bikeSpots.get(num);
			}
		}
		for (Integer num : compactSpots.keySet()) {
			if (num == spotNo) {
				spot = compactSpots.get(num);
			}
		}
		for (Integer num : bigSpots.keySet()) {
			if (num == spotNo) {
				spot = bigSpots.get(num);
			}
		}

		if (!spot.isVacant()) {
			System.out.println("Parking spot is not vacant!");
			return null;
		}

		switch (spot.getType()) {
		case BIKE:
			freeBikeSpotCount--;
			return bikeSpots.remove(spot.getSpotNumber()) != null ? spot : null;

		case COMPACT:
			freeCompactSpotCount--;
			return compactSpots.remove(spot.getSpotNumber()) != null ? spot : null;

		case BIG:
			freeBigSpotCount--;
			return bigSpots.remove(spot.getSpotNumber()) != null ? spot : null;

		default:
			System.out.println("Invalid Parking Spot Type!");
			return null;
		}
	}

	public int getSpotsCount() {
		return bikeSpots.size() + compactSpots.size() + bigSpots.size();
	}

	public ParkingDisplayBoard getDisplayBoard() {
		ParkingSpot spot = null;

		spot = getNewBikeSpot();
		this.displayBoard.setBikeVacantSpot(spot);

		spot = getNewCompactSpot();
		this.displayBoard.setCompactVacantSpot(spot);

		spot = getNewBigSpot();
		this.displayBoard.setBigVacantSpot(spot);

		return displayBoard;
	}

	public void updateDisplayBoard() {
		this.displayBoard.displayVacantSpots();
	}

	public int getVacantParkingSpotCount() {
		return freeBikeSpotCount + freeCompactSpotCount + freeBigSpotCount;
	}

	private ParkingSpot getParkingSpotForVehicle(Vehicle vehicle) {
		ParkingSpot spot = null;

		switch (vehicle.getType()) {
		case BIKE:
			spot = getNewBikeSpot();
			break;

		case CAR:
			spot = getNewCompactSpot();
			break;

		case TRUCK:
		case VAN:
			spot = getNewBigSpot();
			break;
		}

		return spot;
	}

	private void makePayment(ParkingSpot spot) {
		Payment payment = new Payment();
		payment.initTransaction(spot.getVehicle().getTicket(), spot.getType());
	}

	private ParkingSpot getNewBikeSpot() {
		for (Integer num : bikeSpots.keySet()) {
			if (bikeSpots.get(num).isVacant()) {
				return bikeSpots.get(num);
			}
		}
		return null;
	}

	private ParkingSpot getNewCompactSpot() {
		for (Integer num : compactSpots.keySet()) {
			if (compactSpots.get(num).isVacant()) {
				return compactSpots.get(num);
			}
		}
		return null;
	}

	private ParkingSpot getNewBigSpot() {
		for (Integer num : bigSpots.keySet()) {
			if (bigSpots.get(num).isVacant()) {
				return bigSpots.get(num);
			}
		}
		return null;
	}

}
