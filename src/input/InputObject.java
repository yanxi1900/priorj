package input;
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
 * This class represent a object from PriorJ configuration file.
 * 
 * @author Samuel T. C. Santos
 * 
 * @version 1.0.0
 *
 */
public class InputObject {

	/**
	 * This field represent the configuration property name. 
	 */
	private String property;
	
	/**
	 * This field represent the value associated with the property name.
	 */
	private String value;
	
	/**
	 * Construct to a <code>InputObject</code> with property and value.
	 * 
	 * @param property
	 * @param value
	 */
	public InputObject(String property, String value){
		this.property = property;
		this.value = value;
	}

	/**
	 * Default construct.
	 */
	public InputObject(){
		property = "";
		value = "";
	}
	
	/**
	 * Get the property name.
	 * 
	 * @return
	 * 		property name
	 */
	public String getProperty() {
		return property;
	}

	/**
	 * Set the property name;
	 * @param property
	 * 		The property name.
	 */
	public void setProperty(String property) {
		this.property = property;
	}

	/**
	 * Get the property value.
	 * @return
	 * 		The property value.
	 */
	public String getValue() {
		return value;
	}
	
	/**
	 * Set the property value.
	 * @param value
	 * 		The property value.
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
	/**
	 * This method say if the object is empty.
	 * 
	 * An object is empty if property end value are empty.
	 * 
	 * @return
	 */
	public boolean isEmpty(){
		return property.isEmpty() && value.isEmpty();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InputObject other = (InputObject) obj;
		if (property == null) {
			if (other.property != null)
				return false;
		} else if (!property.equals(other.property))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "[property=" + property + ", value=" + value + "]";
	}

}
