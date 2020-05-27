package parkinglot.actors;

import parkinglot.ParkingFloor;
import parkinglot.ParkingLot;
import parkinglot.ParkingSpot;
import parkinglot.enums.AccountStatus;
import parkinglot.model.Account;

public class Admin extends Account {

	public Admin(AccountStatus status) {
		super(status);
	}

	private final String username = "admin";
	private final String password = "parkinglot";

	private final int floorsCount = 2;
	private final int parkingSpotsCount = 5;

	public static Admin admin = null;
	private ParkingLot lot = ParkingLot.getInstance();

	public static Admin getInstance() {
		if (admin == null) {
			admin = new Admin(AccountStatus.ACTIVE);
		}
		return admin;
	}

	public String username() {
		return username;
	}

	public String password() {
		return password;
	}

	public void addParkingFloor(ParkingFloor floor) {
		if (admin.getStatus().equals(AccountStatus.ACTIVE)) {
			if (lot.getFloorsCount() < floorsCount && lot.addParkingFloor(floor)) {
				System.out.println(floor.getFloorNo() + " Floor Added..");
			} else {
				System.out.println("Cannot add Floor");
			}
		} else {
			System.out.println("Admin is not ACTIVE!");
		}
	}

	public void addParkingSpot(int floor, ParkingSpot spot) {
		if (admin.getStatus().equals(AccountStatus.ACTIVE)) {
			if (lot.getFloor(floor) != null && lot.getFloor(floor).getSpotsCount() < parkingSpotsCount
					&& lot.addParkingSpot(floor, spot)) {
				System.out.println(spot.getSpotNumber() + " Spot Added on Floor " + floor + "..");
			} else {
				System.out.println("Cannot add Parking Spot");
			}
		} else {
			System.out.println("Admin is not ACTIVE!");
		}
	}

	public void removeParkingFloor(int floorNo) {
		if (admin.getStatus().equals(AccountStatus.ACTIVE)) {
			if (lot.getFloorsCount() > 0 && lot.removeParkingFloor(floorNo)) {
				System.out.println(floorNo + " Floor Removed..");
			} else {
				System.out.println("Cannot remove Floor");
			}
		} else {
			System.out.println("Admin is not ACTIVE!");
		}
	}

	public void removeParkingSpot(int floor, int spotNo) {
		if (admin.getStatus().equals(AccountStatus.ACTIVE)) {
			if (lot.getFloor(floor) != null && lot.getFloor(floor).getSpotsCount() > 0
					&& lot.removeParkingSpot(floor, spotNo)) {
				System.out.println(spotNo + " Spot Removed on Floor " + floor + "..");
			} else {
				System.out.println("Cannot remove Parking Spot");
			}
		} else {
			System.out.println("Admin is not ACTIVE!");
		}
	}

}
