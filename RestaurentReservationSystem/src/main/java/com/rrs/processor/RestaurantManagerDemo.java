/**
 * 
 */
package com.rrs.processor;

import java.io.IOException;
import java.text.ParseException;

/**
 * @author Kishor
 *
 */
public class RestaurantManagerDemo {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws ParseException 
	 */
	public static void main(String[] args)  {
		RestaurantManager manager = new RestaurantManager();
		manager.startBooking();
	}

}
