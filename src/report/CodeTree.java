package report;

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
