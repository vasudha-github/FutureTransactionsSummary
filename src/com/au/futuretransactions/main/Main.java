package com.au.futuretransactions.main;

import java.util.ArrayList;
import java.util.HashMap;

import com.au.futuretransactions.config.PropertyFileReader;
import com.au.futuretransactions.input.InputSourceReaderImpl;
import com.au.futuretransactions.output.CSVCreationImpl;

public class Main {

	public static final String FIELD_DESCRIPTION_PROPERTY_FILE="FieldDescription.properties";
	public static void main(String[] args) {
		try {
		PropertyFileReader fieldConfigReader = new PropertyFileReader(FIELD_DESCRIPTION_PROPERTY_FILE);
		InputSourceReaderImpl inputSourceReader = new InputSourceReaderImpl();
		ArrayList<HashMap<String,String>> outputValues = inputSourceReader.getValuesFromInput(fieldConfigReader.getConfigMap());
		
		CSVCreationImpl csvCreation = new CSVCreationImpl();
		csvCreation.createCSV(outputValues);
		}catch(Exception e){
			e.printStackTrace();
		}

	}

}
