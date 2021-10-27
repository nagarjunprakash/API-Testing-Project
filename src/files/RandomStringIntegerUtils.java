package files;

import org.apache.commons.lang.RandomStringUtils;

public class RandomStringIntegerUtils {

	
	
	public static String randomString() {
		String generatedString = RandomStringUtils.randomAlphabetic(15);
		return generatedString.toLowerCase();
	}
	
	
	public static int randomInteger(){
		
		String generatedInteger=RandomStringUtils.randomNumeric(6);
	
		return	Integer.parseInt(generatedInteger);
	}
	
	public static String randomAlphaNumeric()
	{
		
		String generatedAlphaNumeric=RandomStringUtils.randomAlphanumeric(6);
		
		return	generatedAlphaNumeric;
	}
	
	
	
	
	
	

}
