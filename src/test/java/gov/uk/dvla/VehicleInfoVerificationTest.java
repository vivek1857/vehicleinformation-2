package gov.uk.dvla;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.util.List;

import org.apache.commons.configuration.ConfigurationException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import gov.uk.dvla.dto.FileAttributes;
import gov.uk.dvla.service.DefaultDirectoryScannerService;
import gov.uk.dvla.service.DirectoryScannerService;
import gov.uk.dvla.util.CsvVehicheDataReader;
import gov.uk.dvla.util.VehicleDataReader;
import gov.uk.dvla.util.XlsVehicleDataReader;

public class VehicleInfoVerificationTest {

	private static final String FILE_DIR_LOCATION = "C:\\proj\\workspaces\\springboot\\vehicleinformation\\";
	private WebDriver driver;
	@BeforeTest
	public void Setup()

	{
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test(dataProvider = "testdata")
	public void testVehicleDetails(String reg, String colour, String make) throws InterruptedException

	{
		driver = new ChromeDriver();
		driver.navigate().to("https://www.gov.uk/get-vehicle-information-from-dvla");
		driver.findElement(By.linkText("Start now")).click();
		driver.findElement(By.xpath("//*[@id=\"Vrm\"]")).sendKeys(reg);
		driver.findElement(By.name("Continue")).click();

		String vehicleMake = driver.findElement(By.xpath("//*[@id=\"pr3\"]/div/ul/li[2]/span[2]/strong")).getText();
		String vehicleColour = driver.findElement(By.xpath("//*[@id=\"pr3\"]/div/ul/li[3]/span[2]/strong")).getText();

		assertThat(make, equalTo(vehicleMake));
		assertThat(colour, equalTo(vehicleColour));

		driver.quit();
	}

	@DataProvider(name = "testdata")
	public String[][] testVehicleData() throws IOException, ConfigurationException {

		DirectoryScannerService service = new DefaultDirectoryScannerService();
		VehicleDataReader csvVehicheDataReader = new CsvVehicheDataReader();
		VehicleDataReader xlsVehicheDataReader = new XlsVehicleDataReader();

		List<FileAttributes> fileInfoList = service.findFileInfo();
		String[][] data = new String[0][];
		for (FileAttributes attr : fileInfoList) {
			if (attr.getFileExtension().equals("xlsx"))
				data = append(data, xlsVehicheDataReader.getVehicleData(FILE_DIR_LOCATION + attr.getFileName()));
			else if (attr.getFileExtension().equals("csv"))
				data = append(data, csvVehicheDataReader.getVehicleData(FILE_DIR_LOCATION + attr.getFileName()));

		}
		return data;
	}

	public static String[][] append(String[][] frist, String[][] second) {
		String[][] result = new String[frist.length + second.length][];
		System.arraycopy(frist, 0, result, 0, frist.length);
		System.arraycopy(second, 0, result, frist.length, second.length);
		return result;
	}

}
