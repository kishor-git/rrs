/**
 * 
 */
package com.rrs.processor;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.rrs.core.Booking;
import com.rrs.core.Restaurant;
import com.rrs.core.Table;
import com.rrs.filter.BookingFilter;
import com.rrs.filter.DayHourBookingFilter;
import com.rrs.filter.HolidayBookingFilter;

/**
 * @author Kishor
 *
 */
public class RestaurantManager {
	
	private Restaurant restaurent;
	private List<Table> availableTables;
	
	private static final String DATE_FORMAT = "yyyy-MM-dd";
	
	public RestaurantManager() throws IOException {
		restaurent = new Restaurant("VENKI");
		restaurent.setChainOfFilters(loadFilters());
		availableTables = new ArrayList<>(restaurent.getTables());
	}
	
	public void startBooking() throws ParseException {
		int bookingCount = 0;
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		Scanner sc;
		while(true) {
			System.out.print("New Booking (y/n):");
			sc = new Scanner(System.in);
			char newBooking = sc.next().charAt(0); 
			if('n' == newBooking || 'N' == newBooking) {
				break;
			}
			System.out.print("\nBooking Id:" + ++bookingCount);
			System.out.print("Booking for Date (yyyy-MM-dd):");
			String date = sc.next();
			System.out.println();
			System.out.print("Booking for Hour of the Day (24):");
			int hour = sc.nextInt();
			System.out.println();
			System.out.print("Booking for how many?:");
			int noOfGuests = sc.nextInt();
			System.out.println();
			Date bookingDate = sdf.parse(date);
			
			Booking b = new Booking(bookingCount, bookingDate, hour, noOfGuests);
			processBooking(b);
		}
		sc.close();
		
		
	}
	
	private void processBooking(Booking b) {
		
		if(!restaurent.isBookingOpen(b)) {
			System.out.println("Restaurent booking is not open for this day or hour");
			return;
		}
		
		Iterator<Table> tableIerator = availableTables.iterator();
		
		while(tableIerator.hasNext()) {
			Table table = tableIerator.next();
			if(table.bookThisTable(b.getNoOfGuests(), b.getHour())) {
				System.out.println("Booked Table " + table.getId());
				b.setStatus(Boolean.TRUE);
				availableTables.remove(table);
				return;
			}
		}
		System.out.println("No vacancy in the restaurent to take your order now. Please visit later!!!");
		
	}

	
	private BookingFilter loadFilters() {
		System.out.println("Loading chaing of filters");
		BookingFilter holidayBookingFilter = HolidayBookingFilter.getInstance();
		holidayBookingFilter.setNextFilter(DayHourBookingFilter.getInstance());
		return holidayBookingFilter;	
	}
	
	
	
}
