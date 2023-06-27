package week7.assign.WebDriverwait;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateoppoScroll {

	public static void main(String[] args) throws InterruptedException {

		//load the url and maximize		
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("disable-notifications");
		ChromeDriver driver=new ChromeDriver(options);

		driver.get("https://login.salesforce.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		//login to the salesforce
		driver.findElement(By.id("username")).sendKeys("viji@testleaf.com");
		driver.findElement(By.id("password")).sendKeys("Alpha@123");
		driver.findElement(By.id("Login")).click();
		//Thread.sleep(8000);


		// give wait explicitly to look for the element to be visible
		// Click on toggle menu button from the left corner
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		WebElement toggle = driver.findElement(By.xpath("//div[@class='slds-icon-waffle']"));
		wait.until(ExpectedConditions.visibilityOf(toggle));
		// wait.until(ExpectedConditions.stalenessOf(toggle));
		toggle.click();
		//Thread.sleep(3000);

		// Click view All and click Sales from App Launcher
		WebElement view = driver.findElement(By.xpath("//button[text()='View All']"));
		wait.until(ExpectedConditions.visibilityOf(view));
		view.click();
		WebElement sales = driver.findElement(By.xpath("//p[text()='Sales']"));
		driver.executeScript("arguments[0].click()", sales);
		Thread.sleep(3000);

		// Click on Opportunity tab
		WebElement opportunity = driver.findElement(By.xpath("//span[text()='Opportunities']"));
		driver.executeScript("arguments[0].click()", opportunity);

		// Click on New button
		driver.findElement(By.xpath("//a[@title='New']")).click();

		// Enter Opportunity name as 'Salesforce Automation by *Your Name*,Get the text
		// and Store it
		driver.findElement(By.xpath("//input[@name='Name']")).sendKeys("Salesforce Automation");
		String text = driver.findElement(By.xpath("//input[@name='Name']")).getAttribute("value");
		System.out.println("Input = "+text);
		//for scroll down the window
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,350)",  "");

		// Choose close date as Today
		WebElement date = driver.findElement(By.xpath("//button[@title='Select a date for Close Date']"));
		driver.executeScript("arguments[0].click()", date);
		//Thread.sleep(3000);
		//driver.findElement(By.xpath("//button[@name='today']")).click();
		driver.findElement(By.xpath("//span[text()='31']")).click();

		// Select 'Stage' as Need Analysis
		driver.findElement(By.xpath("//button[@aria-label='Stage, --None--']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[@title='Needs Analysis']")).click();
		//driver.executeScript("arguments[0].click()", need);
		Thread.sleep(3000);

		// click Save and VerifyOppurtunity Name"
		driver.findElement(By.xpath("//button[text()='Save']")).click();

		String text1 = driver.findElement(By.xpath("//span[contains(@class,'toastMessage')]")).getText();
		//Thread.sleep(3000);
		System.out.println("toast message: "+text1);
		String name = driver.findElement(By.xpath("//span[@class='toastMessage slds-text-heading--small forceActionsText']/a/div")).getText();
		System.out.println("name in toast message : "+name);

		if (text1.contains(text)) {
			System.out.println("new opportunity is verified as "+text1);
		} else {
			System.out.println("new opportunity is not verified");
		}

	}

}
