package parkinglot;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {

	public static ParkingLot lot = null;

	private List<Level> levels;
	private Display mainDisplay;

	// initializes parking lot and levels
	private ParkingLot(int level, int parkingSpots) {
		levels = new ArrayList<Level>(level);
		for (int i = 0; i < level; i++) {
			levels.add(new Level(i, parkingSpots));
			mainDisplay = new Display();
		}
	}

	public static ParkingLot getInstance(int level, int parkingSpots) {
		if (lot == null) {
			lot = new ParkingLot(level, parkingSpots);
		}
		return lot;
	}

	// allocate parking spot to vehicle
	public int allocateParkingSpot(Vehicle vehicle) {
		if (VehicleSize.checkSize(vehicle.getSize()) == null) {
			System.out.println("Vehicle not allowed");
			return -1;
		}

		Parkspot parkSpot = checkVacantSpot(vehicle);
		if (parkSpot == null) {
			System.out.println("Parking for " + VehicleSize.getVehicleType(vehicle.getSize()) + " vehicle is FULL");
			return -1;
		}

		levels.get(parkSpot.getLevelNo()).addVehicle(parkSpot.getSpotNo(), vehicle);

		System.out.println("Display board for each level");
		for (Level level : levels) {
			level.show(level);
		}

		return parkSpot.getSpotNo();
	}

	// removes vehicle from parking spot
	public int removeVehicle(int levelno, int spotNo) {

		Parkspot parkSpot = checkOccupiedSpot(levelno, spotNo);
		if (parkSpot == null) {
			System.out.println("Parking Spot already free");
			return -1;
		}

		levels.get(parkSpot.getLevelNo()).removeVehicle(spotNo);

		System.out.println("Display board for each level");
		for (Level level : levels) {
			level.show(level);
		}

		return parkSpot.getSpotNo();
	}

	// checks vacant spot
	private Parkspot checkVacantSpot(Vehicle vehicle) {
		Parkspot parkspot = null;
		for (Level level : levels) {
			parkspot = level.getVacantParkingSpot(vehicle.getSize());
			if (parkspot != null) {
				break;
			}
		}
		return parkspot;
	}

	// checks occupied spot
	private Parkspot checkOccupiedSpot(int levelno, int spotNo) {
		Parkspot parkspot = null;

		parkspot = levels.get(levelno).getOccupiedParkingSpot(spotNo);

		return parkspot;
	}

	// display vacant spots
	public void displaySpots() {
		System.out.println("-------------------------------------------------");
		for (Level level : levels) {
			System.out.println(mainDisplay.show(level));
		}
		System.out.println("-------------------------------------------------");
	}

	// checks if atleast one spot is vacant in parking lot
	public boolean checkParkingSpotVacany() {

		for (Level level : levels) {
			if (level.getVacantParkingSpot(VehicleSize.SMALL.getSize()) != null)
				return true;
			if (level.getVacantParkingSpot(VehicleSize.MEDIUM.getSize()) != null)
				return true;
			if (level.getVacantParkingSpot(VehicleSize.BIG.getSize()) != null)
				return true;
		}

		return false;
	}
}
