/**
 * @author Adam Pine
 * Exception for when recovRate is <0.
 */
package Exceptions;

public class TooManyAttachmentsException extends Exception {

	public TooManyAttachmentsException(String message) {
		super(message);
	}
}
