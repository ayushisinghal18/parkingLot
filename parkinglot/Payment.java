package parkinglot;

import java.text.ParseException;
import java.util.Date;

import parkinglot.enums.ParkingRateList;
import parkinglot.enums.ParkingSpotType;
import parkinglot.enums.ParkingTicketStatus;
import parkinglot.utils.DateTimeUtils;

public class Payment {

	private String message;

	public void initTransaction(ParkingTicket ticket, ParkingSpotType type) {
		Date paidAt = new Date();
		int hours = 0;
		try {
			hours = DateTimeUtils.parseDateIntoTime(ticket.getIssuedAt(), paidAt);
		} catch (ParseException e) {
			System.out.println("System error for Parking Ticket");
			e.printStackTrace();
		}

		double rate = hours * ParkingRateList.getParkingRate(type);
		ParkingRate parkingRate = new ParkingRate(rate, hours);

		ticket.setRate(parkingRate);
		ticket.setStatus(ParkingTicketStatus.PAID);
		ticket.setPaidAt(paidAt);
		message = ticket.toString();

		displayPaymentMessage(message);
	}

	private void displayPaymentMessage(String message) {
		System.out.println(message);
	}

}
