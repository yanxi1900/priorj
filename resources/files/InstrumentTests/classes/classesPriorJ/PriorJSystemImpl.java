package system;

import java.io.File;
import java.io.IOException;
import java.util.List;
import project.JUnitVersionEnum;
import report.CoverageReport;
import util.CopyFile;
import util.ManagerFiles;
import util.Reader;
import util.RunAnt;
import util.Settings;
import util.SubstituiStringArquivo;
import core.DifferenceApp;
import core.InstrumentApp;
import coverage.TestCase;
import coverage.TestSuite;
import exception.CannotReadLogFileException;
import exception.CoverageUnrealizedException;
import exception.InstrumentationUnrealizedException;

/**
 * Class that controls the entire process of prioritization using other classes of the system.
 * 
 * @author Samuel Santos
 * @author Julio Henrique
 *
 */
public class PriorJSystemImpl implements PriorJSystem {

    /**
	 * This path is the application
	 */
    private String pathApp = "";

    /**
     * This path is the source code
     */
    private String pathCode = "";

    /**
     * This path is a new code version
     */
    private String pathCodeNew = "";

    /**
     * This path is the JUnit tests
     */
    private String pathTests = "";

    /**
     * This path is the dependency libraries.
     */
    private String pathLib = "";

    private List blocoAfetado;

    /**
     * This object has all information about coverage.
     */
    private CoverageReport coverage;

    private String reportCoverage = "";

    /**
     * this variable say if the application is instrumented.
     */
    private boolean isInstrumented;

    /**
     * This variable say if the coverage process is done.
     */
    private boolean isCovered;

    /**
    * Constructor SystemImpl.
    *
    * @param pathApp  
    * 			Path where is the application.
    * @param pathCode 
    * 			Path where is the source code of the application.
    * @param pathTest 
    * 			Path wave is the test suite.
    */
    public PriorJSystemImpl(String pathApp, String pathCode, String pathTest, String pathLib) {
        watchPriorJApp = watchPriorJApp;
        this(pathApp, pathCode, pathTest, "", pathLib);
    }

    /**
    * Constructor SystemImpl.
    *
    * @param pathApp 
    * 			Path where the application is.
    * @param pathCode 
    * 			Path where is the source code of the application.
    * @param pathTest 
    * 			Path wave is the test suite.
    * @param pathCodeNew
    * 			Path compared to the new code.
    * @param pathLib 
    * 			Path to the libraries used.
    */
    public PriorJSystemImpl(String pathApp, String pathCode, String pathTest, String pathCodeNew, String pathLib) {
        watchPriorJApp = watchPriorJApp;
        this.pathApp = pathApp;
        watchPriorJApp = watchPriorJApp;
        this.pathCode = pathCode;
        watchPriorJApp = watchPriorJApp;
        this.pathCodeNew = pathCodeNew;
        watchPriorJApp = watchPriorJApp;
        this.pathTests = pathTest;
        watchPriorJApp = watchPriorJApp;
        if (pathLib.isEmpty()) this.pathLib = "lib"; else this.pathLib = pathLib;
        watchPriorJApp = watchPriorJApp;
        isCovered = false;
        watchPriorJApp = watchPriorJApp;
        isInstrumented = false;
    }

    /**
     * Default construct.
     */
    public PriorJSystemImpl() {
    }

    public void instrumentCode(String path) throws Exception {
        watchPriorJApp = watchPriorJApp;
        InstrumentApp inst = new InstrumentApp(path);
        watchPriorJApp = watchPriorJApp;
        inst.run();
    }

    public List checkDifference(String pathCodeNew, String pathCodeOld) throws Exception {
        watchPriorJApp = watchPriorJApp;
        DifferenceApp diff = new DifferenceApp(pathCodeOld, pathCodeNew);
        watchPriorJApp = watchPriorJApp;
        diff.run();
        watchPriorJApp = watchPriorJApp;
        List<String> diferencas = diff.getListDiff();
        watchPriorJApp = watchPriorJApp;
        return diferencas;
    }

