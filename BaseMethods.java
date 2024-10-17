package org.baseMethods;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.time.Duration;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseMethods {
	
	WebDriver driver;
	
	public void browserLaunch(String browser,String url) {
		
		if(browser.equals("chrome")) {
			driver = new ChromeDriver();
		}else if(browser.equals("firefox")) {
			driver = new FirefoxDriver();
		}else if(browser.equals("edge")) {
			driver = new EdgeDriver();
		}
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.manage().window().maximize();
	}
	
	
	private void brokenLinks() throws IOException {
		List<WebElement> element = driver.findElements(By.tagName("a"));
		int Count = 0;
		for (WebElement single : element) {
			String attribute = single.getAttribute("href");
			URL url = new URL(attribute);
			URLConnection openConnection = url.openConnection();
			HttpURLConnection connection = (HttpURLConnection)openConnection;
			int responseCode = connection.getResponseCode();
			if(responseCode>=400) {
				System.out.println(attribute);
				Count++;	
			}
		}
		System.out.println(Count);

	}
	
	public WebElement withId(String id) {
		WebElement elementid = driver.findElement(By.id(id));
		return elementid;

	}
	public WebElement withName(String name) {
		WebElement elementName = driver.findElement(By.name(name));
		return elementName;
	}
	
	public WebElement withXpath(String xpath) {
		WebElement elementName = driver.findElement(By.xpath(xpath));
		return elementName;
	}
	
	public void sendValues(WebElement element,String data) {
		element.sendKeys(data);
		
	}
	
	private WebElement explicitWait(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement waits = wait.until(ExpectedConditions.elementToBeClickable(element));
		return waits;
	}
	
	public void moveToElement(WebElement element) {
	Actions act = new Actions(driver);
	act.moveToElement(element).perform();	
	}
	
	public void ScreenShot(String path) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File(path);
		FileUtils.copyFile(src, dest);
	}
	
	public void selectOptionsbyText(WebElement element,String input) {
		Select s = new Select(element);
		List<WebElement> options = s.getOptions();
		for (WebElement each : options) {
			String text = each.getText();
			if(text.equals(input)) {
				each.click();
			}
		}
	}
	
	
	
	
	

}
