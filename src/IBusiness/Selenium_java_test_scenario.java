package IBusiness;

import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Selenium_java_test_scenario {
	public WebDriver driver;
	WebDriverWait wait;

	// Launch the Browser and Enter the URL
	@Test(priority = 1)
	public void navigateUrl() {
		
		

		System.setProperty("webdriver.chrome.driver", ".//Driver_Executables//chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://interview.ibusinessfunding.com/");
		wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.titleContains("iBusiness Funding"));

	}

	@Test(priority = 2)
	public void clickYes() {

		// 1.On the first screen, click on “Yes”
		driver.findElement(By.xpath("//button[@class='yes_btn hover_ease']")).click();

		// 2. Choose any value on screen 2

		driver.findElement(By.xpath("(//li[@class='col-sm-4 col-xs-6'])[2]")).click();
	}

	// 3. a. Input values in screen 3 with invalid data in email field, check
	// the checkbox and click on “Get
	@Test(priority = 3)
	public void inputValuesScreen3() {

		driver.findElement(By.id("first_name")).sendKeys(RandomStringUtils.randomAlphabetic(5));

		driver.findElement(By.id("last_name")).sendKeys(RandomStringUtils.randomAlphabetic(5));

		driver.findElement(By.id("email")).sendKeys(RandomStringUtils.randomAlphabetic(5));

		driver.findElement(By.id("phone")).sendKeys(RandomStringUtils.randomNumeric(10));

		driver.findElement(
				By.cssSelector("div[class='form-group legal-checkbox '] label[class='checkbox-inline'] input")).click();

		driver.findElement(By.xpath("(//button[contains(.,'Get Started')])[2]")).click();

		if (driver.findElement(By.cssSelector("div[class='section-3'] span[class='error_text err_email ']"))
				.isDisplayed()) {

			System.out.println(
					driver.findElement(By.cssSelector("div[class='section-3'] span[class='error_text err_email ']"))
							.getText());
			driver.findElement(By.id("email")).sendKeys(RandomStringUtils.randomAlphabetic(5) + "@gmail.com");
			driver.findElement(By.xpath("(//button[contains(.,'Get Started')])[2]")).click();

		}

	}

	// 4. Choose any option on screen 4 and click on “Next”
	@Test(priority = 4)
	public void screen4() {

		driver.findElement(By.xpath("//div[@class='section-3a-content']/ul/li[2]")).click();
		driver.findElement(By.xpath("//div[@id='sct3a-holder']")).click();

	}

	// 5. Input values in screen 5 and click on “Next”

	@Test(priority = 5)
	public void screen5() {

		driver.findElement(By.id("useOfFundAmount")).sendKeys(RandomStringUtils.randomNumeric(5));
		driver.findElement(By.id("useOfFundDescription")).sendKeys(RandomStringUtils.randomAlphabetic(10));
		driver.findElement(By.xpath("(//button[contains(.,'Next')])[2]")).click();
	}

	// 6. Choose “Sole Proprietor” as value on screen 6

	@Test(priority = 6)
	public void screen6() {

		List<WebElement> allEle = driver.findElements(By.xpath("//div[@class='section-4-content']/ul/li"));
		for (WebElement ele : allEle) {
			if (ele.getText().equals("Sole Proprietor")) {
				ele.click();
				break;
			}

		}

	}

	// 7. Input values in screen 7 and click on “Next”
	@Test(priority = 7)
	public void screen7() {

		driver.findElement(By.id("Legal_Name")).sendKeys(RandomStringUtils.randomNumeric(5));

		driver.findElement(By.xpath("(//button[contains(.,'Next')])[4]")).click();
	}

	// 8. Input values in screen 8, choose any checkbox and click on “Next”

	@Test(priority = 8)
	public void screen8() {

		driver.findElement(By.id("Street")).sendKeys("Bangalore Street");

		for (WebElement ele : driver.findElements(By.cssSelector(".pac-item"))) {

			if (ele.getText().contains("Bangalore Street")) {

				ele.click();
				break;

			}

		}

		driver.findElement(By.id("AptSuite")).sendKeys(RandomStringUtils.randomNumeric(5));

		driver.findElement(By.id("PostalCode")).sendKeys(RandomStringUtils.randomNumeric(5));

		driver.findElement(By.id("No_of_employ")).sendKeys(RandomStringUtils.randomNumeric(2));

		List<WebElement> checkboxes = driver.findElements(By.cssSelector(".pac-item"));
		for (WebElement ele : checkboxes) {
			if (ele.getText().contains("home-based business"))
				ele.click();

		}

		driver.findElement(By.xpath("(//button[contains(.,'Next')])[4]")).click();
	}

}
