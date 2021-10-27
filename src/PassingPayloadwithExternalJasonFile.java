import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.JasonValuefetch;
import files.Payload;
import files.RandomStringIntegerUtils;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class PassingPayloadwithExternalJasonFile {
	
	
	

	@Test
	public void addBook() throws InterruptedException, IOException
	{
		byte[] jasonPath = Files.readAllBytes(Paths.get("E:\\Selenium Practice 27Mar2021\\API Testing Project\\Resources\\addBookPayload.jason"));
		RestAssured.baseURI="http://216.10.245.166";
		
		String respone =given().header("Content-Type","application/json").
				body(new String(jasonPath)).
		when().post("Library/Addbook.php").then().
		assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js = JasonValuefetch.jasonMethod(respone);
		 
		 System.out.println(js.getString("ID"));
		 System.out.println(js.getString("Msg"));
		
	}
	
	
	

	
	
}
