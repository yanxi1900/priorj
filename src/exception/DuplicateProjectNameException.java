package exception;


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
 * This class is an exception throw when the user 
 * to try create a new project with same name of one
 * which already exist.
 * 
 * @author Samuel T. C. Santos
 * @version 1.0
 */
public class DuplicateProjectNameException extends Exception {
    
	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 1L;

	public DuplicateProjectNameException(){
		super("There is a project with that name");
	}
}
