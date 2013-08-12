package project;

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
