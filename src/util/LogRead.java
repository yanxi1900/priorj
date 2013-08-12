package util;

/*
* PriorJ: JUnit Test Case Prioritization.
* 
* Copyright (C) 2012-2013  Samuel T. C. Santos
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
import java.io.File;
import java.io.FileInputStream;

import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class LogRead {

    File arquivo;

    FileInputStream logFile;

    public LogRead(String name) {
        this(name, "txt");
    }

    public LogRead(String name, String extension) {
        try {
            this.arquivo = new File(name + "." + extension);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String read() throws Exception {
        
        String result = "";
        if(!arquivo.exists()) throw new Exception("Arquivo de Log nao foi gerado!");
        this.logFile = new FileInputStream(arquivo);
        FileChannel canal = this.logFile.getChannel();
        
        ByteBuffer buf = ByteBuffer.allocate(32768);
        
        int bytesRead = canal.read(buf);
        
        while (bytesRead != -1) {
            
            buf.flip();
            
            while (buf.hasRemaining()) {
                result += "" + (char) buf.get();
            }
            
            buf.clear();
            
            bytesRead = canal.read(buf);
        }
        buf.clear();
        canal.close();
        this.logFile.close();
        
        return result;
    }

    public FileInputStream readFile() throws Exception {

        this.logFile = new FileInputStream(arquivo);
        return logFile;
    }

    

}
