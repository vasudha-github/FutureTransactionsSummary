package test;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import com.au.futuretransactions.config.PropertyFileReader;
import com.au.futuretransactions.output.CSVCreationImpl;

public class CSVCreationImplTest {
	
	@Test
	public void testCreateCSV() throws IOException {
		ArrayList<HashMap<String,String>> data = getInputData();
		CSVCreationImpl csvCreationImpl = new CSVCreationImpl();
		csvCreationImpl.createCSV(data);
		List<String[]> outputFile =  readOutputFile();
		assertTrue(outputFile!=null);		
		assertTrue(outputFile.size()==3);
		Arrays.stream(outputFile.get(0)).forEach(str -> {
			assertTrue(str.equals("Client_Information,,,,Product_Information,,,,Total_Transaction_Amount"));
		});
		Arrays.stream(outputFile.get(1)).forEach(str -> {
			assertTrue(str.equals("CLIENT TYPE,CLIENT NUMBER,ACCOUNT NUMBER,SUBACCOUNT NUMBER,EXCHANGE CODE,PRODUCT GROUP CODE,SYMBOL,EXPIRATION DATE,NET TOTAL"));
		});
		Arrays.stream(outputFile.get(2)).forEach(str -> {
			assertTrue(str.equals("CL,4321,0003,0001,VX,NE,NX,02021980,7"));
		});
	}
	
	private List<String[]> readOutputFile() throws  IOException{
		List<String[]> result = new ArrayList<String[]>();
		try (Stream<String> stream = Files.lines(Paths.get(getPropertyOutputFile()))) {		
	       stream.forEach(p->result.add(new String[] {p}));	       
	     }
		return result;
	}

	private ArrayList<HashMap<String, String>> getInputData() {
		ArrayList<HashMap<String,String>> dataList = new ArrayList<HashMap<String,String>>();		
		
		HashMap<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("CLIENT TYPE", "CL");
		hashMap.put("CLIENT NUMBER", "4321");
		hashMap.put("ACCOUNT NUMBER", "0003");
		hashMap.put("SUBACCOUNT NUMBER", "0001");
		hashMap.put("EXCHANGE CODE", "VX");
		hashMap.put("PRODUCT GROUP CODE", "NE");
		hashMap.put("SYMBOL", "NX");
		hashMap.put("EXPIRATION DATE", "02021980");
		hashMap.put("QUANTITY LONG", "0008");
		hashMap.put("QUANTITY SHORT", "0001");		
		
		dataList.add(hashMap);		
		return dataList;
	}
	
	
	private String getPropertyOutputFile() {
		PropertyFileReader fieldConfigReader = new PropertyFileReader("ConfigProperties.properties");
		return fieldConfigReader.getConfigMap().get("OUTPUT_FILE_NAME");
	}
}
