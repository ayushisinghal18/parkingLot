package parkinglot;

public class Vehicle {
	private int size;
	private int spot;
	private String vehicleNo;

	public Vehicle() {
	}

	public Vehicle(int size, String vehicleNo) {
		this.size = size;
		this.vehicleNo = vehicleNo;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getSpot() {
		return spot;
	}

	public void setSpot(int spot) {
		this.spot = spot;
	}

	public String getVehicleNo() {
		return vehicleNo;
	}

	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}
}
