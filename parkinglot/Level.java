package parkinglot;

import java.util.ArrayList;
import java.util.List;

public class Level {

	private int levelNo;
	private List<Parkspot> parkspots;
	private Display display;

	// initialize parking spots
	public Level(int levelNo, int parkingspotNo) {
		this.levelNo = levelNo;
		parkspots = new ArrayList<Parkspot>(parkingspotNo);
		for (int i = 0; i < parkingspotNo; i++) {
			parkspots.add(new Parkspot(levelNo, i));
		}
		display = new Display();
	}

	// returns next vacant Parking Spot
	public Parkspot getVacantParkingSpot(int size) {

		for (Parkspot parkspot : parkspots) {
			if (parkspot.getSpotSize().getSize() == size && parkspot.isVacant()) {
				return parkspot;
			}
		}
		return null;
	}

	// returns occupied parking spot
	public Parkspot getOccupiedParkingSpot(int spotNo) {

		for (Parkspot parkspot : parkspots) {
			if (parkspot.getSpotNo() == spotNo && !parkspot.isVacant()) {
				return parkspot;
			}
		}
		return null;
	}

	public void addVehicle(int spotNo, Vehicle vehicle) {
		parkspots.get(spotNo).addVehicle(vehicle);
	}

	public void removeVehicle(int spotNo) {
		parkspots.get(spotNo).removeVehicle();
	}

	public List<Parkspot> getParkingSpot() {
		return parkspots;
	}

	public void show(Level level) {
		System.out.println(display.show(level));
	}

	public Display getDisplay() {
		return display;
	}

	public int getLevelNo() {
		return levelNo;
	}
}
