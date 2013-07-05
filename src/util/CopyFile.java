package util;  
  
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class CopyFile {  
  
   /** 
    * Copy files from a place to another.
    * 
    * @param origem -  files origin 
    * @param destino - files destination 
    * @param overwrite - confirmation to rewriter files.
    * 
    * @throws IOException 
    */  
   public static void copy(File origem,File destino,boolean overwrite) throws IOException{  
      if (destino.exists() && !overwrite){  
         return;  
      }  
      FileInputStream   fisOrigem = new FileInputStream(origem);  
      FileOutputStream fisDestino = new FileOutputStream(destino);  
      FileChannel fcOrigem = fisOrigem.getChannel();    
      FileChannel fcDestino = fisDestino.getChannel();    
      fcOrigem.transferTo(0, fcOrigem.size(), fcDestino);    
      fisOrigem.close();    
      fisDestino.close();  
   }  
     
   /** 
    * Copy all files from a directory to another.
    * 
    * @param origem - directory from origin.
    * @param destino - directory destination. 
    * @param overwrite - confirmation to rewriter files.
    *  
    * @throws IOException 
    */  
   public static void copyAll(File origem,File destino,boolean overwrite) throws IOException{  
      if (!destino.exists())  
         destino.mkdir();  
      if (!origem.isDirectory())  
         throw new UnsupportedOperationException("Directory [" + origem.getParent() + "] not found!");
     
	if (!destino.isDirectory() || !destino.exists())
		 throw new UnsupportedOperationException("Directory [" + destino.getParent() + "] not found!");

	 File[] files = origem.listFiles();
	
      for (File file : files) {  
         if (file.isDirectory())  
            copyAll(file,new File(destino+Settings.SEPARATOR+file.getName()),overwrite);  
         else{  
            copy(file, new File(destino+Settings.SEPARATOR+file.getName()),overwrite);  
         }  
      }  
   }  
     
}  
