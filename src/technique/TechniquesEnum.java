package technique;

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
