package com.au.futuretransactions.input;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import com.au.futuretransactions.config.PropertyFileReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author vparpati
 *
 */
public class InputSourceReaderImpl implements InputSourceReaderInterface {
	
	private final String PROPERTY_FILE_NAME = "ConfigProperties.properties";
	private final String INPUT_FILE_NAME = "INPUT_FILE_NAME";
	
	@Override
	public ArrayList<HashMap<String,String>> getValuesFromInput(LinkedHashMap<String,String> configMap) throws IOException{
		List<String> inputLines = readInputSourceFile();
		ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
		inputLines.forEach(line -> list.add(createMap(line,configMap)));
		return list;
	}
	
	/**
	 * Reads Input File and returns the lines as list
	 * @return
	 * @throws IOException
	 */
	private List<String> readInputSourceFile() throws  IOException{
		List<String> result;
		try (Stream<String> stream = Files.lines(Paths.get(getInputFileName()))) {
	      result =  stream.collect(Collectors.toList());
	     }
		return result;
	}

	/**
	 * Maps the values in each line to the keys as mentioned in Fielddescription.properties file
	 * @param line
	 * @param configMap
	 * @return
	 */
	private HashMap<String,String> createMap(String line,HashMap<String,String> configMap) {

		HashMap<String,String> outputMappedValues = new HashMap<String,String>();
		configMap.forEach((key,value)->{
			String[] arrOfStr = value.split("-");			
			int beginIndex =Integer.parseInt(arrOfStr[0])-1;
			int endIndex =Integer.parseInt(arrOfStr[1]);
			if(line.length()>=endIndex) {
			    outputMappedValues.put(key.replace(".", " "), line.substring(beginIndex, endIndex));
			}
		});		
		return outputMappedValues;
	}
	
	/**
	 * Reads the Propertyfile and gets the path of the input file.
	 * @return
	 */
	private String getInputFileName() {
		PropertyFileReader fieldConfigReader = new PropertyFileReader(PROPERTY_FILE_NAME);
		return fieldConfigReader.getConfigMap().get(INPUT_FILE_NAME);
	}
}
 

