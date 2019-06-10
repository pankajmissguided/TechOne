package com.sussex.testBase;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.google.common.base.Function;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.sussex.excelReaders.Excel_Reader;

import io.github.bonigarcia.wdm.WebDriverManager;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class TestBase {
	WebDriverWait wait;
	private final static Logger log = Logger.getLogger(TestBase.class);
	public Properties Repository = new Properties();
	public File f;
	public FileInputStream FI;
	public static WebDriver driver;
	public String startTime;
	public static int indexSI = 1;
	public static ExtentTest test;
	public static ExtentReports report;
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLUE = "\u001B[34m";

	public static final String ANSI_GREEN = "\u001B[32m";

	// public static String loginData = System.getProperty("user.dir") +
	// "//src//main//resources//resources//HotelData.xls";
	public static String testData = System.getProperty("user.dir")
			+ "//src//main//resources//Resources//TestData//TestData.xls";
	public static String hotelDestinations = System.getProperty("user.dir")
			+ "//src//main//resources//resources//HotelDestinations.xls";
	public static String flightsxls = System.getProperty("user.dir")
			+ "//src//main//resources//resources//FlightData.xls";
	public static String flightsxls1 = System.getProperty("user.dir")
			+ "//src//main//resources//resources//FlightAndHotelData.xls";
	public static String tc4xls = System.getProperty("user.dir") + "//src//main//resources//resources//TC4TestData.xls";
	public static String testdataxls = System.getProperty("user.dir")
			+ "//src//main//resources//resources//testData.xls";
	static {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		report = new ExtentReports(System.getProperty("user.dir") + "/src/test/java/com/alpharooms/testReport/test"
				+ formater.format(calendar.getTime()) + ".html", false);
	}

	public void scrollDown() {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		// Thread.sleep(2000);
		jse.executeScript("window.scrollBy(0,500)", "");
	}

	public void scrollUp() {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		// Thread.sleep(2000);
		jse.executeScript("window.scrollBy(0,500)", "");
	}

	public WebElement acceptUnknowAlerts() {

		WebElement ele = driver.findElement(By.xpath("//*[@id='g-kkpr14']/span"));
		return ele;
	}

	public WebDriverWait webdriverWait() {
		return wait = new WebDriverWait(driver, 60);

	}

	public void implicitywait(int timeout) {
		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
	}

	public void pageLoadTimeout(int timeout) {
		driver.manage().timeouts().pageLoadTimeout(timeout, TimeUnit.SECONDS);
	}

	public void setScriptTimeout(int timeout) {
		driver.manage().timeouts().setScriptTimeout(timeout, TimeUnit.SECONDS);
	}

	public void clickEscbtn() {
		try {
			Thread.sleep(1500);
			Actions action = new Actions(driver);
			action.sendKeys(Keys.ESCAPE).build().perform();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void webdriverWait(int timeout, String xpathkey) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathkey)));
	}

	/*
	 * public int getAirlines() { String airlines = driver.findElement(By.
	 * xpath("//div[@class='span3 show-count hidden-phone']/strong")) .getText();
	 * int value = Integer.parseInt(airlines); return value; }
	 */

	public void invisibilityOfElementLocated(int timeout, String xpathkey) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xpathkey)));
	}

	public void elementToBeClickable(int timeout, String xpathkey) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathkey)));
	}

	public void visibilityOfelement(int timeout, WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.visibilityOf(ele));
	}

	public void deletePageCookies() {
		driver.manage().deleteAllCookies();
		driver.navigate().refresh();
	}

	public void fluentWait(final String xpathkey) {
		// Waiting 30 seconds for an element to be present on the page, checking
		// for its presence once every 5 seconds.
		@SuppressWarnings("deprecation")
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(60, TimeUnit.SECONDS)
				.pollingEvery(5, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);

		WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.xpath(xpathkey));
			}
		});
	}

	public void init() throws IOException, InterruptedException {
		// String log4jConfPath = "log4j.properties";
		// PropertyConfigurator.configure(log4jConfPath);
		// loadPropertiesFile();
		selectBrowser("chrome");

		// Dimension d = new Dimension(1900, 1050);
		// driver.manage().window().setSize(d);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	}

	/**
	 * Navigate to a Specific url
	 * 
	 * @throws IOException
	 */
	public void navigatetoUrl(String url) {
		String name = driver.getCurrentUrl();
		if (name.endsWith("/")) {
			driver.navigate().to(name + url);
		} else {
			driver.navigate().to(name + "/" + url);
		}

	}

	public void loadPropertiesFile() throws IOException {
		f = new File(System.getProperty("user.dir") + "//src//test//java//com//sussex//configs//config.properties");
		FI = new FileInputStream(f);
		Repository.load(FI);
		System.out.println(Repository);
	}

	/*
	 * public Object[][] getData(String ExcelName, String testcase) { Excel_Reader
	 * Data = new Excel_Reader( System.getProperty("user.dir") +
	 * "//src//main//resources//resources//" + ExcelName); int rowNum =
	 * Data.getRowCount(testcase); System.out.println(rowNum); int colNum =
	 * Data.getColumnCount(testcase); Object sampleData[][] = new Object[rowNum -
	 * 1][colNum]; for (int i = 2; i <= rowNum; i++) { for (int j = 0; j < colNum;
	 * j++) { sampleData[i - 2][j] = Data.getCellData(testcase, j, i); } } return
	 * sampleData; }
	 */

	public void closeTab() {
		driver.findElement(By.tagName("body")).sendKeys(Keys.CONTROL + "w");
		driver.switchTo().defaultContent();
	}

	public void windowMaximize() {
		driver.manage().window().maximize();
	}

	public void selectBrowser(String browserName) throws IOException, InterruptedException {
		String log4jConfPath = "log4j.properties";
		PropertyConfigurator.configure(log4jConfPath);
		loadPropertiesFile();
		if (browserName.equalsIgnoreCase("firefox")) {

			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions options = new FirefoxOptions();
			String strFFBinaryPath = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
			options.setBinary(strFFBinaryPath);
			driver = new FirefoxDriver(options);

			// driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("chrome")) {

			String path = System.getProperty("user.dir");
			System.out.println(path);
			System.setProperty("webdriver.chrome.driver", path + "\\drivers\\chromedriver.exe");
			ChromeOptions options = new ChromeOptions();

			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			capabilities.setCapability("chrome.switches", Arrays.asList(
					"--ignore-certificate-errors,--web-security=false,--ssl-protocol=any,--ignore-ssl-errors=true"));
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			driver = new ChromeDriver(capabilities);

			/*
			 * DesiredCapabilities cap= new DesiredCapabilities();
			 * 
			 * cap.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
			 * cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			 * cap.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true); driver = new
			 * ChromeDriver(cap);
			 */
			// DesiredCapabilities ieCapabilities =new DesiredCapabilities();
			// ieCapabilities.setCapability("disable-popup-blocking", true);
			// ChromeOptions options = new ChromeOptions();
			// options.setExperimentalOption("excludeSwitches",
			// Arrays.asList("disable-popup-blocking"));
			//// ChromeOptions options = new ChromeOptions();
			// options.addArguments("-incognito");
			// options.addArguments("--disable-popup-blocking");
			// options.addArguments("disable-infobars");
			// options.addArguments("--disable-notifications");
			//// options.addArguments("--start-maximized");
			// options.addArguments("--disable-web-security");
			// options.addArguments("--no-proxy-server");
			// ChromeOptions options = new ChromeOptions();
			// options.addArguments("--start-maximized");
			// options.addArguments("--headless", "--disable-gpu",
			// "--window-size=1920,1200","--ignore-certificate-errors");
			driver = new ChromeDriver();

		} else if (browserName.equalsIgnoreCase("IE")) {

			// if
			// (Repository.getProperty("siteurl").contains("http://qawebsoa.eu-west-1.elasticbeanstalk.com"))
			// {
			String path = System.getProperty("user.dir");
			System.out.println(path);
			System.setProperty("webdriver.ie.driver", path + "\\drivers\\IEDriverServer.exe");
			DesiredCapabilities capabilities = new DesiredCapabilities();
			// capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
			// true);
			// capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			driver = new InternetExplorerDriver(capabilities);
		}
		driver.get("https://sussex-dev.t1cloud.com/T1Default/CiAnywhere/Web/SUSSEX-DEV/LogOn/$S1_STAFF?");
		Thread.sleep(1500);
	}

	private static void until(WebDriver driver, Function<WebDriver, Boolean> waitCondition, Long timeoutInSeconds) {
		WebDriverWait webDriverWait = new WebDriverWait(driver, timeoutInSeconds);
		webDriverWait.withTimeout(timeoutInSeconds, TimeUnit.SECONDS);
		try {
			webDriverWait.until(waitCondition);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void expliciteWait(WebElement element, int timeToWaitInSec) {
		WebDriverWait wait = new WebDriverWait(driver, timeToWaitInSec);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void waitforElementPresent(By element) {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(element)));
	}

	public void waitforElement(WebElement element, int timeOutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.ignoring(NoSuchElementException.class);
		wait.ignoring(ElementNotVisibleException.class);
		wait.ignoring(StaleElementReferenceException.class);
		// wait.ignoring(ElementNotFoundException.class);
		wait.pollingEvery(250, TimeUnit.MILLISECONDS);
		wait.until(elementLocated(element));
	}

	public Function<WebDriver, Boolean> elementLocated(final WebElement element) {
		return new Function<WebDriver, Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				log.debug("Waiting for element : " + element);
				return element.isDisplayed();
			}
		};
	}

	public void waitForPageToLoad(long timeOutInSeconds) {

		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {

			public Boolean apply(WebDriver driver) {
				try {
					Thread.sleep(10000L);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		try {
			// System.out.println("Waiting for page to load...");
			WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);

			wait.ignoring(NullPointerException.class).ignoring(WebDriverException.class)
					.ignoring(NullPointerException.class).ignoring(NoSuchWindowException.class);
			wait.until(expectation);
		} catch (Throwable error) {
			System.out.println(
					"Timeout waiting for Page Load Request to complete after " + timeOutInSeconds + " seconds");
			Assert.assertFalse(true, "Timeout waiting for Page Load Request to complete.");

		}
	}

	public void waitforElementPresent(String xpath) {
		new WebDriverWait(driver, 120).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
	}

	public String waitforTitlePresent(String title) throws InterruptedException {
		Thread.sleep(5000L);
		return title = driver.getTitle();

	}

	public void scrollBy(String length) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0," + length + ")", "");

	}

	public void scrollBy() {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,500)", "");
	}

	public WebElement waitforlinkText(String link) throws InterruptedException {
		Thread.sleep(5000);
		return driver.findElement(By.linkText(link));
	}
	// select the Number of Guests and Rooms

	public void clickonroomandguests() {

		WebElement element = driver.findElement(By.xpath("//button[@class='btn btn-default dropdown-toggle']"));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);

	}

	public void addRooms() {
		WebElement addRoom = driver
				.findElement(By.xpath(".//*[@id='seach-box-wrapper']/div[1]/div[10]/div[2]/div[1]/div[2]/div[1]/i"));
		addRoom.click();
	}

	public String[][] getExcelData(String xlPath, String shtName, String tbName, String tbName1) throws Exception {
		String[][] tabArray = null;
		Workbook workbk = Workbook.getWorkbook(new File(xlPath));
		Sheet sht = workbk.getSheet(shtName);
		int sRow, sCol, eRow, eCol, ci, cj;
		Cell tableStart = sht.findCell(tbName);
		sRow = tableStart.getRow();
		sCol = tableStart.getColumn();
		Cell tableEnd = sht.findCell(tbName1);
		eRow = tableEnd.getRow();
		eCol = tableEnd.getColumn();
		System.out.println("startRow=" + sRow + ", endRow=" + eRow + ", " + "startCol=" + sCol + ", endCol=" + eCol);
		tabArray = new String[eRow - sRow - 1][eCol - sCol - 1];
		ci = 0;
		for (int i = sRow + 1; i < eRow; i++, ci++) {
			cj = 0;
			/*
			 * System.out.println("Row"+i); System.out.println("Column"+sCol);
			 */
			for (int j = sCol + 1; j < eCol; j++, cj++) {
				/*
				 * System.out.println("Row1"+i); System.out.println("Column1"+j);
				 */
				tabArray[ci][cj] = sht.getCell(j, i).getContents();
			}
		}
		return (tabArray);
	}

	// @SuppressWarnings("null")
	// public void embedScreenshotOnFail() {
	// Scenario scenario = null;
	// if (scenario.isFailed()) {
	// final byte[] screenshot = ((TakesScreenshot)
	// driver).getScreenshotAs(OutputType.BYTES);
	// scenario.embed(screenshot,
	// "C:/Users/Public/Downloads/TestScreenshot/Error.jpg"); // stick it in the
	// report
	// }
	// driver.close();
	// }
	//
	// public void selectChildren(String numofChild) {
	// WebElement element = driver.findElement(By.xpath(
	// ".//*[@id='seach-box-wrapper']/div[1]/div[10]/div[2]/div[1]/div[1]/div/div/div/div/div[3]/div/div[2]/select"));
	// List<WebElement> elements = element.findElements(By.tagName("option"));
	// for (WebElement options : elements) {
	// if (options.getText().trim().equals(numofChild)) {
	// options.click();
	// }
	// }
	//
	// }
	//
	// public void getAllChildDivs() {
	//
	// }
	//
	// /*
	// * public void selectSingleChildAge(String childCount, String childAge){ for
	// * (int j = 1; j < 4; j++) {
	// *
	// * List<WebElement> childBox = driver.findElements(By.xpath(
	// *
	// ".//*[@id='seach-box-wrapper']/div[1]/div[10]/div[2]/div[1]/div[1]/div/div/div/div/div[4]/div/div[2]/div/div["
	// * +j+"]/select")); int sizeOfDivs = childBox.size(); String conver =
	// * String.valueOf(sizeOfDivs); //int conBox = Integer.parseInt(sizeOfDivs); if
	// * (conver ==childCount) {
	// *
	// * } List<WebElement> selectChildAge =
	// * childBox.findElements(By.tagName("option")); for(WebElement options :
	// * selectChildAge){ if (options.getText().trim().equals(childAge)) {
	// * options.click(); } }
	// */
	// // }
	// //
	// *[@id='seach-box-wrapper']/div[1]/div[10]/div[2]/div[1]/div[1]/div/div/div/div/div[4]/div/div[2]/div/div
	// public void getAllChildrenSection(String number) {
	// WebElement getAllChildCount = driver.findElement(By.xpath(
	// ".//*[@id='seach-box-wrapper']/div[1]/div[10]/div[2]/div[1]/div[1]/div/div/div[2]/div/div[4]/div/div[2]/div"));
	//
	// for (int i = 0; i < 4; i++) {
	// List<WebElement> getChildCount = getAllChildCount.findElements(By.xpath(
	// ".//*[@id='seach-box-wrapper']/div[1]/div[10]/div[2]/div[1]/div[1]/div/div/div[2]/div/div[4]/div/div[2]/div/div["
	// + i + "]"));
	// for (WebElement options : getChildCount) {
	// if (options.getText().trim().equals(number)) {
	// String ages = options.getText();
	// int actualAge = Integer.parseInt(ages);
	// int parseNumber = Integer.parseInt(number);
	// if (parseNumber > 0 && parseNumber < 2) {
	// getChildCount.get(0).click();
	// } else if (parseNumber > 1 && parseNumber < 3) {
	//
	// }
	// }
	// }
	// }
	// }

	public void getAllDestinations(String destination) throws InterruptedException {
		// webdriverWait().until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@id='seach-box-wrapper']/div[1]/div[8]")));
		WebElement popularDest = driver.findElement(By.xpath(".//*[@id='seach-box-wrapper']/div[1]/div[8]"));
		List<WebElement> allDestinations = popularDest.findElements(By.tagName("a"));
		for (WebElement getDestination : allDestinations) {
			if (getDestination.getText().trim().contains(destination)) {
				Thread.sleep(500L);
				getDestination.click();
			}
		}
	}

	public void selectAdultS(String number) throws InterruptedException {
		// webdriverWait().until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='seach-box-wrapper']/div[1]/div[10]/div[2]/div[1]/div[1]/div/div/div/div/div[3]/div/div[1]/select")));
		WebElement element = driver.findElement(By.xpath(
				"//*[@id='seach-box-wrapper']/div[1]/div[10]/div[2]/div[1]/div[1]/div/div/div/div/div[3]/div/div[1]/select"));
		List<WebElement> elements = element.findElements(By.tagName("option"));
		for (WebElement options : elements) {
			if (options.getText().trim().contains(number)) {
				options.click();

			}
		}
		Thread.sleep(500);
		driver.findElement(By.xpath("//*[@class='pos-absolute done-reset hidden-phone']")).click();

	}

	// Get Number of Nights
	public void setNumberOfNights(String nights) throws InterruptedException {
		Thread.sleep(1000);
		WebElement element = driver.findElement(By.xpath("//*[@id='mbl-dropdownMenu']/button"));// btn

		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);

		List<WebElement> movetoDropDown = driver.findElements(By.xpath(".//*[@id='mbl-dropdownMenu']/div/ul/li/a"));

		for (WebElement options : movetoDropDown) {

			if (options.getText().trim().contentEquals(nights)) {
				options.click();
			}
		}
	}

	public void getDepartureAirport(String departureAirport) throws InterruptedException {
		WebElement div = driver.findElement(By.xpath(".//*[@id='departureAirport']/div"));
		Actions act = new Actions(driver);
		Thread.sleep(100);
		act.moveToElement(div).build().perform();
		List<WebElement> ele = driver.findElements(By.xpath(".//*[@id='departureAirport']/div/ul/li/a/span"));
		for (WebElement options : ele) {
			Thread.sleep(100);
			if (options.getText().trim().equals(departureAirport)) {
				options.click();
				break;
			}
		}
	}

	public void findBrokenLinks(String linkUrl) {
		try {
			List<WebElement> links = driver.findElements(By.tagName("a"));

			System.out.println("Total links are " + links.size());

			for (int i = 0; i < links.size(); i++) {

				WebElement ele = links.get(i);

				String url = ele.getAttribute("href");
			}
			URL url = new URL(linkUrl);

			HttpURLConnection httpURLConnect = (HttpURLConnection) url.openConnection();

			httpURLConnect.setConnectTimeout(3000);

			httpURLConnect.connect();

			if (httpURLConnect.getResponseCode() == 200) {
				System.out.println(linkUrl + " - " + httpURLConnect.getResponseMessage() + " - Code :"
						+ httpURLConnect.getResponseCode());
			}
			if (httpURLConnect.getResponseCode() == HttpURLConnection.HTTP_NOT_FOUND) {
				System.out.println(linkUrl + " - " + httpURLConnect.getResponseMessage() + " - "
						+ HttpURLConnection.HTTP_NOT_FOUND);
			}
		} catch (Exception e) {

		}

	}

	public WebElement modelPopup() {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.id("channelModal")));
		return element;
		// return driver.findElement(By.id("channelModal"));
	}

	public Throwable getMostPopularTwelveHotelDestinations() {
		for (int i = 1; i <= 12; i++) {

			List<WebElement> ele = driver
					.findElements(By.xpath(".//*[@id='homepage']/div[11]/div[2]/ul/li[" + i + "]"));
			// options=null;
			// String names="";
			for (WebElement options : ele)
				System.out.println(options.getText());
			// System.out.println(.//*[@id='homepage']/div[11]/div[2]);
		}
		// return true;
		return null;
	}

	public void setChannelPopUp(String channelLink) throws InterruptedException {
		String parent_win = driver.getWindowHandle();
		for (String child_win : driver.getWindowHandles()) {
			driver.switchTo().window(child_win);
			Thread.sleep(1000);

			WebElement channellink = driver.findElement(By.linkText(channelLink));

			channellink.click();
		}
		driver.switchTo().window(parent_win);

	}

	public WebElement selectModelPopUp(String popup) throws InterruptedException {
		String parent_win = driver.getWindowHandle();
		for (String child_win : driver.getWindowHandles()) {
			driver.switchTo().window(child_win);
			Thread.sleep(1000);

			WebElement channellink = driver.findElement(By.xpath(popup));

			channellink.click();
		}
		driver.switchTo().window(parent_win);
		return null;

	}

	public void clickWhenReady(By locator, int timeout) {
		WebElement element = null;
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		element.click();

	}

	public WebElement fluentWait(final WebElement webElement, int timeinsec) {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(timeinsec, TimeUnit.SECONDS)
				.pollingEvery(5, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
		WebElement element = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return webElement;
			}
		});

		return element;
	};

	public WebElement getWhenVisible(By locator, int timeout) {
		WebElement element = null;
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return element;

	}

	public void waitFor(int sec) throws InterruptedException {
		Thread.sleep(sec * 1000);
	}

	public void selectTab(String tabName) throws InterruptedException {
		Thread.sleep(1500);
		List<WebElement> tabs = driver.findElements(By.xpath(".//*[@id='searchform']/div[2]/span/label/span"));
		// List<WebElement> tabs =
		// form.findElements(By.xpath(".//*[@class='search-box-tabs
		// incFlightOnly']/span/label/span"));
		for (WebElement options : tabs) {
			if (options.getText().trim().contains(tabName)) {
				Thread.sleep(500);
				options.click();
				break;
			}
		}
	}

	public void selectTabs(String tabName) {
		List<WebElement> tabs = driver.findElements(By.xpath("//*[@id='searchFormForm']/div[1]/span/label"));
		// List<WebElement> tabs =
		// form.findElements(By.xpath(".//*[@class='search-box-tabs
		// incFlightOnly']/span/label/span"));
		for (WebElement options : tabs) {
			System.out.println(options.getText());
			if (options.getText().trim().equalsIgnoreCase(tabName)) {
				try {
					Thread.sleep(500);
					options.click();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				break;
			}
		}
	}

	public void dynamicTextwithRegularExp() {

		String sourcestring = driver.getPageSource();
		Pattern re = Pattern.compile("([^$]*?)(-?\\$\\d*\\.\\d{2})",
				Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
		Matcher m = re.matcher(sourcestring);
		int mIdx = 0;
		while (m.find()) {
			for (int groupIdx = 0; groupIdx < m.groupCount() + 1; groupIdx++) {
				System.out.println("[" + mIdx + "][" + groupIdx + "] = " + m.group(groupIdx));
			}
			mIdx++;
		}

	}

	public void getScreenShot(String fileName) throws IOException {
		File outputFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(outputFile, new File(System.getProperty("user.dir")
				+ "//src//test//java//com//alpharooms//screenshots//" + fileName + ".jpg"));
	}

	public void getSystemDateFormat(String expDate) throws ParseException {
		DateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat formatter = new SimpleDateFormat("MMM dd, yyyy");
		Date convertedDate = parser.parse(expDate);
		String output = formatter.format(convertedDate);
		System.out.println(output + "Expected Date Format");
	}

	public static String now(String format) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat fm = new SimpleDateFormat();
		return fm.format(cal.getTime());
	}

	public String getCurrentURL() {
		String currentURL = driver.getCurrentUrl();
		System.out.println(currentURL);
		return currentURL;

	}

	public String getSearchPageURL() {
		String SearchoptionsURL = driver.getCurrentUrl();
		System.out.println(SearchoptionsURL);
		return SearchoptionsURL;

	}

	public String getSearchPageDestURL(String SearchOptionsDestURL) {
		SearchOptionsDestURL = driver.getCurrentUrl();
		return SearchOptionsDestURL;

	}

	public String getEstablishmentsearchURL() {
		String EstablishmentURL = driver.getCurrentUrl();
		return EstablishmentURL;

	}

	public String getMultiEstablishmentsearchURL() {
		String multiEstablishmentURL = driver.getCurrentUrl();
		return multiEstablishmentURL;

	}

	public String getsearchResultsURL() {
		String EstabResultsURL = driver.getCurrentUrl();
		System.out.println(EstabResultsURL);
		return EstabResultsURL;

	}

	public String getMultisearchResultsURL() {
		String MultiEstabResultsURL = driver.getCurrentUrl();
		System.out.println(MultiEstabResultsURL);
		return MultiEstabResultsURL;

	}

	public String getDestinationURL() {
		String DestinationURL = driver.getCurrentUrl();
		System.out.println(DestinationURL);
		return DestinationURL;

	}

	public String getsearchDestiResultsURL() {
		String DestsearchResultsURL = driver.getCurrentUrl();
		System.out.println(DestsearchResultsURL);
		return DestsearchResultsURL;

	}

	// Channell Settings
	/*
	 * public void SwitchToChannel(String channelName, String expurl) throws
	 * InterruptedException {
	 * 
	 * WebElement menu = driver.findElement(By
	 * .xpath(".//*[@id='channel-switcher']/ul/li[1]/div/a/span[1]/span[2]"));
	 * switch (channelName) { case "United Kingdom": menu.click(); for (int i = 1; i
	 * <= 3; i++) { WebElement menuitem = driver.findElement(By.xpath(
	 * ".//*[@id='channel-switcher']/ul/li[1]/div/ul/li["+i+"]"));
	 * 
	 * List<WebElement> chann = menuitem.findElements(By.tagName("a"));
	 * 
	 * for (WebElement options : chann) { Thread.sleep(1000); if
	 * (options.getText().equals(channelName)) {
	 * System.out.println(options.getText()); // options.click();
	 * Thread.sleep(200L); expurl = driver.getCurrentUrl(); break; } } }
	 * 
	 * break; case "Ireland": menu.click(); for (int i =1; i <= 3; i++) { WebElement
	 * menuitem = driver.findElement(By.xpath(
	 * ".//*[@id='channel-switcher']/ul/li[1]/div/ul/li["+i+"]"));
	 * 
	 * List<WebElement> chann = menuitem.findElements(By.tagName("a"));
	 * 
	 * for (WebElement options : chann) { Thread.sleep(1000); if
	 * (options.getText().equals(channelName)) {
	 * 
	 * options.click(); Thread.sleep(200L); expurl = driver.getCurrentUrl(); break;
	 * } } } break; case "United States": menu.click(); for (int i = 1; i <= 3; i++)
	 * { WebElement menuitem = driver.findElement(By.xpath(
	 * ".//*[@id='channel-switcher']/ul/li[1]/div/ul/li["+i+"]"));
	 * 
	 * List<WebElement> chann = menuitem.findElements(By.tagName("a"));
	 * 
	 * for (WebElement options : chann) { if (options.getText().equals(channelName))
	 * { options.click(); Thread.sleep(200L); expurl = driver.getCurrentUrl();
	 * break;
	 * 
	 * } } } break; default: System.out.println("No Channel Found"); break; } }
	 */
	public void setPinCode(int key, String value) {
		List<WebElement> ele = driver.findElements(By.xpath(".//*[@id='postcode-lookup']"));
		ele.get(key).sendKeys(value);
	}

	public void findAddressbutton(int key) {
		List<WebElement> ele = driver.findElements(By.id("find-address-btn"));
		ele.get(key).click();
	}

	public void departureDate(String date) throws InterruptedException {
		WebElement selectDate = driver.findElement(By.id("fromDate"));
		selectDate.click();
		WebElement nextLink = driver.findElement(
				By.xpath("//*[@class='datepicker datepicker-dropdown dropdown-menu']/div[2]/table/thead/tr/th[3]"));
		// button to click in center of calendar header
		WebElement midLink = driver.findElement(
				By.xpath("//*[@class='datepicker datepicker-dropdown dropdown-menu']/div[1]/table/thead/tr[1]/th[2]"));
		// button to move previous month in calendar
		WebElement previousLink = driver.findElement(By
				.xpath("//*[@class='datepicker datepicker-dropdown dropdown-menu']/div[1]/table/thead/tr[1]/th[1]/i"));
		// Split the date time to get only the date part
		String date_dd_MM_yyyy[] = (date.split("/"));
		// get the year difference between current year and year to set in
		// calander
		int yearDiff = Integer.parseInt(date_dd_MM_yyyy[2]) - Calendar.getInstance().get(Calendar.YEAR);
		midLink.click();
		if (yearDiff != 0) {
			// if you have to move next year
			if (yearDiff > 0) {
				for (int i = 0; i < yearDiff; i++) {
					System.out.println("Year Diff->" + i);
					nextLink.click();
				}
			}
			// if you have to move previous year
			else if (yearDiff < 0) {
				for (int i = 0; i < (yearDiff * (-1)); i++) {
					System.out.println("Year Diff->" + i);
					previousLink.click();
				}
			}
		}
		Thread.sleep(1000);
		// Get all months from calendar to select correct one
		List<WebElement> list_AllMonthToBook = driver
				.findElements(By.xpath("//*[@class='datepicker-months']/table/tbody/tr/td/span"));
		list_AllMonthToBook.get(Integer.parseInt(date_dd_MM_yyyy[1])).click();
		Thread.sleep(1000);
		// get all dates from calendar to select correct one
		List<WebElement> list_AllDateToBook = driver.findElements(
				By.xpath("//*[@class='datepicker-days']/table/tbody/tr/td[not(contains(@class,'day old disabled'))]"));
		list_AllDateToBook.get(Integer.parseInt(date_dd_MM_yyyy[0])).click();
	}

	public static void updateResult(int indexSI, String testCaseName, String testCaseStatus, String scriptName)
			throws IOException {
		String startDateTime = new SimpleDateFormat("MM-dd-yyyy_HH-ss").format(new GregorianCalendar().getTime());

		String userDirector = System.getProperty("user.dir");

		String resultFile = userDirector + "//src//test//java//com//alpharooms//report//TestHtmlReport.html";

		File file = new File(resultFile);
		System.out.println(file.exists());

		if (!file.exists()) {
			FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("<html>" + "\n");
			bw.write("<head><title>" + "Test execution report" + "</title>" + "\n");
			bw.write("</head>" + "\n");
			bw.write("<body>");
			bw.write("<font face='Tahoma'size='2'>" + "\n");
			bw.write("<u><h1 align='center'>" + "Test execution report" + "</h1></u>" + "\n");
			bw.flush();
			bw.close();
		}
		BufferedWriter bw1 = new BufferedWriter(new FileWriter(file, true));
		if (indexSI == 1) {
			bw1.write("<table align='center' border='0' width='70%' height='10'>");
			bw1.write("<tr><td width='70%' </td></tr>");
			bw1.write("<table align='center' border='1' width='70%' height='47'>");
			bw1.write("<tr>");
			bw1.write(
					"<td colspan='2' align='center'><b><font color='#000000' face='Tahoma' size='2'>ScriptName :&nbsp;&nbsp;&nbsp;</font><font color='#0000FF'' face='Tahoma' size='2'>"
							+ scriptName + " </font></b></td>");
			bw1.write(
					"<td colspan='1' align='left'><b><font color='#000000' face='Tahoma' size='1'>Start Time :&nbsp;</font></b><font color='#0000FF'' face='Tahoma' size='1'>"
							+ startDateTime + " </font></td>");
			bw1.write("</tr>");
			bw1.write("</tr>");
			bw1.write(
					"<td  bgcolor='#CCCCFF' align='center'><b><font color='#000000' face='Tahoma' size='2'>S.No</font></b></td>");
			bw1.write(
					"<td  bgcolor='#CCCCFF' align='left'><b><font color='#000000' face='Tahoma' size='2'>Test case ID : Test case Description </font></b></td>");

			bw1.write(
					"<td  bgcolor='#CCCCFF' align='center'><b><font color='#000000' face='Tahoma' size='2'>Result </font></b></td>");
			bw1.write("</tr>");
		}
		bw1.write("<tr>" + "\n");
		bw1.write("<td bgcolor='#FFFFDC'align='Center'><font color='#000000' face='Tahoma' size='2'>" + indexSI
				+ "</font></td>" + "\n");
		bw1.write("<td  bgcolor='#FFFFDC' valign='middle' align='left'><b><font color='#000000' face='Tahoma' size='2'>"
				+ testCaseName + "</font></b></td>" + "\n");
		if (testCaseStatus == "Pass") {
			bw1.write(
					"<td  bgcolor='#FFFFDC' valign='middle' align='center'><b><font color='Green' face='Tahoma' size='2'>"
							+ testCaseStatus + "</font></b></td>" + "\n");
		} else {
			bw1.write(
					"<td  bgcolor='#FFFFDC' valign='middle' align='center'><b><font color='red' face='Tahoma' size='2'>"
							+ testCaseStatus + "</font></b></td>" + "\n");
		}
		bw1.write("</tr>" + "\n");
		bw1.write("</body>" + "\n");
		bw1.write("</html>");
		bw1.flush();
		bw1.close();

	}

	public void getresult(ITestResult result) {
		if (result.getStatus() == ITestResult.SUCCESS) {
		} else if (result.getStatus() == ITestResult.SKIP) {
		} else if (result.getStatus() == ITestResult.FAILURE) {
			String screen = captureScreen("");
		} else if (result.getStatus() == ITestResult.STARTED) {
		}
	}

	@AfterMethod()
	public void afterMethod(ITestResult result) {
		getresult(result);
	}

	@BeforeMethod()
	public void beforeMethod(Method result) {
		test = report.startTest(result.getName());
	}

	/*
	 * @AfterClass(alwaysRun = true) public void endTest() { closeBrowser(); }
	 */

	public void closeBrowser() {
		report.endTest(test);
		report.flush();
	}

	public String captureScreen(String fileName) {
		if (fileName == "") {
			fileName = "blank";
		}
		File destFile = null;
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		try {
			String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath()
					+ "/src/main/java/com/test/automation/uiAutomation/screenshot/";
			destFile = new File(
					(String) reportDirectory + fileName + "_" + formater.format(calendar.getTime()) + ".png");
			FileUtils.copyFile(scrFile, destFile);
			// This will help us to link the screen shot in testNG report
			Reporter.log("<a href='" + destFile.getAbsolutePath() + "'> <img src='" + destFile.getAbsolutePath()
					+ "' height='100' width='100'/> </a>");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return destFile.toString();
	}

	// get All hotels Count

	public String getAllHotelCount() {
		// WebElement options =nul;
		try {
			WebElement ele = driver.findElement(By.xpath("//*[@class='hotel-name']"));
			List<WebElement> hotels = ele.findElements(By.tagName("a"));
			int size = hotels.size();
			System.out.println("Hotel Count - " + size);
			for (WebElement options : hotels) {
				System.out.println(options.getText());
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;

		// return startTime;

	}

	// Swtiching between tabs
	public void switchToNewTab() {

		ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(1));

	}

	// Click on Hotel
	public void ClickHotelName(String hotelName) {

		List<WebElement> hotels = driver.findElements(By.linkText(hotelName));
		int size = hotels.size();
		System.out.println("Hotel Count - " + size);
		for (WebElement options : hotels) {
			if (options.getText().trim().equals(hotelName)) {
				options.click();
			}

		}
	}

	public void findBrokenLinks_AToZSubDestinations() throws InterruptedException {
		// TODO Auto-generated method stub

		// String homePage =
		// "https://stagingaccomsoaweb.alpharooms.com/webpages/guides/destinationbrowser.aspx";
		String url = "";
		HttpURLConnection huc = null;
		int respCode = 200;
		List<WebElement> links = driver.findElements(By.xpath(".//*[@id='countrydestinations']/div/div/div/div/a"));
		Iterator<WebElement> it = links.iterator();
		while (it.hasNext()) {
			url = it.next().getAttribute("href");
			if (url == null || url.isEmpty()) {
				System.out.println("URL is either not configured for anchor tag or it is empty");
				continue;
			}
			try {
				huc = (HttpURLConnection) (new URL(url).openConnection());
				huc.setRequestMethod("HEAD");
				huc.connect();
				respCode = huc.getResponseCode();

				if (respCode == 400) {
				} else if (respCode == 401) {
				} else if (respCode == 500) {
				} else if (respCode == 301) {
				} else {
					Thread.sleep(500);
					System.out.println("Valid - " + url);
				}
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void findBrokenLinks_AToZDestinations() {
		// String homePage =
		// "https://stagingaccomsoaweb.alpharooms.com/webpages/guides/destinationbrowser.aspx";
		String url = "";
		HttpURLConnection huc = null;
		int respCode = 200;
		List<WebElement> links = driver.findElements(By.xpath(".//*[@id='destination-listing']/div/div/a"));
		Iterator<WebElement> it = links.iterator();

		while (it.hasNext()) {
			url = it.next().getAttribute("href");
			if (url == null || url.isEmpty()) {
				System.out.println("URL is either not configured for anchor tag or it is empty");
				continue;
			}
			try {
				huc = (HttpURLConnection) (new URL(url).openConnection());

				huc.setRequestMethod("HEAD");

				huc.connect();

				respCode = huc.getResponseCode();

				if (respCode == 400) {
				} else if (respCode == 401) {
				} else if (respCode == 500) {
				} else if (respCode == 301) {
				} else {
					System.out.println("Valid - " + url);
				}
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public Currency getUKCurrency() {
		Locale UK = Locale.UK;
		Currency currency = Currency.getInstance(UK);
		System.out.println(currency.getSymbol(UK));
		return currency;
	}

	public Currency getUSACurrency() {
		Locale USA = Locale.US;
		Currency currency = Currency.getInstance(USA);
		System.out.println(currency.getSymbol(USA));
		return currency;
	}

	public Currency getIECurrency() {
		Locale IT = Locale.ITALY;
		Currency currency = Currency.getInstance(IT);
		System.out.println(currency.getSymbol(IT));
		return currency;
	}

	/**
	 * Alerts Helpers
	 */
	public Alert getAlert() {
		log.debug("");
		return driver.switchTo().alert();
	}

	public void AcceptAlert() {
		log.info("");
		getAlert().accept();
	}

	public void DismissAlert() {
		log.info("");
		getAlert().dismiss();
	}

	public String getAlertText() {
		String text = getAlert().getText();
		log.info(text);
		return text;
	}

	public boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			log.info("true");
			return true;
		} catch (NoAlertPresentException e) {
			// Ignore
			log.info("false");
			return false;
		}
	}

	public void AcceptAlertIfPresent() {
		if (!isAlertPresent())
			return;
		AcceptAlert();
		log.info("");
	}

	public void DismissAlertIfPresent() {

		if (!isAlertPresent())
			return;
		DismissAlert();
		log.info("");
	}

	public void AcceptPrompt(String text) {

		if (!isAlertPresent())
			return;

		Alert alert = getAlert();
		alert.sendKeys(text);
		alert.accept();
		log.info(text);
	}

	/**
	 * Java Script Methods
	 */
	public Object executeScript(String script) {
		JavascriptExecutor exe = (JavascriptExecutor) driver;
		log.info(script);
		return exe.executeScript(script);
	}

	public Object executeScript(String script, Object... args) {
		JavascriptExecutor exe = (JavascriptExecutor) driver;
		log.info(script);
		return exe.executeScript(script, args);
	}

	public void scrollToElemet(WebElement element) {
		executeScript("window.scrollTo(arguments[0],arguments[1])", element.getLocation().x, element.getLocation().y);
		log.info(element);
	}

	public void scrollToElemetAndClick(WebElement element) {
		scrollToElemet(element);
		element.click();
		log.info(element);
	}

	public void scrollIntoView(WebElement element) {
		executeScript("arguments[0].scrollIntoView()", element);
		log.info(element);
	}

	public void scrollIntoViewAndClick(WebElement element) {
		scrollIntoView(element);
		element.click();
		log.info(element);
	}

	public void scrollDownVertically() {
		executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	public void scrollUpVertically() {
		executeScript("window.scrollTo(0, -document.body.scrollHeight)");
	}

	public void scrollDownByPixel() {
		executeScript("window.scrollBy(0,1500)");
	}

	public void scrollUpByPixel() {
		executeScript("window.scrollBy(0,-1500)");
	}

	public void ZoomInBypercentage() {
		executeScript("document.body.style.zoom='40%'");
	}

	public void ZoomBy100percentage() {
		executeScript("document.body.style.zoom='100%'");
	}

	/**
	 * Wait for Elements
	 */
	public void waitForElement(WebElement element, int timeOutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.ignoring(NoSuchElementException.class);
		wait.ignoring(ElementNotVisibleException.class);
		wait.ignoring(StaleElementReferenceException.class);
		// wait.ignoring(ElementNotFoundException.class);
		wait.pollingEvery(250, TimeUnit.MILLISECONDS);
		wait.until(elementLocates(element));
	}

	private Function<WebDriver, Boolean> elementLocates(final WebElement element) {
		return new Function<WebDriver, Boolean>() {

			public Boolean apply(WebDriver driver) {
				log.debug("Waiting for Element : " + element);
				return element.isDisplayed();
			}
		};
	}

	/**
	 * Browser Helpers
	 */
	public void goBack() {
		driver.navigate().back();
		log.info("");
	}

	public void goForward() {
		driver.navigate().forward();
		log.info("");
	}

	public void refresh() {
		driver.navigate().refresh();
		log.info("");
	}

	public Set<String> getWindowHandlens() {
		log.info("");
		return driver.getWindowHandles();
	}

	public void SwitchToWindow(int index) {

		LinkedList<String> windowsId = new LinkedList<String>(getWindowHandlens());

		if (index < 0 || index > windowsId.size())
			throw new IllegalArgumentException("Invalid Index : " + index);

		driver.switchTo().window(windowsId.get(index));
		log.info(index);
	}

	public void switchToParentWindow() {
		LinkedList<String> windowsId = new LinkedList<String>(getWindowHandlens());
		driver.switchTo().window(windowsId.get(0));
		log.info("");
	}

	public void switchToParentWithChildClose() {
		switchToParentWindow();

		LinkedList<String> windowsId = new LinkedList<String>(getWindowHandlens());

		for (int i = 1; i < windowsId.size(); i++) {
			log.info(windowsId.get(i));
			driver.switchTo().window(windowsId.get(i));
			driver.close();
		}

		switchToParentWindow();
	}

	public WebElement getElementWithNull(By locator) {
		log.info(locator);
		try {
			return driver.findElement(locator);
		} catch (NoSuchElementException e) {
			// Ignore
		}
		return null;
	}

	public boolean IsElementPresentQuick(By locator) {
		boolean flag = driver.findElements(locator).size() >= 1;
		log.info(flag);
		return flag;
	}

	public WebElement getElement(By locator) {
		log.info(locator);
		if (IsElementPresentQuick(locator))
			return driver.findElement(locator);

		try {
			throw new NoSuchElementException("Element Not Found : " + locator);
		} catch (RuntimeException re) {
			log.error(re);
			throw re;
		}
	}

	public void switchToFrame(By locator) {
		driver.switchTo().frame(new TestBase().getElement(locator));
		log.info(locator);
	}

	public void switchToFrame(String nameOrId) {
		driver.switchTo().frame(nameOrId);
		log.info(nameOrId);
	}

	/**
	 * get Drop Down helper
	 */

	public void SelectUsingVisibleValue(WebElement element, String visibleValue) {
		Select select = new Select(element);
		select.selectByVisibleText(visibleValue);
		log.info("Locator : " + element + " Value : " + visibleValue);
	}

	public String getSelectedValue(WebElement element) {
		String value = new Select(element).getFirstSelectedOption().getText();
		log.info("WebELement : " + element + " Value : " + value);
		return value;
	}

	public void SelectUsingIndex(WebElement element, int index) {
		Select select = new Select(element);
		select.selectByIndex(index);
		log.info("Locator : " + element + " Value : " + index);
	}

	public List<String> getAllDropDownValues(WebElement locator) {
		Select select = new Select(locator);
		List<WebElement> elementList = select.getOptions();
		List<String> valueList = new LinkedList<String>();

		for (WebElement element : elementList) {
			log.info(element.getText());
			valueList.add(element.getText());
		}
		return valueList;
	}

	/**
	 * Assertion Helpers
	 */
	public static synchronized boolean verifyElementPresent(WebElement element) {
		boolean isDispalyed = false;
		try {
			isDispalyed = element.isDisplayed();
			log.info(element.getText() + " is dispalyed");
		} catch (Exception ex) {
			log.error("Element not found " + ex);
		}

		return isDispalyed;
	}

	public static synchronized boolean verifyElementNotPresent(WebElement element) {
		boolean isDispalyed = false;
		try {
			element.isDisplayed();
			log.info(element.getText() + " is dispalyed");
		} catch (Exception ex) {
			log.error("Element not found " + ex);
			isDispalyed = true;
		}

		return isDispalyed;
	}

	public static synchronized boolean verifyTextEquals(WebElement element, String expectedText) {
		boolean flag = false;
		try {
			String actualText = element.getText();
			if (actualText.equals(expectedText)) {
				log.info("actualText is :" + actualText + " expected text is: " + expectedText);
				return flag = true;
			} else {
				log.error("actualText is :" + actualText + " expected text is: " + expectedText);
				return flag;
			}
		} catch (Exception ex) {
			log.error("actualText is :" + element.getText() + " expected text is: " + expectedText);
			log.info("text not matching" + ex);
			return flag;
		}
	}

	/**
	 * Logger helper
	 */
	private static boolean root = false;

	public static Logger getLogger(Class clas) {
		if (root) {
			return Logger.getLogger(clas);
		}
		PropertyConfigurator.configure(System.getProperty("user.dir") + "//log4j.properties");
		root = true;
		return Logger.getLogger(clas);
	}

	/**
	 * Wait Helpers
	 */
	public void setImplicitWait(long timeout, TimeUnit unit) {
		log.info(timeout);
		driver.manage().timeouts().implicitlyWait(timeout, unit == null ? TimeUnit.SECONDS : unit);
	}

	private WebDriverWait getWait(int timeOutInSeconds, int pollingEveryInMiliSec) {
		log.debug("");
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.pollingEvery(pollingEveryInMiliSec, TimeUnit.MILLISECONDS);
		wait.ignoring(NoSuchElementException.class);
		wait.ignoring(AssertionError.class);
		wait.ignoring(ElementNotVisibleException.class);
		wait.ignoring(StaleElementReferenceException.class);
		wait.ignoring(NoSuchFrameException.class);
		return wait;
	}

	public void waitForElementVisible(WebElement locator, int timeOutInSeconds, int pollingEveryInMiliSec) {
		log.info(locator);
		WebDriverWait wait = getWait(timeOutInSeconds, pollingEveryInMiliSec);
		wait.until(ExpectedConditions.visibilityOf(locator));
	}

	public void waitForElementVisibleOf(WebDriver driver, WebElement element, long timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.visibilityOf(element));
		log.info("element found..." + element.getText());
	}

	public WebElement waitForElementToBeClickable(WebDriver driver, long time, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		return wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	/**
	 * Cookies
	 */
	public Cookie getCookieNamed(String name) {
		return driver.manage().getCookieNamed(name);
	}

	public String getValueOfCookieNamed(String name) {
		return driver.manage().getCookieNamed(name).getValue();
	}

	public void addCookie(String name, String value) {
		driver.manage().addCookie(new Cookie(name, value));
	}
	// public void addCookiesToBrowser(Set<Cookie> cookies, String domain) {
	// for (Cookie c : cookies) {
	// if (c != null) {
	// if (c.getDomain().contains(domain)){
	// driver.manage().addCookie(
	// new Cookie(name, value, domain, path, expiry));
	// }
	// }
	// }
	// driver.navigate().refresh();
	// }

	public void deleteCookieNamed(String name) {
		driver.manage().deleteCookieNamed(name);
	}

	public void deleteAllCookies() {
		driver.manage().deleteAllCookies();
	}

}
