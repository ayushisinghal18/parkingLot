package parkinglot;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum VehicleSize {

	SMALL(1, "SMALL"), MEDIUM(2, "MEDIUM"), BIG(3, "BIG");

	private final int size;
	private final String value;

	private VehicleSize(int size, String value) {
		this.size = size;
		this.value = value;
	}

	public int getSize() {
		return size;
	}

	public String getValue() {
		return value;
	}

	// checks if parking spot of given vehicle size is available
	public static VehicleSize checkSize(int size) {
		return Arrays.stream(VehicleSize.values()).filter(s -> s.getSize() == size).findAny().orElse(null);
	}

	private static final List<VehicleSize> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
	private static final int SIZE = VALUES.size();
	private static final Random RANDOM = new Random();

	// randomly allocate a vehicle type to a parking spot
	public static VehicleSize randomVehicleSize() {
		return VALUES.get(RANDOM.nextInt(SIZE));
	}

	// gives vehicle types based on size
	public static String getVehicleType(int size) {
		return Arrays.stream(VehicleSize.values()).filter(s -> s.getSize() == size).findAny().get().getValue();
	}
}
