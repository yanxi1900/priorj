package util;

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
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;

import coverage.TestSuite;

/**
 * This class read a XML file with XStream.
 * 
 * @author Samuel T. C. Santos
 * @version 1.0
 *
 */
public class Reader {
	
	/**
	 * String with file name.
	 */
	private String arquivoName;
	
	private final String separator = System.getProperty("file.separator");
	
	/**
	 * Construct with file name.
	 * 
	 * @param arquivoName
	 */
    public Reader(String arquivoName){
		this.arquivoName = arquivoName;
	}
	
    /**
     * Read the XML file.
     * 
     * @return
     * 		An Objects list.
     * 
     * @throws Exception
     * 		Error in the file reading.
     */
	public List read() throws Exception{
		LogRead log = new LogRead(arquivoName, "xml");		
		XStream xml = new XStream();
		LogWrite logWrite = new LogWrite("report"+separator+"coveragePriorJ", "xml");
		logWrite.write("</list>");
		logWrite.close();
		List<List> lista = (List<List>)xml.fromXML(log.readFile());
		List listaCompleta = new ArrayList<TestSuite>();
		for (List list : lista) {
			listaCompleta.addAll(list);
		}
		return listaCompleta;
	}
	
}
