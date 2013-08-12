package report;

/*
* PriorJ: JUnit Test Case Prioritization.
* 
* Copyright (C) 2012-2013  Samuel T. C. Santos, Julio Henrique Rocha
*
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
* 
* You should have received a copy of the GNU General Public License
* along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.tools.ant.types.CommandlineJava.SysProperties;
import org.junit.runner.JUnitCore;
import org.junit.runner.Request;
import org.junit.runner.Result;

import util.LogRead;




import junit.framework.TestCase;
import junit.framework.TestResult;

public class GenerateTestSuiteForJUnit4 {
	static String quebra = System.getProperty("line.separator");
	private static String getCabecalho(String packageName){
		String cabecalho = "package " + packageName+quebra;
		cabecalho += "import org.junit.runner.JUnitCore;"+quebra;
		cabecalho += "import org.junit.runner.Request;"+quebra;
		cabecalho += "import org.junit.runner.Result;"+quebra;
		return cabecalho;
	}

	private static String getCorpoSuperior(){
		String corpo = "public class SuitePrioritizedPriorJ {"+quebra;
		corpo += "    private int failureCount = 0;"+quebra;
		corpo += "    private int runCount = 0;"+quebra;
		corpo += "    private int errorCount = 0;"+quebra;
		return corpo;
	}

    public static String generate(String packageName , Set tests, int percentSize){

        int total = tests.size();
        
        // total ------ 100%
        // x     ------ psize
        // x = total * pSize / 100;
        
        int index = (int)(total * percentSize/100.0);
        
        List list = Arrays.asList(tests.toArray());
        
        List l = list.subList(0, index);
        
        Set listSet = new HashSet();
        
        for (Object o: l)
            listSet.add(o);
        
        return generate(packageName,listSet);
    }
    
     public static String generate(String packageName , List<String> tests, int percentSize){

        int total = tests.size();
        
        // total ------ 100%
        // x     ------ psize
        // x = total * pSize / 100;
        
        int index = (int)(total * percentSize/100.0);
        
        List list = Arrays.asList(tests.toArray());
        
        List l = list.subList(0, index);
        
        Set listSet = new HashSet();
        
        for (Object o: l)
            listSet.add(o);
        
        return generate(packageName,listSet);
    }
        
	
	public static String generate(String packageName, Set tests) {
		List<String> jaInstanciada = new ArrayList<String>();
        String code = getCabecalho(packageName);
        code += getCorpoSuperior();
        code += "    public void run(){"+quebra;
        code += "        try{"+quebra;
        code += "            JUnitCore jc = new JUnitCore();"+quebra;
        code += "           Result result = null;"+quebra;
        for (Object string : tests) {
           String caminho = (String) string;
           caminho = caminho.replace(".java", "");
           String[] paths = caminho.split("\\.");
           String tcName = paths[paths.length-1];
           String suiteName = caminho.replaceAll("\\.", "").replace(tcName, "");
           
           String instance = getPathInstance(paths);
           String requestName = generateRequestName(suiteName, tcName);
           if(!jaInstanciada.contains(suiteName)){
               code += "            Class "+suiteName+" = Class.forName(\""+instance+"\");"+quebra;
               jaInstanciada.add(suiteName);
           }
           code += "		    	Request "+requestName+" = Request.method("+suiteName+",\""+tcName+"\");"+quebra;
           code += "                result = jc.run("+requestName+");"+quebra;
           code += "			    setResult(\""+tcName+"\",result);"+quebra;         
        }
        code += "			} catch (Exception e) {"+quebra;
        code += "    			e.printStackTrace();"+quebra;
        code += "        	}"+quebra;  
        code += "         }"+quebra;
        code += getFinalSuite();
        return code;
    }
	
	private static String getFinalSuite(){
		String code = "";
		code += "		private void setResult(String testCaseName,Result result){"+quebra;
		code += "           System.out.println(\"TestCase: \" + testCaseName +\" \" + getStatus(result));"+quebra;
		code += "			runCount += result.getRunCount();"+quebra;
		code += "			failureCount += result.getFailureCount();"+quebra;
		code += "		}"+quebra;
		code += "		private String getStatus(Result result){"+quebra;
		code += "			int failureCount = result.getFailureCount();"+quebra;
		code += "			if(failureCount > 0) return \"[FAILURE]\";"+quebra;
		code += "			else return \"[ACCEPTED]\";"+quebra;
		code += "		}"+quebra;
		code += "		public void printResult(){"+quebra;
		code += "			System.out.println(\"=================== Test Suite Prioritized - PriorJ =================\");"+quebra;
		code += "			System.out.println(\"Run: \" + this.runCount);"+quebra;
		code += "			System.out.println(\"Faults: \" + this.failureCount);"+quebra;
		code += "           System.out.println(\"Errors: \" + this.errorCount);"+quebra;
		code += "		}"+quebra;
		code += "		public static void main(String[] args) {"+quebra;
		code += "			SuitePrioritizedPriorJ st = new SuitePrioritizedPriorJ();"+quebra;
		code += "			st.run();"+quebra;
		code += "			st.printResult();"+quebra;
		code += "		}"+quebra;
		code += "	}"+quebra;
		return code;
		
	}
	
	private static String generateNameSuite(String[] paths){
		String caminho = "tc";
		for (String string : paths) {
			caminho += string.substring(0,1).toUpperCase().concat(string.substring(1));
		}
		return caminho;
	}
	
	private static String generateRequestName(String suiteName, String tcName){
		String result = suiteName+tcName;
		return result.replaceAll("\\.", "");
	}
	private static String getPathInstance(String[] paths){
		String caminho = "";
		for (int i = 0; i < paths.length-1; i++) {
			caminho += paths[i]+".";
		}
		return caminho.substring(0, caminho.length()-1);
	}
	
	public static String getPackageName(String pathTests) throws Exception{
		File dir = new File(pathTests);
		File[] filhos = dir.listFiles();
		File fistTestClass = null;
		for (File file : filhos) {
			if(file.isFile() && file.toString().contains("java")){
				fistTestClass = file;
				break;
			}			
		}
		LogRead leitor = new LogRead(fistTestClass.getAbsolutePath().replace(".java", ""), "java");
		String codigo = leitor.read();
		String[] linhasCodigo = codigo.split("[\\r\\n]+");
		String packageName = "";
		for (String string : linhasCodigo) {
			if(string.contains("package")){
				String[] quebraLinha = string.split(" ");
				if(quebraLinha.length == 2){
					packageName = quebraLinha[1];
					break;
				}
			}
		}
		return packageName;
	}
	

}
