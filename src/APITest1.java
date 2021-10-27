import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import files.JasonValuefetch;
import files.Payload;

public class APITest1 {
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	RestAssured.baseURI="https://rahulshettyacademy.com";
	
	String response = given().queryParam("key", "qaclick123").header("Content-Type","application/json").
	body(Payload.addPlace()).when().post("maps/api/place/add/json").then().assertThat().statusCode(200).body("scope", equalTo("APP"))
			.header("Server", "Apache/2.4.18 (Ubuntu)").extract().response().asString();
	
	System.out.println("This is the response "+response);
	
	JsonPath js=new JsonPath(response);
	String placeID = js.getString("place_id");
	System.out.println("---------------------||----------------");
	System.out.println("This is the PlaceId "+placeID);
	
	String addressToBeUpdated="Channarayapattna";
	
	//Updating the Place Here
	
	given().queryParam("key", "qaclick123").header("Content-Type","application/json").body(Payload.updatePlace(placeID,addressToBeUpdated)).when().
	put("maps/api/place/update/json").then().assertThat().statusCode(200).body("msg",equalTo("Address successfully updated"));
	
	
	
String updatedAddressBodyPutMethod = JasonValuefetch.jasonMethod(Payload.updatePlace(placeID,addressToBeUpdated)).getString("address");

//Asserting or validating through the Restassured
 given().queryParam("key", "qaclick123").queryParam("place_id", placeID).when().get("maps/api/place/get/json")
.then().assertThat().statusCode(200).body("address", equalTo(updatedAddressBodyPutMethod));

 System.out.println(updatedAddressBodyPutMethod);

//TestNg Assertion
String actualAddress = given().queryParam("key", "qaclick123").queryParam("place_id", placeID).when().get("maps/api/place/get/json")
	.then().assertThat().statusCode(200).body("address", equalTo(updatedAddressBodyPutMethod)).extract().response().asString();
	
	Assert.assertEquals(JasonValuefetch.jasonMethod(actualAddress).getString("address"), addressToBeUpdated);
	
	}

}
