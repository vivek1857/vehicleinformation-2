package gov.uk.dvla.service;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.configuration.ConfigurationException;
import org.junit.Before;
import org.junit.Test;

import gov.uk.dvla.dto.FileAttributes;

public class DefaultDirectoryScannerServiceIntegrationTest {
	
	private DefaultDirectoryScannerService service = null;
	@Before
	public void init() throws ConfigurationException {
		service = new DefaultDirectoryScannerService();
	}
	
	@Test
	
	public final void testDefaultDirectoryScannerServiceConstructor() throws ConfigurationException {
		service = new DefaultDirectoryScannerService();
		not(service!= null);
		
	}

	
	@Test
	public final void testGetFileDirectory() {
		assertThat("C://proj//workspaces//springboot//vehicleinformation",is(service.getFileDirectory()));
	}

	@Test
	public final void testGetAllowedFileTypes() {
		String[] allowedType = {"xls","csv","xlsx"} ;
		assertThat(Arrays.asList(allowedType),is(service.getAllowedFileTypes()));
	}

	@Test
	public final void testFindFileInfo() throws IOException {
		List<FileAttributes> fileInfo = service.findFileInfo();
		FileAttributes fileOne = fileInfo.get(0);
		FileAttributes fileTwo = fileInfo.get(1);
		for(FileAttributes attr :fileInfo) {
			System.out.println(attr);
		}
		assertThat("8796196339742.csv",is(fileOne.getFileName()));
		assertThat("application/vnd.ms-excel",is(fileOne.getContentType()));
		assertThat("csv",is(fileOne.getFileExtension()));
		assertTrue(fileOne.getFileSize()>0);
		
		assertThat("test-data.xlsx",is(fileTwo.getFileName()));
		assertThat("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",is(fileTwo.getContentType()));
		assertThat("xlsx",is(fileTwo.getFileExtension()));
		assertTrue(fileTwo.getFileSize()>0);
		
		
	}

}
