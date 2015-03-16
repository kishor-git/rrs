/**
 * 
 */
package com.rrs.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Kishor
 *
 */
public class FileReader {

	public static List<String> readFile(String location) throws IOException {
		
		File f = new File(location);
		BufferedReader buffer = new BufferedReader(new java.io.FileReader(f));
		List<String> fileData = new ArrayList<String>();
		String line;
		while((line = buffer.readLine()) != null) {
			fileData.add(line);
		}
		buffer.close();
		return fileData;
	}
}
