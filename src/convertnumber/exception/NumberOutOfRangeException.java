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
public class NumberOutOfRangeException extends Exception {

    public NumberOutOfRangeException() {
        super("Number is out of range. Valid range is -999, 999 to  999, 999");
    }

    public NumberOutOfRangeException(String message) {
        super(message);
    }

}
