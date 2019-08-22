package com.au.futuretransactions.output;

import java.util.ArrayList;
import java.util.HashMap;

public interface CSVCreationInterface {
	
    void createCSV(ArrayList<HashMap<String, String>> data);
}
