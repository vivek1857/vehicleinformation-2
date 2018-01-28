package gov.uk.dvla.util;

import java.io.IOException;

/**
 * The Interface VehicleDataReader.
 */
public interface VehicleDataReader {
	
	/**
	 * Gets the vehicle data.
	 *
	 * @param filePath the file path
	 * @return the vehicle data
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public String[][] getVehicleData(String filePath) throws IOException;
}
