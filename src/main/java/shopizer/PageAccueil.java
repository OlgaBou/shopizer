package shopizer;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PageAccueil extends BasePage {
	
	public PageAccueil (WebDriver driver) {
		
		super(driver);
}
	
	@FindBy(xpath = "//a[@productid='50']")
	private WebElement buttonAjouterAuPanier;
	
	@FindBy(xpath = "//div[@class='shop-cart']/a")
	private WebElement mouseOverPanier;
	
	@FindBy(xpath = "//a[@onclick='viewShoppingCartPage();']")
	private WebElement buttonOuvrirPanier;

	
	public void ajouterAuPanier() { 
		wait.until(ExpectedConditions.visibilityOf(buttonAjouterAuPanier));
		buttonAjouterAuPanier.click();

	}
	
	public void moveMouse () {
		 Actions builder = new Actions(driver);
		 builder.moveToElement(mouseOverPanier).build().perform();
	}
	
	public PagePanier ouvrirPanier () {
		
		wait.until(ExpectedConditions.elementToBeClickable(buttonOuvrirPanier));
		
		buttonOuvrirPanier.click();
		return new PagePanier(this.driver);
	}
}
