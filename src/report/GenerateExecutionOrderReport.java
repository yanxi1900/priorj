package report;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import technique.TechniquesEnum;
import util.Settings;



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
            builder.append(Settings.NEWLINE);
            
            int number = 0;
            for (String test : testCases) {
                    number ++;
                    builder.append(String.valueOf(number));
                    builder.append(" - ");
                    builder.append(test.replace(".java", ""));
                    builder.append("()");
                    builder.append(Settings.NEWLINE);
            }
            
            return builder.toString();
	}

}
