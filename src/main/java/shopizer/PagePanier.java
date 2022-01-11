package shopizer;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PagePanier extends BasePage {
	
	public PagePanier (WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
}
	@FindBy (xpath = "//div[@class='entry-header']/h1[contains(.,'Revoir votre commande')]")
	WebElement titrePagePanier;
	
	@FindBy(xpath = "//table[@id='mainCartTable']")
	//private WebElement tableauRecap;
	public WebElement tableauRecap;

	@FindBy(xpath = "//div[@class='row-cart']/img")
	public WebElement imageProduct;

	@FindBy (xpath = "//div[@class='row-cart']//span[@class='nomargin']")
	public WebElement nameProduct;
	
	@FindBy (xpath = "//input[@class='input-small quantity text-center']")
	public WebElement quantityProduct;
	
	@FindBy (xpath = "//td[@data-th='Prix']/strong")
	public WebElement priceProduct;
	
	@FindBy (xpath = "//td[@data-th='Total']/strong")
	public WebElement priceTotalArticle;
	
	@FindBy (xpath = "//th[text()='Total']/following-sibling::td/span[@class='amount']")
	public WebElement priceTotal;
	
	@FindBy (xpath = "//a[text()='Recalculer']")
	public WebElement buttonRecalculate;
	
	@FindBy (css = "[class='wc-proceed-to-checkout']>a")
	public WebElement buttonPayment;
	
	
	public void verifyTable() { 
		if(tableauRecap.isDisplayed()){
			System.out.println("Table is Present");
			}else{
			System.out.println("Table is Absent");
			}

	}
		
	public void verifyImage() { 
			if((imageProduct)!= null){
				System.out.println("Image is Present");
				}else{
				System.out.println("Image is Absent");
				}
	
}
	public void verifyName() { 
		if(nameProduct.isDisplayed()){
			System.out.println("Name is Present");
			}else{
			System.out.println("Name is Absent");
			}
}
	public void verifyQuantity() {
		quantityProduct.getAttribute("value").equals("1");
	}
	
	public void verifyPriceProduct() { 
		if(priceProduct.isDisplayed()){
			System.out.println("Price of one product is Present");
			}else{
			System.out.println("Price of one product is Absent");
			}
}
	public void verifyPriceTotalArticle() {
		if(priceTotalArticle.isDisplayed()){
			System.out.println("Total section price is Present");
			}else{
			System.out.println("Total section price is Absent");
			}
	}
	
	public void verifyPriceTotal() {
		if(priceTotal.isDisplayed()){
			System.out.println("Total price is Present");
			}else{
			System.out.println("Total price is Absent");
			}
	}
	
	public void changeQuantity() {
		quantityProduct.click();
		quantityProduct.clear();
		quantityProduct.sendKeys("2");
		
	}	
	
	public void recalculate() {
		buttonRecalculate.click();
			
	}	
	
	public PagePayment effectuerPayment () {
		
		wait.until(ExpectedConditions.elementToBeClickable(buttonPayment));
		buttonPayment.click();
		
		return new PagePayment(this.driver);
	}
}