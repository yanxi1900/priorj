package exception;

/**
 * This class is an exception throw when one or more directory is not found.
 * 
 * @author Samuel T. C. Santos.
 * @version 1.0
 *
 */
public class DirectoryNotExistException extends Exception {

    /**
	 * Serial Version UID
	 */
    private static final long serialVersionUID = 1L;

    public DirectoryNotExistException() {
        super("Directory not exist!");
    }
}