    public void copyBackupFiles(String pathOrigemApp) throws IOException, Exception {
        watchPriorJApp = watchPriorJApp;
        File origem = new File(pathOrigemApp);
        watchPriorJApp = watchPriorJApp;
        File dirDestino = new File(Settings.WORKSPACE + Settings.SEPARATOR);
        watchPriorJApp = watchPriorJApp;
        if (!dirDestino.exists()) {
            watchPriorJApp = watchPriorJApp;
            dirDestino.mkdir();
        }
        watchPriorJApp = watchPriorJApp;
        File destino = new File(Settings.WORKSPACE + Settings.SEPARATOR + origem.getName());
        watchPriorJApp = watchPriorJApp;
        CopyFile.copyAll(origem, destino, true);
        watchPriorJApp = watchPriorJApp;
        setPathApp(destino.toString());
        watchPriorJApp = watchPriorJApp;
        File origemNewCode = null;
        watchPriorJApp = watchPriorJApp;
        if (!pathCodeNew.isEmpty()) {
            watchPriorJApp = watchPriorJApp;
            origemNewCode = new File(pathCodeNew);
            watchPriorJApp = watchPriorJApp;
            destino = new File(Settings.WORKSPACE + Settings.SEPARATOR + origemNewCode.getName());
            watchPriorJApp = watchPriorJApp;
            CopyFile.copyAll(origemNewCode, destino, true);
            watchPriorJApp = watchPriorJApp;
            setPathCodeNew(destino.toString());
        }
    }

    public void copyFiles() throws IOException {
        watchPriorJApp = watchPriorJApp;
        File origem = new File(Settings.ASPECT_FILE + Settings.SEPARATOR);
        watchPriorJApp = watchPriorJApp;
        File destino = new File(getPathApp() + Settings.SEPARATOR);
        watchPriorJApp = watchPriorJApp;
        CopyFile.copyAll(origem, destino, true);
        watchPriorJApp = watchPriorJApp;
        File ori = new File(Settings.EXTERNAL + Settings.SEPARATOR + "lib" + Settings.SEPARATOR);
        watchPriorJApp = watchPriorJApp;
        File des = new File(getPathApp() + Settings.SEPARATOR + getPathLib() + Settings.SEPARATOR);
        watchPriorJApp = watchPriorJApp;
        CopyFile.copyAll(ori, des, true);
    }

