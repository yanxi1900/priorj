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
public enum TechniquesEnum {
	
    // Declaracao das constantes
    TOTAL_STATEMENT_COVERAGE  (1, "Total Statement Coverage"),
    TOTAL_METHOD_COVERAGE  (2, "Total Method Coverage"),
    ADDITIONAL_STATEMENT_COVERAGE  (3, "Additional Statement Coverage"),
    ADDITIONAL_METHOD_COVERAGE  (4, "Additional Method Coverage"),
    CHANGED_BLOCKS_TOTAL  (5, "Changed Blocks Total"),
    Random(6, "Random"),
    CHANGED_BLOCKS_ADDITIONAL  (7, "Changed Blocks Additional"),
    REFACTORING_BASED_APPROACH (8,"Refactoring Based Approach");
    

    // Definicao das constantes
    private final int id;
    private final String name;

    // metodos para acessar os valores
    public int getId() {
        return this.id;
    }
    
    public String getName() {
        return this.name;
    }

    TechniquesEnum(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
