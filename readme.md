Java Exercise ConvertNumber
===========================

# Requirements

Language: Java
Deliverable: All java source files + an executable Jar file that will run the program
Input: Integer values from -999, 999 to 999, 999
Output: String representation of the value

Examples:
```
java -jar ConvertNumber 100    --> one hundred
java -jar ConvertNumber 0      --> zero
java -jar ConvertNumber 1001   --> one thousand one
java -jar ConvertNumber 999221 --> nine hundred ninety nine thousand two hundred twenty one
```

# Assumptions
 * output is in English.
  * Although it would be nice to be able to localise the output.
 * Range is limited.
 * Numbers are in base 10.
 * No decimal points. Whole integers only
 * `-1` will output `minus one`
 * No conjuctions, punctuations or extra grammatical constructs.
  * i.e. 1001 = `one thousand one` and not `one thousand and one`
 * 1600 = `one thousand six hundred` and not `sixteen hundred`
 * Coding in java is not my strong suit. There may be some syntax weirdness.

# The Algorithm

It's mostly generating a translation table containing the string representation of number fragments.
The string values for 0-9, special fragments like 11-19, multiple of ten, the postfixes for hundreds and thousands, etc. See `IntToStr.initData()`

There is a `chunkSize` list which describes how or split the number into chunks and what label and size those chunks should be.

There is also a `cardinality` list which names the postfix associated with the position integers in the number.

```
position:   5  <----  0
number:     9 9 9 9 9 9
```

Once the data has been organised for easy look up, it's a simple matter of splitting the input number into chunks as defined by the `chunkSize` list and 
lookup the chunk translation and gradually building the string. see `IntToStr.makeChunks()` and `IntToStr.render()`



The fact tht the app is data driven means that with a little refactoring, All that is needed to support another language and/or much larger number range is swapping out translation tables.


# Building Jar file

## using Apache ant (recommended)

This option may be used by default if you you are compiling via an IDE.

run the command within the folder containing the `build.xml` file.

`ant compile`


## Using plain old java

```
md dist
jar cf dist/ConvertNumber.jar src/*

```

# Running the command

```
cd dist
java -jar ConvertNumber <Number>
```
