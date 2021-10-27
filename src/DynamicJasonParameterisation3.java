import static io.restassured.RestAssured.given;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.JasonValuefetch;
import files.Payload;
import files.RandomStringIntegerUtils;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class DynamicJasonParameterisation3 {
	
	String[] idvaluesarray=new String[getdata().length];
	int j=0;
	int i=0;
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
//		String id=js.get("ID");
//		System.out.println(id);
//		System.out.println("Get Data--------------"+getdata().length);
		 
		 Thread.sleep(1000);
		 if(j<getdata().length)
			{
			 idvaluesarray[j]=js.get("ID");
				System.out.println("IdValue--------------"+idvaluesarray[j]);
				j++;
			}
		
	}
	
	
	
	@Test(dataProvider="Idvalues",priority=2)
	public void deleteBook(String idValue )
	{
		
		RestAssured.baseURI="http://216.10.245.166";
			
		String respone =given().header("Content-Type","application/json").
				body("{\r\n" + 
						" \r\n" + 
						"\"ID\" : \""+idValue+"\"\r\n" + 
						" \r\n" + 
						"} \r\n" + 
						"").
		when().post("DeleteBook.php").then()
		.extract().response().asString();
		
//		 js = JasonValuefetch.jasonMethod(respone);
//		System.out.println(js.getString("msg"));
		
	}
	
	

	@DataProvider(name="Booksdata")
	public Object[][] getdata()
	{
	
		return new Object[][]{{"bcd","Ws13S"},{"dfw","JKKS215"},{"jsk","HJS12H"}};
		
	}
	
	
	@DataProvider(name="Idvalues")
	public Object[] getIdvalues() 
	{
		
		Object[] test = new Object[]{idvaluesarray[i]};
		i++;
		return test;
	}
	
}
