package week7.assign.WebDriverwait;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebExplicitWait {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

//launch the browser
		WebDriverManager.chromedriver().setup();
		ChromeOptions opt=new ChromeOptions();
		opt.addArguments("disable-notifications");
		ChromeDriver driver=new ChromeDriver(opt);

		driver.get("https://www.leafground.com/waits.xhtml;jsessionid=node018njzuxhhuo4l1il5whhqasf9x375735.node0");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

//wait for visiblity
		//creating object for webdriverwait  
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.findElement(By.xpath("//h5[text()='Wait for Visibility (1 - 10 Sec)']/following::span[text()='Click']")).click();
		//calling the object to say wait 
		//wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[text()='I am here']"))));
		WebElement visible = driver.findElement(By.xpath("//span[text()='I am here']"));
		wait.until(ExpectedConditions.visibilityOf(visible));
		System.out.println(visible.getText());

//wait for invisiblity
		WebElement invisible = driver.findElement(By.xpath("//span[text()='I am about to hide']"));
		boolean displayed = invisible.isDisplayed();//to check the button is visible before click
		System.out.println("button is visible before click : "+displayed);
		driver.findElement(By.xpath("//h5[text()='Wait for Invisibility (1 - 10 Sec)']/following::button")).click();
		WebElement invisible1 = driver.findElement(By.xpath("//span[text()='I am about to hide']"));
		wait.until(ExpectedConditions.invisibilityOf(invisible1));
		//to check the button is invisible we check for the stalesness
		Boolean until = wait.until(ExpectedConditions.stalenessOf(invisible));       
		//boolean displayed1 = invisible.isDisplayed();
		System.out.println("Wait is happened untill the invisiblity : "+until);
//        if(displayed==false) {
//        	System.out.println("button is invisible");
//        }else {
//        	System.out.println("button is vivible still");
//        }

//wait for the button to be clickable
//		driver.findElement(By.xpath("//span[text()='Click First Button']")).click();
//		WebElement secButton = driver.findElement(By.xpath("//span[text()='Click Second']"));
//		wait.until(ExpectedConditions.elementToBeClickable(secButton));
//		secButton.click();

//wait fot text change
		String before = driver.findElement(By.xpath("//span[text()='I am going to change!']")).getText();
		driver.findElement(By.xpath("//h5[text()='Wait for Text Change (1 - 10 Sec)']/following::button")).click();
		WebElement changeButton = driver.findElement(By.xpath("//span[text()='I am going to change!']"));
		wait.until(ExpectedConditions.textToBePresentInElementValue(changeButton, "Did you notice?"));
		String after = driver.findElement(By.xpath("//span[text()='Did you notice?']")).getText();
        System.out.println(before +" and " +after);


	}

}
