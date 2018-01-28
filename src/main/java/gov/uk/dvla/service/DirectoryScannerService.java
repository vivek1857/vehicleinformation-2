/**
 * 
 */
package gov.uk.dvla.service;

import java.io.IOException;
import java.util.List;

import gov.uk.dvla.dto.FileAttributes;

/**
 * @author Vivek.Khandagale
 *
 */
public interface DirectoryScannerService {
	public List<FileAttributes> findFileInfo() throws IOException;
}
