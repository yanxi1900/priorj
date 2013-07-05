package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class WriteFile {
    private File arquivo;
    private FileOutputStream logFile;
    private String quebra;
    private boolean exists;

    public WriteFile(String name, String extension) {
    	this.arquivo = new File(name + "." + extension);
    	this.exists = this.arquivo.exists();
    	this.quebra = System.getProperty("line.separator");
    	try {
    		logFile = new FileOutputStream(arquivo, false);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }

    public WriteFile(String name) {
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
