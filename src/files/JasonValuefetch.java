package files;

import io.restassured.path.json.JsonPath;

public class JasonValuefetch {
	
	public static JsonPath jasonMethod(String variable)
	{
		
		JsonPath js=new JsonPath(variable);
		return js;
	}

	
}
