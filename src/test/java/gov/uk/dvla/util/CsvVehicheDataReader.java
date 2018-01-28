package gov.uk.dvla.util;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import com.opencsv.CSVReader;

public class CsvVehicheDataReader implements VehicleDataReader {
	
	@Override
	public String[][] getVehicleData(String filePath) throws IOException{

		String[][] vehicleData = null;
		
		try(CSVReader csvReader = new CSVReader(new FileReader(filePath), ',')) {
			
			List<String[]> records = csvReader.readAll();
			vehicleData = new String [records.size()][3];
			
			Iterator<String[]> iterator = records.iterator();
			
			int i = 0;
			while (iterator.hasNext()) {
				
				String[] record = iterator.next();
				vehicleData[i][0] = record[0];
				vehicleData[i][1] = record[1];
				vehicleData[i][2] = record[2];
				
				i++;
			}
		}
		return vehicleData;
	}

}
