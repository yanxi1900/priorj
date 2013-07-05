package util;

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
