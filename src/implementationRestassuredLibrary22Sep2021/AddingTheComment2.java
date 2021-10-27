package implementationRestassuredLibrary22Sep2021;

import static io.restassured.RestAssured.given;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

import files.JasonValuefetch;
import files.Payload;
import files.RandomStringIntegerUtils;
import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;

public class AddingTheComment2 {

	@Test
	public void cookieSessionCreateAddComment() {
		RestAssured.baseURI = "http://localhost:8080";
		
		String ExpectedComment=RandomStringIntegerUtils.randomString();
//		String ExpectedComment="Test1";
		String actualComment = null;
		
		SessionFilter session = new SessionFilter();

		// Creating the Cookie Session
		given().relaxedHTTPSValidation().header("Content-Type", "application/json")
				.body("{ \"username\": \"seleniumt83\", \"password\": \"TRgrn64GHv!D#DX\" }").filter(session).when()
				.post("/rest/auth/1/session").then().assertThat().statusCode(200);

		
		
		// Adding the Commment

		String commentIdResponse = given().pathParam("key", "10100").header("Content-Type", "application/json")
				.body("{\r\n" + 
						"    \"body\": \""+ExpectedComment+"\",\r\n" + 
						"    \"visibility\": {\r\n" + 
						"        \"type\": \"role\",\r\n" + 
						"        \"value\": \"Administrators\"\r\n" + 
						"    }\r\n" + 
						"}").filter(session).
				when().post("/rest/api/2/issue/{key}/comment").
				then().assertThat().statusCode(201).extract().response().asString();
		
		//Printing the comment ID
		String commentId = JasonValuefetch.jasonMethod(commentIdResponse).getString("id");
		System.out.println("This is the comment ID for the comment added "+commentId);
		
		//In order to get the issue Details
		String getIssueResponse = given().filter(session).pathParam("key", "10100").queryParam("fields", "comment").
		when().get("/rest/api/2/issue/{key}")
		.then().assertThat().statusCode(200).extract().response().asString();
		
		
		//Logic written to Print the Added comment , Print Id of all the comments and also do the assertion for the Expected and actual comment added
		for(int i=0;i<JasonValuefetch.jasonMethod(getIssueResponse).getInt("fields.comment.comments.size()");i++)
		{
//			Print all comment Ids Present in the Jira Ticket
			System.out.println(JasonValuefetch.jasonMethod(getIssueResponse).getString("fields.comment.comments["+i+"].id"));
			
		if(JasonValuefetch.jasonMethod(getIssueResponse).getString("fields.comment.comments["+i+"].id").equalsIgnoreCase(commentId))
		{
			 actualComment = JasonValuefetch.jasonMethod(getIssueResponse).getString("fields.comment.comments["+i+"].body");
			System.out.println("This is the actual comment added --->>"+ actualComment+"<<---found in the Jira Ticket with Id -->>"+JasonValuefetch.jasonMethod(getIssueResponse).getString("fields.comment.comments["+i+"].id"));
				break;
		}
		}
//		do the assertion for the Expected and actual comment added
		Assert.assertEquals(actualComment, ExpectedComment);
		
		
//		Logic delete all the added comments
		for(int i=0;i<JasonValuefetch.jasonMethod(getIssueResponse).getInt("fields.comment.comments.size()");i++)
		{
			String commentIdforDeleting=JasonValuefetch.jasonMethod(getIssueResponse).getString("fields.comment.comments["+i+"].id");
			given().filter(session).pathParam("key", "10100").
			when().delete("/rest/api/2/issue/{key}/comment/"+commentIdforDeleting+"").
			then().assertThat().statusCode(204);
			
		
		}
		
		
		
	}

}
