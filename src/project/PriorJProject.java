package project;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import exception.EmptyPriorJProjectNameException;

/**
 * This class represent a Priorj Project.
 * 
 * @author Samuel T. C. Santos
 * @vesion 1.0
 */
public class PriorJProject {
    
    private String name;
    private Calendar date;
    private JUnitVersionEnum version;
    private boolean isOpen;
    private String pathApp;
    private String pathCode;
    private String pathLib;
    private String pathTest;
    private String pathCodeNew;
    
    /**
     * Create a new PriorJ Project.
     * 
     * @param name 
     * 		The Project name.
     * @param date 
     * 		The date creation. 
     * @param version 
     * 		The JUnit version used in the tests.
     * 
     * @throws EmptyPriorJProjectNameException 
     * 			when the project name is empty.
     */
    public PriorJProject(String name, Calendar date, JUnitVersionEnum version) throws EmptyPriorJProjectNameException {
        
    	if (name.isEmpty())
    		throw new EmptyPriorJProjectNameException();
    	
    	this.name = name;
        this.date = date;
        this.version = version;
        isOpen = true;
        this.pathApp="";
        this.pathCode="";
        this.pathLib="";
        this.pathTest="";
        this.pathCodeNew="";
    }
    /**
     * Default constructor.
     */
    public PriorJProject(){
    	//empty
    }

    /**
     * Get the project Name;
     * 
     * @return the project name.
     */
    public String getName() {
        return name;
    }
    
    /**
     * Set the project name
     *  
     * @param name project name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get creation date for this project.
     *  
     * @return creation date
     */
    public Calendar getDate() {
        return date;
    }

    /**
     * Set the creation date.
     * 
     * @param date a Calendar
     */
    public void setDate(Calendar date) {
        this.date = date;
    }
    /**
     * Get the JUnit version, 
     * 
     * @return the JUnit version used.
     */
    public JUnitVersionEnum getVersion() {
        return version;
    }
    
    /**
     * Set the JUnit version.
     * 
     * @param version a JUnitVersionEnum
     */
    public void setVersion(JUnitVersionEnum version) {
        this.version = version;
    }
    
    /**
     * Turn the status to Open for this project.
     * 
     */
    public void setOpenProject(){
        isOpen = true;
    }
    /**
     * Turn the status project to Close;
     */
    public void setCloseProject(){
        isOpen = false;
    }
    
    /**
     * Say if the project is Open.
     * 
     * @return
     */
    public boolean isOpenProject(){
        return isOpen;
    }
    /**
     * Get the path of this project.
     * 
     * @return the project path.
     */
    public String getPathApp() {
        return pathApp;
    }
    /**
     * Set the application path.
     * @param pathApp the application path.
     */
    public void setPathApp(String pathApp) {
        this.pathApp = pathApp;
    }
    /**
     * Get the path code.
     * @return the path code.
     */
    public String getPathCode() {
        return pathCode;
    }
    /**
     * Set the path code.
     * 
     * @param pathCode the path code.
     */
    public void setPathCode(String pathCode) {
        this.pathCode = pathCode;
    }
    /**
     * Get the path Libraries.
     * 
     * @return the paths libraries.
     */
    public String getPathLib() {
        return pathLib;
    }
    /**
     * Set the path libraries.
     * 
     * @param pathLib the path Libraries.
     */
    public void setPathLib(String pathLib) {
        this.pathLib = pathLib;
    }

    /**
     * Get the path tests.
     * 
     * @return the path tests.
     */
    public String getPathTest() {
        return pathTest;
    }
    /**
     * Set the test path.
     * 
     * @param pathTest the path test.
     */
    public void setPathTest(String pathTest) {
        this.pathTest = pathTest;
    }
    /**
     * Get the path Old.
     * 
     * @return The Path application.
     */
    public String getPathCodeNew() {
        return pathCodeNew;
    }

    /**
     * Set the path Old, needed only for changed Blocks technique.
     * 
     * @param pathCodeNew
     *      The path of a new project version.
     *      
     */
    public void setPathCodeNew(String pathCodeNew) {
        this.pathCodeNew = pathCodeNew;
    }
    /**
     * Set all paths project.
     * 
     * @param app path application.
     * @param code path code.
     * @param lib path the libraries.
     * @param test path the tests
     * @param old path old application.
     */
    public void setPaths(String app, String code, String lib, String test, String old){
        this.setPathApp(app);
        this.setPathCode(code);
        this.setPathLib(lib);
        this.setPathTest(test);
        this.setPathCodeNew(old);
    }

    public boolean equals(Object obj){
        
        if (!(obj instanceof PriorJProject))
            return false;
        
        PriorJProject p = (PriorJProject) obj;
        
        return this.getName().equals(p.getName()); 
    }
    
    /**
     * Show project informations.
     */
    @Override
    public String toString() {
        SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
        
        return "PriorJProject{" + "name=" + name + ", date=" + formatDate.format(date.getTime())
                + ", version=" + version + '}';
    }
            
}
