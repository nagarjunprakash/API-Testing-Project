package IBusiness;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.internal.thread.ThreadExecutionException;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;

public class IBusinessAPI_Assignment {

	String access_token;
	String id_value;
	WebDriver driver;
	WebDriverWait wait;

	@Test
	public void accessToken() {

		RestAssured.baseURI = "https://login.salesforce.com";

		String response = given().urlEncodingEnabled(false)
				.queryParams("client_id",
						"3MVG9fe4g9fhX0E5qgtfiN3DaF2W..oTppSVm3aQseMPrMG1rR0ie5_Q.pOx7TuLFvaG1dJN1cFTQvboyZobT")
				.queryParams("client_secret", "AA94756377125E3FA965C0C2B91290A55E852B53E401CFBD46BBCEBDABB1B3F3")
				.queryParams("username", "testforibusiness@ibusiness.in").queryParams("password", "Test@123")
				.queryParams("grant_type", "password").when().post("/services/oauth2/token").then().extract().response()
				.asString();

		access_token = new JsonPath(response).getString("access_token");
		System.out.println(access_token);

	}

	@Test
	public void salesforceAccount() {

		RestAssured.baseURI = "https://empathetic-shark-a7palw-dev-ed.my.salesforce.com";

		String response1 = given().header("Authorization", "Bearer " + access_token)
				.header("Content-Type", "application/json").header("Sforce-Auto-Assign", "TRUE")
				.body("{\r\n" + "  \"FirstName\": \"First Name\",\r\n" + "  \"Email\": \"emailid\",\r\n"
						+ "  \"Phone\": \"9544545665\",\r\n" + "  \"LastName\": \"lastname\",\r\n"
						+ "  \"Role\": \"Role Value\"\r\n" + "}\r\n" + "")
				.when().post("/services/data/v48.0/sobjects/Contact").then().extract().response().asString();
		System.out.println(response1);

		id_value = new JsonPath(response1).getString("id");

	}

	@Test
	public void salesforcelogin() {

		System.setProperty("webdriver.chrome.driver", ".//Driver_Executables//chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(" https://login.salesforce.com");
		driver.findElement(By.id("username")).sendKeys("testforibusiness@ibusiness.in");
		driver.findElement(By.id("password")).sendKeys("Test@123");
		driver.findElement(By.id("Login")).click();
		
		driver.navigate().to("https://empathetic-shark-a7palw-dev-ed.lightning.force.com+"+id_value);
		

	}

}
