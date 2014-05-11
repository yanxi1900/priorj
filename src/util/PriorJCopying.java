package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

/**
 * This class do a copy of each file in the select eclipse project.
 * 
 * @author Samuel Thiago Canuto dos Santos
 *
 */
public class PriorJCopying {

	/**
	 * Do a copy from a file.
	 * 
	 * @param reader
	 * @param writer
	 * @throws IOException 
	 */
	public static void copy(Reader reader , Writer writer) throws IOException{
		
		BufferedReader lines = new BufferedReader(reader);
		
		String line;
		
		while( null != (line = lines.readLine())){
			writer.write(line + '\n');			
		}
		
		writer.flush();
	}
}
