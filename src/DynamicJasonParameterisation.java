import static io.restassured.RestAssured.given;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.JasonValuefetch;
import files.Payload;
import files.RandomStringIntegerUtils;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class DynamicJasonParameterisation {
	
	String[] idvaluesarray=new String[getdata().length];
	int j=0;
	JsonPath js;
	

	@Test(dataProvider="Booksdata",priority=1)
	public void addBook(String isbn,String aisle) throws InterruptedException
	{
		RestAssured.baseURI="http://216.10.245.166";
		
		String respone =given().header("Content-Type","application/json").
				body(Payload.addBookPayload(isbn,aisle)).
		when().post("Library/Addbook.php").then().
		assertThat().statusCode(200).extract().response().asString();
		
		 js = JasonValuefetch.jasonMethod(respone);
		 
		 Thread.sleep(1000);
		 if(j<getdata().length)
			{
			 idvaluesarray[j]=js.get("ID");
				System.out.println("IdValue is --------------"+idvaluesarray[j]);
				j++;
			}
		
	}
	

	@DataProvider(name="Booksdata")
	public Object[][] getdata()
	{
	
		return new Object[][]{{"bcd","Ws13S"},{"dfw","JKKS215"},{"jsk","HJS12H"}};
		
	}
	
}
