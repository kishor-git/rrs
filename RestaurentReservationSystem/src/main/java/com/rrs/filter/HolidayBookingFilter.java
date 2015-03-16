/**
 * 
 */
package com.rrs.filter;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.rrs.core.Booking;
import com.rrs.utils.FileReader;

/**
 * @author Kishor
 *
 */
public class HolidayBookingFilter extends BookingFilter {

	private static final String DATE_FORMAT = "yyyy-MM-dd";
	private static List<Date> holidays =  new ArrayList<>();
	private static final BookingFilter holidayBookingFilter = new HolidayBookingFilter();
	
	private HolidayBookingFilter() {
		
		System.out.println("Loading holidays");
		
		try {
			load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void load() throws IOException, ParseException {
		List<String> holidayData = FileReader.readFile("holidays.txt");
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		for (String holiday : holidayData) {
			Date date = sdf.parse(holiday);
			holidays.add(date);			
		}
		
	}

	public static BookingFilter getInstance() {
		return holidayBookingFilter;
	}
	
	@Override
	protected boolean applyThisFilter(Booking booking) {
		if(holidays.contains(booking.getDate())) {
			return false;
		}
		return true;
	}
}
