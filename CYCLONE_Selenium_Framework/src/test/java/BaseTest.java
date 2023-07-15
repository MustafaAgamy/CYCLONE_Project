import com.ej.drivers.CYCLONE;
import pages.demoNoCommerce.DemoNopHomePage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;



public class BaseTest {


    CYCLONE.WebDriver driver;

    protected DemoNopHomePage demoNopHomePage;


    @BeforeMethod
    protected void setUp() {
        driver = new CYCLONE.WebDriver();
        driver.browserAction().navigateToURL();

        demoNopHomePage = new DemoNopHomePage(driver);

    }

    @AfterMethod
    protected void tearDown(){ driver.quit();}
    
}
