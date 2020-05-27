package parkinglot.enums;

import java.util.Arrays;

public enum VehicleType {

	BIKE, CAR, TRUCK, VAN;

	public static VehicleType isValidVehicle(VehicleType type) {
		return Arrays.stream(VehicleType.values()).filter(s -> s == type).findAny().orElse(null);
	}
}
