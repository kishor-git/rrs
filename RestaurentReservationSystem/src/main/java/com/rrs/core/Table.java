/**
 * 
 */
package com.rrs.core;

import com.rrs.filter.CapacityFilter;


/**
 * @author Kishor
 *
 */
public class Table {		
		
	private int id;
	private int capacity;
	private CapacityFilter capaciyFilter;	
	private boolean isBooked;
		
	public CapacityFilter getCapaciyFilter() {
		return capaciyFilter;
	}

	public void setCapaciyFilter(CapacityFilter capaciyFilter) {
		this.capaciyFilter = capaciyFilter;
	}

	public boolean bookThisTable(int noOfGuests, int hr) {
		
		int hourlyCapacity = capaciyFilter.calculateCapacity(hr, capacity);
		
		if(noOfGuests <= hourlyCapacity) {
			return true;
		}
		
		return false;
	}
	
	public Table(int id, int capacity) {
		this.id = id;
		this.capacity = capacity;		
	}

	public int getId() {
		return id;
	}

	public boolean isBooked() {
		return isBooked;
	}

	public void setBooked(boolean isBooked) {
		this.isBooked = isBooked;
	}

}
