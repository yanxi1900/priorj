package dao;

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

import com.thoughtworks.xstream.XStream;
import java.io.FileInputStream;
import java.util.List;

/**
 * Reader Xstream files.
 * @author SamuelSantos
 */
public class XStreamRead {
    
    private String filename;
    private String extension;
        /**
         * Constructor
         *
         * @param filename - The file name.
         */
	public XStreamRead(String filename){
		this.filename = filename;
                this.extension = "xml";
	}

        /**
         * Read a list of object from xml.
         * @return - a generic list
         * @throws Exception  - erro read file
         */
	public List read() throws Exception{

		XStream xml = new XStream();
                
                FileInputStream fis = new FileInputStream(filename + "." + extension);
                
                List list = (List) xml.fromXML(fis);
            
                fis.close();
                
		return list;
	}
}
