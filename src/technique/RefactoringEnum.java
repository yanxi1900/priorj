package technique;


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
 * 
 * @author Samuel Santos
 */
public enum RefactoringEnum {

    RENAME_METHOD   (1, "Rename Method"),
    EXTRAT_METHOD   (2, "Extract Method"),
    MOVE_METHOD     (3, "Move Method"),
    PULL_UP_METHOD  (4, "Pull up Method"),
    PULL_UP_FIELD   (5, "Pull up Field"),
    ADD_PARAMETER   (6, "Add Parameter ");


    private final int id;
    private final String name;

    public int getId() {
        return this.id;
    }
    
    public String getName() {
        return this.name;
    }

    RefactoringEnum(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
