package in.co.buscard.wsx.exception;

/**
 * RecordNotFoundException thrown when a record not found occurred
 * 
 * @author MICARD Technologies
 * @version 1.0
 * @Copyright (c) MICARD Technologies
 */

public class RecordNotFoundException extends Exception{

	/**
	 * @param msg
	 *            error message
	 */
	public RecordNotFoundException(String msg) {
		super(msg);

	}
}
