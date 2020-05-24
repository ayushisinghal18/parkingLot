package main;

import java.util.Scanner;

import parkinglot.ParkingLot;
import parkinglot.Vehicle;
import parkinglot.VehicleSize;

public class TestParkingLot {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);

		System.out.println("Enter levels: ");
		int levels = scan.nextInt();
		System.out.println("Enter parking spots: ");
		int parkingSpots = scan.nextInt();

		//Singleton Pattern
		ParkingLot lot = ParkingLot.getInstance(levels, parkingSpots);

		startParking(lot, scan);
	}

	private static void startParking(ParkingLot lot, Scanner scan) {
		boolean isParkingSpotVacant = true;
		boolean changeInSpotState = false;

		System.out.println("Welcome to Parking Lot..");
		System.out.println("\n -*-*-Main Display Board -*-*-");
		lot.displaySpots();

		while (isParkingSpotVacant) {
			System.out.println("Please choose one: \n "
					+ "1-> Park. \n 2-> Exit ");
			int action = scan.nextInt();
			switch (action) {
			case 1: // alot a parking spot
				System.out.println("Enter vehicle size: \n "
						+ "1-> SMALL. \n 2-> MEDIUM. \n 3-> BIG");
				int vehicleSize = scan.nextInt();
				System.out.println("Enter vehicle no: ");
				String vehicleNo = scan.next();

				Vehicle vehicle = new Vehicle(vehicleSize, vehicleNo);

				int spotNo = lot.allocateParkingSpot(vehicle);
				if (spotNo > -1) {
					System.out.println("Vehicle Parked at spot " + spotNo);
					changeInSpotState = true;
				} else {
					changeInSpotState = false;
				}
				break;
			case 2: // remove vehicle from parking spot
				System.out.println("Enter level no:");
				int levelNo = scan.nextInt();
				System.out.println("Enter Parking spot no:");
				int vacantSpotNo = scan.nextInt();
				vacantSpotNo = lot.removeVehicle(levelNo, vacantSpotNo);
				if (vacantSpotNo > -1) {
					System.out.println("Vehicle removed from spot " + vacantSpotNo);
					changeInSpotState = true;
				} else {
					changeInSpotState = false;
				}
				break;

			default:
				System.out.println("Invalid operation!");
				changeInSpotState = false;
				break;
			}
			if (changeInSpotState) {
				System.out.println("\n -*-*-Main Display Board -*-*-");
				lot.displaySpots();
			}

			isParkingSpotVacant = lot.checkParkingSpotVacany();
		}

		System.out.println("\n .....Parking is full.....");
	}
}
