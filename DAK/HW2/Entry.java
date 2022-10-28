package DAK.HW2;

import java.util.Scanner;

public class Entry {
    String first;
    String last;
    SSN ssn;
    /**
     * convinence function to check if we match the given first and last
     * names
     */
    public boolean checkFirstLast(String first,String last) {
        return first.equals(this.first) && last.equals(this.last);
    }
    
    /**
     * convinence function that sets properties of the class
     * inteanded to be used as an "intilizer overloader" because
     * java is kinda silly
     *  
     * @param first 
     * @param last
     * @param ssn
     */
    private void load(String first,String last,SSN ssn) {
        this.first = first;
        this.last = last;
        this.ssn = ssn;
    }
    Entry(Scanner sc) {
        if (sc.hasNext()) {
            String nextLine = sc.nextLine();
            String [] split_line = nextLine.split("  "); //vs code uses double spaces for tab
            load(split_line[0],split_line[1],new SSN(split_line[2]));
        }
    }
    Entry(String first,String last,SSN ssn) {
        load(first,last,ssn);
    }
}
