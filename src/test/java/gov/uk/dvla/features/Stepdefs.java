package gov.uk.dvla.features;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Stepdefs {
	private WebDriver driver = null; 
	private Scenario myScenario;

    @Before()
    public void embedScreenshotStep(Scenario scenario) {
        myScenario = scenario;
    }

    @Then ("^I take a screenshot$")
    public void i_take_a_screenshot() throws Throwable {

        try {
            myScenario.write("Current Page URL is " + driver.getCurrentUrl());
            byte[] screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
            myScenario.embed(screenshot, "image/png");  // Stick it in the report
        } catch (WebDriverException webDriverException) {
        	webDriverException.printStackTrace();
        } catch (ClassCastException cce) {
            cce.printStackTrace();
        }
    }

   @Given("^I have opened the browser$") 
   public void openBrowser() { 
	   System.setProperty("webdriver.chrome.driver", 
			   "chromedriver.exe");
      driver = new ChromeDriver();
   } 
	
   @When("^I navigate to DVLA Vechicle information site$") 
   public void goDVLAVehicleInfoPage() { 
      driver.navigate().to("https://www.gov.uk/get-vehicle-information-from-dvla"); 
   } 
   @When("^I click on Start now$")
    public void clickSearchButton() {
        WebElement startButton = driver.findElement(By.linkText("Start now"));
        startButton.click();
    }
   @And("^I enter registration number \"(.*)\"$") 
   public void enterCarRegistrationNumber(String reg) {   
     // driver.findElement(By.id("email")).sendKeys(reg); 
      driver.findElement(By.xpath("//*[@id=\"Vrm\"]")).sendKeys(reg);  
   }
   @And("^I click on continue button")
    public void clickContinue() {
        WebElement continueButton = driver.findElement(By.name("Continue"));
        continueButton.click();
    }
	
   @Then("^Login button should exits$") 
   public void loginButton() { 
      if(driver.findElement(By.id("u_0_v")).isEnabled()) { 
         System.out.println("Test 1 Pass"); 
      } else { 
         System.out.println("Test 1 Fail");
      }
   } 
	
   @Then("^Forgot password link should exist$") 
   public void forgotPWD() { 
      if(driver.findElement(By.id("")).isEnabled()) { 
         System.out.println("Test 1 Pass"); 
      } else {
         System.out.println("Test 1 Fail");
      } 
   } 
   @Then("^Single result is shown for '(.*?)'$")
   public void assertSingleResult(String searchResult) {
       WebElement results = driver
           .findElement(By.cssSelector("div#mw-content-text.mw-content-ltr p"));
       //assertFalse(results.getText().contains(searchResult + " may refer to:"));
       //assertTrue(results.getText().startsWith(searchResult));
   }
}