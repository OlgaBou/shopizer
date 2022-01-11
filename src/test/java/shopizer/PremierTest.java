package shopizer;



import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




public class PremierTest {
	WebDriver driver;
	Logger LOGGER = LoggerFactory.getLogger(PremierTest.class);
	
	
	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "src/main/resources/driver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
	

	/*public void setup() throws Exception{
		String browser = System.getProperty("toto");
		//String browser = "";
		if(browser.equalsIgnoreCase("firefox")){
			System.setProperty("webdriver.gecko.driver", "src/main/resources/driver/geckodriver.exe");
			driver = new FirefoxDriver();
		}
		
		else if(browser.equalsIgnoreCase("chrome")){
			System.setProperty("webdriver.chrome.driver","src/main/resources/driver/chromedriver.exe");
			driver = new ChromeDriver();
		}

				else if(browser.equalsIgnoreCase("IE")){
					System.setProperty("webdriver.ie.driver","src/main/resources/driver/IEDriverServer.exe");
					WebDriver driver = new InternetExplorerDriver();
				}
		else{
			System.setProperty("webdriver.chrome.driver","src/main/resources/driver/chromedriver.exe");
			driver = new ChromeDriver();
		}
		
		driver.manage().window().maximize();
	}
	*/

	@Test
	public void run() throws InterruptedException {
		
		PageAccueil accueilPage = new PageAccueil(driver);
		PagePanier pagePanier = new PagePanier(driver);
		
		LOGGER.info("******* ETAPE 1 : Overture de l'application ******");
		driver.get("http://192.168.1.17:8080/shop");
		
		LOGGER.info("******* ETAPE 2 : Incr�mentation du panier suite � l'ajout de produit ******");
		accueilPage.ajouterAuPanier();
		
		LOGGER.info("******* ETAPE 3 : Affichage du contenu du panier ******");
		accueilPage.moveMouse();
		
		LOGGER.info("******* ETAPE 5 : Ouverture du panier ******");
		accueilPage.ouvrirPanier();
		
		LOGGER.info("******* ETAPE 6 : V�rification de l'affichage du panier ******");
		assertTrue("La page Panier n'est pas affich�e", pagePanier.titrePagePanier.isDisplayed());
		
		LOGGER.info("******* ETAPE 7 : V�rification de la pr�sence d'un tableau ******");
		pagePanier.verifyTable();
		
		// assertTrue("Le tableau Panier n'est pas affich�", pagePanier.tableauRecap.isDisplayed());
		LOGGER.info("******* ETAPE 8 : V�rification de la pr�sence d'une image ******");
		pagePanier.verifyImage();
		
		LOGGER.info("******* ETAPE 9 : V�rification de la pr�sence d'un nom ******");
		pagePanier.verifyName();
		
		LOGGER.info("******* ETAPE 10 : V�rification de la pr�sence de la quantit� ******");
		pagePanier.verifyQuantity();
		
		LOGGER.info("******* ETAPE 11 : V�rification de la pr�sence du prix par article ******");
		pagePanier.verifyPriceProduct();
		
		LOGGER.info("******* ETAPE 10 : V�rification de la pr�sence du total par article ******");
		pagePanier.verifyPriceTotalArticle();
		
		LOGGER.info("******* ETAPE 11 : R�cuperation du total de la section en String ******");
		String priceTotalArticle_un = pagePanier.priceTotalArticle.getText().substring(4, 9);
		
		LOGGER.info("******* ETAPE 12 : V�rification de la pr�sence du total ******");
		pagePanier.verifyPriceTotal();
		
		LOGGER.info("******* ETAPE 13 : R�cuperation du total en String ******");
		String priceTotal_un = pagePanier.priceTotal.getText().substring(4, 9);
		
		LOGGER.info("******* ETAPE 14 : Doublement de la quantit�  ******");
		pagePanier.changeQuantity();
		
		LOGGER.info("******* ETAPE 15 : V�rification du doublement de la quantit� ******");
		assertTrue("La quantit�e n'a pas �t� doubl�e", pagePanier.quantityProduct.getAttribute("value").equals("2"));
		
		LOGGER.info("******* ETAPE 16 : V�rification que les prix n'ont pas �t� modifi�s******");
		assertTrue("Le prix de l'article a chang�", pagePanier.priceTotalArticle.getText().substring(4, 9).equals(priceTotalArticle_un));
		assertTrue("Le total de section a chang�", pagePanier.priceTotal.getText().substring(4, 9).equals(priceTotal_un));
		
		LOGGER.info("******* ETAPE 17 : R�calculation de la quantit� ******");	
		pagePanier.recalculate();
		
		/*LOGGER.info("******* ETAPE 18 : V�rification du doublement de la quantit� ******");	
		String total_section_deux = pagePanier.priceTotalArticle.getText().substring(4, 9);
		double prix_article_double = Double.parseDouble(priceTotalArticle_un);
		double total_section_double = Double.parseDouble(total_section_deux);
		assertTrue("Le total de section n'a pas �t� multipli� par deux", total_section_double == prix_article_double*2);
		String total_commande_deux = pagePanier.priceTotal.getText().substring(4, 9);
		double total_commande_double = Double.parseDouble(total_commande_deux);
		assertTrue("Le total de la commande n'a pas �t� multipli� par deux", total_commande_double == prix_article_double*2);*/
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		LOGGER.info("******* ETAPE 19 : Payment ******");	
		pagePanier.effectuerPayment();
		
		Thread.sleep(3000);
		
	}

	@After 
	public void teardown() { 
		driver.quit();  
		}
	
}
