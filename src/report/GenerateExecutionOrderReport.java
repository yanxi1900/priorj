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
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import technique.TechniquesEnum;
import util.PathTo;



/**
 * This class generate an execution order report.
 * 
 * @author Samuel T. C. Santos
 * @version 1.0
 * 
 */
public class GenerateExecutionOrderReport {
	
	private List<String> testCases;
	private TechniquesEnum technique;
	

        public GenerateExecutionOrderReport(List<String> testCases, int technique){
		this.testCases = testCases;
		this.technique = TechniquesEnum.values()[technique-1];
	}
	
	public String generateExecutionOrder(){
            StringBuilder builder = new StringBuilder();
            
            builder.append("............: Prioritization Order ");
            builder.append(this.technique.getName());
            builder.append(" :..............");
            builder.append(PathTo.NEWLINE);
            
            int number = 0;
            for (String test : testCases) {
                    number ++;
                    builder.append(String.valueOf(number));
                    builder.append(" - ");
                    builder.append(test.replace(".java", ""));
                    builder.append("()");
                    builder.append(PathTo.NEWLINE);
            }
            
            return builder.toString();
	}

}
