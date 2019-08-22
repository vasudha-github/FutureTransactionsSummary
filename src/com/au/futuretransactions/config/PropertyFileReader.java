package com.au.futuretransactions.config;

import java.util.LinkedHashMap;
import java.util.Properties;

/**
 * @author vparpati
 *
 */
public class PropertyFileReader {	

		private LinkedHashMap<String,String> mymap = new LinkedHashMap<String, String>();

		/**
		 * Loads the Property file by name and sets the properties in a LinkedHashMap
		 * @param propertyFileName
		 */
		public PropertyFileReader(String propertyFileName){

		    Properties properties = new Properties();

		    try {
		      properties.load(PropertyFileReader.class.getResourceAsStream(propertyFileName));
		    } catch (Exception e) {

		    }
		    mymap = getProperties(properties);		    
		}
		
		/**
		 * Creates a linkedHashMap of the properties.
		 * (Created a linkedHashMap to maintain the order of the properties in FieldDescription.properties)
		 * @param properties
		 * @return
		 */
		public LinkedHashMap<String,String> getProperties(Properties properties) {
			
			LinkedHashMap<String,String> config = new LinkedHashMap<String, String>();
			for (String key : properties.stringPropertyNames()) {
			    String value = properties.getProperty(key);
			    config.put(key, value);
			}
			return config;
		}
		
		/**
		 * @return
		 */
		public LinkedHashMap<String,String> getConfigMap() {
			return mymap;		
		}


}
