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
import com.thoughtworks.xstream.XStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class write to a xml file.
 * 
 * @author Samuel T. C. Santos
 * @version 1.0
 * 
 */
public class WriteXML {

    private String filename;
    private String extension;

    /**
     * Construtor
     * @param filename - String with file name.
     */
    public WriteXML (String filename){
        this.filename = filename;
        this.extension = "xml";
    }

    /**
     * Write in to xml file.
     * @param object
     */
    public void write (Object object){
        XStream xml = new XStream();

        try {
            xml.toXML(object, new FileOutputStream(filename + "." + extension));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(WriteXML.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
    
}
