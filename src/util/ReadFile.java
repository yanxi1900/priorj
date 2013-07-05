package util;

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
