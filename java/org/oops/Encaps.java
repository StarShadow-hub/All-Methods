package org.oops;

import java.util.HashMap;
import java.util.Map;

public class Encaps {
public static void main(String[] args) {
	
	
	Map<String, Object> m = new HashMap<String, Object>();
	m.put("Name", "vijay");
	Object object = m.get("Name");
	System.out.println(object);
	
	
}


}

