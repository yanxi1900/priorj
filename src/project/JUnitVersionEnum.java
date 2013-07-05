package project;

/**
 * This class is used to represent the JUnit version
 * used in the test cases.
 *
 * @author Samuel T. C. Santos
 * @version 1.0
 * 
 */
public enum JUnitVersionEnum {

    JUNIT3  (1, "JUnit 3.x"),
    JUNIT4  (2, "JUnit 4.x");
    
   /**
    * Constructor 
    * 
    * @param version
    * 		The version Name
    * @param name
    * 		The name
    */
    JUnitVersionEnum(int version, String name) {
        this.version = version;
        this.name = name;
    }
    
    private final int version;
    private final String name;

    /**
     * Get the JUnit version.
     * 
     * @return the JUnit version.
     */
    public int getVersion() {
        return this.version;
    }
    /**
     * Get the name version.
     * 
     * @return the name version.
     */
    public String getName() {
        return this.name;
    }

}
