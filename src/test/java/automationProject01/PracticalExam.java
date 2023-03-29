package automationProject01;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class PracticalExam {
	WebDriver driver;

	@Before
	public void init() {
		System.setProperty("webdriver.chrome.driver", "driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.get("http://techfios.com/test/101/");
		driver.manage().window().maximize();
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	private void addSomeCheckBox() throws InterruptedException {
		// Adding four check boxes that we will select later
		driver.findElement(By.name("data")).sendKeys("CheckBox01");
		driver.findElement(By.cssSelector("input[name='submit'][value='Add']")).click();
		driver.findElement(By.name("data")).sendKeys("CheckBox02");
		driver.findElement(By.cssSelector("input[name='submit'][value='Add']")).click();
		driver.findElement(By.name("data")).sendKeys("CheckBox03");
		driver.findElement(By.cssSelector("input[name='submit'][value='Add']")).click();
		driver.findElement(By.name("data")).sendKeys("CheckBox04");
		driver.findElement(By.cssSelector("input[name='submit'][value='Add']")).click();
		Thread.sleep(2000);
	}

	private void removeAllcheckbox() throws InterruptedException {
		// First check all check box
		driver.findElement(By.name("allbox")).click();
	
		// Next deleting all the check boxes
		driver.findElement(By.cssSelector("input[name='submit'][value='Remove']")).click();
	}

	@Test
	public void test1() throws InterruptedException {
		removeAllcheckbox();
		addSomeCheckBox();
		driver.findElement(By.name("allbox")).click();
		WebElement checkbox1 = driver.findElement(By.name("todo[0]"));
		WebElement checkbox2 = driver.findElement(By.name("todo[1]"));
		WebElement checkbox3 = driver.findElement(By.name("todo[2]"));
		WebElement checkbox4 = driver.findElement(By.name("todo[3]"));

		Assert.assertTrue("checkbox is not selected!",
				checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected());
	}

	@Test
	public void test2() throws InterruptedException {
		driver.findElement(By.name("data")).sendKeys("CheckBox05");
		driver.findElement(By.cssSelector("input[name='submit'][value='Add']")).click();
		driver.findElement(By.name("todo[4]")).click();
		driver.findElement(By.cssSelector("input[name='submit'][value='Remove']")).click();

		Boolean checkboxDeleted;
		try {
			driver.findElement(By.name("todo[4]"));
			checkboxDeleted = false;
		} catch (NoSuchElementException e) {
			checkboxDeleted = true;
		}
		Assert.assertTrue("Not able to delete check box!", checkboxDeleted);
	}

	@Test
	public void test3() throws InterruptedException {
		removeAllcheckbox();
		Boolean checkboxExist = (driver.findElements(By.cssSelector("input[type='checkbox']")).size() > 0);
		Assert.assertTrue("Not able to delete all the check boxes!", checkboxExist);
	}

	@After
	public void tearDown() {
		driver.close();
		driver.quit();
	}
}

