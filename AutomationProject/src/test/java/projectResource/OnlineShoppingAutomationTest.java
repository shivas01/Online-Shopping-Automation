package projectResource;


	import java.io.File;
	import java.io.IOException;

	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.WebDriverWait;

	import java.time.Duration;
	import java.util.ArrayList;
	import java.util.List;
	import java.util.Scanner;
	import java.util.Set;

	import org.apache.commons.io.FileUtils;
	import org.openqa.selenium.By;
	import org.openqa.selenium.OutputType;
	import org.openqa.selenium.TakesScreenshot;

	public class OnlineShoppingAutomationTest extends Validation {
		public static String productPrice1;
		public static String productPrice2;
		public static String cartSubTotal;
		Scanner sc=new Scanner(System.in);
		
		//based on user input browser will be choosen
		public void browserChoice() throws IOException {
			System.out.println("Press 1 for Chrome ");
			System.out.println("press 2 for Edge");
			System.out.println("press 3 for Firefox");

			System.out.print("Enter your choice :");
			int choice =sc.nextInt();
			switch(choice) {
			case 1:DriverSetup.getWebDriver("chrome");
			break;
			case 2:DriverSetup.getWebDriver("edge");
			break;
			case 3:DriverSetup.getWebDriver("firefox");
			break;
			default:System.out.println("Enter correct choice");
			}
		}
		
		
		public void getUrl(String url) throws IOException {
			driver.get(url);//opening page url
			System.out.println("Website Navigation Successful");
			System.out.println("------------------------------");
			
			
			
			
			
		}
		
		
		
	      public void maximize() {
	    	//maximizing window
	    	  	driver.manage().window().maximize();
	      }
		
	      
	      
		public void searchAndClick() throws IOException {
			
			driver.findElement(By.id("twotabsearchtextbox")).sendKeys(Utility.searchData());//passing excel data to search bar
			driver.findElement(By.id("nav-search-submit-button")).click();//clicking on search
			
			screenshot("search.png");
			
			
			
			
			System.out.println("Search Successful");
			System.out.println("------------------------------");
		}
		
		
		

		
	public void addToCart() throws IOException {
			
			//clicking on first product
			driver.findElement(By.cssSelector("div[class='s-widget-container s-spacing-small s-widget-container-height-small celwidget slot=MAIN template=SEARCH_RESULTS widgetId=search-results_1'] span[class='a-price-whole']")).click();
	//taking screenshot
			screenshot("product1.png");
			//getting all window handles in set and storing in list
			Set<String>winList=driver.getWindowHandles();
			List<String>windowlist=new ArrayList<String>(winList);
			
			
			String childWindow=windowlist.get(1);//second window
			String parentWindow=windowlist.get(0);//first window
			//switching to second window 
			driver.switchTo().window(childWindow);	
			//getting product 1 price
			productPrice1=driver.findElement(By.xpath("//span[@class='a-price aok-align-center reinventPricePriceToPayMargin priceToPay']// span[@class='a-price-whole']")).getText();
			//printing product 1 price
			System.out.println("product 1 price: "+productPrice1);
			//clicking on add to cart
			driver.findElement(By.xpath("//input[@name='submit.add-to-cart']")).click();
			//clicking on skip(popup)
			driver.findElement(By.xpath("//input[@aria-labelledby='attachSiNoCoverage-announce']")).click();
			
			
			
			
			
			//giving explicit wait until cart got updated
			WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span[class='a-price sw-subtotal-amount'] span[class='a-price-whole']")));
			
			
			
			//taking screenshot
			
			screenshot("product1cart.png");	
			System.out.println("product 1 Successfully added to cart");
			System.out.println("------------------------------");
			driver.close();//closing the second window
			driver.switchTo().window(parentWindow);//switching to parent window
			//driver.navigate().refresh();//refreshing home page to update cart

	}


	public void addToCart2() throws IOException {

		//clicking on second product
		driver.findElement(By.cssSelector("div[class='s-widget-container s-spacing-small s-widget-container-height-small celwidget slot=MAIN template=SEARCH_RESULTS widgetId=search-results_2'] span[class='a-price-whole']")).click();
		
		//taking screenshot	
		screenshot("product2.png");	
		//getting all window handles in set and storing in list

		Set<String>winList=driver.getWindowHandles();
		List<String>windowlist=new ArrayList<String>(winList);
		String childWindow=windowlist.get(1);//second window
		
		driver.switchTo().window(childWindow);//first window
		//getting product 2 price
		productPrice2=driver.findElement(By.cssSelector("span[class='a-price aok-align-center reinventPricePriceToPayMargin priceToPay'] span[class='a-price-whole']")).getText();
		//printing product 2 price
		System.out.println("product 2 price: "+productPrice2);
		driver.findElement(By.xpath("//input[@name='submit.add-to-cart']")).click();//clicking on add to cart
		driver.findElement(By.xpath("//input[@aria-labelledby='attachSiNoCoverage-announce']")).click();//clicking on skip
		
		//getting total cart price
		cartSubTotal=driver.findElement(By.cssSelector("span[class='a-price sw-subtotal-amount'] span[class='a-price-whole']")).getText();
		//taking screenshot	
		screenshot("Totalcart.png");
		//printing success message
		System.out.println("product 2 Successfully added to cart");
		System.out.println("------------------------------");
		//printing cart price
		System.out.println("Total cart price: "+cartSubTotal);
		System.out.println("------------------------------");
			
		
	}

	//taking screenshot
	public void screenshot(String fileName) throws IOException {
		TakesScreenshot ts=(TakesScreenshot)driver;
		File src=ts.getScreenshotAs(OutputType.FILE);
		File trg=new File(System.getProperty("user.dir")+"\\screenshot\\"+fileName);
		FileUtils.copyFile(src, trg);
	}



	public void quit() {
		//closing browser
		driver.quit();
	}

	public void timeout() {
		//giving implicit timeout of 10 seconds
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	}

		
			


			
		public static void main(String[] args) throws IOException {
			OnlineShoppingAutomationTest obj=new OnlineShoppingAutomationTest();//creating object for OnlineShoppingAutomation class
			obj.browserChoice();//calling browserchoice method
			obj.timeout();//implicit wait method
			
			String baseURL="https://Amazon.in/";
			obj.getUrl(baseURL);//opening site url
			obj.maximize();//maximizing window
			obj.searchAndClick();//searching for products
			obj.addToCart();//adding to cart
			obj.addToCart2();//adding to cart
			obj.quit();//closing browser
			obj.validation(productPrice1,productPrice2,cartSubTotal);//sending values for validation
			
				}
	}
