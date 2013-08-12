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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.LinkedList;
import java.util.List;

/**
 * Class to read text file.
 * 
 * @author Samuel T. C. Santos
 * @version 1.0
 * 
 */
public class ReadFile {
    
    /**
     * Read a text file.
     * @param filename
     * @return - the content of the file.
     */
    public static String read(String filename) throws Exception {
        
        String result = "";
        
        File file = new File(filename);
        
        if(!file.exists()) throw new Exception("file not found!");
        
        FileInputStream f = new FileInputStream(file);
        
        FileChannel canal = f.getChannel();
        
        ByteBuffer buf = ByteBuffer.allocate(32768);
        
        int bytesRead = canal.read(buf);
        
        while (bytesRead != -1) {
            
            buf.flip();
            
            while (buf.hasRemaining()) {
                result += "" + (char) buf.get();
            }
            
            buf.clear();
            
            bytesRead = canal.read(buf);
        }
        buf.clear();
        canal.close();
        f.close();
        
        return result;
    }
    /**
     * This method return a list with the names of tests found into the file.
     * 
     * @param filename
     * 		the file name.
     * @return
     * 		A list.
     */
    public static List<String> readFileAndReturnList(String filename){
        List<String> list = new LinkedList<String>();
        
        try {
            BufferedReader in = new BufferedReader(new FileReader(filename));
            String str;
            while (in.ready()) {
                str = in.readLine();
                if (str.contains("()")){
                    String split [] = str.split(" ");
                    list.add(split[2].trim().replace("()",""));
                }
            }
            in.close();
            return list;
        } catch (IOException e) {
            System.out.println("Read file and return list error: " + e.getMessage());
            return new LinkedList<String>();
        }
    }
    

}
