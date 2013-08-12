package ui;

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
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import coverage.ClassCode;
import coverage.Method;
import coverage.Statement;
import coverage.TestCase;
import coverage.TestSuite;


/**
 * This class create a JTree with information contained in XML file.
 *
 * @author Samuel T. C. Santos
 * @version 1.0
 */
public class TreeBuilder {

	JTree tree;
	StringBuilder builder;
    
    public TreeBuilder() {
    	builder = new StringBuilder();
    }

    /**
     * Create a tree for xml.
     */
    public void buildTree(List<TestSuite> suitelist) {

        List<String> itenslist = new ArrayList<String>();
        HashMap<String, String> map = new HashMap<String, String>();
        List<TestSuite> suites = suitelist;

        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Coverage");

        String title = "Set of Test Cases";
        DefaultMutableTreeNode nodeSuite = new DefaultMutableTreeNode(title);
        
        builder.append(title);
        builder.append("\n");
        
        
        //create suites nodes
        for (TestSuite testSuite : suites) {

            DefaultMutableTreeNode newSuite = new DefaultMutableTreeNode(testSuite.getName());
            builder.append(" ");
            builder.append(testSuite.getName());
            builder.append("\n");
           
            
            for (TestCase testCase : testSuite.getTestCases()) {

                DefaultMutableTreeNode newTestCase = new DefaultMutableTreeNode(testCase.getName());
                builder.append("  ");
                builder.append(testCase.getName());
                builder.append("\n");
                
                DefaultMutableTreeNode newMethodCoverage = new DefaultMutableTreeNode("Methods Covered");

                for (ClassCode classCode : testCase.getClassCoverage()) {

                    for (Method method : classCode.getMethodCoverage()) {
               
                        for (Statement stmt : method.getUniqueStatements()) {
                            String key = classCode.getName() + "."+ method.getName();
                            String value = stmt.getLineNumber();

                            if (!map.containsKey(key)) {
                                map.put(key, value);
                            }

                            if (!map.get(key).contains(value)) {
                                map.put(key, map.get(key) + " " + value);
                            }

                        }
                    }

                    newTestCase.add(newMethodCoverage);

                }

                Set<String> keys = map.keySet();

                for (String k : keys) {
                    newMethodCoverage.add(new DefaultMutableTreeNode(k + " - [" + map.get(k) + "]"));
                    builder.append("   ");
                    builder.append(k);
                    builder.append(" - [");
                    builder.append(map.get(k));
                    builder.append("]");
                    builder.append("\n");
                }

                keys.clear();

                newSuite.add(newTestCase);
            }
            nodeSuite.add(newSuite);
        }
        root.add(nodeSuite);

        this.tree = new JTree(root);
    }
    /**
     * Get the JTree.
     * 
     * @return
     * 		A tree with all informations about the coverage.
     *  
     */
    public JTree getTree(){
    	return this.tree;
    }
    
    /**
     * A String with log informations.
     * 
     */
    public String toString(){
    	return builder.toString();
    }
}

