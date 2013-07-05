package dao;

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
