package dao;

/*
* PriorJ: JUnit Test Case Prioritization.
* 
* Copyright (C) 2012-2013  Samuel T. C. Santos, Julio H. Rocha
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
