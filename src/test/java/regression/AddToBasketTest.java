package regression;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.uiFramework.KTCTC.Pages.AddToBasketPage;
import com.uiFramework.KTCTC.helper.browserConfiguration.ChromeBrowser;
import com.uiFramework.KTCTC.testbase.TestBase;

public class AddToBasketTest extends TestBase{
	
	AddToBasketPage addToBasketPage;
	int initialCount = 1;

	@BeforeClass
	public void beforeClassOfA() {
		driver = ChromeBrowser.getBrowserInstance();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		//driver.manage().window().maximize();
		test = extent.createTest(getClass().getSimpleName());
		driver.get(proObj.getPropertyValueFromFile("baseURL"));
		cmObj.acceptPrivateConnectionWarningIfPresent(driver);		
		cmObj.loginToApplication(driver, proObj.getPropertyValueFromFile("consumerNumber"),proObj.getPropertyValueFromFile("consumerPass"));
	}
	
	
	
	@Test(priority=1)
	public void verifySearchInputField()
	{
		SoftAssert sAssert = new SoftAssert();
		addToBasketPage = new AddToBasketPage(driver);
		String str=addToBasketPage.searchInputField();
		sAssert.assertEquals(str, "input", "tag name for search is not matching");
		sAssert.assertAll();
	}
	@Test(priority=2)
	public void verifySearchedProductIsDisplayedOnSearchPage()
	{
		SoftAssert sAssert = new SoftAssert();
		addToBasketPage.searchProductOnSearchBoxOnHomePage("aaaaa");
		boolean flag=addToBasketPage.isSearchedProductIsDisplayed();
		sAssert.assertTrue(flag, "searched product is not displayed");
		sAssert.assertAll();
	}

	@Test(priority=3)
	public void verifyUserCanAddProductToBasket()
	{
		SoftAssert sAssert = new SoftAssert();
		addToBasketPage.addToBasketButtonOnSelectedProductModal();
		addToBasketPage.increaseQuantityOfProducts(2);
		
		boolean flag=addToBasketPage.addToCartButton();
		sAssert.assertTrue(flag, "item added into basket banar is not displayed");
		sAssert.assertAll();
	}
	
	@Test(priority=4)
	public void verifyNewProductWillAddedFromViewBasketPage()
	{
		SoftAssert sAssert = new SoftAssert();
		addToBasketPage.viewBasketLinkAndAddNewProduct();
		boolean flag= addToBasketPage.addToCartButton();
		sAssert.assertTrue(flag, "item added into basket banar is not displayed");
		sAssert.assertAll();
	}
	
	@Test
	public void VerifyUserCanPlaceTheOrder()
	{
		System.out.println("changes made by sudarshan");
		System.out.println("sssss");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