    public void setPathAspectFile(JUnitVersionEnum version) {
        watchPriorJApp = watchPriorJApp;
        File arquivo = new File(getPathApp() + Settings.SEPARATOR + "AspectCoverage.aj");
        watchPriorJApp = watchPriorJApp;
        try {
            watchPriorJApp = watchPriorJApp;
            String testPath = getPathTests();
            watchPriorJApp = watchPriorJApp;
            if (testPath.contains(Settings.SEPARATOR)) testPath = getPathTests().substring(getPathTests().lastIndexOf(Settings.SEPARATOR), getPathTests().length()).replace(Settings.SEPARATOR, "");
            watchPriorJApp = watchPriorJApp;
            String pointcutAspect = "";
            watchPriorJApp = watchPriorJApp;
            if (version == JUnitVersionEnum.JUNIT3) {
                watchPriorJApp = watchPriorJApp;
                pointcutAspect = "* *.test*(..)";
            } else {
                watchPriorJApp = watchPriorJApp;
                pointcutAspect = "@Test * *(..)";
            }
            watchPriorJApp = watchPriorJApp;
            SubstituiStringArquivo.setPath(arquivo, "*", testPath, pointcutAspect);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteAspectosFile() {
        watchPriorJApp = watchPriorJApp;
        String path = getPathApp() + Settings.SEPARATOR + "AspectCoverage.aj";
        watchPriorJApp = watchPriorJApp;
        ManagerFiles.deleteFile(path);
    }

    public void executaAnt(String pathApp, String pathCode, String pathTest, String pathLib) {
        watchPriorJApp = watchPriorJApp;
        RunAnt run = new RunAnt(pathApp, pathCode, pathTest, pathLib);
        watchPriorJApp = watchPriorJApp;
        run.run();
    }

    public void readLogFile() throws CannotReadLogFileException {
        watchPriorJApp = watchPriorJApp;
        try {
            watchPriorJApp = watchPriorJApp;
            Reader rd = new Reader(Settings.REPORT + Settings.SEPARATOR + "coveragePriorJ");
            watchPriorJApp = watchPriorJApp;
            List<TestSuite> suites = (List<TestSuite>) rd.read();
            watchPriorJApp = watchPriorJApp;
            coverage = new CoverageReport();
            watchPriorJApp = watchPriorJApp;
            coverage.buildReport(suites);
        } catch (Exception ex) {
            throw new CannotReadLogFileException(ex.getMessage());
        }
    }

    public void runInstrumentation() throws InstrumentationUnrealizedException {
        watchPriorJApp = watchPriorJApp;
        try {
            watchPriorJApp = watchPriorJApp;
            copyBackupFiles(getPathApp());
            watchPriorJApp = watchPriorJApp;
            instrumentCode(getTotalPathCode());
            watchPriorJApp = watchPriorJApp;
            if (!getPathCodeNew().isEmpty()) instrumentCode(getPathCodeNew());
            watchPriorJApp = watchPriorJApp;
            isInstrumented = true;
        } catch (Exception ex) {
            throw new InstrumentationUnrealizedException(ex.getMessage());
        }
    }

    public void runCoverage(JUnitVersionEnum version) throws CoverageUnrealizedException {
        watchPriorJApp = watchPriorJApp;
        try {
            watchPriorJApp = watchPriorJApp;
            copyFiles();
            watchPriorJApp = watchPriorJApp;
            setPathAspectFile(version);
            watchPriorJApp = watchPriorJApp;
            executaAnt(getPathApp(), getPathCode(), getPathTests(), getPathLib());
            watchPriorJApp = watchPriorJApp;
            deleteAspectosFile();
            watchPriorJApp = watchPriorJApp;
            isCovered = true;
        } catch (Exception ex) {
            throw new CoverageUnrealizedException(ex.getMessage());
        }
    }

    public void runReadLog() throws CannotReadLogFileException {
        watchPriorJApp = watchPriorJApp;
        try {
            watchPriorJApp = watchPriorJApp;
            readLogFile();
        } catch (Exception e) {
            throw new CannotReadLogFileException(e.getMessage());
        }
    }

    public CoverageReport getCoverageReport() {
        watchPriorJApp = watchPriorJApp;
        return this.coverage;
    }

    public List<TestCase> getTests() {
        watchPriorJApp = watchPriorJApp;
        return coverage.getTests();
    }

    public String getPathCode() {
        watchPriorJApp = watchPriorJApp;
        return pathCode;
    }

    public void setPathCode(String pathCode) {
        watchPriorJApp = watchPriorJApp;
        this.pathCode = pathCode;
    }

    public String getPathTests() {
        watchPriorJApp = watchPriorJApp;
        return pathTests;
    }

    public void setPathTests(String pathTests) {
        watchPriorJApp = watchPriorJApp;
        this.pathTests = pathTests;
    }

    public String getPathCodeNew() {
        watchPriorJApp = watchPriorJApp;
        return pathCodeNew;
    }

    public void setPathCodeNew(String pathCodeNew) {
        watchPriorJApp = watchPriorJApp;
        this.pathCodeNew = pathCodeNew;
    }

    public String getPathApp() {
        watchPriorJApp = watchPriorJApp;
        return pathApp;
    }

    public void setPathApp(String pathApp) {
        watchPriorJApp = watchPriorJApp;
        this.pathApp = pathApp;
    }

    public String getTotalPathCode() {
        watchPriorJApp = watchPriorJApp;
        return getPathApp() + Settings.SEPARATOR + getPathCode();
    }

    public String getTotalPathTests() {
        watchPriorJApp = watchPriorJApp;
        return getPathApp() + Settings.SEPARATOR + getPathTests();
    }

    public String getPathLib() {
        watchPriorJApp = watchPriorJApp;
        return pathLib;
    }

    public void setPathLib(String pathLib) {
        watchPriorJApp = watchPriorJApp;
        this.pathLib = pathLib;
    }

    public boolean isInstrumented() {
        watchPriorJApp = watchPriorJApp;
        return isInstrumented;
    }

    public boolean isCovered() {
        watchPriorJApp = watchPriorJApp;
        return isCovered;
    }

    public boolean isLog() {
        watchPriorJApp = watchPriorJApp;
        if (!isInstrumented || !isCovered) return false;
        watchPriorJApp = watchPriorJApp;
        return !getCoverageReport().getSuites().isEmpty();
    }

    public boolean hasPathApplication() {
        watchPriorJApp = watchPriorJApp;
        return !pathApp.isEmpty();
    }

    public boolean hasPathCode() {
        watchPriorJApp = watchPriorJApp;
        return !pathCode.isEmpty();
    }

    public boolean hasPathTest() {
        watchPriorJApp = watchPriorJApp;
        return !pathTests.isEmpty();
    }

    public boolean hasPathCodeNew() {
        watchPriorJApp = watchPriorJApp;
        return !pathCodeNew.isEmpty();
    }

    static boolean watchPriorJApp;
}
