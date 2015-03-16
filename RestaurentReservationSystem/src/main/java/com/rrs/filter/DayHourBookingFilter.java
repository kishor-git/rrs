/**
 * 
 */
package com.rrs.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rrs.core.Booking;
import com.rrs.utils.FileReader;

/**
 * @author Kishor
 *
 */
public class DayHourBookingFilter extends  BookingFilter {
	
	private static Map<Integer, List<Integer>> dayHourAvailabilityMap = new HashMap<>();
	private static final BookingFilter dayBookingFilter = new DayHourBookingFilter();
	
	private DayHourBookingFilter() {
		System.out.println("Loading day hour availabiliy");
		try {
			load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static BookingFilter getInstance() {
		return dayBookingFilter;
	}
	
	private void load() throws IOException {
		List<String> dayHourAvailabilityData = FileReader.readFile("day_hour_availability.txt");
		
		for (String dayHourAvailability : dayHourAvailabilityData) {
			String[] data = dayHourAvailability.split(" ");
			int day = Integer.parseInt(data[0]);
			
			String hrData = data[1];
			
			String[] hrs = hrData.split(",");
			List<Integer> hrList = new ArrayList<>();
			for (String hr : hrs) {
				hrList.add(Integer.parseInt(hr));
			}
			
			dayHourAvailabilityMap.put(day,hrList);
			
		}
		
	}

	@Override
	protected boolean applyThisFilter(Booking booking) {
		
		List<Integer> availableHours = dayHourAvailabilityMap.get(getDay(booking.getDate()));
		int bookingHour = booking.getHour();
		
		for (int hour : availableHours) {
			if(bookingHour == hour) {
				return true;
			}
		}		
		
		return false;
	}

	private static int getDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_WEEK);
		
	}


}
