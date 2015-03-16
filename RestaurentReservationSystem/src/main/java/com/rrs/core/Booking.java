/**
 * 
 */
package com.rrs.core;

import java.util.Date;

/**
 * @author Kishor
 *
 */
public class Booking {
	
	private int id;
	private Date date;
	private int hour;
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getNoOfGuests() {
		return noOfGuests;
	}

	public void setNoOfGuests(int noOfGuests) {
		this.noOfGuests = noOfGuests;
	}

	private int noOfGuests;
	
	public boolean performBooking() {
		return true;
	}
	
	public Booking(int id, Date date, int hr, int noOfGuests) {
		this.id = id;
		this.date = date;
		this.hour = hr;
		this.noOfGuests = noOfGuests;
	}
	
	private boolean status;
	private String message;
	private int bookedTableId;
	
	
	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getBookedTableId() {
		return bookedTableId;
	}

	public void setBookedTableId(int bookedTableId) {
		this.bookedTableId = bookedTableId;
	}

	

}
