package DAK.Encryption;

import javax.swing.text.html.StyleSheet;

public class Ceaser extends DAK.Encryption.Enc {
    /**
     * performs modulus as if on a circle labeled with natural numbers
     * this means that when encountering negative numbers, they get re mapped
     * to posotive natural numbers, as apposed to bieng the negative remainder that
     * java uses by default 
     *
     */
    public static int cmod(int x,int m) {
        int ret_val = x % m;
        if (ret_val < 0) return m + ret_val;
        return ret_val;
    }
    /*** */
    public int shift_key;
    public Ceaser(int shift_amount) {
        shift_key = shift_amount;
    }
    //contains some test code to make sure that everything works
   public static void main(String [] args){

    Ceaser c = new Ceaser(5);
    if (c.testInverse()) {
        System.out.println("[*] inverse works propperly!");
    }
    else {
        System.out.println("[ERROR] inversion failed!");
    }
    System.out.println("[TEST 1 DUMP]");
    System.out.println("\t data:" + Ceaser.inversion_test_string);
    System.out.println("\t ecoded:" + c.encode(Ceaser.inversion_test_string));
    System.out.println("\t decoded:" + c.decode(c.encode(Ceaser.inversion_test_string)));

    c.decode("E");
}

//rotates the nunmber x within the given start and stop number ranges
//see  https://en.wikipedia.org/wiki/Modulo_operation for properties
//of modulus and how the math works
//basically its just math to rotate numbers around a disc starting and stoping on the givne
//values
public static int rotateRange(int x,int shift,int start,int stop) {
    return cmod(x+shift-start,stop-start+1)+start;
}
//this function takes a shift amount and uses it to encode the givenc haracter
public static char EncodeChar(char c,int shift) {
    return (char)rotateRange((int)c, shift, 45, 96);
}
@Override
public String encode(String data) {
    data = data.toUpperCase();
    String ret_val = "";
    for (int i = 0; i < data.length(); i++) {
       
        //shifts the characters around using ascii code
        ret_val += Ceaser.EncodeChar(data.charAt(i), shift_key);
    }
    return ret_val;
}

@Override
public String decode(String data) {
    data = data.toUpperCase();
    String ret_val = "";
    for (int i = 0; i < data.length(); i++) {
       ret_val += Ceaser.EncodeChar(data.charAt(i), -shift_key); 
    }
    return ret_val;
} 

}