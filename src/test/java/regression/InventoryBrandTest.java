package regression;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.uiFramework.KTCTC.Pages.InventoryBrandPage;
import com.uiFramework.KTCTC.helper.browserConfiguration.ChromeBrowser;
import com.uiFramework.KTCTC.testbase.TestBase;

public class InventoryBrandTest extends TestBase {

	InventoryBrandPage inventoryBrandPage;
	String brandName = null;
	String newBrandName = null;
	int initialCount = 100;

	@BeforeClass
	public void beforeClassOfA() {
		driver = ChromeBrowser.getBrowserInstance();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		test = extent.createTest(getClass().getSimpleName());
		driver.get(proObj.getPropertyValueFromFile("baseURL"));
		cmObj.acceptPrivateConnectionWarningIfPresent(driver);
		cmObj.loginToApplication(driver, proObj.getPropertyValueFromFile("adminNumber"),
				proObj.getPropertyValueFromFile("adminPass"));
	}

	@Test(priority = 1)
	public void verifyNewBrandCanBeAddedOnBrandPage() {
		inventoryBrandPage = new InventoryBrandPage(driver);
		SoftAssert sAssert = new SoftAssert();
		brandName = cmObj.getAlphaNumericString(7);
		cmObj.expandInventoryOption(driver);
		inventoryBrandPage.expandBrandOptionFromInventory();
		initialCount = inventoryBrandPage.getCountOfBrandsOnBrandPage();
		inventoryBrandPage.addNewBrandOnInventoryBrandPage(brandName);
		boolean flag = inventoryBrandPage.isBrandAddedSuccessfullyBannerDisplayed();
		sAssert.assertTrue(flag, "brand added successfully banar is not displayed");
		sAssert.assertAll();
	}

	@Test(priority = 2)
	public void verifyCountGetIncreasedAfterAddingNewBrand() {
		SoftAssert sAssert = new SoftAssert();
		int finalCount = inventoryBrandPage.getCountOfBrandsOnBrandPage();
		sAssert.assertEquals(finalCount, initialCount + 1, "Count is not increased by one after adding new category");
		sAssert.assertAll();
	}

	@Test(priority = 3)
	public void verifyNewlyAddedBrandIsDisplayedOnBrandPage() {
		SoftAssert sAssert = new SoftAssert();
		boolean flag = inventoryBrandPage.isBrandDisplayedOnBrandPage(brandName);
		sAssert.assertTrue(flag, "newly added brand is not displayed on brand page");
		sAssert.assertAll();
	}

	@Test(priority = 4)
	public void verifyUserCanUpdateExistingBrand() {
		SoftAssert sAssert = new SoftAssert();
		newBrandName = cmObj.getAlphaNumericString(7);
		initialCount = inventoryBrandPage.getCountOfBrandsOnBrandPage();

		inventoryBrandPage.editExistingBrandOnBrandPage(brandName, newBrandName);

		boolean flag = inventoryBrandPage.isBrandUpdatedSuccessfullyBanarDisplayed();
		sAssert.assertTrue(flag, "brand updated successfully banar is not displayed");
		sAssert.assertAll();

	}

	@Test(priority = 5)
	public void verifyCountWillNotBeChangedAfterEditingNewBrand() {
		SoftAssert sAssert = new SoftAssert();
		int finalCount = inventoryBrandPage.getCountOfBrandsOnBrandPage();
		sAssert.assertEquals(initialCount, finalCount, "count is updated while updating existing brand");
		sAssert.assertAll();

	}

	@Test(priority = 6)
	public void verifyUpdatedBrandIsDisplayedOnBrandPage() {
		SoftAssert sAssert = new SoftAssert();
		boolean flag = inventoryBrandPage.isBrandDisplayedOnBrandPage(newBrandName);
		sAssert.assertTrue(flag, "updated brand is not displayed on brand page");
		boolean flag2 = inventoryBrandPage.isBrandDisplayedOnBrandPage(brandName);
		sAssert.assertFalse(flag2, "old brand name is still displayed on brand page");
		sAssert.assertAll();
	}

	@Test(priority = 7)
	public void verifyUserCanDeleteExistingBrandFromBrandPage() throws Exception {
		SoftAssert sAssert = new SoftAssert();
		initialCount = inventoryBrandPage.getCountOfBrandsOnBrandPage();
		inventoryBrandPage.deleteExistingBrandOnBrandPage(newBrandName);
		boolean flag = inventoryBrandPage.isExistingBrandDeletedSuccessfullyDeletedBanarDisplayed();
		sAssert.assertTrue(flag, "brand deleted successfully banar is not displayed");
		sAssert.assertAll();
	}

	@Test(priority = 9)
	public void verifyCountOfBrandsWillGetReducedAfterDeletingBrand() {
		SoftAssert sAssert = new SoftAssert();
		int finalCount = inventoryBrandPage.getCountOfBrandsOnBrandPage();
		sAssert.assertEquals(finalCount, initialCount - 1, "count is not decreased by one after deleting brand");
		sAssert.assertAll();
	}

	@Test(priority = 8)
	public void verifyDeletedBrandIsNotDisplayedOnBrandPage() {

		SoftAssert sAssert = new SoftAssert();
		boolean flag2 = inventoryBrandPage.isBrandDisplayedOnBrandPage(newBrandName);
		sAssert.assertFalse(flag2, "deleted brand name is still displayed on brand page");
		sAssert.assertAll();
	}

}
