package input;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.tools.ant.types.FileList.FileName;

import util.FileManager;

/*
 * PriorJ: JUnit Test Case Prioritization.
 * 
 * Copyright (C) 2012-2013  Samuel T. C. Santos
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
/**
 * This class read a PriorJ configurations file and do a parse.
 * 
 * @author Samuel T. C. Santos
 * @version 1.0
 */

public class InputParse {

	/**
	 * Path to configuration file.
	 */
	String configFile;

	/**
	 * objects found in the configurations file.
	 */
	List<InputObject> listParse;

	/**
	 * Construct default.
	 */
	public InputParse(){
		listParse = new ArrayList<InputObject>();
	}
	
	/**
	 * Construct to Input parse with configuration file path.
	 * 
	 * @param filename
	 *            The configuration file path.
	 */
	public InputParse(String filename) {
		this();
		configFile = filename;
	}
	
	/**
	 * This method do the parse process.
	 */
	public void runParse() {
		if ( checkFile()) 
			doParse();
	}

	/**
	 * This method verify the configuration file.
	 * 
	 * @return
	 */
	public boolean checkFile() {
		return FileManager.existFileOrDirectory(configFile);
	}

	/**
	 * Do parse.
	 */
	private void doParse() {
		 
        try {
            BufferedReader in = new BufferedReader(new FileReader(configFile));
            String line;
            while (in.ready()) {
                line = in.readLine();
                handlerInput(line);
            }
            in.close();
            
        } catch (IOException e) {
            System.out.println("error in the parse: " + e.getMessage());
        }
	}
	
	/**
	 * 
	 * This method is a handler input.
	 * 
	 * @param line
	 */
	private void handlerInput(String line) {
		InputObject inputObj = lineParse(line);
		
		if (!inputObj.isEmpty())
			listParse.add(inputObj);
	}
	
	/**
	 * This method do a line parse.
	 * 
	 * @param line	
	 * 		A string with content line.
	 * @return
	 * 		An object with content line.
	 */
	public InputObject lineParse(String line){
		InputObject input = new InputObject();
		
		if (line.contains(":"))
			input = extractData(line);
		
		return input;
	}
	
	/**
	 * this method extract data from line.
	 * 
	 * @param line
	 * @return
	 */
	public InputObject extractData(String line){
		
		InputObject obj = new InputObject();
		
		String split[] = line.split(":");
		
		String property = split[0];
		String value = split[1];
		
		property = property.replace("[", "");
		property = property.replace("]", "");
		
		value = value.replace("[", "");
		value = value.replace("]", "");
		
		obj.setProperty(property);
		
		if(value.equals("empty")) 
			obj.setValue("");
		else 
			obj.setValue(value);
		
		return obj;
	}
	
	
	/**
	 * This method get the objects result from parse.
	 * 
	 * @return
	 */
	public List<InputObject> getResult(){
		return listParse;
	}
	
	/**
	 * Get the value associated with this property.
	 * 
	 * @param property
	 * @return
	 */
	public String getValue(Property property){
		return valueToProperty(property.getName());
	}
	
	/**
	 * This method get a object with the informed property.
	 * 
	 * @param property
	 * @return
	 */
	public InputObject getObjectWithProperty(String property){
		for (InputObject obj : listParse){
			if (obj.getProperty().equals(property))
				return obj;
		}
		
		return new InputObject();
	}
	
	/**
	 * This method return the value to informed property.
	 * 
	 * @param property
	 * @return
	 */
	public String valueToProperty(String property){
		InputObject input = getObjectWithProperty(property);
		return input.getValue();
	}

	public String getValue(PropertyTest application) {
		// TODO Auto-generated method stub
		return "";
	}
	
	
	
}
