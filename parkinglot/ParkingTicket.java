package parkinglot;

import java.util.Date;

import parkinglot.enums.ParkingTicketStatus;

public class ParkingTicket {

	private int ticketNumber;
	private ParkingTicketStatus status;
	private Date issuedAt;
	private Date paidAt;
	private ParkingRate rate;

	public ParkingTicket(int ticketNumber) {
		this.ticketNumber = ticketNumber;
		status = ParkingTicketStatus.ACTIVE;
		issuedAt = new Date();
	}

	public int getTicketNumber() {
		return ticketNumber;
	}

	public void setTicketNumber(int ticketNumber) {
		this.ticketNumber = ticketNumber;
	}

	public ParkingTicketStatus getStatus() {
		return status;
	}

	public void setStatus(ParkingTicketStatus status) {
		this.status = status;
	}

	public ParkingRate getRate() {
		return rate;
	}

	public void setRate(ParkingRate rate) {
		this.rate = rate;
	}

	public Date getIssuedAt() {
		return issuedAt;
	}

	public void setIssuedAt(Date issuedAt) {
		this.issuedAt = issuedAt;
	}

	public Date getPaidAt() {
		return paidAt;
	}

	public void setPaidAt(Date paidAt) {
		this.paidAt = paidAt;
	}

	@Override
	public String toString() {
		return "ParkingTicket [ticketNumber=" + ticketNumber + ", status=" + status + ", issuedAt=" + issuedAt
				+ ", paidAt=" + paidAt + ", rate=" + rate.toString() + "]";
	}

}
