package com.Rythmos.RabbitMqProject;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class SeleniumTesting extends MessageSenderApplicationTests {
	static WebDriver driver;

	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "/home/kbyrosu/Downloads/chromedriver_linux64/chromedriver");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}

	@Test
	public void verifyRabbitMqQueues() throws InterruptedException {
		driver.get("http://localhost:15672/#/queues");
		driver.findElement(By.xpath("//*[@id=\"login\"]/form/table/tbody/tr[1]/td/input")).sendKeys("guest");
		driver.findElement(By.xpath("//*[@id=\"login\"]/form/table/tbody/tr[2]/td/input")).sendKeys("guest");
		driver.findElement(By.xpath("//*[@id=\"login\"]/form/table/tbody/tr[3]/td/input")).click();
		driver.navigate().refresh();
		Thread.sleep(2000);
		Select drpdown = new Select(driver.findElement(By.id("update-every")));
		drpdown.selectByValue("30000");
		driver.navigate().refresh();
		Thread.sleep(5000);
		String st = driver.findElement(By.xpath("//*[@id=\"main\"]/div[2]/table/tbody/tr[2]/td[7]"))
				.getText();
		assertEquals(1, Integer.parseInt(st));
		driver.findElement(By.cssSelector("#tabs > li:nth-child(4) > a")).click();
		String str = driver.findElement(By.xpath("//*[@id=\"main\"]/div[2]/table/tbody/tr[8]/td[1]/a")).getText();
		assertEquals("header-exchange", str);
		driver.quit();
	}

}
