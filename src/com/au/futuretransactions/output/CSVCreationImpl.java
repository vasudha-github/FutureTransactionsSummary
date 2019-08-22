package com.au.futuretransactions.output;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.au.futuretransactions.config.PropertyFileReader;

public class CSVCreationImpl implements CSVCreationInterface {
	
	private final String PROPERTY_FILE_NAME = "ConfigProperties.properties";
	private final String OUTPUT_FILE_NAME = "OUTPUT_FILE_NAME";
	private final String[] TOP_MAIN_HEADERS = { "Client_Information","","","","Product_Information","","","", "Total_Transaction_Amount" };
	private final String[] OUTPUTCSV_ALL_HEADERS = {"CLIENT TYPE", "CLIENT NUMBER", "ACCOUNT NUMBER", "SUBACCOUNT NUMBER","EXCHANGE CODE", "PRODUCT GROUP CODE", "SYMBOL", "EXPIRATION DATE", "NET TOTAL"};
	private final String[] ALL_NEEDED_VALUES = {"CLIENT TYPE", "CLIENT NUMBER", "ACCOUNT NUMBER", "SUBACCOUNT NUMBER","EXCHANGE CODE", "PRODUCT GROUP CODE", "SYMBOL", "EXPIRATION DATE", "QUANTITY LONG","QUANTITY SHORT"};
	

	@Override
	public void createCSV(ArrayList<HashMap<String,String>> data) {
		List<String[]> dataLines = new ArrayList<>();		
		dataLines.add(TOP_MAIN_HEADERS);
		dataLines.add(OUTPUTCSV_ALL_HEADERS);		
		try {
			dataLines.addAll(getDataLines(data));
			writeToCSV(dataLines);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	
	/**
	 * return the dataLines to be written into CSV
	 * @param data
	 * @return
	 */
	public List<String[]> getDataLines(ArrayList<HashMap<String,String>> data){
		
		List<String[]> list = new ArrayList<>();		
		data.forEach(map ->list.add(getFilteredMapWithNetTotal(getFilteredMapWithRequiredValues(map).values().toArray(new String[0]))));	
		return list;
	}
	
	/**
	 * Create a map with key/value in the order of the headers 
	 * @param dataMap
	 * @return
	 */
	private Map<String,String> getFilteredMapWithRequiredValues(final HashMap<String,String> dataMap){
		return Arrays.stream(ALL_NEEDED_VALUES).map(elm -> {				
				String value = dataMap.get(elm);
				value = value.startsWith("0")?"'"+value:value;
				return new AbstractMap.SimpleEntry<>(elm, dataMap.get(elm));
			}
		).collect(Collectors.toMap(AbstractMap.SimpleEntry:: getKey, AbstractMap.SimpleEntry:: getValue,(e1,e2) ->e2, LinkedHashMap::new));		
	}
	
	
	/**
	 * Change array with short/long quantities into an array with NetTotal
	 * @param orig
	 * @return
	 */
	private String[] getFilteredMapWithNetTotal(String[] orig){		
		String quantityShort = orig[orig.length-1];
		String quantityLong = orig[orig.length-2];
		int netTotal = Integer.parseInt(quantityLong)-Integer.parseInt(quantityShort);
		String netTotals = String.valueOf(netTotal);
		String[] newArray = Arrays.copyOf(orig, orig.length-2);
		String[] arrayWithNetTotal = Arrays.copyOf(newArray, newArray.length+1);
		arrayWithNetTotal[arrayWithNetTotal.length-1] = netTotals;		
		return arrayWithNetTotal;
	}
	
	/**
	 * Writing the dataLines to the CSV
	 * @param dataLines
	 * @throws IOException
	 */
	private void writeToCSV(List<String[]> dataLines) throws IOException {		
	    File csvOutputFile = new File(getPropertyConfigMap().get(OUTPUT_FILE_NAME));
	    try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
	        dataLines.stream()
	          .map(this::convertToCSV)
	          .forEach(pw::println);
	    }	  
	}

	/**
	 * Convert String array of data into comma separated strings
	 * @param data
	 * @return
	 */
	private String convertToCSV(String[] data) {
	    return Stream.of(data)
	      .collect(Collectors.joining(","));
	}
	
	/**
	 * Get the property config file
	 * @return
	 */
	private Map<String,String> getPropertyConfigMap() {
		PropertyFileReader fieldConfigReader = new PropertyFileReader(PROPERTY_FILE_NAME);
		return fieldConfigReader.getConfigMap();
	}

}
