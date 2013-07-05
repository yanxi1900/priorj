package util;

import com.thoughtworks.xstream.XStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe responsável por criar e escrever dados no arquivo xml.
 * @author Convidado
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
