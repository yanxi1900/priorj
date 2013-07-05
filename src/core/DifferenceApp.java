package core;


import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;


public class DifferenceApp {

    private File pathNewCode;

    private File pathOldCode;

    private List<String> listDiff;

    public DifferenceApp(String pathNewCode, String pathOldCode) {
        this.pathNewCode = new File(pathNewCode);
        this.pathOldCode = new File(pathOldCode);
        listDiff = new ArrayList<String>();
    }

    public void run() throws Exception {
        
        checkDifference(pathNewCode, pathOldCode);
    }

    private void checkDifference(File pathNew, File pathOld) throws Exception {
        FilenameFilter filter = new FilenameFilter() {

            public boolean accept(File dir, String name) {
                return !name.contains("Exception")
                && !name.contains("Test") && !name.contains("PriorJ");
            }
        };
        
        File[] children = pathNew.listFiles(filter);
        
        for (File arq : children) {
            
            if (arq.isDirectory()) {
                String arquivo = arq.getName();
                checkDifference(new File(pathNew + "/" + arquivo), new File(pathOld + "/" + arquivo));
            } else if (arq.getName().endsWith(".java")) {
            	if(!pathNew.exists() || !pathOld.exists()){
            		throw new Exception("File "+ pathOld+" not found in old code.");
            	}
                Difference dif = new Difference(pathNew.toString() + "/" + arq.getName(), pathOld.toString() + "/" + arq.getName());
                dif.diff();
                List<String> affected = dif.getStatementsDiff();
                if (affected.size() > 0) {
                    listDiff.addAll(affected);
                }
            }
        }
    }

    public List<String> getListDiff() {
        
        return listDiff;
    }
    

    public void setListDiff(List<String> listDiff) {
        
        this.listDiff = listDiff;
    }
    
    public static void main(String[] args) throws Exception {
		DifferenceApp diff = new DifferenceApp("C:/UsarAnt/src/app", "C:/UsarAnt/src/appOld");
		diff.run();
		for (String string : diff.getListDiff()) {
			System.out.println(string);
		}
	}

}
