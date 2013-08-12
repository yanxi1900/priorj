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
import java.awt.ScrollPane;

import ui.TreeBuilder;


/**
 * This class represent a log to coverage does by test cases.
 * 
 * @author Samuel T. C. Santos
 * @version 1.0
 */
public class CodeTree {

    ScrollPane panel;
    String codeTree;
    
    /**
     * Constructor
     * 
     * @param logTree
     * 			A JTree with informations about coverage. 
     */
	public CodeTree(TreeBuilder logTree){
		this.panel = new ScrollPane();
		this.panel.add(logTree.getTree());
		
		codeTree = logTree.toString();
	}
	
	/**
	 * The ScrollPane with a Jtree.
	 * 
	 * @return
	 * 		The GUI to CodeTree.
	 */
	public ScrollPane getTreePanel(){
		return this.panel;
	}
	
	/**
	 * A String with log coverage.
	 * 
	 * @return
	 * 		The log coverage.
	 */
	public String getCodeTreeString(){
		return codeTree;
	}
	
}
