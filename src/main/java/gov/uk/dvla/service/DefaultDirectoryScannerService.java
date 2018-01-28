/**
 * 
 */
package gov.uk.dvla.service;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import gov.uk.dvla.dto.FileAttributes;

/**
 * The Class DefaultDirectoryScannerService.
 *
 * @author Vivek.Khandagale
 */
@Service("directoryScannerService")
public class DefaultDirectoryScannerService implements DirectoryScannerService {

	private static final String APPLICATION_PROPERTIES = "application.properties";
	/** The config. */
	private Configuration config = null;
	
	/**
	 * Instantiates a new default directory scanner service.
	 *
	 * @throws ConfigurationException the configuration exception
	 */
	public DefaultDirectoryScannerService() throws ConfigurationException {
		config = new PropertiesConfiguration(APPLICATION_PROPERTIES);
	}
	/** The Constant log. */
	private static final Logger log = LoggerFactory.getLogger(DefaultDirectoryScannerService.class);
	
	/**
	 * Gets the file directory.
	 *
	 * @return the file directory
	 */
	public String getFileDirectory() {
		return (String)config.getProperty("file.directory.path.for.scanning");
	}
	
	/**
	 * Gets the allowed file types.
	 *
	 * @return the allowed file types
	 */
	public List<String> getAllowedFileTypes() {
		return (List<String>)config.getProperty("file.supported.mimetype");
	}
	/* (non-Javadoc)
	 * @see gov.uk.dvla.service.DirectoryScannerService#findFileInfo()
	 */
	@Override
	public List<FileAttributes> findFileInfo() throws IOException {
		log.debug("Scanning files in directory {} " + getFileDirectory());
		Path dir = Paths.get(getFileDirectory());
		System.out.println(getFileDirectory());
		List<FileAttributes> attributes = new ArrayList<FileAttributes>();

		try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(dir)) {
			for (Path file : directoryStream) {
				BasicFileAttributes attrs = Files.readAttributes(file, BasicFileAttributes.class);
				String fileExtension = FilenameUtils.getExtension(file.getFileName().toString());
				List<String> allowedFileTypes = getAllowedFileTypes();
				if (attrs.isRegularFile() && allowedFileTypes.contains(fileExtension)) {
					FileAttributes fileAttributes = new FileAttributes();
					fileAttributes.setFileName(file.getFileName().toString());
					fileAttributes.setContentType(Files.probeContentType(file));
					fileAttributes.setFileExtension(fileExtension);
					fileAttributes.setFileSize(Files.size(file));
					attributes.add(fileAttributes);
					System.out.println(" File name: " + file.getFileName() + ", Contetn type: "
							+ Files.probeContentType(file) + ",	 File extension: " + fileExtension);
				}
			}
		}
		log.debug("Finished scanning files in directory {} " + getFileDirectory());
		return attributes;
	}

}
