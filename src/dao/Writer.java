package dao;



import util.LogWrite;

import com.thoughtworks.xstream.XStream;



/**
 * Class responsible for writing data into xml format.
 * 
 * @author SamuelSantos
 * @uathor Julio Henrique
 * 
 */
public class Writer {

    private String nomeArquivo;

    /**
     * The constructor.
     *  
     * @param arquivo the file name.
     */
    Writer(String arquivo) {
        this.nomeArquivo = arquivo;
    }

    /**
     * A object for record into the file.
     * 
     * @param objeto a object.
     */
    public void write(Object objeto) {
        
        XStream xml = new XStream();
        
        String dados = xml.toXML(objeto);
        
        LogWrite logg = new LogWrite(nomeArquivo, "xml");
        if(!logg.exists()) dados = "<list>"+dados; 
        logg.write(dados);
    }

}
