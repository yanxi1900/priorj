package dao;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class Deserializa  implements Serializable  {
	
	private static final String data = "data.ser";

	public static Object deserialize() throws IOException, ClassNotFoundException {
		
		File file = new File("data.ser");
		
		if (!file.exists()){
			file.createNewFile();
			return new Object();
		}
		
		ObjectInputStream ios = new ObjectInputStream(new BufferedInputStream(new FileInputStream(data)));
		Object object = ios.readObject();
		ios.close();
		return object;
	}
}