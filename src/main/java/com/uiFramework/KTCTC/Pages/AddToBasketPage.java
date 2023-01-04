package com.uiFramework.KTCTC.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.uiFramework.KTCTC.helper.wait.WaitHelper;

public class AddToBasketPage {
	
	WebDriver driver;
	WaitHelper wait;
	
	By searchBoxOnHomePage= 
			By.name("keyword");
	By nameOfProduct = 
			By.xpath("//*[contains(text(),'aaaaa')]");
	By searchButtonOfSearchBoxOnAddToBasketPage= 
			By.xpath("//button[@type='submit']");
	By addToBasketButtonOnSearchPage= 
			By.xpath("//section//a[contains(.,'Add To Basket')]");
	By increaseQuantityButtonOnAddToBasketPage= 
			By.id("add");
	By addToCartButtonOnAddToBasketPage= 
			By.xpath("//input[@type='submit' and @value=' Add to Cart']");
	By itemAddedIntoBasketBanarOnAddToBasketPage= 
			By.xpath("//*[contains(text(),'Item added into Basket..!!')]");
	By viewBasketLinkOnAddToBasketPage =
			By.xpath("//*[contains(text(),' View Basket')]");
	By addProductButtonOnViewBasketLinkOnAddToBasketPage= 
			By.xpath("//section//a[contains(.,'Add Product')]");
	By nameOfNewproductOnAddToBasketPage=
			By.xpath("//*[contains(text(),'PQRST')]");
	By addToBasketButtonOfAddNewProductOnAddToBasketPage= 
	   By.xpath("//div/h3/a[contains(text(),'PQRST')]"
	+ "/parent::h3/parent::div//following-sibling::a[normalize-space('Add To Basket')]");
	
	
	//*//section//tbody//tr[1]//td[4]//input
	
	public AddToBasketPage(WebDriver driver)
	{
		this.driver=driver;
		wait=new WaitHelper(driver);
	}
	
	public String searchInputField()
	{
		String searchTagName=driver.findElement(searchBoxOnHomePage).getTagName();
		return searchTagName;
		
	}
	
	public void searchProductOnSearchBoxOnHomePage(String productName)
	{
		wait.waitForElement(driver.findElement(searchBoxOnHomePage), 10);
		driver.findElement(searchBoxOnHomePage).sendKeys(productName);
		driver.findElement(searchButtonOfSearchBoxOnAddToBasketPage).click();
	}
	
	public boolean isSearchedProductIsDisplayed()
	{
		boolean flag=false;
		try {
			flag=driver.findElement(nameOfProduct).isDisplayed();
		} catch (Exception e) {
			
		}
		return flag;
	}
		
	public void addToBasketButtonOnSelectedProductModal()
	{
		driver.findElement(addToBasketButtonOnSearchPage).click();
	}
	
	public void increaseQuantityOfProducts(int i)
	{	for(i=0;i<2;i++) 
	{
		driver.findElement(increaseQuantityButtonOnAddToBasketPage).click();
		
	}
	}
	
	public boolean addToCartButton()
	{
		driver.findElement(addToCartButtonOnAddToBasketPage).click();
		boolean flag=false;
		flag=driver.findElement(itemAddedIntoBasketBanarOnAddToBasketPage).isDisplayed();
		return flag;
	}
	
	public void viewBasketLinkAndAddNewProduct()
	{
		driver.findElement(viewBasketLinkOnAddToBasketPage).click();
		driver.findElement(addProductButtonOnViewBasketLinkOnAddToBasketPage).click();
		driver.findElement(addToBasketButtonOfAddNewProductOnAddToBasketPage).click();
		
	}
	
}
