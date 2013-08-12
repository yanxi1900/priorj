package util;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ReadFileTest {

	ReadFile reader;
	String path = "";
	
	@Before
	public void setUp(){
		path = Settings.RESOURCES_FILES + Settings.SEPARATOR;
	}
	
	
	@Test
	public void testReadContentFileAsList() {
		String filename ="TMC.txt";
		
		path += filename;
		
		List<String> contentList = reader.readFileAndReturnList(path);
		
		assertFalse(contentList.isEmpty());
		assertEquals(6, contentList.size());
	}

}
