package com.au.futuretransactions.input;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public interface InputSourceReaderInterface {

	ArrayList<HashMap<String, String>> getValuesFromInput(LinkedHashMap<String, String> configMap) throws IOException;

}
