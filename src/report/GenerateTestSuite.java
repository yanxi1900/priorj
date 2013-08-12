package report;

/*
* PriorJ: JUnit Test Case Prioritization.
* 
* Copyright (C) 2012-2013  Samuel T. C. Santos, Julio Henrique Rocha.
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import util.LogRead;


import junit.framework.TestCase;
import junit.framework.TestResult;

public class GenerateTestSuite {
      

	static String quebra = System.getProperty("line.separator");
	private static String getCabecalho(String packageName){
		String cabecalho = "package " + packageName + quebra;
		cabecalho += "import junit.framework.TestCase;"+quebra;
		cabecalho += "import junit.framework.TestResult;"+quebra;
		return cabecalho;
	}

	private static String getCorpoSuperior(){
		String corpo = "public class SuitePrioritizedPriorJ {"+quebra;
		corpo += "    private int failureCount = 0;"+quebra;
		corpo += "    private int runCount = 0;"+quebra;
		corpo += "   private int errorCount = 0;"+quebra;
		return corpo;
	}
        
        private static String getCorpoSuperior(String className){
		String corpo = "public class " + className + " {"+quebra;
		corpo += "    private int failureCount = 0;"+quebra;
		corpo += "    private int runCount = 0;"+quebra;
		corpo += "   private int errorCount = 0;"+quebra;
		return corpo;
	}

        public static String generate(String packageName, List<String> tests) {
        List<String> jaInstanciada = new ArrayList<String>();
        String code = getCabecalho(packageName);
        code += getCorpoSuperior();
        code += "    public void run(){"+quebra;
        for (String string : tests) {
           String caminho = (String) string;
           caminho = caminho.replace(".java", "");
           String[] paths = caminho.split("\\.");
           String tcName = paths[paths.length-1];
           String suiteName = generateNameSuite(paths);
           String instance = getPathInstance(paths);
           code += "        TestCase "+generateNameSuite(paths)+" = null;"+quebra;
           code += "        "+generateNameSuite(paths) + " = new "+instance+"(){"+quebra;
           code += "   			public void runTest(){"+quebra;
           code += "                try{"+quebra;
           code += "   			    	"+tcName+"();"+quebra;
           code += "				} catch (Exception e) {"+quebra;
           code += "    				e.printStackTrace();"+quebra;
           code += "        		}"+quebra;
           code += "   			}"+quebra;
           code += "   		};"+quebra;
           code += "   		setResult(\""+tcName+"\","+generateNameSuite(paths)+".run());"+quebra;

        }
        code += "         }"+quebra;
        code += getFinalSuite();
        return code;
    }

    public static String generate(String packageName,String className, List<String> tests, int percentSize){

         int total = tests.size();
        
        int index = (int)(total * percentSize/100.0);
        
        //List list = Arrays.asList(tests.toArray());
        
        List l = tests.subList(0, index);
        
        return generate(packageName, className, l);
    }
   
    public static String generate(String packageName , Set tests, int percentSize){

        int total = tests.size();
        
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
        
        int index = (int)(total * percentSize/100.0);
        
        List list = Arrays.asList(tests.toArray());
        
        List l = list.subList(0, index);
        
        Set listSet = new HashSet();
        
        for (Object o: l)
            listSet.add(o);
        
        return generate(packageName,listSet);
    }
    
    private static String generate(String packageName,String clazz, List<String> tests) {
        List<String> jaInstanciada = new ArrayList<String>();
        String code = getCabecalho(packageName);
        code += getCorpoSuperior(clazz);
        code += "    public void run(){"+quebra;
        for (String string : tests) {
           String caminho = (String) string;
           caminho = caminho.replace(".java", "");
           String[] paths = caminho.split("\\.");
           String tcName = paths[paths.length-1];
           String suiteName = generateNameSuite(paths);
           String instance = getPathInstance(paths);
           code += "        TestCase "+generateNameSuite(paths)+" = null;"+quebra;
           code += "        "+generateNameSuite(paths) + " = new "+instance+"(){"+quebra;
           code += "   			public void runTest(){"+quebra;
           code += "                try{"+quebra;
           code += "   			    	"+tcName+"();"+quebra;
           code += "				} catch (Exception e) {"+quebra;
           code += "    				e.printStackTrace();"+quebra;
           code += "        		}"+quebra;           
           code += "   			}"+quebra;
           code += "   		};"+quebra;
           code += "   		setResult(\""+tcName+"\","+generateNameSuite(paths)+".run());"+quebra;
           
        }
        code += "         }"+quebra;
        code += getFinalSuite(clazz);
        return code;
    }
        
    private static String generate(String packageName, Set tests) {
        List<String> jaInstanciada = new ArrayList<String>();
        String code = getCabecalho(packageName);
        code += getCorpoSuperior();
        code += "    public void run(){"+quebra;
        for (Object string : tests) {
           String caminho = (String) string;
           caminho = caminho.replace(".java", "");
           String[] paths = caminho.split("\\.");
           String tcName = paths[paths.length-1];
           String suiteName = generateNameSuite(paths);
           String instance = getPathInstance(paths);
           code += "        TestCase "+generateNameSuite(paths)+" = null;"+quebra;
           code += "        "+generateNameSuite(paths) + " = new "+instance+"(){"+quebra;
           code += "   			public void runTest(){"+quebra;
           code += "                try{"+quebra;
           code += "   			    	"+tcName+"();"+quebra;
           code += "				} catch (Exception e) {"+quebra;
           code += "    				e.printStackTrace();"+quebra;
           code += "        		}"+quebra;           
           code += "   			}"+quebra;
           code += "   		};"+quebra;
           code += "   		setResult(\""+tcName+"\","+generateNameSuite(paths)+".run());"+quebra;
           
        }
        code += "         }"+quebra;
        code += getFinalSuite();
        return code;
    }

	private static String getFinalSuite(){
		String code = "";
		code += "		private void setResult(String testCaseName,TestResult result){"+quebra;
		code += "           System.out.println(\"TestCase: \" + testCaseName +\" \" + getStatus(result));"+quebra;
		code += "			runCount += result.runCount();"+quebra;
		code += "			failureCount += result.failureCount();"+quebra;
		code += "           errorCount += result.errorCount();"+quebra;
		code += "		}"+quebra;
		code += "		private String getStatus(TestResult result){"+quebra;
		code += "			int erroCount = result.errorCount();"+quebra;
		code += "			int failureCount = result.failureCount();"+quebra;
		code += "			if(erroCount > 0) return \"[ERROR]\";"+quebra;
		code += "			else if(failureCount > 0) return \"[FAILURE]\";"+quebra;
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

        private static String getFinalSuite(String suiteName){
		String code = "";
		code += "		private void setResult(String testCaseName,TestResult result){"+quebra;
		code += "           System.out.println(\"TestCase: \" + testCaseName +\" \" + getStatus(result));"+quebra;
		code += "			runCount += result.runCount();"+quebra;
		code += "			failureCount += result.failureCount();"+quebra;
		code += "           errorCount += result.errorCount();"+quebra;
		code += "		}"+quebra;
		code += "		private String getStatus(TestResult result){"+quebra;
		code += "			int erroCount = result.errorCount();"+quebra;
		code += "			int failureCount = result.failureCount();"+quebra;
		code += "			if(erroCount > 0) return \"[ERROR]\";"+quebra;
		code += "			else if(failureCount > 0) return \"[FAILURE]\";"+quebra;
		code += "			else return \"[ACCEPTED]\";"+quebra;
		code += "		}"+quebra;
		code += "		public void printResult(){"+quebra;
		code += "			System.out.println(\"=================== Test Suite Prioritized - PriorJ =================\");"+quebra;
		code += "			System.out.println(\"Run: \" + this.runCount);"+quebra;
		code += "			System.out.println(\"Faults: \" + this.failureCount);"+quebra;
		code += "           System.out.println(\"Errors: \" + this.errorCount);"+quebra;
		code += "		}"+quebra;
		code += "		public static void main(String[] args) {"+quebra;
                
		code += "			"+ suiteName + " st = new " + suiteName +"();"+quebra;

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