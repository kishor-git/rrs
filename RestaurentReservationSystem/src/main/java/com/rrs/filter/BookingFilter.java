package com.rrs.filter;

import com.rrs.core.Booking;

/**
 * 
 * 
 * @author Kishor
 *
 */
public abstract class BookingFilter {
	
	private BookingFilter nextFilter;
	
	public boolean isBookingAvailable(Booking booking) {
		boolean result = this.applyThisFilter(booking);
		
		if(result) {
			if(nextFilter != null) {
				result = result && nextFilter.isBookingAvailable(booking);
			}			
		}
		
		return result;
	}
	
	public void setNextFilter(BookingFilter nextFilter) {
		this.nextFilter = nextFilter;
	}
	
	protected abstract boolean applyThisFilter(Booking booking);
	
	

}
