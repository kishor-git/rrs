/**
 * 
 */
package com.rrs.filter;

/**
 * @author Kishor
 *
 */
public class HourlyCapacityFilter implements CapacityFilter {
	
	private static final int LUNCH_CLOSING_HOUR = 16;

	private static final CapacityFilter capacityFilter = new HourlyCapacityFilter();
	
	private HourlyCapacityFilter() {
		
	}
	
	public static CapacityFilter getInstance() {
		return capacityFilter;
	}
	
	public int calculateCapacity(int hour, int capacity) {
		if(hour < LUNCH_CLOSING_HOUR) {
			return 2*capacity;
		} else {
			return capacity;
		}		
	}

}
