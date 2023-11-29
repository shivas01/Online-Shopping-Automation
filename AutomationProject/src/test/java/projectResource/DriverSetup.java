package projectResource;
import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class DriverSetup {
	
	
public static WebDriver driver;


public static void getWebDriver(String Browser) throws IOException {

	
	if(Browser.equals("chrome")) {
		//creating chrome driver
		driver = new ChromeDriver();
	}

	else if(Browser.equals("edge")) {
		//creating edge driver
		driver = new EdgeDriver();
	}
	else if(Browser.equals("firefox")) {
		//creating edge driver
		driver = new FirefoxDriver();
	}
	

	
}

}
