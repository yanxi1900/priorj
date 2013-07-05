package dao;

import com.thoughtworks.xstream.XStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Writer into XStream file.
 * 
 * @author SamuelSantos
 */
public class XStreamWrite {
   
    private String filename;
    private String extension;

    /**
     * Construtor
     * @param filename - String with file name.
     */
    public XStreamWrite(String filename){
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
            FileOutputStream fos = new FileOutputStream(filename + "." + extension); 
            
            xml.toXML(object, fos);
            fos.close();
            
        } catch (FileNotFoundException ex) {
            System.err.println(ex.getMessage());
        }
        catch ( IOException ioe){
            System.out.println(ioe.getMessage());
        }

    } 
}
