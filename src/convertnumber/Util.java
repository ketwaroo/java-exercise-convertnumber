package convertnumber;

/**
 * Odds and ends.
 *
 * @author Yaasir Ketwaroo <yaasir@ketwaroo.com>
 */
public class Util {

    /**
     *
     * stolen from http://stackoverflow.com/a/14414014
     *
     * @param array the array we need
     * @param index
     * @return
     */
    public static boolean indexExists(String[] array, int index) {
        if (array != null && index >= 0 && index < array.length) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * a way to quickly exit with a message.
     *
     * @param str
     * @param code "optional" specifies the exit code. defaults to 0
     */
    public static void die(String str, int code) {
        out(str);
        System.exit(code);
    }

    /**
     * a way to quickly exit with a message.
     *
     * @param str
     */
    public static void die(String str) {
        die(str, 0);
    }

    /**
     * outputs something. really miss duck typing
     *
     * @param str
     */
    public static void out(Object str) {
        System.out.println(str);
    }

    public static void out(String str) {
        System.out.println(str);
    }

    public static void out(boolean str) {
        System.out.println(str);
    }

    public static void out(char str) {
        System.out.println(str);
    }

    public static void out(char[] str) {
        System.out.println(str);
    }

    public static void out(double str) {
        System.out.println(str);
    }

    public static void out(float str) {
        System.out.println(str);
    }

    public static void out(int str) {
        System.out.println(str);
    }

    public static void out(long str) {
        System.out.println(str);
    }
}
