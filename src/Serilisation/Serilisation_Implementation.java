package Serilisation;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import files.Payload;
import io.restassured.RestAssured;

public class Serilisation_Implementation {
	

	
	@Test
	public void serilisationConceptAddPlaceAPI()
	{
		AddPlace ap = new AddPlace();
		List<String> ty=new ArrayList<String>();
		ty.add("shoe park");
		ty.add("shop");
		ap.setTypes(ty);
		ap.setWebsite("http://google.com");
		ap.setAddress("29, side layout, cohen 09");
		ap.setName("Frontline house");
		ap.setAccuracy(50);
		ap.setLanguage("French-IN");
		ap.setPhone_number("(+91) 983 893 3937");
		Location l = new Location();
		l.setLng(33.427362);
		l.setLat(-38.383494);
		ap.setLocation(l);
		
		
	RestAssured.baseURI="https://rahulshettyacademy.com";
//	.log().all()
	
	String response = given().queryParam("key", "qaclick123").header("Content-Type","application/json").
	body(ap).when().post("maps/api/place/add/json").asString();
			
	System.out.println(response);
	
	}
	

}
