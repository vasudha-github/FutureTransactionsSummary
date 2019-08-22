package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.junit.jupiter.api.Test;

import com.au.futuretransactions.input.InputSourceReaderImpl;
import com.au.futuretransactions.input.InputSourceReaderInterface;


public class InputSourceReaderImplTest {
	
	public static final String RECORD_CODE = "RECORD CODE";
	public static final String CLIENT_TYPE = "CLIENT TYPE";
	public static final String CLIENT_NUMBER = "CLIENT NUMBER";
	public static final String ACCOUNT_NUMBER = "ACCOUNT NUMBER";
	public static final String OPEN_CLOSE_CODE = "OPEN CLOSE CODE";
	public static final String OPPOSITE_TRADER_ID = "OPPOSITE TRADER ID";
	
	LinkedHashMap<String,String> configMap = new LinkedHashMap<String,String>();
	
	
	@Test
	public void testCreateCSV() throws IOException{
		
		InputSourceReaderInterface inputReader = new InputSourceReaderImpl();		
		ArrayList<HashMap<String,String>> resultedList = inputReader.getValuesFromInput(createConfigData());
		assertEquals(resultedList.size(),717);
		assertEquals(resultedList.get(0).size(),6);
		assertTrue(resultedList.get(0).get(RECORD_CODE).equals("315"));
		assertTrue(resultedList.get(0).get(CLIENT_TYPE).equals("CL  "));
		assertTrue(resultedList.get(0).get(CLIENT_NUMBER).equals("4321"));		
		assertTrue(resultedList.get(0).get(ACCOUNT_NUMBER).equals("0002"));		
		assertTrue(resultedList.get(0).get(OPPOSITE_TRADER_ID).equals("       "));
		assertTrue(resultedList.get(0).get(OPEN_CLOSE_CODE).equals("O"));
	}
	
	
	public LinkedHashMap<String,String> createConfigData() {
		
		configMap.putIfAbsent(RECORD_CODE, "1-3");
		configMap.putIfAbsent(CLIENT_TYPE, "4-7");
		configMap.putIfAbsent(CLIENT_NUMBER, "8-11");
		configMap.putIfAbsent(ACCOUNT_NUMBER, "12-15");
		configMap.putIfAbsent(OPEN_CLOSE_CODE, "176-176");
		configMap.putIfAbsent(OPPOSITE_TRADER_ID, "169-175");
		return configMap;
	}

}
