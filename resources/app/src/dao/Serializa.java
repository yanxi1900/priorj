package dao;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Serialize to data persistence.
 * 
 * @author Samuel T. C. Santos
 * @version 1.0
 */
public class Serializa implements Serializable {

	private static final String data = "data.ser";

	public static void serializes(Object obj) throws IOException,
			FileNotFoundException {
		ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(data)));
		oos.writeObject(obj);
		oos.close();
	}
}