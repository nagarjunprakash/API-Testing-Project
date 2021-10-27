package implementationRestassuredLibrary22Sep2021;

import static io.restassured.RestAssured.given;

import java.io.File;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;

public class AddingTheCommentandAttachment {

	@Test
	public void cookieSessionCreateAddComment() {
		RestAssured.baseURI = "http://localhost:8080";

		SessionFilter session = new SessionFilter();

		// Creating the Cookie Session
		given().header("Content-Type", "application/json")
				.body("{ \"username\": \"seleniumt83\", \"password\": \"TRgrn64GHv!D#DX\" }").filter(session).when()
				.post("/rest/auth/1/session").then().assertThat().statusCode(200);

		// Adding the Commment

		given().pathParam("key", "10100").header("Content-Type", "application/json")
				.body("{\r\n"
						+ "    \"body\": \"This is the Second comment added for Payment Issue to add the screenshot attachment\",\r\n"
						+ "    \"visibility\": {\r\n" + "        \"type\": \"role\",\r\n"
						+ "        \"value\": \"Administrators\"\r\n" + "    }\r\n" + "}").filter(session).
				when().post("/rest/api/2/issue/{key}/comment").
				then().assertThat().statusCode(201);

		// Add the Attachment
		given().pathParam("key", "10100").header("X-Atlassian-Token", "no-check")
				.header("Content-Type", "multipart/form-data")
				.multiPart("file", new File("./Resources\\Screenshot (745).png")).filter(session).when()
				.post("/rest/api/2/issue/{key}/attachments").then().assertThat().statusCode(200);

	}

}
