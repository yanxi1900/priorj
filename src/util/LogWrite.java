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
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class LogWrite {
    private File arquivo;
    private FileOutputStream logFile;
    private String quebra;
    private boolean exists;

    public LogWrite(String name, String extension) {
    	this.arquivo = new File(name + "." + extension);
    	this.exists = this.arquivo.exists();
    	this.quebra = System.getProperty("line.separator");
    	try {
    		logFile = new FileOutputStream(arquivo, true);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    
    public LogWrite(String name) {
    	this(name, "txt");
    }
    
    public boolean exists(){
    	return this.exists;
    }


    public void write(String str) {
        
        String texto = str + quebra;
        
        try {
            
            logFile.write(texto.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
         try {    
            logFile.close();
        } catch (IOException e) {
        }
    }

}
