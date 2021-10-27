package SpecsBuilder;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import Serilisation.AddPlace;
import Serilisation.Location;
import files.Payload;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecsBuilder_Implementation {
	

	
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
		
		
		
		RequestSpecification reqSpec = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").
		addQueryParam("key", "qaclick123").
		setContentType(ContentType.JSON).build();
		
		ResponseSpecification resSpec = new ResponseSpecBuilder().expectStatusCode(200).build();
		
	
	String response = given().spec(reqSpec).
	body(ap).when().post("maps/api/place/add/json").
	then().spec(resSpec).extract().response().asString();
	
			
	System.out.println(response);
	
	}
	

}
