package technique;


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
