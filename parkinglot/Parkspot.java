package parkinglot;

public class Parkspot {

	private int spotNo;
	private Vehicle vehicle;
	private int level;
	private VehicleSize type;
	private boolean isVacant;

	public Parkspot(int levelNo, int spotNo) {
		this.spotNo = spotNo;
		this.level = levelNo;
		isVacant = true;
		type = VehicleSize.randomVehicleSize();
	}

	public boolean isVacant() {
		return isVacant;
	}

	public void spotOccupied(Vehicle vehicle) {
		this.isVacant = false;
		this.vehicle = vehicle;
	}

	public void vacantSpot() {
		this.isVacant = true;
		this.vehicle = null;
	}

	public int getSpotNo() {
		return spotNo;
	}

	public VehicleSize getSpotSize() {
		return type;
	}

	public int getLevelNo() {
		return level;
	}

	public String getVehicleType() {
		return type.getValue();
	}

	@Override
	public String toString() {
		return "Parkspot [spotNo=" + spotNo + ", vehicle=" + vehicle + ", level=" + level + ", type=" + type
				+ ", isVacant=" + isVacant + "]";
	}
}
