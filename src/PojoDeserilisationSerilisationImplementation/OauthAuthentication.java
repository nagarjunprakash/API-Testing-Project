package PojoDeserilisationSerilisationImplementation;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import files.JasonValuefetch;
import io.restassured.parsing.Parser;

import static io.restassured.RestAssured.given;

import java.util.List;

public class OauthAuthentication {
	
	
	@Test
	public void oAuthImplementation() throws InterruptedException
	{
		
//		System.setProperty("webdriver.chrome.driver","E:\\Selenium Practice 27Mar2021\\Selenium-Practice-27Mar2021\\Practice_Selenium\\Driver_Executables\\chromedriver.exe");
//		ChromeDriver driver = new ChromeDriver();
		
//		
//		driver.get("https://accounts.google.com/o/oauth2/v2/auth/oauthchooseaccount?scope=https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&auth_url=https%3A%2F%2Faccounts.google.com%2Fo%2Foauth2%2Fv2%2Fauth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https%3A%2F%2Frahulshettyacademy.com%2FgetCourse.php&flowName=GeneralOAuthFlow");
//		driver.findElement(By.cssSelector("input[type='email']")).sendKeys("seleniumt84@gmail.com");
//		driver.findElement(By.cssSelector("input[type='email']")).sendKeys(Keys.ENTER);
//		Thread.sleep(3000);
//		driver.findElement(By.name("password")).sendKeys("Password123@");
//		driver.findElement(By.name("password")).sendKeys(Keys.ENTER);
//		Thread.sleep(3000);
//		
//		String URLTogetAuthorisationCode=driver.getCurrentUrl();

		
		String URLTogetAuthorisationCode = "https://rahulshettyacademy.com/getCourse.php?code=4%2F0AX4XfWhtK1IaoPQptKysXhSYVIs2UlyAoTXdaoWENfNVni9BbSdGUkzukYjYfOnLJUEg5A&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=none#";
		String authorisation_code = URLTogetAuthorisationCode.split("code=")[1].split("&scope=")[0].toString();
		
		
		String toGetToken= given().urlEncodingEnabled(false).queryParams("code",authorisation_code).
		queryParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com").
		queryParams("client_secret","erZOWM9g3UtwNRj340YYaK_W").
		queryParams("redirect_uri","https://rahulshettyacademy.com/getCourse.php").
		queryParams("grant_type","authorization_code").
		when().post("https://www.googleapis.com/oauth2/v4/token").asString();
	
		
		String accesstoken=JasonValuefetch.jasonMethod(toGetToken).get("access_token");
		
		System.out.println("accesstoken is --->>"+accesstoken);
		
		
		//Deserialisation Converting the Response Body/PayLoad into Java Object
		GetcourseDetails gc = given().queryParam("access_token", accesstoken).expect().defaultParser(Parser.JSON).
		when().get("https://rahulshettyacademy.com/getCourse.php").
		as(GetcourseDetails.class);
		
		
//		Print Linked Details from the response
		System.out.println(gc.getLinkedIn());
		
//		Print the course Title of WebAutomation Present in the 2nd Position ie in index 1
		System.out.println(gc.getCourses().getWebAutomation().get(1).getCourseTitle());
		
		
//		Get the Price of the Course Rest Assured Automation using Java
		
		List<Api> apiCourse  =gc.getCourses().getApi();
		
		for(int i=0;i<apiCourse.size();i++)
		{
			
			if(apiCourse.get(i).getCourseTitle().equalsIgnoreCase("Rest Assured Automation using Java"))
			{
				System.out.println("Price is -->>"+apiCourse.get(i).getPrice());
			}
			
		}
		
	//Get Course Names of WebAutomation	
		List<WebAutomation> webAut = gc.getCourses().getWebAutomation();
		
	for(int i=0;i<webAut.size();i++)
	{
		System.out.println("Courses Under WebAutomation is---->> "+webAut.get(i).getCourseTitle());
		
	}
		
	}

}
