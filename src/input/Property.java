package input;

/**
 * This <code>enum</code> represents the properties of a configuration file.
 * 
 * @author Samuel T. C. Santos
 * @version 1.0
 *
 */
public enum Property {

	/**
	 * This property indicates the application path.
	 * 
	 */
	APPLICATION ("application"),
	/**
	 * This property indicates the code path.
	 */
	CODE("code"),
	/**
	 * This property indicates the libraries.
	 */
	LIB("libraries"),
	/**
	 * This property indicates the tests.
	 */
	TESTS("tests"),
	/**
	 * This property indicates the new code.
	 */
	NEW_CODE("new_code"),
	
	/**
	 * This property indicates the JUnit3 version.
	 */
	JUNIT3("junit_version_3"),
	/**
	 * This property indicates the JUnit4 version.
	 */
	JUNIT4("junit_version_4"),
	
	/**
	 * This property indicates Total Method Coverage.
	 */
	TMC("technique_tmc"),

	/**
	 * This property indicates Total Statement Coverage.
	 */
	TSC("technique_tsc"),
	/**
	 * This property indicates Addition Method Coverage.
	 */
	AMC("technique_amc"),
	/**
	 * This property indicates Addition Statement Coverage.
	 */
	ASC("technique_asc"),
	/**
	 * This property indicates Random.
	 */
	RND("technique_rnd"),
	/**
	 * This property indicates Changed Blocks.
	 */
	CB("technique_cb"),
	/**
	 * This property indicates Refactoring Based Approach.
	 */
	RBA("technique_rba"),
	
	/**
	 * Report Option: coverage report.
	 */
	COVERAGE_REPORT("coverage_report"),
	/**
	 * Report Option: code tree.
	 */
	CODE_TREE("code_tree"),
	/**
	 * Report Option: prioritized execution order. 
	 */
	EXECUTION_ORDER ("execution_order"),

	/**
	 * Report Option: simple JUnit report
	 */
	JUNIT_REPORT ("junit_report"),
	
	/**
	 * 
	 */
	SUITE_TMC ("suite_tmc"),
	/**
	 * 
	 */
	SUITE_TSC ("suite_tsc"),
	/**
	 * 
	 */
	SUITE_AMC ("suite_amc"),
	/**
	 * 
	 */
	SUITE_ASC ("suite_asc"),
	/**
	 * 
	 */
	SUITE_RND ("suite_rnd"),
	/**
	 * 
	 */
	SUITE_CB ("suite_cb"),
	/**
	 * 
	 */
	SUITE_RBA ("suite_rba"),
	/**
	 * 
	 */
	RM_PATH_APP ("rm_path_application"),
	
	/**
	 * 
	 */
	RM_CLASS_NAME ("rm_class_name"),
	
	/**
	 * Rename method: method name. 
	 */
	RM_METHOD_NAME("rm_method_name"),
	
	/**
	 * Rename method: new method name.
	 */
	RM_NEW_METHOD_NAME("rm_new_method_name"),
	
	/**
	 * Extract Method: 
	 */
	EM_PATH_APP("em_path_application"),
	/**
	 * 
	 */
	EM_ORIGIN_METHOD_NAME("em_origin_method_name"),
	/**
	 * 
	 */
	EM_CLASS_NAME ("em_class_name"),
	/**
	 * 
	 */
	EM_NEW_METHOD_NAME("em_new_method_name"),
	/**
	 * 
	 */
	EM_BEGIN_LINE("em_begin_line"),
	/**
	 * 
	 */
	EM_END_LINE("em_end_line"),
	/**
	 * 
	 */
	MM_PATH_APP("mm_path_application"),
	/**
	 * 
	 */
	MM_CLASS_ONE_NAME ("mm_class_one_name"),
	/**
	 * 
	 */
	MM_CLASS_TWO_NAME("mm_class_two_name"),
	/**
	 * 
	 */
	MM_METHOD_NAME("mm_method_name"),
	/**
	 * 
	 */
	PUM_PATH_APP ("pum_path_application"),
	/**
	 * 
	 */
	PUM_CLASS_ONE_NAME ("pum_class_one_name"),
	/**
	 * 
	 */
	PUM_CLASS_TWO_NAME("pum_class_two_name"),
	/**
	 * 
	 */
	PUM_METHOD_NAME("pum_method_name"),
	/**
	 * 
	 */
	PUF_PATH_APP ("puf_path_application"),
	/**
	 * Pull Up Field : class one name
	 */
	PUF_CLASS_ONE_NAME ("puf_class_one_name"),
	/**
	 * Pull Up Field: class two name
	 */
	PUF_CLASS_TWO_NAME("puf_class_two_name"),
	/**
	 * Pull Up Field: field name
	 */
	PUF_FIELD_NAME ("puf_field_name"),

	/**
	 * Add Parameter: application path.
	 */
	AP_PATH_APP ("ap_path_application"),
	/**
	 * Add Parameter: class name
	 */
	AP_CLASS_NAME("ap_class_name"),
	/**
	 * Add Parameter: method name
	 */
	AP_METHOD_NAME("ap_method_name"),

	/**
	 * Used to indicates APFD faults.
	 */
 	FAULT_ENTRY("fault_entry");
	
	/**
	 * Property of configuration.
	 */
	private String property;
	
	/**
	 * Constructor.
	 * @param property
	 */
	Property(String property){
		this.property = property;
	}
	
	public String getName(){
		return property;
	}
	
	
}
