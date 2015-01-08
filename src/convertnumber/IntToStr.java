/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package convertnumber;

import java.util.HashMap;
import convertnumber.exception.*;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class IntToStr {

    // some manner of constants
    public static final String KEY_SINGLES = "singles";
    public static final String KEY_TENS = "tens";
    public static final String KEY_HUNDREDS = "hundreds";
    public static final String KEY_THOUSANDS = "thousands";
    public static final int MAX_RANGE = 1000000;

    protected HashMap<String, String> translation;
    protected HashMap<String, Integer> chunkSizes;
    protected ArrayList<String> cardinality;

    protected String input = "";
    protected int intInput;
    protected String validatorRegex = "^\\-?[0-9\\,]+$";
    boolean isNegative = false;

    public IntToStr(String input) throws InvalidNumberFormatException, NumberOutOfRangeException {
        this.input = input;
        validateInput();
        initData();
    }

    private void validateInput() throws InvalidNumberFormatException, NumberOutOfRangeException {

        this.input = input.trim();

        if (!(input.matches(validatorRegex))) {
            throw new InvalidNumberFormatException();
        }

        if (input.charAt(0) == '-') {
            this.isNegative = true;
        }
        // remove anything which is suspicious and leaves us with an positive number.
        input = input.replaceAll("[^0-9]+", "");

        intInput = Integer.parseInt(input);
        if (intInput > MAX_RANGE) {
            throw new NumberOutOfRangeException();
        }
    }

    /**
     * chunk the string in groups that can be easily looked up in the
     * translation table.
     *
     * @param in
     * @return
     */
    private ArrayList<String> makeChunks(String in) {
        int lastIndex = in.length();

        ArrayList<String> out = new ArrayList<>();

        for (String x : cardinality) {
            int chunkSize = chunkSizes.get(x);

            lastIndex = lastIndex - chunkSize;

            if (lastIndex < 0) {
                chunkSize = lastIndex + chunkSize;
                lastIndex = 0;
            }

            out.add(in.substring(lastIndex, (lastIndex + chunkSize)));

            if (lastIndex == 0) {
                break;
            }
        }

        return out;
    }

    /**
     * currently tested only for english language and values within range.
     *
     * @return the converted string.
     */
    public String render() {

        String out = "";
        // heuristics
        if (translation.containsKey(input)) {
            out = translation.get(input);
        } else {
            ArrayList<String> chunks = makeChunks(input);

            for (int i = 0; i < chunks.size(); i++) {

                String postfixKey = cardinality.get(i);
                String postfixValue = translation.get(postfixKey);
                String numValue = translation.get(chunks.get(i));
                out = numValue
                        + postfixValue
                        + " "
                        + out;
            }
        }
        if (isNegative) {
            return "minus " + out;
        } else {
            return "" + out;
        }

    }

    /**
     * could be localised with some effort
     */
    private void initData() {
        chunkSizes = new HashMap<>();
        // postfixkey=>max number of integers it occupies
        chunkSizes.put(KEY_TENS, 2); // 1-99
        chunkSizes.put(KEY_HUNDREDS, 1); // 1-9 hundred
        chunkSizes.put(KEY_THOUSANDS, 2); // 1-99 thousand

        // the order in which the chunks occur
        cardinality = new ArrayList<>();
        cardinality.add(0, KEY_TENS);
        cardinality.add(1, KEY_HUNDREDS);
        cardinality.add(2, KEY_THOUSANDS);
        cardinality.add(3, KEY_HUNDREDS); // hundred of thousands

        translation = new HashMap<>();
        //0-9
        translation.put("0", "zero");
        translation.put("1", "one");
        translation.put("2", "two");
        translation.put("3", "three");
        translation.put("4", "four");
        translation.put("5", "five");
        translation.put("6", "six");
        translation.put("7", "seven");
        translation.put("8", "eight");
        translation.put("9", "nine");

        // 11-19
        translation.put("11", "eleven");
        translation.put("12", "twelve");
        translation.put("13", "thirteen");
        translation.put("14", "forteen");
        translation.put("15", "fifteen");
        translation.put("16", "sixteen");
        translation.put("17", "seventeen");
        translation.put("18", "eighteen");
        translation.put("19", "nineteen");

        //tens
        translation.put("10", "ten");
        translation.put("20", "twenty");
        translation.put("30", "thirty");
        translation.put("40", "forty");
        translation.put("50", "fifty");
        translation.put("60", "sixty");
        translation.put("70", "seventy");
        translation.put("80", "eighty");
        translation.put("90", "ninety");

        // some precompilation for more linear lookup
        // 21-99
        for (int i = 21; i < 100; i++) {
            String strkey = String.valueOf(i);
            if (translation.containsKey(strkey)) {
                continue; // skip existing
            }
            String tens = String.valueOf(Integer.valueOf((i / 10))) + "0";
            String singles = String.valueOf((i % 10));
            String val = translation.get(tens)
                    + " "
                    + translation.get(singles); // zeros are already defined and therefored skipped
            translation.put(strkey, val);
        }
        // now we have a direct key=>value lookups for 2 digit numbers.

        //postfixes hundreds, thousands etc.
        translation.put(KEY_SINGLES, "");
        translation.put(KEY_TENS, ""); // can use empty postfix as indication that 
        translation.put(KEY_HUNDREDS, " hundred");
        translation.put(KEY_THOUSANDS, " thousand");
    }
}
