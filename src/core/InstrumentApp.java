package core;

import java.io.File;
import java.io.FilenameFilter;

public class InstrumentApp {

    private File dir;

    public InstrumentApp(String path) {
        this.dir = new File(path);
    }

    public void run() throws Exception {
        
        instrumentClassOfPath(dir);
    }

    private void instrumentClassOfPath(File path) throws Exception {       
        FilenameFilter filter = new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return !name.contains("Exception")
                && !name.contains("Test")
                && !name.contains("PriorJ");
            }
        };
        File[] children = path.listFiles(filter);
        if(children != null){
        	for (File arq : children) {
        		if (arq.isDirectory()) {
        			instrumentClassOfPath(arq);
        		} else if (arq.getName().endsWith(".java")) {
        			InstrumentClass instrumenta = new InstrumentClass(path.toString() + "/", arq.getName());
        			instrumenta.instrumentationRun();
        		}
        	}
        }
    }

}
