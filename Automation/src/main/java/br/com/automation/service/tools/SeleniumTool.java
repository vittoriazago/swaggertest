package br.com.automation.service.tools;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.springframework.stereotype.Component;

import com.google.common.base.Function;

import br.com.automation.service.IAutomationTool;

@Component
public class SeleniumTool implements IAutomationTool<WebDriver, WebElement> {

	static WebDriver page;

	public SeleniumTool() {
	}

	@Override
	public WebDriver openPage(String url) {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		page = new ChromeDriver();
		
		// System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
		// page = new FirefoxDriver();
		
		page.get(url);
		page.manage().window().maximize();
		return page;
	}

	@Override
	public WebElement getFirstByXpath(String xpath) {
		return page.findElement(By.xpath(xpath));
	}

	@Override
	public List<WebElement> getByXPath(String xpath) {
		return (List<WebElement>) page.findElements(By.xpath(xpath));
	}

	@Override
	public WebDriver executeJavaScript(String javaScript) {
		if (javaScript.isEmpty())
			return page;
		((JavascriptExecutor) page).executeScript(javaScript);
		return page;
	}

	@Override
	public WebDriver click(WebElement element) {
		element.click();
		return page;
	}

	@Override
	public WebDriver setValue(WebElement element, String value) {
		element.sendKeys(value);
		return page;
	}

	@Override
	public WebElement getInternalElement(WebElement element, String xpath) {
		return element.findElement(By.xpath(xpath));
	}

	@Override
	public String getAttributeValue(WebElement element, String attribute) {
		return element.getAttribute(attribute);
	}

	@Override
	public String getElementTagName(WebElement element) {
		return element.getTagName();
	}

	@Override
	public String getElementContent(WebElement element) {
		return element.getText();
	}

	/*
	 * Waits until element appears on page
	 */
	public void wait(final By element, long seconds) {

		Wait<WebDriver> wait = new FluentWait<WebDriver>(page).withTimeout(15, TimeUnit.SECONDS)
				.pollingEvery(5, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);

		wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(element);
			}
		});

	}

	@Override
	public void wait(long seconds, String xpath) {
		try {
			Thread.sleep(seconds);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// wait(By.xpath(xpath), seconds)
	}

	@Override
	public List<WebElement> getInternalElements(WebElement element, String xpath) {
		return element.findElements(By.xpath(xpath));
	}

	@Override
	public void close() {
		if (page != null)
			page.close();

	}
}