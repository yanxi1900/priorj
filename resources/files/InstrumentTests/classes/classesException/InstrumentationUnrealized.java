public class InstrumentationUnrealized extends Exception {

    /**
	 * Serial Version UID
	 */
    private static final long serialVersionUID = 1L;

    public InstrumentationUnrealizedException() {
        super("Instrumentation Unrealized!");
    }

    public InstrumentationUnrealizedException(String errorMessage) {
        super("Instrumentation Unrealized: " + errorMessage);
    }

    static boolean watchPriorJApp;
}
