package util;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;

import coverage.TestSuite;



public class Reader {
	private String arquivoName;
	private final String separator = System.getProperty("file.separator");
	
        public Reader(String arquivoName){
		this.arquivoName = arquivoName;
	}
	
	public List read() throws Exception{
		LogRead log = new LogRead(arquivoName, "xml");		
		XStream xml = new XStream();
		LogWrite logWrite = new LogWrite("report"+separator+"coveragePriorJ", "xml");
		logWrite.write("</list>");
		logWrite.close();
		List<List> lista = (List<List>)xml.fromXML(log.readFile());
		List listaCompleta = new ArrayList<TestSuite>();
		for (List list : lista) {
			listaCompleta.addAll(list);
		}
		return listaCompleta;
	}
	
//	public static void main(String[] args) throws Exception {
//		 String local = System.getProperty("user.dir");
//
//	     Reader rd = new Reader(local + "/report/coveragePriorJ");
//
//	     List<TestSuite> suiteLista = (List<TestSuite>) rd.read();
//	     
//	     System.out.println(suiteLista.toString());
//	}
	
}
