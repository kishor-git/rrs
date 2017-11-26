/**
 * 
 */
package com.rrs.processor;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.rrs.core.Booking;
import com.rrs.core.Restaurant;
import com.rrs.filter.BookingFilter;
import com.rrs.filter.DayHourBookingFilter;
import com.rrs.filter.HolidayBookingFilter;

/**
 * @author Kishor
 *
 */
public class RestaurantManager {
	
	private Restaurant restaurent;
	private List<Booking> bookings;
	
	private Scanner inputScanner = new Scanner(System.in);;
	
	private static final String DATE_FORMAT = "yyyy-MM-dd";
	
	public RestaurantManager() {
		try {
			restaurent = new Restaurant("RESTAURANT");
		} catch (IOException e) {
			System.out.println("Error Occured while initializaing Restauren information. Cannot perfrom any booking. Error: " + e );
		}
		restaurent.setChainOfFilters(loadFilters());		
		bookings = new ArrayList();
	}
	
	public void startBooking() {
		int bookingCount = 0;		
		
		while(true) {
			System.out.print("New Booking (y/n):");
			
			Booking booking;

			try {
				char newBooking = inputScanner.next().charAt(0);
				
				if('n' == newBooking || 'N' == newBooking) {
					break;
				}
				
				booking = takeNewBooking(bookingCount++);
			} catch (Exception e) {
				System.out.println("Invalid booking details. please try again!!" + e);
				continue;
			}
			
			processBooking(booking);
			
			if(booking.isStatus()) {
				bookings.add(booking);
			}
			
			System.out.println(booking.getMessage());
			
		}	
	}
	
	private Booking takeNewBooking(int bookingCount) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		System.out.println("Booking Id:" + bookingCount);
		System.out.print("Booking for Date (yyyy-MM-dd):");
		String date = inputScanner.next();
		System.out.println();
		System.out.print("Booking for Hour of the Day (24):");
		int hour = inputScanner.nextInt();
		System.out.println();
		System.out.print("Booking for how many?:");
		int noOfGuests = inputScanner.nextInt();
		System.out.println();					
		Date bookingDate = sdf.parse(date);
		
		return new Booking(bookingCount, bookingDate, hour, noOfGuests);
	}
	
	private void processBooking(Booking booking) {
		
		if(!restaurent.isBookingOpen(booking)) {
			booking.setStatus(Boolean.FALSE);
			booking.setMessage("Restaurent booking is not open for this day or hour");
			return;
		} 
		
		int selectedTable = restaurent.selectTable(booking);
		
		if(selectedTable == 0) {
			booking.setStatus(Boolean.FALSE);
			booking.setMessage("No vacancy in the restaurent to take your order now. Please visit later!!!");
		} else {
			booking.setStatus(Boolean.TRUE);
			booking.setBookedTableId(selectedTable);
			booking.setMessage("Booking success. Table number: " + selectedTable);
		}	
	}

	
	private BookingFilter loadFilters() {
		System.out.println("Loading chaing of filters");
		BookingFilter holidayBookingFilter = HolidayBookingFilter.getInstance();
		holidayBookingFilter.setNextFilter(DayHourBookingFilter.getInstance());
		return holidayBookingFilter;	
	}
	
	@Override
	protected void finalize() {
		inputScanner.close();

	}
	
}
