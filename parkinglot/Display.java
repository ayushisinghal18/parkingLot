package parkinglot;

public class Display {

	private int levelNo;
	private Parkspot bikeVacantSpot;
	private Parkspot carVacantSpot;
	private Parkspot truckVacantSpot;

	// display vacant parking spots
	public String show(Level l) {
		levelNo = l.getLevelNo();
		bikeVacantSpot = l.getVacantParkingSpot(VehicleSize.SMALL.getSize());
		carVacantSpot = l.getVacantParkingSpot(VehicleSize.MEDIUM.getSize());
		truckVacantSpot = l.getVacantParkingSpot(VehicleSize.BIG.getSize());

		return "******Level=" + levelNo + "*****\nBikeSpot="
				+ (bikeVacantSpot != null ? bikeVacantSpot.getSpotNo() : "FULL") + ", CarSpot="
				+ (carVacantSpot != null ? carVacantSpot.getSpotNo() : "FULL") + ", TruckSpot="
				+ (truckVacantSpot != null ? truckVacantSpot.getSpotNo() : "FULL");

	}

}
