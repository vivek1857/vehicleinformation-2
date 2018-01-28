package gov.uk.dvla.util;

import static org.junit.Assert.*;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class XlsVehicleDataReaderTest {

	private XlsVehicleDataReader xlsVehicleDataReader = null;
	@Before
	public void setUp() throws Exception {
		xlsVehicleDataReader = new XlsVehicleDataReader();
	}

	@Test
	public final void testGetVehicleData() {
		String[][] vehicleData = xlsVehicleDataReader.getVehicleData("test-data.xlsx");
		List<String> vehicleDeails = new ArrayList<String>();
		vehicleDeails.add("X666 EON");
		vehicleDeails.add("BLACK");
		vehicleDeails.add("Ford");
		vehicleDeails.add("YR12UMB");
        vehicleDeails.add("RED");
		vehicleDeails.add("PEUGEOT");
		vehicleDeails.add("KS08OSD");
		vehicleDeails.add("SILVER");
        vehicleDeails.add("KIA");
		vehicleDeails.add("LN51RTU");
		vehicleDeails.add("BLACK");
		vehicleDeails.add("VOLKSWAGEN");
		
		List<String> csvData = new ArrayList<String>();
		int length = vehicleData.length;
		
		for(int i = 0; i< length;i++) {
			int j = vehicleData[i].length;
			for(j = 0;j<vehicleData[i].length;j++)
			//System.out.println(vehicleData[i][j]);
				csvData.add(vehicleData[i][j]);
		}
		assertTrue(csvData.containsAll(vehicleDeails));
	}

}
