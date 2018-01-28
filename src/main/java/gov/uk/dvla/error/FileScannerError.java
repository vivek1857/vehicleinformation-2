/**
 * 
 */
package gov.uk.dvla.error;

/**
 * @author Vivek.Khandagale
 *
 */
public class FileScannerError {

	private String error;
	
	public FileScannerError(String error) {
		this.error = error;
	}

	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
}
