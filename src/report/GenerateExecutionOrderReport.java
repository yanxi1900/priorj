package report;

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
import java.util.List;

import technique.TechniqueCreator;

/**
 * This class generate an execution order report to a specific technique.
 * 
 * @author Samuel T. C. Santos
 * @version 1.0
 * 
 */
public class GenerateExecutionOrderReport {
	
	private static final String newline = System.getProperty("line.separator");
	
	public static String create(int typeOfTechnique, List<String> tests){
            StringBuilder builder = new StringBuilder();
            
            builder.append("............: Prioritization Order ");
            builder.append(TechniqueCreator.acronyms(typeOfTechnique));
            builder.append(" :..............");
            builder.append(newline);
            
            int number = 0;
            for (String test : tests) {
                    number ++;
                    builder.append(String.valueOf(number));
                    builder.append(" - ");
                    builder.append(test);
                    builder.append("()");
                    builder.append(newline);
            }
            
            return builder.toString();
	}

}
