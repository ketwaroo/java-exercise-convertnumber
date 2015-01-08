package convertnumber;

import convertnumber.exception.*;

/**
 * <pre>
 * Language: Java
 * Deliverable: All java source files + an executable Jar file
 * that will run the program
 * Input: Integer values from -999, 999 to 999, 999
 * Output: String representation of the value
 * Examples:
 *  java -jar ConvertNumber 100 --&gt; one hundred
 *  java -jar ConvertNumber 0 --&gt; zero
 *  java -jar ConvertNumber 1001 --&gt; one thousand one
 *  java -jar ConvertNumber 999221 --&gt; nine hundred ninety nine thousand two hundred twenty one
 *
 * Assumptions:
 * - output is in English.
 * - numbers are in base 10.
 * - no decimal points. whole integers only
 * - `-1` will output `minus one`
 * - no conjuctions, punctuations or extra grammatical constructs.
 *  * i.e. 1001 = `one thousand one` and not `one thousand and one`
 * - 1600 = `one thousand six hundred` and not `sixteen hundred`
 * - coding in java isn't my strong suit. There may be some syntax weirdness.
 * </pre>
 *
 * @author Yaasir Ketwaroo <yaasir@ketwaroo.com>
 */
public class ConvertNumber {

    /**
     * constructor
     */
    public ConvertNumber() {

    }

    /**
     * entry point for ConvertNumber
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /**
         * the input string.
         */
        String inStr = "";
        IntToStr convertor;

        // this bit is a bit warbly but it's all for input validation
        if (Util.indexExists(args, 0)) {
            // we have something.
            inStr = args[0];
        } else {
            // explain the situation
            dumpDocumentation();
        }
        try {

            convertor = new IntToStr(inStr);
            Util.out(convertor.render());

        } catch (Exception e) { // catch could be more specific but general handling is fine for now.
            Util.out(e.getMessage());
            dumpDocumentation(); // for good measure.            
        }
    }

    public static void dumpDocumentation() {
        Util.out("ConvertNumber Usage:");
        Util.out("Input: Integer values from -999, 999 to 999, 999");
        Util.out("Output: String representation of the value");
        Util.out("Examples:");
        Util.out(" java -jar ConvertNumber 100    --> one hundred");
        Util.out(" java -jar ConvertNumber 0      --> zero");
        Util.out(" java -jar ConvertNumber 1001   --> one thousand one");
        Util.out(" java -jar ConvertNumber 999221 --> nine hundred ninety nine thousand two hundred twenty one");
        Util.out("Author: Yaasir Ketwaroo");
        Util.die("");
    }

}
