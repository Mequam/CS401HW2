package DAK.Encryption;

/***
 * Simple encryption interface that the program can use to change around its encryption methods
 * 
 */
public abstract class Enc {
    abstract public String encode(String data);
    abstract public String decode(String data);
  
    public static String inversion_test_string = "abcdefghijklmnopqrstuvwxyz0123456789".toUpperCase();
    /** convinence function that ensures that the class is working*/
    public Boolean testInverse() {
        return testInverse(inversion_test_string);
    }
    /** convinence function that ensures that the class is working */
    public Boolean testInverse(String data) {
        return decode(encode(data)).equals(data);
    }
}
