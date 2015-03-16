/**
 * 
 */
package com.rrs.core;


/**
 * @author Kishor
 *
 */
public class Table {
	
	private static final int LUNCH_CLOSING_HOUR = 16;	
		
	private int id;
	private int capacity;
	
		
	public boolean bookThisTable(int noOfGuests, int hr) {
		
		int hourlyCapacity = getHourlyCapacity(hr);
		
		if(noOfGuests <= hourlyCapacity) {
			return true;
		}
		
		return false;
	}

	private int getHourlyCapacity(int hr) {
		if(hr < LUNCH_CLOSING_HOUR) {
			return 2*capacity;
		} else {
			return capacity;
		}
	}
	
	public Table(int id, int capacity) {
		this.id = id;
		this.capacity = capacity;
	}

	public int getId() {
		return id;
	}

}
