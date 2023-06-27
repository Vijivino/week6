package week7.steps;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOpportunity extends Baseclass{
//
//public ChromeDriver driver;
//@Given("launch the chrome browser")
//public void launch_the_chrome_browser() {
//	WebDriverManager.chromedriver().setup();
//}
//@Given("load the url and maximize and disable notifications")
//public void load_the_url_and_maximize_and_disable_notifications() {
//	ChromeOptions opt=new ChromeOptions();
//	driver=new ChromeDriver(opt);
//	driver.get("https://login.salesforce.com");
//	driver.manage().window().maximize();
//	driver.manage().deleteAllCookies();
//	opt.addArguments("--disable-notifications");
//    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
//}
	public WebDriverWait wait;
	public String text,text1;
	
@When("enter the username as {string}")
public void enter_the_username_as(String username) {
	driver.findElement(By.id("username")).sendKeys(username);
}
@When("enter the password as {string}")
public void enter_the_password_as(String password) {
	driver.findElement(By.id("password")).sendKeys(password);
}
@When("click login")
public void click_login() throws InterruptedException {
	driver.findElement(By.id("Login")).click();
	Thread.sleep(4000);
	}
@When("click toggle menu")
public void click_toggle_menu() throws InterruptedException {
	wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	WebElement toggle = driver.findElement(By.xpath("//div[@class='slds-icon-waffle']"));
	wait.until(ExpectedConditions.visibilityOf(toggle));
	toggle.click();
	//Thread.sleep(3000);
}
@When("click view all")
public void click_view_all() throws InterruptedException {
	WebElement view = driver.findElement(By.xpath("//button[text()='View All']"));
	wait.until(ExpectedConditions.visibilityOf(view));
	view.click();
	WebElement sales = driver.findElement(By.xpath("//p[text()='Sales']"));
	driver.executeScript("arguments[0].click()", sales);
	Thread.sleep(3000);
}
@When("click opportunity tab")
public void click_opportunity_tab() {
	WebElement opportunity = driver.findElement(By.xpath("//span[text()='Opportunities']"));
	driver.executeScript("arguments[0].click()", opportunity);
}
@When("click new button")
public void click_new_button() {
	driver.findElement(By.xpath("//a[@title='New']")).click();
}
@When("enter the opportunity name as (.*)$")
public void enter_the_opportunity_name_as_opportunityname(String opportunityname) {
	driver.findElement(By.xpath("//input[@name='Name']")).sendKeys("Salesforce Automation");
}	
@And("store the input text")
public void storetext() {	
	text = driver.findElement(By.xpath("//input[@name='Name']")).getAttribute("value");
	System.out.println("Input = "+text);
}
@And ("enter the stage as (.*)$")
public void enterstage(String stagename) {
	driver.findElement(By.xpath("//button[@aria-label='Stage, --None--']")).click();
	driver.findElement(By.xpath("//span[@title='"+stagename+"']")).click();
	}
@And ("choose date and click save")
public void choosedate() {
	WebElement date = driver.findElement(By.xpath("//button[@title='Select a date for Close Date']"));
	driver.executeScript("arguments[0].click()", date);
	//Thread.sleep(3000);
	//driver.findElement(By.xpath("//button[@name='today']")).click();
	driver.findElement(By.xpath("//span[text()='31']")).click();
	driver.findElement(By.xpath("//button[text()='Save']")).click();
}
@Then("verify opportunity name")
public void verify() throws InterruptedException {
	text1 = driver.findElement(By.xpath("//span[contains(@class,'toastMessage')]")).getText();
	Thread.sleep(3000);
	System.out.println("toast message: "+text1);
	String name = driver.findElement(By.xpath("//span[@class='toastMessage slds-text-heading--small forceActionsText']/a/div")).getText();
	System.out.println("name in output : "+name);
//verifications using assertions -- hard assertions-stop the execution and throw the error immediately
	//Assert.assertEquals(name, text); //exactly equals
	//Assert.assertTrue(name.contains(text)); //text contains 
	//System.out.println("Opportunity with name is verified ");
//verification by soft assertion--have the failed error and execute the next steps further and 
//throw the error at last only when we are calling it
	SoftAssert soft=new SoftAssert();
	soft.assertTrue(name.contains(text));
	//soft.assertEquals(name, "text");
//call the error can be done immediately or also at the end
	soft.assertAll(); //mandatory so that the result can be roprted,without this it simply say passed
	
//	if (text1.contains(text)) {
//		System.out.println("new opportunity is verified as "+text1);
//	} else {
//		System.out.println("new opportunity is not verified");
//	}

}





}
