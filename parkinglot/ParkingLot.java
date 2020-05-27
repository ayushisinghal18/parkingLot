package parkinglot;

import java.util.HashMap;
import java.util.Map;

import parkinglot.enums.VehicleType;

public class ParkingLot {

	public static ParkingLot lot = null;

	private HashMap<Integer, ParkingFloor> floors;
	private int id;
	private int freeBikeSpotCount;
	private int freeCompactSpotCount;
	private int freeBigSpotCount;

	private final int maxBikeSpots;
	private final int maxCompactSpots;
	private final int maxBigSpots;

	private static int ticketNumber;
	private ParkingDisplayBoard displayBoard;

	// initializes parking lot and floors
	private ParkingLot(int id) {
		this.id = id;

		floors = new HashMap<>();

		maxBikeSpots = 10;
		maxCompactSpots = 10;
		maxBigSpots = 10;

		ticketNumber = 1;

		displayBoard = new ParkingDisplayBoard(id);
	}

	public static ParkingLot getInstance() {
		if (lot == null) {
			lot = new ParkingLot(0000);
		}
		return lot;
	}

	public ParkingTicket getNewParkingTicket(Vehicle vehicle) {
		if (VehicleType.isValidVehicle(vehicle.getType()) == null) {
			System.out.println("Invalid Vehicle Type!");
			return null;
		}

		ParkingTicket ticket = new ParkingTicket(ticketNumber++);
		vehicle.addTicket(ticket);

		updateSpotCountAfterAddingVehicle(vehicle.getType());

		return ticket;
	}

	public boolean addParkingFloor(ParkingFloor floor) {
		return floors.put(floor.getFloorNo(), floor) != null;
	}

	public boolean addParkingSpot(int floor, ParkingSpot spot) {
		boolean added = floors.get(floor).addParkingSpot(spot);
		if (added) {
			switch (spot.getType()) {
			case BIKE:
				freeBikeSpotCount++;
				break;
			case COMPACT:
				freeCompactSpotCount++;
				break;
			case BIG:
				freeBigSpotCount++;
				break;
			}
		}
		return added;
	}

	public Object getParkingFloor(String floor) {
		return null;
	}

	public boolean removeParkingFloor(int floorNo) {

		if (floors.containsKey(floorNo) && floors.get(floorNo)
				.getVacantParkingSpotCount() == (freeBikeSpotCount + freeCompactSpotCount + freeBigSpotCount)) {
			return floors.remove(floorNo) != null;
		}
		return false;
	}

	public boolean removeParkingSpot(int floor, int spotNo) {
		ParkingSpot removedSpot = floors.get(floor).removeParkingSpot(spotNo);
		if (removedSpot != null) {
			switch (removedSpot.getType()) {
			case BIKE:
				freeBikeSpotCount--;
				return true;
			case COMPACT:
				freeCompactSpotCount--;
				return true;
			case BIG:
				freeBigSpotCount--;
				return true;
			}
		}
		return false;
	}

	public int getFloorsCount() {
		return floors.size();
	}

	public ParkingFloor getFloor(int floor) {
		return floors.get(floor);
	}

	public boolean isParkingFull() {
		int freeSpots = freeBikeSpotCount + freeCompactSpotCount + freeBigSpotCount;
		int totalSpot = maxBikeSpots + maxCompactSpots + maxBigSpots;

		return freeSpots == totalSpot;
	}

	public boolean displayBoardforFullParking() {
		if (floors.size() == 0 || (freeBikeSpotCount + freeCompactSpotCount + freeBigSpotCount) == 0) {
			displayBoard.displayParkingFullMessage();
			return true;
		}

		for (Map.Entry<Integer, ParkingFloor> floor : floors.entrySet()) {
			System.out.println("\n-----------------DISPLAY BOARD-----------------");
			System.out.println("*** Floor " + floor.getKey() + " ***");
			floor.getValue().getDisplayBoard().displayVacantSpots();
			System.out.println("-----------------------------------------------");
		}

		return false;
	}

	public boolean assignVehicleToSpot(Vehicle vehicle) {
		ParkingSpot spot = null;

		for (Map.Entry<Integer, ParkingFloor> floor : floors.entrySet()) {
			spot = floor.getValue().assignVehicleToSpot(vehicle);
			if (spot != null) {
				System.out.println("Vehicle " + vehicle.getVehicleNumber() + " parked at spot " + spot.getSpotNumber()
						+ " on Floor " + floor.getValue().getFloorNo());
				getNewParkingTicket(vehicle);
				return true;
			}
		}

		System.out.println("Vehicle " + vehicle.getVehicleNumber() + " cannot be parked at spot");
		return false;
	}

	public boolean removeVehicleFromSpot(int spotNo, String vehicleNumber) {
		ParkingSpot spot = null;

		for (Map.Entry<Integer, ParkingFloor> floor : floors.entrySet()) {
			spot = floor.getValue().removeVehicleFromSpot(spotNo, vehicleNumber);
			if (spot != null) {
				updateSpotCountAfterRemovingVehicle(spot.getVehicle().getType());
				spot.removeVehicle();
				System.out.println("Vehicle " + vehicleNumber + " removed from spot " + spotNo);
				return true;
			}
		}
		System.out.println("Vehicle " + vehicleNumber + " not parked at spot " + spotNo);
		return false;

	}

	public boolean diplayParkingFull() {
		int freeSpots = freeBikeSpotCount + freeCompactSpotCount + freeBigSpotCount;
		if (freeSpots == 0) {
			displayBoard.displayParkingFullMessage();
			return true;
		}

		return false;
	}

	private void updateSpotCountAfterRemovingVehicle(VehicleType type) {
		if (type == VehicleType.BIKE) {
			freeBikeSpotCount++;
		}
		if (type == VehicleType.CAR) {
			freeCompactSpotCount++;
		}
		if (type == VehicleType.TRUCK || type == VehicleType.VAN) {
			freeCompactSpotCount++;
		}
	}

	private void updateSpotCountAfterAddingVehicle(VehicleType type) {
		if (type == VehicleType.BIKE) {
			freeBikeSpotCount--;
		}
		if (type == VehicleType.CAR) {
			freeCompactSpotCount--;
		}
		if (type == VehicleType.TRUCK || type == VehicleType.VAN) {
			freeCompactSpotCount--;
		}
	}
}
