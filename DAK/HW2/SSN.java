package DAK.HW2;
import java.util.regex.*;

public class SSN {
    
    public Boolean isSSN(String s) {
        return Pattern.matches("\\d{3}-\\d{3}-\\d{4}", s);
    }
    
    int [] ssnData = new int[3];
   
    @Override
    public String toString() {
        return Integer.toString(ssnData[0]) +"-" + 
            Integer.toString(ssnData[1]) + "-" + 
            Integer.toString(ssnData[2]);
    }

    SSN(String ssn) throws IllegalArgumentException {
        if (!isSSN(ssn)) throw new IllegalArgumentException("Invalid SSN!");

        String [] split_ssn = ssn.split("-");
        for (int i = 0; i < split_ssn.length; i++) {
            ssnData[i] = Integer.parseInt(split_ssn[i]);
        }

    }
}
