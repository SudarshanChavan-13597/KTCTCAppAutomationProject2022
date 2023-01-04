package com.uiFramework.KTCTC.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.uiFramework.KTCTC.helper.wait.WaitHelper;

public class InventoryBrandPage {
	
	WebDriver driver;
	WaitHelper wait;
	
	By brandOptionFromInventoryPage = By.xpath("//*[contains(text(), 'Brand')]");
	By newButtonOnInventoryBrandPage= By.xpath("//a[contains(text(), 'New')]");
	By newTextBoxOnAddNewBrandModal = By.id("name");
	By closeButtonOnAddnewBrandModal= By.xpath("//*[@id=\"addnewunit\"]/div/div/div[3]/button[1]");
	
	By saveButtonOnAddNewBrandModal = By.name("add");
	By brandAddedSuccessfullyBannerOnBrandPage = By.xpath("//section//*[contains(.,'Brand added successfully')]");
	
	By countOfBrandsOnBrandPage = By.id("example1_info");
	
	By searchBoxOnBrandPage = By.xpath("//*[@id=\"example1_filter\"]/label/input");
	By editButtonOnBrandPage= By.xpath("//button[contains(text(),'Edit')]");
	
	By nameTextBoxOnEditBrandModal=By.id("edit_unit");
	By updateButtonOnEditBrandModal=By.xpath("//button[contains(text(),'Update')]");
	By brandUpdatedSuccessfullyOnBrandPage =By.xpath("//section//*[contains(.,'Brand updated successfully')]");
	
	By deleteButtonOnBrandPage = By.xpath("//td/button[contains(text(),'Delete')]");

	By deleteButtonOnDeleteBrandModal = By.name("delete");
	By categoryDeletedSuccessfullyBannerOnBrandPage = By
			.xpath("//section//*[contains(.,'Brand deleted successfully')]");
	
	
	
	public InventoryBrandPage(WebDriver driver) {
		
		this.driver=driver;
		wait=new WaitHelper(driver);
		
	}
	
	public void expandBrandOptionFromInventory() {
		wait.waitForElement(driver.findElement(brandOptionFromInventoryPage), 10);
		driver.findElement(brandOptionFromInventoryPage).click();
	}
	
	
	public void addNewBrandOnInventoryBrandPage(String name) {
		
		
		driver.findElement(newButtonOnInventoryBrandPage).click();
		wait.waitForElement(driver.findElement(newTextBoxOnAddNewBrandModal), 10);
		driver.findElement(newTextBoxOnAddNewBrandModal).sendKeys(name);
		driver.findElement(saveButtonOnAddNewBrandModal).click();
	}
	
	public boolean isBrandAddedSuccessfullyBannerDisplayed() {
		boolean flag=false;
		
		try {
			wait.waitForElement(driver.findElement(brandAddedSuccessfullyBannerOnBrandPage), 10);
			flag= driver.findElement(brandAddedSuccessfullyBannerOnBrandPage).isDisplayed();
		} catch (Exception e) {
			
		}
		return flag;
	}
	/**
	 * This method returns count of records present on category page
	 * @return
	 */
	public int getCountOfBrandsOnBrandPage() {
		wait.waitForElement(driver.findElement(countOfBrandsOnBrandPage), 10);
		String cn=driver.findElement(countOfBrandsOnBrandPage).getText();
		String[] cc=cn.split(" ");
		String cd=cc[5];
		
		int cnt= Integer.parseInt(cd);
		return cnt;
	}
	
	public void clearSearchBoxOnBrandPage() {
		
		String str=driver.findElement(searchBoxOnBrandPage).getAttribute("value");
		//driver.findElement(searchBoxOnBrandPage).click();
		
		for(int i=0; i<str.length(); i++)
		{
			driver.findElement(searchBoxOnBrandPage).sendKeys(Keys.BACK_SPACE);
		}
		
	}
	
	public void searchStringInSearchBoxOnBrandPage(String SearchItem)
	{
		clearSearchBoxOnBrandPage();
		wait.waitForElement(driver.findElement(searchBoxOnBrandPage), 10);
		driver.findElement(searchBoxOnBrandPage).sendKeys(SearchItem);
	}
	
	
	public void editExistingBrandOnBrandPage(String existingBrandName, String newBrandName)
	{
		searchStringInSearchBoxOnBrandPage(existingBrandName);
		driver.findElement(editButtonOnBrandPage).click();
		wait.waitForElement(driver.findElement(nameTextBoxOnEditBrandModal), 10);
		driver.findElement(nameTextBoxOnEditBrandModal).clear();
		
		driver.findElement(updateButtonOnEditBrandModal).sendKeys(newBrandName);
		
		driver.findElement(updateButtonOnEditBrandModal).click();
		clearSearchBoxOnBrandPage();		
		
		
	}
	
	public boolean isBrandUpdatedSuccessfullyBanarDisplayed()
	{	
		boolean flag = false;

		try {
			wait.waitForElement(driver.findElement(brandUpdatedSuccessfullyOnBrandPage), 10);
			flag = driver.findElement(brandUpdatedSuccessfullyOnBrandPage).isDisplayed();

		} catch (Exception e) {

		}
		return flag;

	}
	
	public void deleteExistingBrandOnBrandPage(String existingBrandName) throws Exception
	{
		
		searchStringInSearchBoxOnBrandPage(existingBrandName);
		wait.waitForElement(driver.findElement(deleteButtonOnBrandPage), 10);
		driver.findElement(deleteButtonOnBrandPage).click();
		wait.waitForElement(driver.findElement(deleteButtonOnDeleteBrandModal), 10);

		driver.findElement(deleteButtonOnDeleteBrandModal).click();
		
	}
	
	public boolean isExistingBrandDeletedSuccessfullyDeletedBanarDisplayed() 
	{	
		boolean flag= false;
		try {
			wait.waitForElement(driver.findElement(categoryDeletedSuccessfullyBannerOnBrandPage), 10);

			flag=driver.findElement(categoryDeletedSuccessfullyBannerOnBrandPage).isDisplayed();
		} catch (Exception e) {
			
		}
		return flag;
	}
	
	public boolean isBrandDisplayedOnBrandPage(String brandName) 
	{
		boolean flag=false;
		searchStringInSearchBoxOnBrandPage(brandName);
		
		try {
			Thread.sleep(2000);
			//wait.waitForElement(driver.findElement(By.xpath("//td[contains(text(),'"+brandName+"')]")), 10);

			flag=driver.findElement(By.xpath("//td[contains(text(),'"+brandName+"')]")).isDisplayed();
		} catch (Exception e) {
			
		}
		clearSearchBoxOnBrandPage();
		return flag;
	}
	
}
