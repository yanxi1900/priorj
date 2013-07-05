package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

/**
 * This claas do a replace a string inside the indicated file.
 * 
 * @author Samuel T. C. Santos
 * @author Julio Henrique
 * @version 
 */
public class SubstituiStringArquivo {
	
	/**
	 * Constructor with name and extension.
	 * @param name
	 * 		The file name.
	 * @param extension
	 * 		The file extension
	 * @return
	 * 		A string with content modified.
	 * 
	 * @throws Exception
	 */
	private static String getStringFile(String name, String extension) throws Exception{
		File f = new File(name+"."+extension);
		String quebra = System.getProperty("line.separator");
		String data = "";
		if(!f.exists())
		{
			System.exit(-1);
		}
		try {
			BufferedReader in = new BufferedReader(new FileReader(f));
			String line;
			int lineCount = 0;
			while((line = in.readLine())!=null){
				data+=line+quebra;
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;

	}
	/**
	 * Construct default.
	 */
	public SubstituiStringArquivo(){
		//default
	}
	
	/**
	 * This method write a string into the file.
	 * 
	 * @param dados
	 * 		the information to write into the file.
	 * @param name
	 * 		the file name
	 * @param extension
	 * 		the string extension.
	 */
	private static void writeStringFile(String dados, String name, String extension){
		 try {
			FileOutputStream arquivo = new FileOutputStream(new File(name+"."+extension));
			arquivo.write(dados.getBytes());
			arquivo.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * This code set a information into the file.
	 * 
	 * @param arquivo
	 * 		The file
	 * @param pathCode
	 * 		The path code
	 * @param pathTest
	 * 		The path test.
	 * @param pointcut
	 * 		The parameter to pointcut.
	 * @throws Exception
	 * 		An exception to access files.
	 */
	public static void setPath(File arquivo, String pathCode, String pathTest, String pointcut) throws Exception{
		if(arquivo.isFile()){			
			String extensao = arquivo.getName().substring(arquivo.getName().lastIndexOf(".")+1, arquivo.getName().length()); 
			String nome = arquivo.toString().substring(0, arquivo.toString().lastIndexOf("."));		
			String data = getStringFile(nome, extensao);
			data = data.replace("[pathTest]", pathTest);
			data = data.replace("[pathCode]", pathCode);
            data = data.replace("[parameter]", pointcut);
			writeStringFile(data, nome, extensao);
		}
	}
	
}
