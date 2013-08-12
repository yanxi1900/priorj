package util;

/*
* PriorJ: JUnit Test Case Prioritization.
* 
* Copyright (C) 2012-2013  Samuel T. C. Santos
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
import japa.parser.JavaParser;
import japa.parser.ParseException;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.body.BodyDeclaration;
import japa.parser.ast.body.ClassOrInterfaceDeclaration;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.body.TypeDeclaration;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.List;


public class LastTestCase {
	private static String ultimosTests = "";
	public static String getLastTestCase(String path) throws Exception{
		getLastTCChildren(new File(path));
		return ultimosTests.substring(0, ultimosTests.length()-2);
	}
	private static void getLastTCChildren(File path) throws Exception {
		FilenameFilter filter = new FilenameFilter() {

			public boolean accept(File dir, String name) {
				return name.contains("Test");
			}
		};
		File[] children = path.listFiles(filter);
		if (children != null) {
			for (File file : children) {
				if (file.isDirectory()) {
					getLastTCChildren(file);
				} else {
					ultimosTests += getLastTestCase(file);
				}			
			}
		}
	}
	
	private static String getLastTestCase(File path) throws Exception{
		if (path.isDirectory()) return "";
		String lastTestCase = "";
		CompilationUnit compilation;
		compilation = JavaParser.parse(path);
		ClassOrInterfaceDeclaration classe = null;
		List<TypeDeclaration> types = compilation.getTypes();    
        for (TypeDeclaration typeDeclaration : types) {           
            if (typeDeclaration instanceof ClassOrInterfaceDeclaration && !((ClassOrInterfaceDeclaration) typeDeclaration).isInterface()) {
                classe = (ClassOrInterfaceDeclaration) typeDeclaration;
            }
        }       
        if (classe == null) return "";        
        List<BodyDeclaration> membros = classe.getMembers();              
        for (BodyDeclaration bodyDeclaration : membros) {           
            if (bodyDeclaration instanceof MethodDeclaration) {
                String nameMethod = ((MethodDeclaration) bodyDeclaration).getName();
                if(nameMethod.contains("test")){
                	lastTestCase = nameMethod;
                }
            }
        }
        return "tcName.equals(\""+lastTestCase+"\")||";
	}
	
	
	public static void main(String[] args) throws Exception {
		System.out.println(getLastTestCase("C:/jmock/core/acceptance-tests/core"));
	}

}
