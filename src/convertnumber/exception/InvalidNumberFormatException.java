/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package convertnumber.exception;

/**
 *
 * @author Administrator
 */
public class InvalidNumberFormatException extends Exception {

    /**
     * Creates a new instance of <code>InvalidNumberFormatException</code>
     * without detail message.
     */
    public InvalidNumberFormatException() {
        super("Number format is invalid");
    }

    /**
     * Constructs an instance of <code>InvalidNumberFormatException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public InvalidNumberFormatException(String msg) {
        super(msg);
    }
}
