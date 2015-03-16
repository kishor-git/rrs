/**
 * 
 */
package com.rrs.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.rrs.filter.BookingFilter;
import com.rrs.filter.HourlyCapacityFilter;
import com.rrs.utils.FileReader;

/**
 * @author Kishor
 *
 */
public class Restaurant {
	
	private String name;
	
	private BookingFilter chainOfFilters;
	
	private List<Table> tables = new ArrayList<Table>();

	public List<Table> getTables() {
		return tables;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Restaurant() {
		
	}
	
	public Restaurant(String name) throws IOException  {
		setName(name);		
		loadTables();
	}
	
	private void loadTables() throws IOException {
		System.out.println("Loading Restaurent table data");
		List<String> tablesData = FileReader.readFile("tables.txt");
		
		for (String line : tablesData) {
			String[] tableData = line.split(" ");
			int id = Integer.parseInt(tableData[0]);
			int capacity = Integer.parseInt(tableData[1]);
			Table t = new Table(id, capacity);
			t.setCapaciyFilter(HourlyCapacityFilter.getInstance());
			tables.add(t);
		}		
	}

	public BookingFilter getChainOfFilters() {
		return chainOfFilters;
	}

	public void setChainOfFilters(BookingFilter chainOfFilters) {
		this.chainOfFilters = chainOfFilters;
	}

	public boolean isBookingOpen(Booking b) {
		return this.getChainOfFilters().isBookingAvailable(b);
	}
	
	public int selectTable(Booking booking) {
		for (Table table : tables) {
			if(!table.isBooked()) {
				if(table.bookThisTable(booking.getNoOfGuests(), booking.getHour())) {
					table.setBooked(Boolean.TRUE);
					return table.getId();
				}
			}
		}
		return 0;
		
		
	}
}
