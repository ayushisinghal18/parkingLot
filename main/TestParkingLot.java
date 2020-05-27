package main;

import java.util.Scanner;

import parkinglot.ParkingFloor;
import parkinglot.ParkingLot;
import parkinglot.ParkingSpot;
import parkinglot.Vehicle;
import parkinglot.actors.Admin;
import parkinglot.parkingspots.BigSpot;
import parkinglot.parkingspots.BikeSpot;
import parkinglot.parkingspots.CompactSpot;
import parkinglot.vehicles.Bike;
import parkinglot.vehicles.Car;
import parkinglot.vehicles.Truck;
import parkinglot.vehicles.Van;

public class TestParkingLot {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);

		// Singleton Pattern
		ParkingLot lot = ParkingLot.getInstance();
		Admin admin = Admin.getInstance();

		performAdminActions(admin, scan);
		startParking(lot, scan);
	}

	private static void performAdminActions(Admin admin, Scanner scan) {
		System.out.println("Hello Admin!");
		boolean buildSystem = true;

		int spotNo = 0;
		int floorNo = 0;

		while (buildSystem) {
			System.out.println("Please provide input: " + "\n 1-> Add Floor. \n 2-> Add Parking Spot. "
					+ "\n 3-> Remove Floor. \n 4-> Remove Parking Spot.");
			int input = scan.nextInt();

			switch (input) {
			case 1:
				floorNo++;
				addFloor(scan, admin, floorNo);
				System.out.println("Add Parking Spot: ");
				break;
			case 2:
				spotNo++;
				addParkingSpot(scan, admin, spotNo);
				break;
			case 3:
				removeFloor(scan, admin);
				break;
			case 4:
				removeParkingSpot(scan, admin);
				break;
			default:
				System.out.println("Invalid input!");
				break;
			}

			System.out.println("Want to continue building the system? (Y/N)?");
			String adminInput = scan.next();
			if (adminInput.equalsIgnoreCase("y")) {
				buildSystem = true;
			} else {
				buildSystem = false;
			}
		}
	}

	private static void addFloor(Scanner scan, Admin admin, int floorNo) {
		ParkingFloor floor = new ParkingFloor(floorNo);
		admin.addParkingFloor(floor);
	}

	private static void removeFloor(Scanner scan, Admin admin) {
		System.out.println("Enter Floor number: ");
		int floorNo = scan.nextInt();
		admin.removeParkingFloor(floorNo);
	}

	private static void addParkingSpot(Scanner scan, Admin admin, int spotNo) {
		System.out.println("Enter floor number: ");
		int floorNo = scan.nextInt();

		System.out.println("Enter Spot type: " + "\n 1-> Bike. " + "\n 2-> Compact. " + "\n 3-> Big ");
		int spotType = scan.nextInt();
		ParkingSpot spot = null;

		switch (spotType) {
		case 1:
			spot = new BikeSpot(spotNo);
			break;
		case 2:
			spot = new CompactSpot(spotNo);
			break;
		case 3:
			spot = new BigSpot(spotNo);
			break;
		default:
			System.out.println("Invalid input!");
			break;
		}

		admin.addParkingSpot(floorNo, spot);
	}

	private static void removeParkingSpot(Scanner scan, Admin admin) {
		System.out.println("Enter floor number: ");
		int floorNo = scan.nextInt();

		System.out.println("Enter Spot number: ");
		int spotNo = scan.nextInt();

		admin.removeParkingSpot(floorNo, spotNo);
	}

	private static void startParking(ParkingLot lot, Scanner scan) {
		System.out.println("Welcome to Parking Lot..");

		boolean isParkingFull = lot.displayBoardforFullParking();
		if (isParkingFull)
			return;

		while (true) {
			System.out.println("\nPlease choose one: " + "\n 1-> Park. " + "\n 2-> Exit ");
			int action = scan.nextInt();
			switch (action) {
			case 1: // alot a parking spot
				boolean isParkingSpotVacant = lot.diplayParkingFull();
				if (!isParkingSpotVacant) {
					System.out.println("Enter vehicle type: " + "\n 1-> BIKE. " + "\n 2-> CAR. " + "\n 3-> TRUCK. "
							+ "\n 3-> VAN");
					int vehicleType = scan.nextInt();
					Vehicle vehicle = null;
					switch (vehicleType) {
					case 1:
						vehicle = new Bike();
						break;
					case 2:
						vehicle = new Car();
						break;
					case 3:
						vehicle = new Truck();
						break;
					case 4:
						vehicle = new Van();
						break;
					default:
						System.out.println("Invalid input!");
						break;
					}

					System.out.println("Enter vehicle number: ");
					String vehicleNo = scan.next();
					vehicle.setVehicleNumber(vehicleNo);

					lot.assignVehicleToSpot(vehicle);
				}
				break;
			case 2: // remove vehicle from parking spot
				System.out.println("Enter Parking spot no:");
				int spotNo = scan.nextInt();
				System.out.println("Enter vehicle number: ");
				String vehicleNumber = scan.next();

				lot.removeVehicleFromSpot(spotNo, vehicleNumber);
				break;

			default:
				System.out.println("Invalid operation!");
				break;
			}
		}
	}
}
