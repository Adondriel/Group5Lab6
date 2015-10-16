/**
 * @author Adam Pine
 * Exception for when recovRate is <0.
 */
package Exceptions;

public class RecovRateIsNegative extends Exception {

	public RecovRateIsNegative(String message) {
		super(message);
	}
}
