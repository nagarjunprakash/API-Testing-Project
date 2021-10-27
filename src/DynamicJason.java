import org.apache.commons.lang.RandomStringUtils;
import org.testng.annotations.Test;

import files.JasonValuefetch;
import files.Payload;
import files.RandomStringIntegerUtils;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
	
import static io.restassured.RestAssured.*;

public class DynamicJason {

	
	@Test
	public void addBook()
	{
		
		RestAssured.baseURI="http://216.10.245.166";
			
		String alphabetic=RandomStringIntegerUtils.randomString();
		String alphaNumeric=RandomStringIntegerUtils.randomAlphaNumeric();

		System.out.println(alphabetic+" -----"+ alphaNumeric);
		
		String respone =given().header("Content-Type","application/json").
				body(Payload.addBookPayload(alphabetic,alphaNumeric)).
		when().post("Library/Addbook.php").then().
		assertThat().statusCode(200).extract().response().asString();
		
		
		JsonPath js = JasonValuefetch.jasonMethod(respone);
		String id=js.get("ID");
		
		
		System.out.println(id);
		
		
		
		
	}
	
	
}
